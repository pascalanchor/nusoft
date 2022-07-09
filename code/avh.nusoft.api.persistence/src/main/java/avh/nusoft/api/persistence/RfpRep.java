package avh.nusoft.api.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Rfp;

@Repository
public interface RfpRep extends CrudRepository<Rfp, String> {

}
