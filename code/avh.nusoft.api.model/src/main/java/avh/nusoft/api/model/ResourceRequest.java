package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the resource_request database table.
 * 
 */
@Entity
@Table(name="resource_request")
@NamedQuery(name="ResourceRequest.findAll", query="SELECT r FROM ResourceRequest r")
public class ResourceRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;

	@Temporal(TemporalType.DATE)
	@Column(name="cdate")
	private Date date;

	private String currency;

	private int duration;

	@Column(name="hourly_rate")
	private double hourlyRate;

	@Column(name="nb_resource")
	private int nbResource;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	private String status;

	//uni-directional many-to-one association to Subscription
	@ManyToOne
	@JoinColumn(name="subscription_id")
	private Subscription subscription;

	public ResourceRequest() {
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

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getHourlyRate() {
		return this.hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public int getNbResource() {
		return this.nbResource;
	}

	public void setNbResource(int nbResource) {
		this.nbResource = nbResource;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Subscription getSubscription() {
		return this.subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

}