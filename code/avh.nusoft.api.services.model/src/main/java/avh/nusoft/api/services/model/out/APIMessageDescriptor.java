package avh.nusoft.api.services.model.out;

import avh.nusoft.api.services.model.APIMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APIMessageDescriptor extends APIMessage {
	private String eid;
	private String status;
}
