/***************************************Dropping Tables***********************************************/ 

DROP TABLE IF EXISTS t_trx_sales_order ; 

DROP TABLE IF EXISTS t_mtr_sell_event ; 

DROP TABLE IF EXISTS t_mtr_request_change_mdn ; 

DROP TABLE IF EXISTS t_mtr_outlet ; 

DROP TABLE IF EXISTS t_mtr_distributor_type ; 

DROP TABLE IF EXISTS t_grid_outlet ; 

DROP TABLE IF EXISTS t_mtr_handset ; 

DROP TABLE IF EXISTS t_mtr_user ; 

DROP TABLE IF EXISTS t_mtr_user_group ; 

DROP TABLE IF EXISTS t_mtr_item_paket ; 

DROP TABLE IF EXISTS t_mtr_item_package ; 

DROP TABLE IF EXISTS t_mtr_item_group ; 

DROP TABLE IF EXISTS t_mtr_item_extra ; 

DROP TABLE IF EXISTS t_mtr_warehouse ; 

DROP TABLE IF EXISTS t_mtr_territory ; 

DROP TABLE IF EXISTS t_mtr_territory_type ; 

DROP TABLE IF EXISTS t_mtr_item_category ; 

DROP TABLE IF EXISTS t_mtr_item ; 

DROP TABLE IF EXISTS t_mtr_distributor ; 
/*_________________________________________________Dropped____________________________________________________________*/ 
 
/***************************************Table : t_mtr_distributor ***********************************************/ 

CREATE TABLE t_mtr_distributor (
	distributor_id INT  NOT NULL,
	original_distributor_code VARCHAR(25)  NOT NULL,
	original_regional_name VARCHAR(30)  NOT NULL,
	distributor_name VARCHAR(100)  NOT NULL,
	distributor_address VARCHAR(255) ,
	istatus INT DEFAULT 1 NOT NULL,
	original_cluster_id VARCHAR(100)  NOT NULL,
	territory_id INT DEFAULT 1 NOT NULL,
	distributor_type INT ,
PRIMARY KEY (distributor_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_item ***********************************************/ 

CREATE TABLE t_mtr_item (
	item_id INT  NOT NULL,
	item_name VARCHAR(255) ,
	item_group_id INT DEFAULT 1,
	iccid VARCHAR(25)  NOT NULL,
	mdn VARCHAR(20)  NOT NULL,
	item_code VARCHAR(20) ,
	reseller_price DECIMAL DEFAULT 0,
	default_price DECIMAL DEFAULT 0,
	original_distributor_code VARCHAR(25) DEFAULT '-',
	original_ship_to_code CHAR DEFAULT '-',
	istatus INT DEFAULT 1 NOT NULL,
	shipment_date DATE ,
	item_mapping_id INT  NOT NULL,
	distributor_id INT DEFAULT 1 NOT NULL,
	dwh_activated INT DEFAULT 0,
	do_number VARCHAR(50) ,
	dwh_termination INT DEFAULT 0,
	termination_date DATE ,
	activation_date DATETIME ,
	subs_id VARCHAR(30) ,
	meid VARCHAR(25) ,
	d_channel_id INT ,
	sub_channel_id INT ,
	activation_date_mtrass DATETIME ,
PRIMARY KEY (item_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_item_category ***********************************************/ 

CREATE TABLE t_mtr_item_category (
	dbr_id INT  NOT NULL,
	dbr_name VARCHAR(50)  NOT NULL,
	istatus INT DEFAULT 1,
PRIMARY KEY (dbr_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_territory_type ***********************************************/ 

CREATE TABLE t_mtr_territory_type (
	territory_type_id INT  NOT NULL,
	territory_type_name VARCHAR(255)  NOT NULL,
	use_territory_code INT  NOT NULL,
	istatus INT DEFAULT 1 NOT NULL,
	territory_type_icon VARCHAR(255) ,
	parent_id INT ,
	is_use_top INT DEFAULT 0,
PRIMARY KEY (territory_type_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_territory ***********************************************/ 

CREATE TABLE t_mtr_territory (
	territory_id INT  NOT NULL,
	territory_name VARCHAR(255)  NOT NULL,
	parent_id INT  NOT NULL,
	user_id VARCHAR(30) ,
	territory_type_id INT  NOT NULL,
	istatus INT DEFAULT 1 NOT NULL,
	ordering INT ,
	fill_color VARCHAR(8) ,
	border_color VARCHAR(8) ,
	tms_guid VARCHAR(40) ,
PRIMARY KEY (territory_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_warehouse ***********************************************/ 

CREATE TABLE t_mtr_warehouse (
	warehouse_id INT  NOT NULL,
	warehouse_name VARCHAR(50)  NOT NULL,
	region_id INT ,
	cluster_id INT ,
	status INT  NOT NULL,
	address LONGTEXT ,
PRIMARY KEY (warehouse_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_item_extra ***********************************************/ 

CREATE TABLE t_mtr_item_extra (
	item_id INT  NOT NULL,
	box_no VARCHAR(50) ,
	do_number VARCHAR(50) ,
	warehouse_id INT ,
	po_no VARCHAR(50) ,
PRIMARY KEY (item_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_item_group ***********************************************/ 

CREATE TABLE t_mtr_item_group (
	item_group_id INT  NOT NULL,
	item_group_name VARCHAR(50)  NOT NULL,
	istatus INT DEFAULT 1,
PRIMARY KEY (item_group_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_item_package ***********************************************/ 

CREATE TABLE t_mtr_item_package (
	item_id INT  NOT NULL,
	package_no VARCHAR(50)  NOT NULL,
PRIMARY KEY (item_id,package_no)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_item_paket ***********************************************/ 

CREATE TABLE t_mtr_item_paket (
	detail_paket_id INT  NOT NULL,
	item_mapping_id INT  NOT NULL,
	paket_id INT  NOT NULL,
	status INT  NOT NULL,
PRIMARY KEY (detail_paket_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_user_group ***********************************************/ 

CREATE TABLE t_mtr_user_group (
	user_group_id INT  NOT NULL,
	user_group_name VARCHAR(100)  NOT NULL,
	parent_id INT ,
	istatus INT DEFAULT 1 NOT NULL,
	having_stock INT DEFAULT 0 NOT NULL,
	territory_type_id INT DEFAULT 0 NOT NULL,
	user_group_caption VARCHAR(30) ,
	is_use_top INT DEFAULT 0,
	is_use_handset INT DEFAULT 0,
	is_use_credit_limit INT DEFAULT 0,
	is_self_service INT DEFAULT 0,
	allow_scan_in INT DEFAULT 0,
	allow_sell_in INT ,
	allow_mobsell INT ,
	allow_consignment INT ,
	allow_sell_out INT ,
	allow_to_sris INT DEFAULT 0,
	having_jc INT DEFAULT 0,
PRIMARY KEY (user_group_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_user ***********************************************/ 

CREATE TABLE t_mtr_user (
	user_id VARCHAR(30)  NOT NULL,
	nik VARCHAR(30)  NOT NULL,
	user_name VARCHAR(255)  NOT NULL,
	user_password VARCHAR(255)  NOT NULL,
	user_group_id INT  NOT NULL,
	must_change_password INT DEFAULT 0 NOT NULL,
	logon_attempt INT DEFAULT 0 NOT NULL,
	job_title VARCHAR(50) ,
	reporting_to VARCHAR(30) ,
	mobile_number VARCHAR(20) DEFAULT '',
	email VARCHAR(50) DEFAULT '',
	istatus INT DEFAULT 1 NOT NULL,
	is_allowed_time INT DEFAULT 1 NOT NULL,
	last_time_change_password DATE DEFAULT (CURRENT_DATE) NOT NULL,
	is_login INT DEFAULT 0 NOT NULL,
	region VARCHAR(50) ,
	cluster VARCHAR(50) ,
	last_time_change DATETIME ,
	must_change_pwd INT ,
	join_date VARCHAR(30) ,
	resign_date VARCHAR(30) ,
	mc_login INT ,
	bank_account VARCHAR(30) ,
	bank_name VARCHAR(30) ,
	bank_account_name VARCHAR(255) DEFAULT NULL,
	bank_code VARCHAR(8) DEFAULT NULL,
	last_login DATETIME ,
	last_logout DATETIME ,
	last_login_mob DATETIME ,
	last_logout_mob DATETIME ,
PRIMARY KEY (user_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_handset ***********************************************/ 

CREATE TABLE t_mtr_handset (
	handset_id INT  NOT NULL,
	handset_model VARCHAR(50)  NOT NULL,
	handset_man VARCHAR(50)  NOT NULL,
	user_id VARCHAR(30)  NOT NULL,
	imei VARCHAR(50)  NOT NULL,
	imsi VARCHAR(50)  NOT NULL,
	msisdn VARCHAR(50)  NOT NULL,
	istatus INT DEFAULT 1 NOT NULL,
	android_id VARCHAR(32) ,
PRIMARY KEY (handset_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_grid_outlet ***********************************************/ 

CREATE TABLE t_grid_outlet (
	user_id VARCHAR(30) ,
	outlet_id VARCHAR(20) ,
	outlet_name VARCHAR(255) ,
	address VARCHAR(255) ,
	image_path VARCHAR(100) ,
	is_valid INT ,
	istatus INT ,
	lon VARCHAR(20) ,
	lat VARCHAR(20) ,
	id INT  NOT NULL,
PRIMARY KEY (id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_distributor_type ***********************************************/ 

CREATE TABLE t_mtr_distributor_type (
	id_seq INT  NOT NULL,
	distributor_type VARCHAR(50) ,
	description LONGTEXT ,
	istatus INT ,
PRIMARY KEY (id_seq)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_outlet ***********************************************/ 

CREATE TABLE t_mtr_outlet (
	outlet_id VARCHAR(20)  NOT NULL,
	territory_id INT  NOT NULL,
	outlet_name VARCHAR(255)  NOT NULL,
	address VARCHAR(255)  NOT NULL,
	city VARCHAR(100) DEFAULT '-' NOT NULL,
	province_id INT ,
	post_code VARCHAR(15) ,
	phone VARCHAR(50) ,
	eload_number VARCHAR(25) ,
	business_structure VARCHAR(50) ,
	image_path VARCHAR(255) ,
	lon VARCHAR(50) ,
	lat VARCHAR(50) ,
	istatus INT DEFAULT 1 NOT NULL,
	outbond_caller_id VARCHAR(50) DEFAULT 'N/A',
	tms_guid VARCHAR(40) ,
	provinsi_id VARCHAR(10) ,
	kota_id VARCHAR(10) ,
	kabupaten_id VARCHAR(10) ,
	kecamatan_id VARCHAR(10) ,
	outlet_parent_code VARCHAR(50) ,
	kelurahan_id VARCHAR(10) ,
	outlet_class VARCHAR(5) DEFAULT 'N',
	outlet_exclusive_class VARCHAR(35) DEFAULT 'Register',
PRIMARY KEY (outlet_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_request_change_mdn ***********************************************/ 

CREATE TABLE t_mtr_request_change_mdn (
	id_change_mdn INT  NOT NULL,
	request_from VARCHAR(15)  NOT NULL,
	outlet_id VARCHAR(32) ,
	mdn_before VARCHAR(25) ,
	mdn_new VARCHAR(25) ,
	status INT DEFAULT 0,
	notes VARCHAR(500) ,
PRIMARY KEY (id_change_mdn)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_mtr_sell_event ***********************************************/ 

CREATE TABLE t_mtr_sell_event (
	sell_event_id INT  NOT NULL,
	sell_event_name VARCHAR(100)  NOT NULL,
	istatus INT DEFAULT 1 NOT NULL,
PRIMARY KEY (sell_event_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : t_trx_sales_order ***********************************************/ 

CREATE TABLE t_trx_sales_order (
	so_id BIGINT  NOT NULL,
	territory_id INT  NOT NULL,
	so_date DATE  NOT NULL,
	sales_id VARCHAR(30)  NOT NULL,
	payment_method VARCHAR(30) ,
	discount DECIMAL ,
	cash_paid DECIMAL ,
	remark VARCHAR(1000) ,
	cluster_id INT ,
	region_id INT ,
	user_group_id INT ,
PRIMARY KEY (so_id)); 
/*_________________________________________________END____________________________________________________________*/ 

