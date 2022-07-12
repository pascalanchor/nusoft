package avh.nusoft.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import avh.nusoft.api.common.NusoftConstants;
import avh.nusoft.api.services.impl.DomainSvcImpl;

@RestController
public class DomainSvc {
	@Autowired private DomainSvcImpl dmnSvc;

	
	@GetMapping(NusoftConstants.PublicServletPath + "/currencies")
	public ResponseEntity<List<String>> getRegisteredCurrencies() {
		try {
			return ResponseEntity.ok().body(dmnSvc.getRegisteredCurrencies());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve defined currencies...");
		}
	}
	
	@GetMapping(NusoftConstants.PublicServletPath + "/countries")
	public ResponseEntity<List<String>> getRegisteredCountries() {
		try {
			return ResponseEntity.ok().body(dmnSvc.getRegisteredCountries());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve defined countries...");
		}
	}
	
	@GetMapping(NusoftConstants.PublicServletPath + "/domains")
	public ResponseEntity<List<String>> getRegisteredDomains() {
		try {
			return ResponseEntity.ok().body(dmnSvc.getRegisteredDomains());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve defined countries...");
		}
	}
	
	@GetMapping(NusoftConstants.PublicServletPath + "/domain/{domainid}/skills")
	public ResponseEntity<List<String>> getDomainSkills(@PathVariable String domainid) {
		try {
			return ResponseEntity.ok().body(dmnSvc.getDomainSkills(domainid));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
