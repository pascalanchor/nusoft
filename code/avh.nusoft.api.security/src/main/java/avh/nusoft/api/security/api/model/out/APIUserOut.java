package avh.nusoft.api.security.api.model.out;

import avh.nusoft.api.security.api.model.APIUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIUserOut extends APIUser {
	private String eid;
	private APIAddressOut address;
}
