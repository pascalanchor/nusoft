package avh.nusoft.api.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Message;

@Repository
public interface MessageRep extends CrudRepository<Message, String> {

}