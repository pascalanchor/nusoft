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
import org.springframework.web.server.ResponseStatusException;

import avh.nusoft.api.common.NusoftConstants;
import avh.nusoft.api.model.Application;
import avh.nusoft.api.model.ResourceRequest;
import avh.nusoft.api.services.impl.ResourceRequestSvcImpl;
import avh.nusoft.api.services.model.in.APIResourceRequestIn;
import avh.nusoft.api.services.model.out.APIApplicationOut;
import avh.nusoft.api.services.model.out.APIResourceRequestOut;
import avh.nusoft.api.services.model.transformer.ModelTransformer;

@RestController
public class ResourceRequestSvc {
	@Autowired private ResourceRequestSvcImpl rrsvc;
	
	@PreAuthorize("hasAnyRole('User')")
	@PostMapping(NusoftConstants.PrivateServletPath + "/resourcerequest")
	public ResponseEntity<APIResourceRequestOut> createResourceRequest(@RequestBody APIResourceRequestIn rri) {
		try {
			ResourceRequest rr = ModelTransformer.ResourceRequestAPI2Model(rri);
			rr = rrsvc.createResourceRequest(rr, rri.getSubscriptionId(), rri.getAllSkills());
			APIResourceRequestOut res = ModelTransformer.ResourceRequestModel2API(rr); 
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@PreAuthorize("hasAnyRole('User')")
	@PatchMapping(NusoftConstants.PrivateServletPath + "/resourcerequest/{id}/update")
	public ResponseEntity<APIResourceRequestOut> updateResourceRequest(@PathVariable("id") String id, @RequestBody APIResourceRequestIn rri) {
		try {
			return ResponseEntity.ok().body(null);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@PreAuthorize("hasAnyRole('User')")
	@DeleteMapping(NusoftConstants.PrivateServletPath + "/resourcerequest/{id}/delete")
	public ResponseEntity<Boolean> deleteResourceRequest(@PathVariable("id") String id) {
		try {
			boolean res = rrsvc.deleteResourceRequest(id);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/resourcerequest/{id}")
	public ResponseEntity<APIResourceRequestOut> findResourceRequestById(@PathVariable String id) {
		try {
			ResourceRequest rr = rrsvc.getResourceRequest(id);
			APIResourceRequestOut rro = ModelTransformer.ResourceRequestModel2API(rr);
			return ResponseEntity.ok().body(rro);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/resourcerequest/all")
	public ResponseEntity<List<APIResourceRequestOut>> getAllResourceRequests(@RequestParam String subid) {
		try {
			List<ResourceRequest> all = rrsvc.getAllResourceRequests(subid);
			
			if (all == null)
				return ResponseEntity.ok().body(null);
			
			List<APIResourceRequestOut> res = new ArrayList<>();
			all.forEach(x -> res.add(ModelTransformer.ResourceRequestModel2API(x)));
			
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	
	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/resourcerequest/{id}/applications")
	public ResponseEntity<List<APIApplicationOut>> getAllRequestApplication(@PathVariable String id) {
		try {
			List<Application> apps = rrsvc.getAllApplications(id);
			if ((apps == null) || (apps.size() <= 0))
				return ResponseEntity.ok().body(null);
			
			List<APIApplicationOut> res = new ArrayList<>();
			apps.forEach(x -> res.add(ModelTransformer.ApplicationModel2API(x)));
			
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	
	
}
