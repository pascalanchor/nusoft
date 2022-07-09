package avh.nusoft.api.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Domain;

@Repository
public interface DomainRep extends CrudRepository<Domain, String> {

}
