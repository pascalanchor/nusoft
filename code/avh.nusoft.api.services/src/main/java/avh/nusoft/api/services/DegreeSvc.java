package avh.nusoft.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import avh.nusoft.api.model.Degree;
import avh.nusoft.api.services.impl.DegreeSvcImpl;
import avh.nusoft.api.services.model.in.APIDegreeIn;
import avh.nusoft.api.services.model.out.APIDegreeOut;
import avh.nusoft.api.services.transformer.Transformer;

@RestController
public class DegreeSvc {
	@Autowired private DegreeSvcImpl degSvc;
	
	@PreAuthorize("hasAnyRole('User')")
	@PostMapping("/avh/nusoft/api/authorized/degree")
	public ResponseEntity<APIDegreeOut> addDegree(@RequestBody APIDegreeIn di) {
		try {
			Degree d = Transformer.DegreeAPI2Model(di);
			d = degSvc.addNewDegree(d, di.getEmail());
			APIDegreeOut res = Transformer.DegreeModel2API(d);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
}
