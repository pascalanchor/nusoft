package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the skill database table.
 * 
 */
@Entity
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;

	private Integer experience;

	@Column(name="slevel")
	private int level;

	//uni-directional many-to-one association to DomainSkill
	@ManyToOne
	@JoinColumn(name="skill_id")
	private DomainSkill domainSkill;

	public Skill() {
	}

	public String getEid() {
		return this.eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public Integer getExperience() {
		return this.experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public DomainSkill getDomainSkill() {
		return this.domainSkill;
	}

	public void setDomainSkill(DomainSkill domainSkill) {
		this.domainSkill = domainSkill;
	}

}