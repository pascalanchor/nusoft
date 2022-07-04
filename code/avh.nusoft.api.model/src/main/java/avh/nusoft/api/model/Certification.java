package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the certification database table.
 * 
 */
@Entity
@NamedQuery(name="Certification.findAll", query="SELECT c FROM Certification c")
public class Certification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="sname")
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	//uni-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name="contact_id")
	private Contact contact;

	public Certification() {
	}

	public String getEid() {
		return this.eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}