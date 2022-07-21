package avh.nusoft.api.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Certification;

@Repository
public interface CertificationRep extends CrudRepository<Certification, String> {

}
