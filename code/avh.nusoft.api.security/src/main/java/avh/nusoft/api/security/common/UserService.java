package avh.nusoft.api.security.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.UserRole;
import avh.nusoft.api.model.reps.NusoftRep;

@Service
@Transactional
public class UserService implements UserDetailsService {
	@Autowired NusoftRep rep;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Contact> all = rep.getContactRep().findByEmail(username);
		if ((all == null) || (all.size() <= 0))
			throw new UsernameNotFoundException(username);
		
		Contact res = all.get(0);
		List<UserRole> roles = rep.getUserRoleRep().findByContact(res);
		
		List<SimpleGrantedAuthority> aus = new ArrayList<>();
		for (UserRole m : roles) {
			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(m.getRole().getName());
			aus.add(sga);
		}
		
		// - construct the spring User
		return User.builder()
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.password(res.getPwd())
				.username(username)
				.authorities(aus)
				.build();
	}

}
