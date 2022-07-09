package avh.nusoft.api.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Domain;
import avh.nusoft.api.model.Subscription;
import avh.nusoft.api.model.domains.SubscriptionStatus;
import avh.nusoft.api.persistence.NusoftRep;
import avh.nusoft.api.services.impl.util.RepHelper;

@Component
public class SubscriptionSvcImpl {
	@Autowired private NusoftRep rep;
	@Autowired private RepHelper helper;
	
	public SubscriptionSvcImpl() {}
	
	@Transactional
	public Subscription registerSubscription(Subscription s, String email, String domain) {
		Domain d = rep.getDomainRep().findById(domain)
				.orElseThrow(() -> new IllegalArgumentException(String.format("undefined domain: %s", domain)));
		
		Contact ct = helper.getUser(email)
				.orElseThrow(() -> new IllegalArgumentException(String.format("the user %s is not registered.", email)));
		
		List<Subscription> all = rep.getSubscriptionRep().findByContactAndDomain(ct, d);
		if ((all != null) && (all.size() > 0))
			throw new IllegalArgumentException(String.format("the user %s is already registered to the domain %s", email, domain));
		
		s.setContact(ct);
		s.setDomain(d);
		s.setEid(UUID.randomUUID().toString());
		s.setStatus(SubscriptionStatus.Active.toString());
		
		rep.getSubscriptionRep().save(s);
		
		return s;
	}
	
	public List<Subscription> getAllSubscriptions(String email) {
		Contact ct = helper.getUser(email)
				.orElseThrow(() -> new IllegalArgumentException(String.format("the user %s is not registered", email)));
		
		return rep.getSubscriptionRep().findByContact(ct);
		
	}
	
	public Subscription getSubscription(String id) {
		return rep.getSubscriptionRep().findById(id)
				.orElseThrow(() -> new IllegalArgumentException(String.format("the subscription %s couldn't be found", id)));
	}
	
	@Transactional
	public boolean unregisterSubscription(String sid) {
		Subscription s = rep.getSubscriptionRep().findById(sid)
				.orElseThrow(() -> new IllegalArgumentException(String.format("no subscription with the id %s", sid)));

		s.setStatus(SubscriptionStatus.Cancelled.toString());
		
		// cancel:
		// 	- all applications
		//	- all projects
		
		return true;
	}
}
