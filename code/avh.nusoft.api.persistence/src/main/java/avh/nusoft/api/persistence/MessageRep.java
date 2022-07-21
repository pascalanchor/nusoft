package avh.nusoft.api.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Contact;
import avh.nusoft.api.model.Message;

@Repository
public interface MessageRep extends CrudRepository<Message, String> {
	public List<Message> findByReceiver(Contact receiver);
	public List<Message> findByReceiverAndStatus(Contact receiver, String status);
}
