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
import avh.nusoft.api.services.impl.ApplicationSvcImpl;
import avh.nusoft.api.services.model.in.APIApplicationIn;
import avh.nusoft.api.services.model.out.APIApplicationOut;
import avh.nusoft.api.services.model.transformer.ModelTransformer;

@RestController
public class ApplicationSvc {
	@Autowired private ApplicationSvcImpl svc;
	
	@PreAuthorize("hasAnyRole('User')")
	@PostMapping(NusoftConstants.PrivateServletPath + "/application")
	public ResponseEntity<APIApplicationOut> applyToRequest(@RequestBody APIApplicationIn ain) {
		try {
			Application app = ModelTransformer.ApplicationAPI2Model(ain);
			app = svc.applyToRequest(app, ain.getSubscriptionId(), ain.getRequestId());
			APIApplicationOut res = ModelTransformer.ApplicationModel2API(app);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@PreAuthorize("hasAnyRole('User')")
	@PatchMapping(NusoftConstants.PrivateServletPath + "/application/{id}/update")
	public ResponseEntity<APIApplicationOut> updateApplication(@PathVariable String id, @RequestBody APIApplicationIn ain) {
		try {
			Application app = ModelTransformer.ApplicationAPI2Model(ain);
			app = svc.updateApplication(app, id);
			APIApplicationOut res = ModelTransformer.ApplicationModel2API(app); 
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@PreAuthorize("hasAnyRole('User')")
	@DeleteMapping(NusoftConstants.PrivateServletPath + "/application/{id}/delete")
	public ResponseEntity<Boolean> deleteApplication(@PathVariable String id) {
		try {
			boolean res = svc.cancelApplication(id);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/application/{id}")
	public ResponseEntity<APIApplicationOut> getApplication(@PathVariable String id) {
		try {
			Application app = svc.getApplication(id);
			APIApplicationOut res = ModelTransformer.ApplicationModel2API(app);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/application/all")
	public ResponseEntity<List<APIApplicationOut>> getAllApplication(@RequestParam String subscriptionId) {
		try {
			List<Application> apps = svc.getAllApplications(subscriptionId);
			List<APIApplicationOut> res = new ArrayList<>();
			apps.forEach(x -> res.add(ModelTransformer.ApplicationModel2API(x)));
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
