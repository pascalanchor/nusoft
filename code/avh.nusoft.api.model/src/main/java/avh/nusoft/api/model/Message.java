package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;


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