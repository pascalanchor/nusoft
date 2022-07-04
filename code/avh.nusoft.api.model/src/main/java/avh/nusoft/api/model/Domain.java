package avh.nusoft.api.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sdomain database table.
 * 
 */
@Entity
@Table(name="sdomain")
@NamedQuery(name="Domain.findAll", query="SELECT d FROM Domain d")
public class Domain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;

	private String name;

	public Domain() {
	}

	public String getEid() {
		return this.eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}