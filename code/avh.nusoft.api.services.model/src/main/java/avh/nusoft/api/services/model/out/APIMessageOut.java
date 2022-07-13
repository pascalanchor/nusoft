package avh.nusoft.api.services.model.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APIMessageOut extends APIMessageDescriptor {
	private String body;
}
