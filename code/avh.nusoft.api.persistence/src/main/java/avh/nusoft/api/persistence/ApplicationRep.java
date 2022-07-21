package avh.nusoft.api.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Application;
import avh.nusoft.api.model.ResourceRequest;
import avh.nusoft.api.model.Subscription;

@Repository
public interface ApplicationRep extends CrudRepository<Application, String> {
	public List<Application> findBySubscription(Subscription s);
	public List<Application> findByResourceRequest(ResourceRequest r);
}
