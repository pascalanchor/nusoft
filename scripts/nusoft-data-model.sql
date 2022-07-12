drop table if exists bid				cascade;
drop table if exists rfp				cascade;
drop table if exists message			cascade;
drop table if exists application		cascade;
drop table if exists request_skills		cascade;
drop table if exists resource_request	cascade;
drop table if exists review				cascade;
drop table if exists ssubscription		cascade;
drop table if exists skill				cascade;
drop table if exists domain_skill		cascade;
drop table if exists sdomain			cascade;
drop table if exists experience			cascade;
drop table if exists sdegree			cascade;
drop table if exists certification		cascade;
drop table if exists article			cascade;
drop table if exists user_role			cascade;
drop table if exists srole				cascade;
drop table if exists contact			cascade;
drop table if exists saddress			cascade;
drop table if exists scountry			cascade;
drop table if exists scurrency			cascade;

create table scountry (
	cname varchar(128) primary key
);

insert into scountry (cname) values ('Australia');
insert into scountry (cname) values ('Lebanon');
insert into scountry (cname) values ('United Arab Emirates');

create table scurrency (
	cname varchar(128) primary key
);
insert into scurrency (cname) values('USD');
insert into scurrency (cname) values('AUD');
insert into scurrency (cname) values('LBP');
insert into scurrency (cname) values('AED');

create table saddress (
	eid 	varchar(128) primary key,
	country	varchar(128) not null,
	city	varchar(128),
	street	varchar(128),
	zipcode	varchar(128),
	sstate	varchar(128)
);

create table contact (
	eid			varchar(128) 	primary key,
	email		varchar(128) 	not null,
	pwd			varchar(256) 	not null,
	firstname	varchar(128) 	not null,
	lastname	varchar(128) 	not null,
	gender		varchar(8) 		not null,
	phone		varchar(128) 	not null,
	dob			date 			not null,
	addr_id		varchar(128) 	not null,
	hourly_rate	DECIMAL			not null,
	currency	varchar(128)	not null,
	
	constraint 	fk_ctt_addr
		foreign key(addr_id)
		references saddress(eid)
);

create table srole (
	eid		varchar(128) primary key,
	rname	varchar(128) not null
);

insert into srole (eid, rname) values('ROLE_Admin', 'ROLE_Admin');
insert into srole (eid, rname) values('ROLE_User', 'ROLE_User');

create table user_role (
	eid			varchar(128) primary key,
	contact_id	varchar(128) not null,
	role_id		varchar(128) not null,
	
	constraint fk_ur_ctt
		foreign key(contact_id)
		references contact(eid),
	constraint fk_ur_rl
		foreign key(role_id)
		references srole(eid)
);

create table article (
	eid				varchar(128) primary key,
	contact_id		varchar(128) not null,
	title			varchar(128) not null,
	description		varchar(128) not null,
	article_date	date 		 not null,
	url_link		varchar(128) not null,
	
	constraint 		fk_atc_ctt
		foreign key(contact_id)
		references contact(eid)
);

create table certification (
	eid			varchar(128) primary key,
    contact_id	varchar(128) not null,
	sname		varchar(128) not null,
	description	varchar(128) not null,
	start_date	date		 not null,
    end_date	date,
	
	constraint	fk_crt_ctt
		foreign key(contact_id)
		references contact(eid)  
);

create table sdegree (
	eid			varchar(128) primary key,
	contact_id	varchar(128) not null,
	dlevel		varchar(128) not null,
	institution	varchar(129) not null,
	ddate		date		 not null,
	honour		varchar(128) not null,
	
	constraint	fk_degree_contact
		foreign key(contact_id)
		references contact(eid)
);

create table experience (
	eid			varchar(128) primary key,
	contact_id	varchar(128) not null,
	date_from	date 		 not null,
	date_to		date 		 not null,
	srole		varchar(128) not null,
	description	varchar(128) not null,
	company		varchar(128) not null,
	sref		varchar(128),
	
	constraint	fk_xpr_ctt
		foreign key(contact_id)
		references contact(eid)
);

create table sdomain (
	eid		varchar(128) primary key,
	name	varchar(128) not null
);

insert into sdomain(eid, name) values ('Software Development', 'Software Development');


create table domain_skill (
	eid			varchar(128) primary key,
    domain_id	varchar(128) not null,
	skill_name	varchar(128) not null,
	
	constraint	fk_dom_skill
		foreign key(domain_id)
		references sdomain(eid)
);

insert into
	domain_skill (eid, domain_id, skill_name)
	values ('Java', 'Software Development', 'Java');
insert into
	domain_skill (eid, domain_id, skill_name)
	values ('C#', 'Software Development', 'C#');
insert into
	domain_skill (eid, domain_id, skill_name)
	values ('Spring Boot', 'Software Development', 'Spring Boot');
insert into
	domain_skill (eid, domain_id, skill_name)
	values ('Web Services', 'Software Development', 'Web Services');
insert into
	domain_skill (eid, domain_id, skill_name)
	values ('Design Patterns', 'Software Development', 'Design Patterns');

create table skill (
	eid			varchar(128) primary key,
	skill_id	varchar(128) not null,
	slevel		int 		 not null,
	experience	int			 not null,
	contact_id	varchar(128) not null,
	
	constraint	fk_skl_dsk
		foreign key(skill_id)
		references domain_skill(eid),
	constraint fk_skl_ctt
		foreign key(contact_id)
		references contact(eid)
);

create table ssubscription (
	eid					varchar(128) primary key,
	domain_id			varchar(128) not null,
	contact_id			varchar(128) not null,
	subscription_date	date		 not null,
	sstatus				varchar(128) not null,
	
	constraint fk_sub_dom
		foreign key(domain_id)
		references sdomain(eid),
	constraint fk_sub_ctt
		foreign key(contact_id)
		references contact(eid)
);

create table review (
	eid				varchar(128) primary key,
	subscription_id varchar(128) not null,
    stars			decimal		 not null,
    author			varchar(128) not null,
    comments		varchar(128),
	
	constraint fk_rvw_sub
		foreign key(subscription_id)
		references ssubscription(eid)
);

create table resource_request (
	eid				varchar(128) primary key,
	subscription_id	varchar(128) not null,
	status			varchar(128) not null,
	cdate			date not null,
	currency		varchar(128),
	nb_resource		int not null default 1,
	duration		int,
	start_date		date,
	hourly_rate		decimal,

	constraint fk_rqt_sub
		foreign key(subscription_id)
		references ssubscription(eid)
);

create table request_skills (
	eid 		varchar(128) primary key,
	request_id	varchar(128) not null,
	skill_id	varchar(128) not null,
	
	constraint fk_skl_rqt
		foreign key(request_id)
		references resource_request(eid),
	constraint fk_skl_skl
		foreign key(skill_id)
		references domain_skill(eid)
);

create table application (
	eid					varchar(128) primary key,
	subscription_id		varchar(128) not null,
	request				varchar(128) not null,
	adate				date		 not null,
	proposed_rate		decimal		 not null,
	currency			varchar(128) not null,
	status				varchar(128) not null,
	
	constraint fk_app_ctt
		foreign key(subscription_id)
		references ssubscription(eid),
	constraint fk_app_rqt
		foreign key(request)
		references resource_request(eid)
);

create table message (
	eid			varchar(128) primary key,
	subject		varchar(128),
	mbody		varchar(1024) not null,
	sender		varchar(128) not null,
	receiver	varchar(128) not null,
	
	constraint fk_msg_ctt_1
		foreign key(sender)
		references contact(eid),
	constraint fk_msg_ctt_2
		foreign key(receiver)
		references contact(eid)
);

create table rfp (
	eid 		 	varchar(128) 	primary key,
	subscription_id	varchar(128) 	not null,
	cdate 		 	timestamp 		not null,
	start_date 	 	timestamp 		not null,
	closing_date 	timestamp 		not null,
	description  	varchar(1024) 	not null,
	doc_location 	varchar(128),
	
	constraint fk_rfp_ctt
		foreign key(subscription_id)
		references ssubscription(eid)
);

create table bid (
	eid 			varchar(128) primary key,
	subscription_id varchar(128) not null,
	rfp_id 			varchar(128) not null,
	submission_date timestamp 	 not null,
	doc_location 	varchar(128) not null,
	msg				varchar(1024),
	
	constraint fk_bid_ctt
		foreign key(subscription_id)
		references ssubscription(eid),
	constraint fk_bid_rfp
		foreign key(rfp_id)
		references rfp(eid)
);
