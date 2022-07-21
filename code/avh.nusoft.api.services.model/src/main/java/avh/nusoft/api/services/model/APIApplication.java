package avh.nusoft.api.services.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APIApplication {
	private Date date;
	private String currency;
	private double proposedRate;
	private String requestId;
	private String subscriptionId;
}
