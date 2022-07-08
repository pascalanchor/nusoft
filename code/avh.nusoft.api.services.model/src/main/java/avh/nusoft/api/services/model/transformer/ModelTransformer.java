package avh.nusoft.api.services.model.transformer;

import java.util.UUID;

import avh.nusoft.api.model.Address;
import avh.nusoft.api.model.Article;
import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Degree;
import avh.nusoft.api.model.domains.Honour;
import avh.nusoft.api.services.model.in.APIAddressIn;
import avh.nusoft.api.services.model.in.APIArticleIn;
import avh.nusoft.api.services.model.in.APIDegreeIn;
import avh.nusoft.api.services.model.in.APIUserIn;
import avh.nusoft.api.services.model.out.APIAddressOut;
import avh.nusoft.api.services.model.out.APIArticleOut;
import avh.nusoft.api.services.model.out.APIDegreeOut;
import avh.nusoft.api.services.model.out.APIUserOut;

public class ModelTransformer {
	// ----------------------------------------------
	// --- API -> Model
	// ----------------------------------------------
	public static Address AddressAPI2Model(APIAddressIn adr) {
		Address res = new Address();
		
		res.setCity(adr.getCity());
		res.setCountry(adr.getCountry());
		res.setEid(UUID.randomUUID().toString());
		res.setState(adr.getState());
		res.setStreet(adr.getStreet());
		res.setZipcode(adr.getZipcode());
		
		return res;
	}
	
	public static Degree DegreeAPI2Model(APIDegreeIn di) {
		Degree d = new Degree();
		d.setDate(di.getDate());
		d.setHonour(di.getHonour().toString());
		d.setInstitution(di.getInstitution());
		d.setLevel(di.getLevel());
		return d;
	}
	
	public static Contact UserAPI2Model(APIUserIn u) {
		Contact c = new Contact();
		
		c.setAddress(ModelTransformer.AddressAPI2Model(u.getAddress()));
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
	
	public static Article ArticleAPI2Model(APIArticleIn ai) {
		Article a=new Article();
		a.setArticleDate(ai.getDate());
		a.setDescription(ai.getDescription());
		a.setTitle(ai.getTile());
		a.setUrlLink(ai.getUrl());
		return a;
	}
	
	// ----------------------------------------------
	// --- Model -> API
	// ----------------------------------------------
	public static APIDegreeOut DegreeModel2API(Degree d) {
		APIDegreeOut res = new APIDegreeOut();
		
		res.setDate(d.getDate());
		res.setEid(d.getEid());
		res.setHonour(Honour.valueOf(d.getHonour()));
		res.setInstitution(d.getInstitution());
		res.setLevel(d.getLevel());
		res.setEmail(d.getContact().getEmail());
		
		return res;
	}
	
	public static APIAddressOut AddressModel2API(Address adr) {
		APIAddressOut res = new APIAddressOut();
		
		res.setEid(adr.getEid());
		res.setCountry(adr.getCountry());
		res.setCity(adr.getCity());
		res.setState(adr.getState());
		res.setStreet(adr.getStreet());
		res.setZipcode(adr.getZipcode());
		
		return res;
	}
	
	public static APIUserOut UserModel2API(Contact ctt) {
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
		res.setAddress(ModelTransformer.AddressModel2API(ctt.getAddress()));
		
		return res;
	}
	
	public static APIArticleOut ArticleModel2API(Article a) {
		
		APIArticleOut res=new APIArticleOut();
		res.setDate(a.getArticleDate());
		res.setDescription(a.getDescription());
		res.setTile(a.getTitle());
		res.setUrl(a.getUrlLink());
		res.setEid(a.getEid());
		res.setEmail(a.getContact().getEmail());
		return res;
	}
}
