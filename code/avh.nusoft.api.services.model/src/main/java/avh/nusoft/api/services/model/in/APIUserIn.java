package avh.nusoft.api.services.model.in;

import avh.nusoft.api.services.model.APIUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APIUserIn extends APIUser {
	private String pwd;
	private APIAddressIn address;
}
