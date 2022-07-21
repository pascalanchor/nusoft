package avh.nusoft.api.security.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import avh.nusoft.api.common.NusoftConstants;
import avh.nusoft.api.model.Address;
import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Domain;
import avh.nusoft.api.model.Role;
import avh.nusoft.api.model.Subscription;
import avh.nusoft.api.model.UserRole;
import avh.nusoft.api.model.domains.SubscriptionStatus;
import avh.nusoft.api.persistence.NusoftRep;
import avh.nusoft.api.security.jwt.JWTProvider;
import avh.nusoft.api.services.model.in.APIUserIn;
import avh.nusoft.api.services.model.out.APIUserOut;
import avh.nusoft.api.services.model.transformer.ModelTransformer;

@RestController
public class SecurityController {
	@Autowired AuthenticationManager authenticationManager;
    @Autowired JWTProvider tokenProvider;
    @Autowired NusoftRep rep;
	@Autowired PasswordEncoder pHasher;
	
    @PostMapping(NusoftConstants.LoginServletPath)
    public ResponseEntity<APIUserOut> login(@RequestParam String email, @RequestParam String password) {
    	try {
    		UsernamePasswordAuthenticationToken atk = new UsernamePasswordAuthenticationToken(email, password);
    		Authentication authentication = authenticationManager.authenticate(atk);
    		
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);
            List<Contact> all = rep.getContactRep().findByEmail(email);
            System.out.println(all.get(0).getEmail());
            if ((all == null) || (all.size() <= 0))
            	throw new UsernameNotFoundException(email);
            
            Contact c = all.get(0);
            APIUserOut lr = ModelTransformer.UserModel2API(c);
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
    @PostMapping(NusoftConstants.RegisterServletPath)
    public boolean registerUser(@RequestBody APIUserIn usr) {
    	try {
    		// any registered user with the same name ?
    		List<Contact> ctts = rep.getContactRep().findByEmail(usr.getEmail());
    		if ((ctts != null) && (ctts.size() > 0))
    			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, String.format("the user %s is already registered", usr.getEmail()));
    		// now create the user with the ROLE_User role
    		Role role = rep.getRoleRep().findById(NusoftConstants.RoleUser).get();
    		Contact res = ModelTransformer.UserAPI2Model(usr);
    		res.setPwd(pHasher.encode(usr.getPwd()));
    		
    		
    		UserRole mb = new UserRole();
    		mb.setEid(UUID.randomUUID().toString());
    		mb.setContact(res);
    		mb.setRole(role);
    		
    		rep.getAddressRep().save(res.getAddress());
    		rep.getContactRep().save(res);
    		rep.getUserRoleRep().save(mb);
    		
    		/* temp solution for V1
    		 * When the user register on our site, subscribe him/her to the
    		 * *unique* domain defined in our system -> 'Software Development'
    		 * It is a temp solution because eventually, our system will have multiple 
    		 * domains and the user must select to which domain he/she would like 
    		 * to subscribe: 
    		 * 	- Software Development
    		 * 	- Civil Engeneering
    		 * 	- Art
    		 * 	- etc.
    		 */
    		
    		Domain d = rep.getDomainRep().findById(NusoftConstants.SoftDevDomain).get();
//    		Subscription s = new Subscription();
//    		s.setContact(res);
//    		s.setDomain(d);
//    		s.setEid(UUID.randomUUID().toString());
//    		s.setSubscriptionDate(new Date());
//    		s.setStatus(SubscriptionStatus.Active.toString());
//    		
//    		rep.getSubscriptionRep().save(s);
    		
    		return true;
    	} catch (Exception e) {
    		throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
    	}
    }

    @Transactional
	@PreAuthorize("hasAnyRole('User')")
	@PatchMapping("/avh/nusoft/api/authorized/contact/update")
    public ResponseEntity<APIUserOut> updateUserInfo(@RequestBody APIUserIn uin, @RequestParam String eid) {
    	try {
    		Optional<Contact> usrOp = rep.getContactRep().findById(eid);
    		if (usrOp.isEmpty())
    			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, String.format("the user %s does not exist", eid));
    		
			Contact usr = usrOp.get();
			Contact ct = ModelTransformer.UserAPI2Model(uin);
			
			if (ct.getCurrency() != null)
				usr.setCurrency(ct.getCurrency());
			if (ct.getDob() != null)
				usr.setDob(ct.getDob());
			if (ct.getEmail() != null)
				usr.setEmail(ct.getEmail());
			if (ct.getFirstname() != null)
				usr.setFirstname(ct.getFirstname());
			if (ct.getLastname() != null)
				usr.setLastname(ct.getLastname());
			if (ct.getGender() != null)
				usr.setGender(ct.getGender());
			if (ct.getHourlyRate() >= 0)
				usr.setHourlyRate(ct.getHourlyRate());
			if (ct.getPhone() != null)
				usr.setPhone(ct.getPhone());			
			if (ct.getAddress() != null) {
				Address existingAdr = usr.getAddress();
				Address newAddress = ct.getAddress();
				if (newAddress.getCity() != null)
					existingAdr.setCity(newAddress.getCity());
				if (newAddress.getCountry() != null)
					existingAdr.setCountry(newAddress.getCountry());
				if (newAddress.getState() != null)
					existingAdr.setState(newAddress.getState());
				if (newAddress.getStreet() != null)
					existingAdr.setStreet(newAddress.getStreet());
				if (newAddress.getZipcode() != null)
					existingAdr.setZipcode(newAddress.getZipcode());
				rep.getAddressRep().save(existingAdr);
			}
			rep.getContactRep().save(usr);
			APIUserOut uout = ModelTransformer.UserModel2API(usr);
			return ResponseEntity.ok().body(uout);
    	} catch (Exception e) {
    		throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
    	}
    }

    @Transactional
	@PreAuthorize("hasAnyRole('User')")
	@PatchMapping("/avh/nusoft/api/authorized/contact/pwd")
    public ResponseEntity<APIUserOut> updateUserPassword(@RequestParam String email, @RequestParam String newPassword) {
    	try {
            List<Contact> all = rep.getContactRep().findByEmail(email);
            if ((all == null) || (all.size() <= 0))
            	throw new UsernameNotFoundException(email);
            
            Contact c = all.get(0);
            c.setPwd(pHasher.encode(newPassword));
            APIUserOut lr = ModelTransformer.UserModel2API(c);
            rep.getContactRep().save(c);
            return ResponseEntity.ok().body(lr);
    	} catch (Exception e) {
    		throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
    	}
    }
}
