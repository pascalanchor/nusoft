package avh.nusoft.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import avh.nusoft.api.common.NusoftConstants;
import avh.nusoft.api.model.Degree;
import avh.nusoft.api.services.impl.DegreeSvcImpl;
import avh.nusoft.api.services.model.in.APIDegreeIn;
import avh.nusoft.api.services.model.out.APIDegreeOut;
import avh.nusoft.api.services.model.transformer.ModelTransformer;

@RestController
public class DegreeSvc {
	@Autowired private DegreeSvcImpl degSvc;

	@PreAuthorize("hasAnyRole('User')")
	@PostMapping(NusoftConstants.PrivateServletPath + "/degree")
	public ResponseEntity<APIDegreeOut> addDegree(@RequestBody APIDegreeIn di) {
		try {
			Degree d = ModelTransformer.DegreeAPI2Model(di);
			d = degSvc.addNewDegree(d, di.getEmail());
			APIDegreeOut res = ModelTransformer.DegreeModel2API(d);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@PreAuthorize("hasAnyRole('User')")
	@DeleteMapping(NusoftConstants.PrivateServletPath + "/degree/{id}/delete")
	public ResponseEntity<Boolean> deleteDegree(@PathVariable String id) {
		try {
			boolean res = degSvc.deleteDegree(id);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@PreAuthorize("hasAnyRole('User')")
	@PatchMapping(NusoftConstants.PrivateServletPath + "/degree/{id}/update")
	public ResponseEntity<APIDegreeOut> updateDegree(@PathVariable String id, @RequestBody APIDegreeIn di) {
	try {
		Degree d = ModelTransformer.DegreeAPI2Model(di);
		Degree updatedDegree = degSvc.updateDegree(id, d);
		APIDegreeOut res = ModelTransformer.DegreeModel2API(updatedDegree);
		return ResponseEntity.ok().body(res);
	} catch (Exception e) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
	}	
	}

	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/degree/{id}")
	public ResponseEntity<APIDegreeOut> findDegreeById(@PathVariable(value = "id") String id) {
		try{
			Degree d = degSvc.getDegree(id);
			APIDegreeOut res = ModelTransformer.DegreeModel2API(d);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}

	}
	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/degree/all")
	public ResponseEntity<List<APIDegreeOut>> getAllDegree(@RequestParam String email) {
		try{
			List<Degree> d = degSvc.getAllDegree(email);
			List<APIDegreeOut> ap = new ArrayList<>();
			for(Degree de : d) {
				APIDegreeOut res = ModelTransformer.DegreeModel2API(de);	
				ap.add(res);
			}
			
			return ResponseEntity.ok().body(ap);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	

	
	

}
