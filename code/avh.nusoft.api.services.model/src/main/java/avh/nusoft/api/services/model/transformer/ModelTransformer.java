package avh.nusoft.api.services.model.transformer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import avh.nusoft.api.model.Address;
import avh.nusoft.api.model.Application;
import avh.nusoft.api.model.Article;
import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Degree;
import avh.nusoft.api.model.Message;
import avh.nusoft.api.model.ResourceRequest;
import avh.nusoft.api.model.Subscription;
import avh.nusoft.api.model.domains.Honour;
import avh.nusoft.api.services.model.in.APIAddressIn;
import avh.nusoft.api.services.model.in.APIApplicationIn;
import avh.nusoft.api.services.model.in.APIArticleIn;
import avh.nusoft.api.services.model.in.APIDegreeIn;
import avh.nusoft.api.services.model.in.APIMessageIn;
import avh.nusoft.api.services.model.in.APIResourceRequestIn;
import avh.nusoft.api.services.model.in.APISubscriptionIn;
import avh.nusoft.api.services.model.in.APIUserIn;
import avh.nusoft.api.services.model.out.APIAddressOut;
import avh.nusoft.api.services.model.out.APIApplicationOut;
import avh.nusoft.api.services.model.out.APIArticleOut;
import avh.nusoft.api.services.model.out.APIDegreeOut;
import avh.nusoft.api.services.model.out.APIMessageDescriptor;
import avh.nusoft.api.services.model.out.APIMessageOut;
import avh.nusoft.api.services.model.out.APIResourceRequestOut;
import avh.nusoft.api.services.model.out.APISubscriptionOut;
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
	
	public static Degree DegreeAPI2Model(APIDegreeIn di) {
		Degree d = new Degree();
		d.setDate(di.getDate());
		d.setHonour(di.getHonour().toString());
		d.setInstitution(di.getInstitution());
		d.setLevel(di.getLevel());
		return d;
	}
	
	public static Article ArticleAPI2Model(APIArticleIn ai ) {
		Article a = new Article();
		
		a.setArticleDate(ai.getDate());
		a.setDescription(ai.getDescription());
		a.setTitle(ai.getTile());
		a.setUrlLink(ai.getUrl());
		
		return a;
	}
	
	public static Subscription SubscriptionAPI2Model(APISubscriptionIn si) {
		Subscription res = new Subscription();
		res.setSubscriptionDate(si.getSubscriptionDate());
		return res;
	}
	
	public static ResourceRequest ResourceRequestAPI2Model(APIResourceRequestIn rri) {
		ResourceRequest res = new ResourceRequest();
		
		res.setEid(UUID.randomUUID().toString());
		res.setCurrency(rri.getCurrency());
		res.setDate(rri.getDate());
		res.setDuration(rri.getDuration());
		res.setHourlyRate(rri.getHourlyRate());
		res.setNbResource(rri.getNbResource());
		res.setStartDate(rri.getStartDate());
		
		return res;
	}
	
	public static Application ApplicationAPI2Model(APIApplicationIn ain) {
		Application res = new Application();
		
		res.setCurrency(ain.getCurrency());
		res.setDate(ain.getDate());
		res.setProposedRate(ain.getProposedRate());

		return null;
	}
	
	public static Message MessageAPI2Model(APIMessageIn mi) {
		Message m = new Message();
		m.setBody(mi.getBody());
		m.setDate(mi.getDate());
		m.setSubject(mi.getSubject());
		
		return m;
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
	
	public static APISubscriptionOut SubscriptionModel2API(Subscription s) {
		APISubscriptionOut res = new APISubscriptionOut();
		
		res.setDomain(s.getDomain().getEid());
		res.setEid(s.getEid());
		res.setEmail(s.getContact().getEmail());
		res.setStatus(s.getStatus());
		res.setSubscriptionDate(s.getSubscriptionDate());
		
		return res;
	}
	
	public static APIResourceRequestOut ResourceRequestModel2API(ResourceRequest rr) {
		APIResourceRequestOut res = new APIResourceRequestOut();
		
		res.setId(rr.getEid());
		res.setCurrency(rr.getCurrency());
		res.setDate(rr.getDate());
		res.setDuration(rr.getDuration());
		res.setHourlyRate(rr.getHourlyRate());
		res.setNbResource(rr.getNbResource());
		res.setStartDate(rr.getStartDate());
		res.setStatus(rr.getStatus());
		res.setSubscriptionId(rr.getSubscription().getEid());
		rr.getSkills().forEach(x -> res.addSkill(x.getDomainSkill().getSkillName()));
		return res;
	}

	public static APIApplicationOut ApplicationModel2API(Application app) {
		APIApplicationOut res = new APIApplicationOut();
		
		res.setCurrency(app.getCurrency());
		res.setDate(app.getDate());
		res.setEid(app.getEid());
		res.setProposedRate(app.getProposedRate());
		res.setRequestId(app.getResourceRequest().getEid());
		res.setStatus(app.getStatus());
		res.setSubscriptionId(app.getSubscription().getEid());
		
		return res;
	}
	
	public static APIMessageDescriptor MessageModel2APIDesc(Message m) {
		APIMessageDescriptor res = new APIMessageDescriptor();
		
		res.setDate(m.getDate());
		res.setEid(m.getEid());
		res.setReceiver(m.getReceiver().getEmail());
		res.setSender(m.getSender().getEmail());
		res.setSubject(m.getSubject());
		res.setStatus(m.getStatus());
		
		return res;
	}
	
	public static APIMessageOut MessageModel2API(Message m) {
		APIMessageOut res = new APIMessageOut();
		
		res.setDate(m.getDate());
		res.setEid(m.getEid());
		res.setReceiver(m.getReceiver().getEmail());
		res.setSender(m.getSender().getEmail());
		res.setSubject(m.getSubject());
		res.setBody(m.getBody());
		res.setStatus(m.getStatus());

		return res;
	}
}
