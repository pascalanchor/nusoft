package avh.nusoft.api.model.reps;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Certification;

@Repository
public interface CertificationRep extends CrudRepository<Certification, String> {

}
