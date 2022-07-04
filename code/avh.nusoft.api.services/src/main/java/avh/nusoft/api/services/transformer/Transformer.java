package avh.nusoft.api.services.transformer;

import avh.nusoft.api.model.Degree;
import avh.nusoft.api.model.domains.Honour;
import avh.nusoft.api.services.model.in.APIDegreeIn;
import avh.nusoft.api.services.model.out.APIDegreeOut;

public class Transformer {
	public static Degree DegreeAPI2Model(APIDegreeIn di) {
		Degree d = new Degree();
		d.setDate(di.getDate());
		d.setHonour(di.getHonour().toString());
		d.setInstitution(di.getInstitution());
		d.setLevel(di.getLevel());
		return d;
	}
	
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
}
