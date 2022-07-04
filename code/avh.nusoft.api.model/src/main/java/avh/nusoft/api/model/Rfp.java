package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rfp database table.
 * 
 */
@Entity
@NamedQuery(name="Rfp.findAll", query="SELECT r FROM Rfp r")
public class Rfp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;

	@Column(name="cdate")
	private long date;

	@Column(name="closing_date")
	private long closingDate;

	private String description;

	@Column(name="doc_location")
	private String docLocation;

	@Column(name="start_date")
	private long startDate;

	//uni-directional many-to-one association to Subscription
	@ManyToOne
	@JoinColumn(name="subscription_id")
	private Subscription subscription;

	public Rfp() {
	}

	public String getEid() {
		return this.eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public long getDate() {
		return this.date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public long getClosingDate() {
		return this.closingDate;
	}

	public void setClosingDate(long closingDate) {
		this.closingDate = closingDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocLocation() {
		return this.docLocation;
	}

	public void setDocLocation(String docLocation) {
		this.docLocation = docLocation;
	}

	public long getStartDate() {
		return this.startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public Subscription getSubscription() {
		return this.subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

}