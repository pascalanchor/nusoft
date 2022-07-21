package avh.nusoft.api.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Degree;
import avh.nusoft.api.persistence.NusoftRep;
import avh.nusoft.api.services.impl.util.RepHelper;

@Component
public class DegreeSvcImpl {
	@Autowired private NusoftRep rep;
	@Autowired private RepHelper helper;

	public DegreeSvcImpl() {}

	@Transactional
	public Degree addNewDegree(Degree d, String email) {
		Contact usr = helper.getUser(email)
				.orElseThrow(() -> new IllegalArgumentException(String.format("User '%s' not found", email)));

		d.setEid(UUID.randomUUID().toString());
		d.setContact(usr);
		rep.getDegreeRep().save(d);

		return d;
	}

	@Transactional
	public boolean deleteDegree(String id) {
		rep.getDegreeRep().deleteById(id);
		return true;
	}
	
	@Transactional
	public Degree updateDegree(String id, Degree d) {
		Degree dbDegree = rep.getDegreeRep().findById(id)
				.orElseThrow(() -> new IllegalArgumentException(String.format("the Degree %s was not found", id)));
		
		if (d.getDate() != null)
			dbDegree.setDate(d.getDate());
		if (d.getHonour() != null)
			dbDegree.setHonour(d.getHonour());
		if (d.getInstitution() != null)
			dbDegree.setInstitution(d.getInstitution());
		if (d.getLevel() != null)
			dbDegree.setLevel(d.getLevel());
		
		rep.getDegreeRep().save(dbDegree);
		return dbDegree;
	}

	public Degree getDegree(String id) {
		Degree res = rep.getDegreeRep().findById(id)
						.orElseThrow(() -> new IllegalArgumentException(String.format("the Degree %s was not found", id)));
		return res;
	}
	
	public List<Degree> getAllDegree(String email) {
		Contact ct = helper.getUser(email)
						.orElseThrow(() -> new IllegalArgumentException(String.format("the Contact %s was not found", email)));

		List<Degree> res = rep.getDegreeRep().findByContact(ct);
		return res;
	}
}
