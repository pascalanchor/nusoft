package avh.nusoft.api.services.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
public class APIResourceRequest {
	private Date date;
	private String currency;
	private int duration;
	private double hourlyRate;
	private int nbResource;
	private Date startDate;
	private String subscriptionId;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private List<String> requestedSkills = new ArrayList<>();
	
	public boolean addSkill(String skl) {
		if (requestedSkills.contains(skl))
			return false;
		requestedSkills.add(skl);
		return true;
	}
	
	public List<String> getAllSkills() {
		return requestedSkills;
	}
	
	public boolean removeSkill(String skl) {
		return requestedSkills.remove(skl);
	}
}
