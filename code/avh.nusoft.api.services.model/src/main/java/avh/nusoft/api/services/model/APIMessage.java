package avh.nusoft.api.services.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class APIMessage {
	private String subject;
	private String sender;
	private String receiver;
	private Date date;
}
