package avh.nusoft.api.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import avh.nusoft.api.model.Article;
import avh.nusoft.api.model.Contact;
import avh.nusoft.api.persistence.NusoftRep;
import avh.nusoft.api.services.impl.util.RepHelper;

@Component
public class ArticleSvcImpl {
	@Autowired private NusoftRep rep;
	@Autowired private RepHelper helper;
	
	public ArticleSvcImpl() {}

	@Transactional
	public Article addNewArticle(Article a, String email) {
		Contact usr = helper.getUser(email)
				.orElseThrow(() -> new IllegalArgumentException(String.format("User '%s' not found", email)));

		a.setEid(UUID.randomUUID().toString());
		a.setContact(usr);
		rep.getArticleRep().save(a);

		return a;
	}

	@Transactional
	public boolean deleteArticle(String aid) {
		rep.getArticleRep().deleteById(aid);
		return true;
	}
	
	@Transactional
	public Article updateArticle(String aid, Article newArticle) {
		Article dbArticle = rep.getArticleRep().findById(aid)
				.orElseThrow(() -> new IllegalArgumentException(String.format("the Article %s was not found", aid)));
		
		if (newArticle.getArticleDate() != null)
			dbArticle.setArticleDate(newArticle.getArticleDate());
		
		if (newArticle.getDescription() != null)
			dbArticle.setDescription(newArticle.getDescription());
		
		if (newArticle.getTitle() != null)
			dbArticle.setTitle(newArticle.getTitle());
		
		if (newArticle.getUrlLink() != null)
			dbArticle.setUrlLink(newArticle.getUrlLink());
		
		rep.getArticleRep().save(dbArticle);
		return dbArticle;
	}
	
	public Article getArticle(String id) {
		Article res = rep.getArticleRep().findById(id)
						.orElseThrow(() -> new IllegalArgumentException(String.format("the Article %s was not found", id)));
		return res;
	}
	
	public List<Article> getAllArticle(String email) {
		Contact ct = helper.getUser(email)
						.orElseThrow(() -> new IllegalArgumentException(String.format("the Contact %s was not found", email)));

		List<Article> res = rep.getArticleRep().findByContact(ct);
		return res;
	}
	

	
}
