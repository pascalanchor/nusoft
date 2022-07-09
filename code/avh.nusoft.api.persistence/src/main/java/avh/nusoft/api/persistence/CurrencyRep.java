package avh.nusoft.api.persistence;

import org.springframework.data.repository.CrudRepository;

import avh.nusoft.api.model.Currency;

public interface CurrencyRep extends CrudRepository<Currency, String> {

}
