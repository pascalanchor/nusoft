package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scountry database table.
 * 
 */
@Entity
@NamedQuery(name="Scountry.findAll", query="SELECT s FROM Scountry s")
public class Scountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cname;

	public Scountry() {
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}