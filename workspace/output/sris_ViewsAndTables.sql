
/****************** Table : track_sris_t_mtr_client    View : view_sris_t_mtr_client ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_client;

DROP TABLE IF EXISTS track_sris_t_mtr_client;


CREATE TABLE track_sris_t_mtr_client (
	track_client_id INT);

CREATE VIEW view_sris_t_mtr_client AS
SELECT *
FROM sris_t_mtr_client
WHERE sris_t_mtr_client.client_id NOT IN(SELECT track_sris_t_mtr_client.track_client_id FROM track_sris_t_mtr_client);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_item_blacklist    View : view_sris_t_mtr_item_blacklist ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_item_blacklist;

DROP TABLE IF EXISTS track_sris_t_mtr_item_blacklist;


CREATE TABLE track_sris_t_mtr_item_blacklist (
	track_item_mapping_id INT);

CREATE VIEW view_sris_t_mtr_item_blacklist AS
SELECT *
FROM sris_t_mtr_item_blacklist
WHERE sris_t_mtr_item_blacklist.item_mapping_id NOT IN(SELECT track_sris_t_mtr_item_blacklist.track_item_mapping_id FROM track_sris_t_mtr_item_blacklist);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_item_detail_discount    View : view_sris_t_mtr_item_detail_discount ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_item_detail_discount;

DROP TABLE IF EXISTS track_sris_t_mtr_item_detail_discount;


CREATE TABLE track_sris_t_mtr_item_detail_discount (
	track_id INT);

CREATE VIEW view_sris_t_mtr_item_detail_discount AS
SELECT *
FROM sris_t_mtr_item_detail_discount
WHERE sris_t_mtr_item_detail_discount.id NOT IN(SELECT track_sris_t_mtr_item_detail_discount.track_id FROM track_sris_t_mtr_item_detail_discount);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_region    View : view_sris_t_mtr_region ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_region;

DROP TABLE IF EXISTS track_sris_t_mtr_region;


CREATE TABLE track_sris_t_mtr_region (
	track_region_id INT);

CREATE VIEW view_sris_t_mtr_region AS
SELECT *
FROM sris_t_mtr_region
WHERE sris_t_mtr_region.region_id NOT IN(SELECT track_sris_t_mtr_region.track_region_id FROM track_sris_t_mtr_region);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_cluster    View : view_sris_t_mtr_cluster ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_cluster;

DROP TABLE IF EXISTS track_sris_t_mtr_cluster;


CREATE TABLE track_sris_t_mtr_cluster (
	track_cluster_id INT);

CREATE VIEW view_sris_t_mtr_cluster AS
SELECT *
FROM sris_t_mtr_cluster
WHERE sris_t_mtr_cluster.cluster_id NOT IN(SELECT track_sris_t_mtr_cluster.track_cluster_id FROM track_sris_t_mtr_cluster);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_province    View : view_sris_t_mtr_province ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_province;

DROP TABLE IF EXISTS track_sris_t_mtr_province;


CREATE TABLE track_sris_t_mtr_province (
	track_province_id INT);

CREATE VIEW view_sris_t_mtr_province AS
SELECT *
FROM sris_t_mtr_province
WHERE sris_t_mtr_province.province_id NOT IN(SELECT track_sris_t_mtr_province.track_province_id FROM track_sris_t_mtr_province);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_partner    View : view_sris_t_mtr_partner ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_partner;

DROP TABLE IF EXISTS track_sris_t_mtr_partner;


CREATE TABLE track_sris_t_mtr_partner (
	track_partner_id INT);

CREATE VIEW view_sris_t_mtr_partner AS
SELECT *
FROM sris_t_mtr_partner
WHERE sris_t_mtr_partner.partner_id NOT IN(SELECT track_sris_t_mtr_partner.track_partner_id FROM track_sris_t_mtr_partner);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_item_dispute    View : view_sris_t_mtr_item_dispute ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_item_dispute;

DROP TABLE IF EXISTS track_sris_t_mtr_item_dispute;


CREATE TABLE track_sris_t_mtr_item_dispute (
	track_dispute_id BIGINT);

CREATE VIEW view_sris_t_mtr_item_dispute AS
SELECT *
FROM sris_t_mtr_item_dispute
WHERE sris_t_mtr_item_dispute.dispute_id NOT IN(SELECT track_sris_t_mtr_item_dispute.track_dispute_id FROM track_sris_t_mtr_item_dispute);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_user_group    View : view_sris_t_mtr_user_group ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_user_group;

DROP TABLE IF EXISTS track_sris_t_mtr_user_group;


CREATE TABLE track_sris_t_mtr_user_group (
	track_user_group_id INT);

CREATE VIEW view_sris_t_mtr_user_group AS
SELECT *
FROM sris_t_mtr_user_group
WHERE sris_t_mtr_user_group.user_group_id NOT IN(SELECT track_sris_t_mtr_user_group.track_user_group_id FROM track_sris_t_mtr_user_group);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_user    View : view_sris_t_mtr_user ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_user;

DROP TABLE IF EXISTS track_sris_t_mtr_user;


CREATE TABLE track_sris_t_mtr_user (
	track_user_id VARCHAR);

CREATE VIEW view_sris_t_mtr_user AS
SELECT *
FROM sris_t_mtr_user
WHERE sris_t_mtr_user.user_id NOT IN(SELECT track_sris_t_mtr_user.track_user_id FROM track_sris_t_mtr_user);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_item_dispute_argument    View : view_sris_t_mtr_item_dispute_argument ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_item_dispute_argument;

DROP TABLE IF EXISTS track_sris_t_mtr_item_dispute_argument;


CREATE TABLE track_sris_t_mtr_item_dispute_argument (
	track_argument_id BIGINT);

CREATE VIEW view_sris_t_mtr_item_dispute_argument AS
SELECT *
FROM sris_t_mtr_item_dispute_argument
WHERE sris_t_mtr_item_dispute_argument.argument_id NOT IN(SELECT track_sris_t_mtr_item_dispute_argument.track_argument_id FROM track_sris_t_mtr_item_dispute_argument);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_item_fsp_fm    View : view_sris_t_mtr_item_fsp_fm ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_item_fsp_fm;

DROP TABLE IF EXISTS track_sris_t_mtr_item_fsp_fm;


CREATE TABLE track_sris_t_mtr_item_fsp_fm (
	track_id INT);

CREATE VIEW view_sris_t_mtr_item_fsp_fm AS
SELECT *
FROM sris_t_mtr_item_fsp_fm
WHERE sris_t_mtr_item_fsp_fm.id NOT IN(SELECT track_sris_t_mtr_item_fsp_fm.track_id FROM track_sris_t_mtr_item_fsp_fm);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_item_group    View : view_sris_t_mtr_item_group ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_item_group;

DROP TABLE IF EXISTS track_sris_t_mtr_item_group;


CREATE TABLE track_sris_t_mtr_item_group (
	track_group_id INT);

CREATE VIEW view_sris_t_mtr_item_group AS
SELECT *
FROM sris_t_mtr_item_group
WHERE sris_t_mtr_item_group.group_id NOT IN(SELECT track_sris_t_mtr_item_group.track_group_id FROM track_sris_t_mtr_item_group);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_sris_t_mtr_item_mapping    View : view_sris_t_mtr_item_mapping ******************/

DROP VIEW IF EXISTS view_sris_t_mtr_item_mapping;

DROP TABLE IF EXISTS track_sris_t_mtr_item_mapping;


CREATE TABLE track_sris_t_mtr_item_mapping (
	track_item_mapping_id INT);

CREATE VIEW view_sris_t_mtr_item_mapping AS
SELECT *
FROM sris_t_mtr_item_mapping
WHERE sris_t_mtr_item_mapping.item_mapping_id NOT IN(SELECT track_sris_t_mtr_item_mapping.track_item_mapping_id FROM track_sris_t_mtr_item_mapping);

/*_________________________________________________END____________________________________________________________*/ 
