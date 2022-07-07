package avh.nusoft.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import avh.nusoft.api.model.Article;
import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Degree;
import avh.nusoft.api.model.reps.NusoftRep;
import avh.nusoft.api.services.impl.util.RepHelper;

@Component
public class ArticleSvcImp {
	@Autowired
	private NusoftRep rep;
	@Autowired
	private RepHelper helper;
	
	public ArticleSvcImp() {
		
	}
	@Transactional
	public Article addNewArticle(Article a , String email) {
		Contact usr = helper.getUser(email)
				.orElseThrow(() -> new IllegalArgumentException(String.format("User '%s' not found", email)));

		a.setEid(UUID.randomUUID().toString());
		a.setContact(usr);
		rep.getArticleRep().save(a);

		return a;
	
}
public List<Article> getArticle (String c) {
		
		List<Contact> ct = rep.getContactRep().findByEmail(c);
	
		List<Article> d = rep.getArticleRep().findByContact(ct.get(0));
		return d;
		
	}
	
	
	
	}
