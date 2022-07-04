package avh.nusoft.api.model.reps;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Application;

@Repository
public interface ApplicationRep extends CrudRepository<Application, String> {

}
