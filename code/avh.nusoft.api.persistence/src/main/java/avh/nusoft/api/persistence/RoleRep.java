package avh.nusoft.api.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Role;

@Repository
public interface RoleRep extends CrudRepository<Role, String> {

}
