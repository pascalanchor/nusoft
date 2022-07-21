package avh.nusoft.api.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Domain;
import avh.nusoft.api.model.DomainSkill;

@Repository
public interface DomainSkillRep extends CrudRepository<DomainSkill, String> {
	public List<DomainSkill> findByDomain(Domain domain);
}
