package avh.nusoft.api.model.reps;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Rfp;

@Repository
public interface RfpRep extends CrudRepository<Rfp, String> {

}
