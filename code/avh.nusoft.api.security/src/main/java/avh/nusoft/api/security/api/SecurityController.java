package avh.nusoft.api.security.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Role;
import avh.nusoft.api.model.UserRole;
import avh.nusoft.api.model.reps.NusoftRep;
import avh.nusoft.api.security.api.model.in.APIUserIn;
import avh.nusoft.api.security.api.model.out.APIUserOut;
import avh.nusoft.api.security.common.API2ModelTransformer;
import avh.nusoft.api.security.common.SecurityConstants;
import avh.nusoft.api.security.jwt.JWTProvider;

@RestController
public class SecurityController {
	@Autowired AuthenticationManager authenticationManager;
    @Autowired JWTProvider tokenProvider;
    @Autowired NusoftRep rep;
	@Autowired PasswordEncoder pHasher;
	
    @PostMapping(SecurityConstants.LoginServletPath)
    public ResponseEntity<APIUserOut> login(@RequestParam String email, @RequestParam String password) {
    	try {
    		UsernamePasswordAuthenticationToken atk = new UsernamePasswordAuthenticationToken(email, password);
    		System.out.println("-------------------------------------");
    		Authentication authentication = authenticationManager.authenticate(atk);
    		
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);
            System.out.println("==================================");
            List<Contact> all = rep.getContactRep().findByEmail(email);
            System.out.println(all.get(0).getEmail());
            if ((all == null) || (all.size() <= 0))
            	throw new UsernameNotFoundException(email);
            
            Contact c = all.get(0);
            APIUserOut lr = API2ModelTransformer.TransformContact(c);
            List<UserRole> mbs = rep.getUserRoleRep().findByContact(c);
            
            mbs.stream().forEach(m -> lr.addRole(m.getRole().getName()));
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).body(lr);
    	} catch (InternalAuthenticationServiceException e) {
    		throw new ResponseStatusException(HttpStatus.FORBIDDEN, String.format("the user %s is not defined", email));
    	} catch (BadCredentialsException bce) {
    		throw new ResponseStatusException(HttpStatus.FORBIDDEN, String.format("wrong password for the user %s", email));
    	}   
    }

    @Transactional
    @PostMapping(SecurityConstants.RegisterServletPath)
    public boolean registerUser(@RequestBody APIUserIn usr) {
    	try {
    		// any registered user with the same name ?
    		List<Contact> ctts = rep.getContactRep().findByEmail(usr.getEmail());
    		if ((ctts != null) && (ctts.size() > 0))
    			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, String.format("the user %s is already registered", usr.getEmail()));
    		// now create the user with the ROLE_User role
    		Role role = rep.getRoleRep().findById(SecurityConstants.RoleUser).get();
    		Contact res = API2ModelTransformer.TransformUser(usr);
    		res.setPwd(pHasher.encode(usr.getPwd()));
    		
    		
    		UserRole mb = new UserRole();
    		mb.setEid(UUID.randomUUID().toString());
    		mb.setContact(res);
    		mb.setRole(role);
    		
    		rep.getAddressRep().save(res.getAddress());
    		rep.getContactRep().save(res);
    		rep.getUserRoleRep().save(mb);
    		return true;
    	} catch (Exception e) {
    		throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
    	}
    }
}
