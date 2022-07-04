package avh.nusoft.api.security.common;

import java.util.UUID;

import avh.nusoft.api.model.Address;
import avh.nusoft.api.model.Contact;
import avh.nusoft.api.security.api.model.in.APIAddressIn;
import avh.nusoft.api.security.api.model.in.APIUserIn;
import avh.nusoft.api.security.api.model.out.APIAddressOut;
import avh.nusoft.api.security.api.model.out.APIUserOut;

public class API2ModelTransformer {
	public static APIAddressOut TransformModelAddress(Address adr) {
		APIAddressOut res = new APIAddressOut();
		
		res.setEid(adr.getEid());
		res.setCountry(adr.getCountry());
		res.setCity(adr.getCity());
		res.setState(adr.getState());
		res.setStreet(adr.getStreet());
		res.setZipcode(adr.getZipcode());
		
		return res;
	}
	
	public static APIUserOut TransformContact(Contact ctt) {
		APIUserOut res = new APIUserOut();
		
		res.setEid(ctt.getEid());
		res.setCurrency(ctt.getCurrency());
		res.setDob(ctt.getDob());
		res.setEmail(ctt.getEmail());
		res.setFirstname(ctt.getFirstname());
		res.setLastname(ctt.getLastname());
		res.setGender(ctt.getGender());
		res.setHourlyRate(ctt.getHourlyRate());
		res.setPhone(ctt.getPhone());
		res.setAddress(API2ModelTransformer.TransformModelAddress(ctt.getAddress()));
		
		return res;
	}
	
	public static Address TransformInAddress(APIAddressIn adr) {
		Address res = new Address();
		
		res.setCity(adr.getCity());
		res.setCountry(adr.getCountry());
		res.setEid(UUID.randomUUID().toString());
		res.setState(adr.getState());
		res.setStreet(adr.getStreet());
		res.setZipcode(adr.getZipcode());
		
		return res;
	}
	
	public static Contact TransformUser(APIUserIn u) {
		Contact c = new Contact();
		
		c.setAddress(API2ModelTransformer.TransformInAddress(u.getAddress()));
		c.setCurrency(u.getCurrency());
		c.setDob(u.getDob());
		c.setEid(UUID.randomUUID().toString());
		c.setEmail(u.getEmail());
		c.setFirstname(u.getFirstname());
		c.setGender(u.getGender());
		c.setHourlyRate(u.getHourlyRate());
		c.setLastname(u.getLastname());
		c.setPhone(u.getPhone());
		
		return c;
	}
}
