package avh.nusoft.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import avh.nusoft.api.common.NusoftConstants;
import avh.nusoft.api.model.Subscription;
import avh.nusoft.api.services.impl.SubscriptionSvcImpl;
import avh.nusoft.api.services.model.in.APISubscriptionIn;
import avh.nusoft.api.services.model.out.APISubscriptionOut;
import avh.nusoft.api.services.model.transformer.ModelTransformer;

@RestController
public class SubscriptionSvc {
	@Autowired
	private SubscriptionSvcImpl subSvc;

	@PostMapping(NusoftConstants.PrivateServletPath + "/subscription")
	public ResponseEntity<APISubscriptionOut> registerSubscription(@RequestBody APISubscriptionIn sin) {
		try {
			Subscription s = ModelTransformer.SubscriptionAPI2Model(sin);

			s = subSvc.registerSubscription(s, sin.getEmail(), sin.getDomain());
			APISubscriptionOut res = ModelTransformer.SubscriptionModel2API(s);

			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	
	@DeleteMapping(NusoftConstants.PrivateServletPath + "/subscription/{id}/delete")
	public ResponseEntity<Boolean> unregisterSubscription(@PathVariable String id) {
		try {
			return ResponseEntity.ok().body(subSvc.unregisterSubscription(id));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	
	@GetMapping(NusoftConstants.PrivateServletPath + "/subscription/{id}")
	public ResponseEntity<APISubscriptionOut> getSubscription(@PathVariable String id) {
		try {
			Subscription s = subSvc.getSubscription(id);
			APISubscriptionOut res = ModelTransformer.SubscriptionModel2API(s);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	
	@GetMapping(NusoftConstants.PrivateServletPath + "/subscription/all")
	public ResponseEntity<List<APISubscriptionOut>> getAllSubscriptions(@RequestParam String email) {
		try {
			List<APISubscriptionOut> res = new ArrayList<>();
			List<Subscription> all = subSvc.getAllSubscriptions(email);
			all.forEach(x -> res.add(ModelTransformer.SubscriptionModel2API(x)));
			
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	
}
