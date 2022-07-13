package avh.nusoft.api.services.model.in;

import avh.nusoft.api.services.model.APIMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APIMessageIn extends APIMessage {
	private String body;
}
