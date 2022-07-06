package avh.nusoft.api.services.model;

import java.util.Date;

import avh.nusoft.api.model.domains.Honour;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIDegree {
	private Date date;
	private String level;
	private Honour honour;
	private String institution;
	private String email;
}
