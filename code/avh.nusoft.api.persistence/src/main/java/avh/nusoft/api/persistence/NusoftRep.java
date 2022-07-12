package avh.nusoft.api.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class NusoftRep {
	@Autowired private AddressRep addressRep;
	@Autowired private ApplicationRep applicationRep;
	@Autowired private ArticleRep articleRep;
	@Autowired private BidRep bidRep;
	@Autowired private CertificationRep certificationRep;
	@Autowired private ContactRep contactRep;
	@Autowired private DegreeRep degreeRep;
	@Autowired private DomainRep domainRep;
	@Autowired private ExperienceRep experienceRep;
	@Autowired private MessageRep messageRep;
	@Autowired private RequestSkillRep requestSkillRep;
	@Autowired private ResourceRequestRep resourceRequestRep;
	@Autowired private ReviewRep reviewRep;
	@Autowired private RfpRep rfpRep;
	@Autowired private SkillRep skillRep;
	@Autowired private SubscriptionRep subscriptionRep;
	@Autowired private RoleRep roleRep;
	@Autowired private UserRoleRep userRoleRep;
	@Autowired private CountryRep countryRep;
	@Autowired private CurrencyRep currencyRep;
	@Autowired private DomainSkillRep domSkillRep;
}
