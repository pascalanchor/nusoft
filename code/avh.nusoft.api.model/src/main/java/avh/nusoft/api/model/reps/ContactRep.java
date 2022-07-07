package avh.nusoft.api.model.reps;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Contact;

@Repository
public interface ContactRep extends CrudRepository<Contact, String> {
	
	@Query(value = "select * from contact c where c.email=:email",nativeQuery = true)
	public List<Contact> findByEmail(String email);
	
	public Contact findByEid(String id);
	
	
	
}
