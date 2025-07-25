--liquibase formatted sql
--changeset accesscontrol-forms:create_db_accesscontrol-forms.sql
--preconditions onFail:MARK_RAN onError:WARN
DROP TABLE IF EXISTS accesscontrol_forms_controller_entrytype_datahandler_config;
CREATE TABLE accesscontrol_forms_controller_entrytype_datahandler_config (
	id_access_controller int,
	code_question varchar(100),
	PRIMARY KEY( id_access_controller )
);
