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
public class APIArticle {
	
	private String tile;
	private String description;
	private Date date;
	private String Url;
    private String email;
}
