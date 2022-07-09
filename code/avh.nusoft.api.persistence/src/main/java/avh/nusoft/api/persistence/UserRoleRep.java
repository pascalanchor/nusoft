package avh.nusoft.api.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.UserRole;

@Repository
public interface UserRoleRep extends CrudRepository<UserRole, String> {
	public List<UserRole> findByContact(Contact contact);
}
