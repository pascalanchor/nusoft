package avh.nusoft.api.persistence;

import org.springframework.data.repository.CrudRepository;

import avh.nusoft.api.model.Country;

public interface CountryRep extends CrudRepository<Country, String> {

}
