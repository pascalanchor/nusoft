package avh.nusoft.api.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import avh.nusoft.api.model.Bid;

@Repository
public interface BidRep extends CrudRepository<Bid, String> {

}
