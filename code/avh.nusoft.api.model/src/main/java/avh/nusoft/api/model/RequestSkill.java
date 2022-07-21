package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the request_skills database table.
 * 
 */
@Entity
@Table(name="request_skills")
@NamedQuery(name="RequestSkill.findAll", query="SELECT r FROM RequestSkill r")
public class RequestSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;

	//uni-directional many-to-one association to DomainSkill
	@ManyToOne
	@JoinColumn(name="skill_id")
	private DomainSkill domainSkill;

	//bi-directional many-to-one association to ResourceRequest
	@ManyToOne
	@JoinColumn(name="request_id")
	private ResourceRequest resourceRequest;

	public RequestSkill() {
	}

	public String getEid() {
		return this.eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public DomainSkill getDomainSkill() {
		return this.domainSkill;
	}

	public void setDomainSkill(DomainSkill domainSkill) {
		this.domainSkill = domainSkill;
	}

	public ResourceRequest getResourceRequest() {
		return this.resourceRequest;
	}

	public void setResourceRequest(ResourceRequest resourceRequest) {
		this.resourceRequest = resourceRequest;
	}

}