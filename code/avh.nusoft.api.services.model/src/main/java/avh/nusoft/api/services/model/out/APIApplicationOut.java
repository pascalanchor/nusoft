package avh.nusoft.api.services.model.out;

import avh.nusoft.api.services.model.APIApplication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APIApplicationOut extends APIApplication {
	private String eid;
	private String status;
}
