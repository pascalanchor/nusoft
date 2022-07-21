package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;

	@Column(name="mbody")
	private String body;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="mdate")
	private Date date;

	@Column(name="mstatus")
	private String status;

	private String subject;

	//uni-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name="sender")
	private Contact sender;

	//uni-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name="receiver")
	private Contact receiver;

	public Message() {
	}

	public String getEid() {
		return this.eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Contact getSender() {
		return this.sender;
	}

	public void setSender(Contact sender) {
		this.sender = sender;
	}

	public Contact getReceiver() {
		return this.receiver;
	}

	public void setReceiver(Contact receiver) {
		this.receiver = receiver;
	}

}