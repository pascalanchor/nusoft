package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bid database table.
 * 
 */
@Entity
@NamedQuery(name="Bid.findAll", query="SELECT b FROM Bid b")
public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;

	@Column(name="doc_location")
	private String docLocation;

	private String msg;

	@Column(name="submission_date")
	private long submissionDate;

	//uni-directional many-to-one association to Rfp
	@ManyToOne
	@JoinColumn(name="rfp_id")
	private Rfp rfp;

	//uni-directional many-to-one association to Subscription
	@ManyToOne
	@JoinColumn(name="subscription_id")
	private Subscription subscription;

	public Bid() {
	}

	public String getEid() {
		return this.eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getDocLocation() {
		return this.docLocation;
	}

	public void setDocLocation(String docLocation) {
		this.docLocation = docLocation;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getSubmissionDate() {
		return this.submissionDate;
	}

	public void setSubmissionDate(long submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Rfp getRfp() {
		return this.rfp;
	}

	public void setRfp(Rfp rfp) {
		this.rfp = rfp;
	}

	public Subscription getSubscription() {
		return this.subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

}