package avh.nusoft.api.model.reps;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Domain;

@Repository
public interface DomainRep extends CrudRepository<Domain, String> {

}
