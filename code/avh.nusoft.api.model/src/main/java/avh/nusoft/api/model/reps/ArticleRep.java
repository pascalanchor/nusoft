package avh.nusoft.api.model.reps;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Article;
import avh.nusoft.api.model.Contact;


@Repository
public interface ArticleRep extends CrudRepository<Article, String> {

	public Article findByEid(String eid);
	List<Article> findByContact(Contact contact);
}
