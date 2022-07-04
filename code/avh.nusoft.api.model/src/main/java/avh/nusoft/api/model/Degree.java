package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sdegree database table.
 * 
 */
@Entity
@Table(name="sdegree")
@NamedQuery(name="Degree.findAll", query="SELECT d FROM Degree d")
public class Degree implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;

	@Temporal(TemporalType.DATE)
	@Column(name="ddate")
	private Date date;

	@Column(name="dlevel")
	private String level;

	private String honour;

	private String institution;

	//uni-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name="contact_id")
	private Contact contact;

	public Degree() {
	}

	public String getEid() {
		return this.eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getHonour() {
		return this.honour;
	}

	public void setHonour(String honour) {
		this.honour = honour;
	}

	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}