package avh.nusoft.api.model.reps;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Degree;

@Repository
public interface DegreeRep extends CrudRepository<Degree, String> {
    public Degree findByEid(String eid);
    public List<Degree> findByContact(Contact contact);
}
