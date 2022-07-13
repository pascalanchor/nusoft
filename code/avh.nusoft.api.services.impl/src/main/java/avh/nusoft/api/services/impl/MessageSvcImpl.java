package avh.nusoft.api.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Message;
import avh.nusoft.api.model.domains.MessageStatus;
import avh.nusoft.api.persistence.NusoftRep;
import avh.nusoft.api.services.impl.util.RepHelper;

@Component
public class MessageSvcImpl {
	@Autowired private NusoftRep rep;
	@Autowired private RepHelper helper;
	
	public Message sendMessage(String from, String to, String subject, String body, Date mdate) {
		Contact sender = helper.getUser(from)
				.orElseThrow(() -> new IllegalArgumentException(String.format("User '%s' not found", from)));
		Contact receiver = helper.getUser(to)
				.orElseThrow(() -> new IllegalArgumentException(String.format("User '%s' not found", to)));
		
		Message m = new Message();
		m.setBody(body);
		m.setEid(UUID.randomUUID().toString());
		m.setReceiver(receiver);
		m.setSender(sender);
		m.setSubject(subject);
		m.setStatus(MessageStatus.Unread.toString());
		m.setDate(mdate);
		
		rep.getMessageRep().save(m);
		return m;
	}
	
	public List<Message> getUnreadMessages(String receiver) {
		Contact usr = helper.getUser(receiver)
				.orElseThrow(() -> new IllegalArgumentException(String.format("User '%s' not found", receiver)));
		List<Message> res = rep.getMessageRep().findByReceiverAndStatus(usr, MessageStatus.Unread.toString());
		return res;
	}

	public List<Message> getAllMessages(String receiver) {
		Contact usr = helper.getUser(receiver)
				.orElseThrow(() -> new IllegalArgumentException(String.format("User '%s' not found", receiver)));
		List<Message> res = rep.getMessageRep().findByReceiver(usr);
		return res;
	}

	public Message getMessage(String mid) {
		Message msg = rep.getMessageRep().findById(mid)
				.orElseThrow(() -> new IllegalArgumentException(String.format("message '%s' not found", mid)));
	
		return msg;
	}
	
	@Transactional
	public boolean readMessage(List<String> mid) {
		Iterable<Message> msgs = rep.getMessageRep().findAllById(mid);
		
		msgs.forEach(x -> x.setStatus(MessageStatus.Read.toString()));
		
		rep.getMessageRep().saveAll(msgs);
		return true;
	}
	
	public boolean deleteMessage(String mid) {
		rep.getMessageRep().deleteById(mid);		
		return true;
	}
}
