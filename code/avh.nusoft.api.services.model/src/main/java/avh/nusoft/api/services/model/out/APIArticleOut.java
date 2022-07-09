package avh.nusoft.api.services.model.out;

import avh.nusoft.api.services.model.APIArticle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class APIArticleOut extends APIArticle{
	private String eid;
}
