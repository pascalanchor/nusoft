package avh.nusoft.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import avh.nusoft.api.model.Domain;
import avh.nusoft.api.model.DomainSkill;
import avh.nusoft.api.persistence.NusoftRep;

@Component
public class DomainSvcImpl {
	@Autowired private NusoftRep rep;
	
	public DomainSvcImpl() {}
	
	public List<String> getRegisteredCurrencies() {
		List<String> res = new ArrayList<>();
		rep.getCurrencyRep().findAll().forEach(x -> res.add(x.getName()));
		return res;
	}
	
	public List<String> getRegisteredCountries() {
		List<String> res = new ArrayList<>();
		rep.getCountryRep().findAll().forEach(x -> res.add(x.getName()));
		return res;
	}
	
	public List<String> getRegisteredDomains() {
		List<String> res = new ArrayList<>();
		rep.getDomainRep().findAll().forEach(x -> res.add(x.getName()));
		return res;
	}
	
	public List<String> getDomainSkills(String did) {
		Domain d = rep.getDomainRep().findById(did)
					.orElseThrow(() -> new IllegalArgumentException(String.format("the domain %s doesn't exist", did)));
		
		List<DomainSkill> dms = rep.getDomSkillRep().findByDomain(d);
		List<String> res = new ArrayList<>();
		dms.forEach(x -> res.add(x.getEid()));
		return res;
	}
}
