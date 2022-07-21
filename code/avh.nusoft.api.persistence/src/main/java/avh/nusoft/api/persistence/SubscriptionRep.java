package avh.nusoft.api.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Domain;
import avh.nusoft.api.model.Subscription;

@Repository
public interface SubscriptionRep extends CrudRepository<Subscription, String> {
	public List<Subscription> findByContactAndDomain(Contact contact, Domain domain);
	public List<Subscription> findByContact(Contact contact);
}
