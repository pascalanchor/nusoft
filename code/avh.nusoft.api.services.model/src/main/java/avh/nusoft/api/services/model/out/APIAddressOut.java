package avh.nusoft.api.services.model.out;

import avh.nusoft.api.services.model.APIAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class APIAddressOut extends APIAddress {
	private String eid;
}
