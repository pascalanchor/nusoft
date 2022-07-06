package avh.nusoft.api.services.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class APIAddress {
	private String country;
	private String state;
	private String city;
	private String street;
	private String zipcode;
}
