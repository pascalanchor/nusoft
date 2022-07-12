package avh.nusoft.api.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import avh.nusoft.api.model.DomainSkill;
import avh.nusoft.api.model.RequestSkill;
import avh.nusoft.api.model.ResourceRequest;
import avh.nusoft.api.model.Subscription;
import avh.nusoft.api.model.domains.ResourceRequestStatus;
import avh.nusoft.api.persistence.NusoftRep;

@Component
public class ResourceRequestSvcImpl {
	@Autowired private NusoftRep rep;

	public ResourceRequestSvcImpl() {}
	
	@Transactional
	public ResourceRequest createResourceRequest(ResourceRequest rr, String subid, List<String> skills) {
		rep.getSubscriptionRep().findById(subid)
			.orElseThrow(() -> new IllegalArgumentException(String.format("No subscription ('%s') found", subid)));
		
		this.setRequestSkills(rr, skills);
		
		rr.setEid(UUID.randomUUID().toString());
		rr.setStatus(ResourceRequestStatus.Open.toString());
		
		if (rr.getSkills().size() != skills.size())
			throw new IllegalArgumentException("There are unknown skills");
		
		rep.getResourceRequestRep().save(rr);
		rep.getRequestSkillRep().saveAll(rr.getSkills());
		
		return rr;
	}

	public ResourceRequest getResourceRequest(String id) {
		return rep.getResourceRequestRep().findById(id)
			.orElseThrow(() -> new IllegalArgumentException(String.format("cannot find the request %s", id)));
	}

	public List<ResourceRequest> getAllResourceRequests(String sid) {
		Subscription s = rep.getSubscriptionRep().findById(sid)
							.orElseThrow(() -> new IllegalArgumentException(String.format("no subscription %s", sid)));
		
		List<ResourceRequest> res = rep.getResourceRequestRep().findBySubscription(s);
		return res;
	}
	
	@Transactional
	public boolean deleteResourceRequest(String rrid) {
		ResourceRequest rr = rep.getResourceRequestRep().findById(rrid)
								.orElseThrow(() -> new IllegalArgumentException(String.format("no resource request with id '%s'", rrid)));
		
		rr.setStatus(ResourceRequestStatus.Canceled.toString());
		rep.getResourceRequestRep().save(rr);
		return true;
	}
	
	@Transactional
	public ResourceRequest updateResourceRequest(String rrid, ResourceRequest newRR, List<String> skills) {
		ResourceRequest rr = rep.getResourceRequestRep().findById(rrid)
				.orElseThrow(() -> new IllegalArgumentException(String.format("no resource request with id '%s'", rrid)));
		
		if (newRR.getCurrency() != null)
			rr.setCurrency(newRR.getCurrency());
		if (newRR.getDuration() > 0)
			rr.setDuration(newRR.getDuration());
		if (newRR.getHourlyRate() > 0)
			rr.setHourlyRate(newRR.getHourlyRate());
		if (newRR.getNbResource() > 0)
			rr.setNbResource(newRR.getNbResource());
		if (newRR.getStartDate() != null)
			rr.setStartDate(newRR.getStartDate());
		
		if ((skills != null) && (skills.size() > 0)) {
			rep.getRequestSkillRep().deleteAll(rr.getSkills());
			setRequestSkills(rr, skills);
		}
		
		rep.getResourceRequestRep().save(rr);
		rep.getRequestSkillRep().saveAll(rr.getSkills());
		
		return rr;
	}
	
	private void setRequestSkills(ResourceRequest rr, List<String> skills) {
		Iterable<DomainSkill> dsk = rep.getDomSkillRep().findAllById(skills);
		for (DomainSkill skl : dsk) {
			RequestSkill rs = new RequestSkill();
			rs.setDomainSkill(skl);
			rs.setResourceRequest(rr);
			rs.setEid(UUID.randomUUID().toString());
			rr.addSkill(rs);	
		}
	}
}
