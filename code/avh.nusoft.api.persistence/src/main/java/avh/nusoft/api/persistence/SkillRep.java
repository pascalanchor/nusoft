package avh.nusoft.api.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Skill;

@Repository
public interface SkillRep extends CrudRepository<Skill, String> {

}
