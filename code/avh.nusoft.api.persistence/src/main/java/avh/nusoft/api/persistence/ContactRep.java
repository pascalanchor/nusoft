package avh.nusoft.api.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Contact;

@Repository
public interface ContactRep extends CrudRepository<Contact, String> {
	public List<Contact> findByEmail(String email);
	public Contact findByEid(String id);
}
