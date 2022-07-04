package avh.nusoft.api.model.reps;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Subscription;

@Repository
public interface SubscriptionRep extends CrudRepository<Subscription, String> {

}
