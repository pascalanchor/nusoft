package avh.nusoft.api.services.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APISubscription {
	private Date subscriptionDate;
	private String email;
	private String domain;
}
