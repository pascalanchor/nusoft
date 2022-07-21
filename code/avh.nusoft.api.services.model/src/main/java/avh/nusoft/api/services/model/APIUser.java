package avh.nusoft.api.services.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APIUser {
	private String email;
	private String firstname;
	private String lastname;
	private String gender;
	private Date dob;
	private String phone;
	private double hourlyRate;
	private String currency;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private List<String> roles = new ArrayList<String>();
	
	public boolean addRole(String role) {
		if (roles.contains(role))
			return false;
		roles.add(role);
		return true;
	}
	
	public List<String> getAllRoles() {
		return roles;
	}
	
	public boolean removeRole(String role) {
		return roles.remove(role);
	}
}
