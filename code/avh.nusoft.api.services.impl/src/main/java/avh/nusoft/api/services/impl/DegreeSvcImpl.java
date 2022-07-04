package avh.nusoft.api.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Degree;
import avh.nusoft.api.model.reps.NusoftRep;
import avh.nusoft.api.services.impl.util.RepHelper;

@Component
public class DegreeSvcImpl {
	@Autowired private NusoftRep rep;
	@Autowired private RepHelper helper;
	
	public DegreeSvcImpl() {}
	
	@Transactional
	public Degree addNewDegree(Degree d, String email) {
		Contact usr = helper.getUser(email).orElseThrow(() -> new IllegalArgumentException(String.format("User '%s' not found", email)));
		
		d.setEid(UUID.randomUUID().toString());
		d.setContact(usr);
		rep.getDegreeRep().save(d);
		
		return d;
	}
}
