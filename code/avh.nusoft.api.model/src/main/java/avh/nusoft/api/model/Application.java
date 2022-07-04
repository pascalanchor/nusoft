package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the application database table.
 * 
 */
@Entity
@NamedQuery(name="Application.findAll", query="SELECT a FROM Application a")
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;

	@Temporal(TemporalType.DATE)
	@Column(name="adate")
	private Date date;

	private String currency;

	@Column(name="proposed_rate")
	private double proposedRate;

	private String status;

	//uni-directional many-to-one association to ResourceRequest
	@ManyToOne
	@JoinColumn(name="request")
	private ResourceRequest resourceRequest;

	//uni-directional many-to-one association to Subscription
	@ManyToOne
	@JoinColumn(name="subscription_id")
	private Subscription subscription;

	public Application() {
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getProposedRate() {
		return this.proposedRate;
	}

	public void setProposedRate(double proposedRate) {
		this.proposedRate = proposedRate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ResourceRequest getResourceRequest() {
		return this.resourceRequest;
	}

	public void setResourceRequest(ResourceRequest resourceRequest) {
		this.resourceRequest = resourceRequest;
	}

	public Subscription getSubscription() {
		return this.subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

}