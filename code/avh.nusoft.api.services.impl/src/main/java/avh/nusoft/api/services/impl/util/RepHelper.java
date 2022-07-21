package avh.nusoft.api.services.impl.util;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.persistence.NusoftRep;

@Component
public class RepHelper {
	@Autowired private NusoftRep rep;
	
	public Optional<Contact> getUser(String email) {
		List<Contact> ctts = rep.getContactRep().findByEmail(email);
		if ((ctts != null) && (ctts.size() > 0))
			return Optional.of(ctts.get(0));
		return Optional.empty();
	}
}
