package avh.nusoft.api.services.model.out;

import avh.nusoft.api.services.model.APIResourceRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APIResourceRequestOut extends APIResourceRequest {
	private String id;
	private String status;
}
