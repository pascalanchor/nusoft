package avh.nusoft.api.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Article;
import avh.nusoft.api.model.Contact;


@Repository
public interface ArticleRep extends CrudRepository<Article, String> {
	public List<Article> findByContact(Contact contact);
}
