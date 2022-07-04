package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the domain_skill database table.
 * 
 */
@Entity
@Table(name="domain_skill")
@NamedQuery(name="DomainSkill.findAll", query="SELECT d FROM DomainSkill d")
public class DomainSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;

	@Column(name="skill_name")
	private String skillName;

	//uni-directional many-to-one association to Domain
	@ManyToOne
	@JoinColumn(name="domain_id")
	private Domain domain;

	public DomainSkill() {
	}

	public String getEid() {
		return this.eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getSkillName() {
		return this.skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public Domain getDomain() {
		return this.domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

}