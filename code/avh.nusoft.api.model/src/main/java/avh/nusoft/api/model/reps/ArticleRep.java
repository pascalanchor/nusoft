package avh.nusoft.api.model.reps;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Article;
import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Degree;
@Repository
public interface ArticleRep extends CrudRepository<Article, String> {

	public Article findByEid(String eid);
	List<Article> findByContact(Contact contact);
}
