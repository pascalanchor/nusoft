package avh.nusoft.api.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.ResourceRequest;
import avh.nusoft.api.model.Subscription;

@Repository
public interface ResourceRequestRep extends CrudRepository<ResourceRequest, String> {
	public List<ResourceRequest> findBySubscription(Subscription s);
}
