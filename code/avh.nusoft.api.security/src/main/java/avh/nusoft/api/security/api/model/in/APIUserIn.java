package avh.nusoft.api.security.api.model.in;

import avh.nusoft.api.security.api.model.APIUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APIUserIn extends APIUser {
	private String pwd;
	private APIAddressIn address;
}
