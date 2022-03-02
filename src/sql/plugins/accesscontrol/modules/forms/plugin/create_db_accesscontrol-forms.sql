DROP TABLE IF EXISTS accesscontrol_forms_controller_entrytype_datahandler_config;
CREATE TABLE accesscontrol_forms_controller_entrytype_datahandler_config (
	id_access_controller int,
	code_question varchar(100),
	PRIMARY KEY( id_access_controller )
);

DROP TABLE IF EXISTS accesscontrol_forms_controller_entrytype;
CREATE TABLE accesscontrol_forms_controller_entrytype (
	id_entry_type int AUTO_INCREMENT,
	code_question varchar(100),
	id_access_controller_multiple varchar(100),
	PRIMARY KEY( id_entry_type )
);

DROP TABLE IF EXISTS ac_forms_controller_entrytype_datahandler_multiple_config;
CREATE TABLE ac_forms_controller_entrytype_datahandler_multiple_config (
	id_access_controller_multiple int,
	PRIMARY KEY( id_access_controller_multiple )
);