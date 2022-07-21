package avh.nusoft.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import avh.nusoft.api.common.NusoftConstants;
import avh.nusoft.api.model.Message;
import avh.nusoft.api.services.impl.MessageSvcImpl;
import avh.nusoft.api.services.model.in.APIMessageIn;
import avh.nusoft.api.services.model.out.APIMessageDescriptor;
import avh.nusoft.api.services.model.out.APIMessageOut;
import avh.nusoft.api.services.model.transformer.ModelTransformer;

@RestController
public class MessageSvc {
	@Autowired
	MessageSvcImpl svc;

	@PreAuthorize("hasAnyRole('User')")
	@PostMapping(NusoftConstants.PrivateServletPath + "/message")
	public ResponseEntity<APIMessageOut> sendMessage(@RequestBody APIMessageIn m) {
		try {
			Message msg = svc.sendMessage(m.getSender(), m.getReceiver(), m.getSubject(), m.getBody(), m.getDate());
			APIMessageOut res = ModelTransformer.MessageModel2API(msg);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@PreAuthorize("hasAnyRole('User')")
	@DeleteMapping(NusoftConstants.PrivateServletPath + "/message/{id}/delete")
	public ResponseEntity<Boolean> deleteMessage(@PathVariable String mid) {
		try {
			boolean res = svc.deleteMessage(mid);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/message/{id}")
	public ResponseEntity<APIMessageOut> getMessageDetails(@PathVariable String id) {
		try {
			Message msg = svc.getMessage(id);
			APIMessageOut res = ModelTransformer.MessageModel2API(msg);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	
	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/message/all")
	public ResponseEntity<List<APIMessageDescriptor>> getAllMessages(@RequestParam String receiver) {
		try {
			List<Message> msgs = svc.getAllMessages(receiver);
			List<APIMessageDescriptor> res = new ArrayList<>();
			msgs.forEach(x -> res.add(ModelTransformer.MessageModel2APIDesc(x)));
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	
	@PreAuthorize("hasAnyRole('User')")
	@GetMapping(NusoftConstants.PrivateServletPath + "/message/unread")
	public ResponseEntity<List<APIMessageDescriptor>> getAllUnreadMessages(@RequestParam String receiver) {
		try {
			List<Message> msgs = svc.getUnreadMessages(receiver);
			List<APIMessageDescriptor> res = new ArrayList<>();
			msgs.forEach(x -> res.add(ModelTransformer.MessageModel2APIDesc(x)));
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
	
	@PreAuthorize("hasAnyRole('User')")
	@PutMapping(NusoftConstants.PrivateServletPath + "/message/{id}/markread")
	public ResponseEntity<Boolean> markMessageAsRead(@PathVariable String id) {
		try {
			List<String> ids = new ArrayList<>();
			ids.add(id);
			boolean res = svc.readMessage(ids);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}
}
