package avh.nusoft.api.model.reps;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Address;

@Repository
public interface AddressRep extends CrudRepository<Address, String> {

}
