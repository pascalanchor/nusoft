package avh.nusoft.api.services.model.out;

import avh.nusoft.api.services.model.APIDegree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class APIDegreeOut extends APIDegree {
	private String eid;
}
