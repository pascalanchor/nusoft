package avh.nusoft.api.services.model;

import java.util.Date;

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
}
