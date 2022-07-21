package avh.nusoft.api.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.RequestSkill;

@Repository
public interface RequestSkillRep extends CrudRepository<RequestSkill, String> {

}
