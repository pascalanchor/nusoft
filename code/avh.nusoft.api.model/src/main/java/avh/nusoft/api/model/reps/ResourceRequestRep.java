package avh.nusoft.api.model.reps;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.ResourceRequest;

@Repository
public interface ResourceRequestRep extends CrudRepository<ResourceRequest, String> {

}
