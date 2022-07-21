package avh.nusoft.api.services.model.out;


import avh.nusoft.api.services.model.APISubscription;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APISubscriptionOut extends APISubscription {
	private String eid;
	private String status;
}
