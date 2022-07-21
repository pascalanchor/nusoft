package avh.nusoft.api.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Review;

@Repository
public interface ReviewRep extends CrudRepository<Review, String> {

}
