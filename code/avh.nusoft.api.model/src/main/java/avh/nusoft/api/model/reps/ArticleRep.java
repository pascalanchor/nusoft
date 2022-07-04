package avh.nusoft.api.model.reps;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Article;

@Repository
public interface ArticleRep extends CrudRepository<Article, String> {

}
