package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scurrency database table.
 * 
 */
@Entity
@NamedQuery(name="Scurrency.findAll", query="SELECT s FROM Scurrency s")
public class Scurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cname;

	public Scurrency() {
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}