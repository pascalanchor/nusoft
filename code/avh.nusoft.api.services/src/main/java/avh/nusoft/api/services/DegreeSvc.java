package avh.nusoft.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Degree;
import avh.nusoft.api.model.reps.NusoftRep;
import avh.nusoft.api.services.impl.DegreeSvcImpl;
import avh.nusoft.api.services.model.in.APIDegreeIn;
import avh.nusoft.api.services.model.out.APIDegreeOut;
import avh.nusoft.api.services.transformer.Transformer;

@RestController
public class DegreeSvc {
	@Autowired
	private DegreeSvcImpl degSvc;
	@Autowired
	private NusoftRep rep;

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

	@PreAuthorize("hasAnyRole('User')")
	@DeleteMapping("avh/nusoft/api/authorized/delete/degree/id")
	public void deleteDegree(@RequestParam String id) {
		rep.getDegreeRep().deleteById(id);

	}

	@PreAuthorize("hasAnyRole('User')")
	@PutMapping("avh/nusoft/api/authorized/edit/degree")
	public ResponseEntity<APIDegreeOut> UpdateDegree(@PathVariable String id,@RequestBody APIDegreeIn di) {
	try {	Degree d = rep.getDegreeRep().findByEid(id);
		d.setDate(di.getDate());
		d.setHonour(di.getHonour().toString());
		d.setInstitution(di.getInstitution());
		d.setLevel(di.getLevel());
		rep.getDegreeRep().save(d);
		APIDegreeOut res=Transformer.DegreeModel2API(d);
		return ResponseEntity.ok().body(res);}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
		
		
	}

	@PreAuthorize("hasAnyRole('User')")
	@GetMapping("/avh/nusoft/api/authorized/degrees")
	public List<Degree> getAllDegrees() {
		return degSvc.getAllDegree();

	}

	@PreAuthorize("hasAnyRole('User')")
	@GetMapping("avh/nusoft/api/authorized/get/degree/{id}")
	public ResponseEntity<APIDegreeOut> findDegreeById(@PathVariable(value = "id") String id) {
		try{Degree d = rep.getDegreeRep().findByEid(id);
		APIDegreeOut res = Transformer.DegreeModel2API(d);
		return ResponseEntity.ok().body(res);}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}

	}
	@PreAuthorize("hasAnyRole('User')")
	@GetMapping("avh/nusoft/api/authorized/all/degress/contact")
	public List<APIDegreeOut> getAllDegree(@RequestParam String email){
		try{List<Degree> d = degSvc.getAllDegree(email);
		List<APIDegreeOut> ap = new ArrayList<>();
		for(Degree de : d) {
			APIDegreeOut res = Transformer.DegreeModel2API(de);	
			ap.add(res);
		}
		
		return ap;		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	

}
