package avh.nusoft.api.model.reps;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Experience;

@Repository
public interface ExperienceRep extends CrudRepository<Experience, String> {

}
