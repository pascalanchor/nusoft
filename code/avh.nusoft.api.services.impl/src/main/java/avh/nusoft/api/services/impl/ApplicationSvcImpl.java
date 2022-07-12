package avh.nusoft.api.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import avh.nusoft.api.model.Application;
import avh.nusoft.api.model.ResourceRequest;
import avh.nusoft.api.model.Subscription;
import avh.nusoft.api.model.domains.ApplicationStatus;
import avh.nusoft.api.model.domains.ResourceRequestStatus;
import avh.nusoft.api.model.domains.SubscriptionStatus;
import avh.nusoft.api.persistence.NusoftRep;

@Component
public class ApplicationSvcImpl {
	@Autowired NusoftRep rep;
	
	public ApplicationSvcImpl() {}
	
	@Transactional
	public Application applyToRequest(Application app, String subscriptionId, String resourceRequestId) {
		Subscription s = rep.getSubscriptionRep().findById(subscriptionId)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Subscription %s not found", subscriptionId)));
		ResourceRequest rr = rep.getResourceRequestRep().findById(resourceRequestId)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Request %s not found", resourceRequestId)));
		
		// TODO
		// check if s and rr belongs to the same domain
		// avoid having a civil Engineer applying for software dev
		
		if (s.getStatus().equals(SubscriptionStatus.Active.toString())) {
			if (rr.getStatus().equals(ResourceRequestStatus.Open.toString())) {		
				app.setEid(UUID.randomUUID().toString());
				app.setResourceRequest(rr);
				app.setSubscription(s);
				app.setStatus(ApplicationStatus.Pending.toString());
			
				rep.getApplicationRep().save(app);
			
				return app;
			} else { // request is closed
				throw new IllegalArgumentException(String.format("the request %s is not open anymore", resourceRequestId));
			}
		} else { // subscription isn't active
			throw new IllegalArgumentException(String.format("the subscription %s is not active", subscriptionId));
		}
	}
	
	@Transactional
	public boolean cancelApplication(String appId) {
		Application app = rep.getApplicationRep().findById(appId)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Application %s not found", appId)));
		
		app.setStatus(ApplicationStatus.Canceled.toString());
		rep.getApplicationRep().save(app);
		return true;
	}
	
	public Application updateApplication(Application newApp, String appId) {
		Application app = rep.getApplicationRep().findById(appId)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Application %s not found", appId)));
		
		if (newApp.getCurrency() != null)
			app.setCurrency(newApp.getCurrency());
		if (newApp.getProposedRate() > 0)
			app.setProposedRate(newApp.getProposedRate());

		rep.getApplicationRep().save(app);
		return app;
	}
	
	public Application getApplication(String appId) {
		return rep.getApplicationRep().findById(appId)
				.orElseThrow(() -> new IllegalArgumentException(String.format("No application with the id %s", appId)));
	}
	
	public List<Application> getAllApplications(String subscriptionId) {
		Subscription s = rep.getSubscriptionRep().findById(subscriptionId)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Subscription %s not found", subscriptionId)));
		return rep.getApplicationRep().findBySubscription(s);
	}
}
