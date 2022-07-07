package avh.nusoft.api.services.transformer;

import avh.nusoft.api.model.Article;
import avh.nusoft.api.services.model.in.APIArticleIn;
import avh.nusoft.api.services.model.out.APIArticleOut;

public class ArticleTransformer {
	public static Article ArticleAPI2Model(APIArticleIn ai) {
		Article a=new Article();
		a.setArticleDate(ai.getDate());
		a.setDescription(ai.getDescription());
		a.setTitle(ai.getTile());
		a.setUrlLink(ai.getUrl());
		return a;
	}
	
	public static APIArticleOut ArticleModel2API(Article a) {
		
		APIArticleOut res=new APIArticleOut();
		res.setDate(a.getArticleDate());
		res.setDescription(a.getDescription());
		res.setTile(a.getTitle());
		res.setUrl(a.getUrlLink());
		res.setEid(a.getEid());
		res.setEmail(a.getContact().getEmail());
		return res;
		
		
	}
}
