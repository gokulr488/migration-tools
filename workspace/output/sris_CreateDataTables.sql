/***************************************Dropping Tables***********************************************/ 

DROP TABLE IF EXISTS sris_t_mtr_item_mapping ; 

DROP TABLE IF EXISTS sris_t_mtr_item_group ; 

DROP TABLE IF EXISTS sris_t_mtr_item_fsp_fm ; 

DROP TABLE IF EXISTS sris_t_mtr_item_dispute_argument ; 

DROP TABLE IF EXISTS sris_t_mtr_user ; 

DROP TABLE IF EXISTS sris_t_mtr_user_group ; 

DROP TABLE IF EXISTS sris_t_mtr_item_dispute ; 

DROP TABLE IF EXISTS sris_t_mtr_partner ; 

DROP TABLE IF EXISTS sris_t_mtr_province ; 

DROP TABLE IF EXISTS sris_t_mtr_cluster ; 

DROP TABLE IF EXISTS sris_t_mtr_region ; 

DROP TABLE IF EXISTS sris_t_mtr_item_detail_discount ; 

DROP TABLE IF EXISTS sris_t_mtr_item_blacklist ; 

DROP TABLE IF EXISTS sris_t_mtr_client ; 
/*_________________________________________________Dropped____________________________________________________________*/ 
 
/***************************************Table : sris_t_mtr_client ***********************************************/ 

CREATE TABLE sris_t_mtr_client (
	client_id INT  NOT NULL,
	client_name VARCHAR(30)  NOT NULL,
PRIMARY KEY (client_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_item_blacklist ***********************************************/ 

CREATE TABLE sris_t_mtr_item_blacklist (
	item_mapping_id INT  NOT NULL,
	item_code VARCHAR(20)  NOT NULL,
	item_name VARCHAR(50)  NOT NULL,
	status INT DEFAULT 0,
PRIMARY KEY (item_mapping_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_item_detail_discount ***********************************************/ 

CREATE TABLE sris_t_mtr_item_detail_discount (
	plan_id INT  NOT NULL,
	item_code VARCHAR(10)  NOT NULL,
	status INT  NOT NULL,
	id INT  NOT NULL,
PRIMARY KEY (id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_region ***********************************************/ 

CREATE TABLE sris_t_mtr_region (
	region_id INT  NOT NULL,
	region_name VARCHAR(50)  NOT NULL,
	region_code VARCHAR(10)  NOT NULL,
	status INT DEFAULT 0 NOT NULL,
	cost_center VARCHAR(30) ,
PRIMARY KEY (region_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_cluster ***********************************************/ 

CREATE TABLE sris_t_mtr_cluster (
	cluster_id INT  NOT NULL,
	cluster_name VARCHAR(50)  NOT NULL,
	cluster_code VARCHAR(10)  NOT NULL,
	region_id INT  NOT NULL,
	status INT DEFAULT 0 NOT NULL,
	cluster_code_v2 VARCHAR(10) ,
	minimum_target INT DEFAULT 0 NOT NULL,
	bank_account_no VARCHAR(50) ,
	bank_account_name VARCHAR(50) ,
	npwp VARCHAR(30) ,
	npwp_name VARCHAR(50) ,
	npwp_city VARCHAR(50) ,
PRIMARY KEY (cluster_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_province ***********************************************/ 

CREATE TABLE sris_t_mtr_province (
	province_id INT  NOT NULL,
	province_name VARCHAR(50)  NOT NULL,
	status INT  NOT NULL,
PRIMARY KEY (province_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_partner ***********************************************/ 

CREATE TABLE sris_t_mtr_partner (
	partner_id INT  NOT NULL,
	partner_code VARCHAR(30)  NOT NULL,
	partner_name VARCHAR(100)  NOT NULL,
	status INT DEFAULT 0 NOT NULL,
	address VARCHAR(255) ,
	city VARCHAR(255) ,
	province_id INT ,
	image_path VARCHAR(255) ,
	lon VARCHAR(50) ,
	lat VARCHAR(50) ,
	region_id INT ,
	cluster_id INT  NOT NULL,
	eload_number LONGTEXT ,
	program_id INT DEFAULT 0 NOT NULL,
	have_pks VARCHAR(10) DEFAULT 'false' NOT NULL,
	distributor_name VARCHAR(100) ,
	mitra INT ,
	outlet_class VARCHAR(5) DEFAULT 'N',
	outlet_exclusive_class VARCHAR(35) DEFAULT 'Register',
PRIMARY KEY (partner_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_item_dispute ***********************************************/ 

CREATE TABLE sris_t_mtr_item_dispute (
	dispute_id BIGINT  NOT NULL,
	serial_iccid VARCHAR(50) ,
	mdn VARCHAR(30) ,
	subs_id VARCHAR(30) ,
	notification_sent INT DEFAULT 0 NOT NULL,
	reason_to_lock LONGTEXT  NOT NULL,
	partner_id_1 INT  NOT NULL,
	cluster_id_1 INT  NOT NULL,
	region_id_1 INT  NOT NULL,
	partner_id_2 INT  NOT NULL,
	cluster_id_2 INT  NOT NULL,
	region_id_2 INT  NOT NULL,
	status INT  NOT NULL,
	client_id INT  NOT NULL,
	released_to INT ,
	meid LONGTEXT ,
	d_channel_id LONGTEXT ,
PRIMARY KEY (dispute_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_user_group ***********************************************/ 

CREATE TABLE sris_t_mtr_user_group (
	user_group_id INT  NOT NULL,
	user_group_name VARCHAR(50)  NOT NULL,
	user_group_caption VARCHAR(10) ,
	parent_id INT DEFAULT 0 NOT NULL,
	status INT DEFAULT 0 NOT NULL,
	territory_type_id INT  NOT NULL,
	is_use_handset INT DEFAULT 0 NOT NULL,
	is_partner INT DEFAULT 0 NOT NULL,
	user_group_id_sts INT DEFAULT 0 NOT NULL,
	parent_id_sts INT DEFAULT 0 NOT NULL,
PRIMARY KEY (user_group_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_user ***********************************************/ 

CREATE TABLE sris_t_mtr_user (
	user_id VARCHAR(30)  NOT NULL,
	partner_id INT ,
	user_name VARCHAR(50)  NOT NULL,
	user_password VARCHAR(50)  NOT NULL,
	user_group_id INT  NOT NULL,
	must_change_password INT DEFAULT 0 NOT NULL,
	logon_attempt INT DEFAULT 0 NOT NULL,
	job_title VARCHAR(50) ,
	mobile_number VARCHAR(50) ,
	email VARCHAR(50) ,
	status INT DEFAULT 0 NOT NULL,
	is_allowed_time INT  NOT NULL,
	last_time_change_password DATE  NOT NULL,
	is_login INT DEFAULT 0 NOT NULL,
	user_picture VARCHAR(100) ,
	forgot_password INT ,
	show_eula INT ,
	st_quiz INT DEFAULT 0,
	quiz_date DATE DEFAULT (CURRENT_DATE),
	bank_account VARCHAR(30) ,
	bank_name VARCHAR(30) ,
	is_validated INT DEFAULT 0,
	last_login DATETIME ,
	last_logout DATETIME ,
	last_login_mob DATETIME ,
	last_logout_mob DATETIME ,
PRIMARY KEY (user_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_item_dispute_argument ***********************************************/ 

CREATE TABLE sris_t_mtr_item_dispute_argument (
	argument_id BIGINT  NOT NULL,
	dispute_id INT  NOT NULL,
	user_id LONGTEXT  NOT NULL,
	argument LONGTEXT  NOT NULL,
	client_id INT  NOT NULL,
	attachment LONGTEXT ,
	status INT  NOT NULL,
PRIMARY KEY (argument_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_item_fsp_fm ***********************************************/ 

CREATE TABLE sris_t_mtr_item_fsp_fm (
	mdn VARCHAR(20) ,
	subs_id VARCHAR(30) ,
	activation_date DATETIME ,
	item_code LONGTEXT ,
	d_channel_id LONGTEXT ,
	region_id INT ,
	cluster_id INT ,
	flag_insentif INT ,
	topup_date_eload_mfino DATETIME ,
	reff_id_eload_mfino VARCHAR(14) ,
	id INT  NOT NULL,
PRIMARY KEY (id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_item_group ***********************************************/ 

CREATE TABLE sris_t_mtr_item_group (
	group_id INT  NOT NULL,
	group_name VARCHAR(50)  NOT NULL,
	status INT  NOT NULL,
PRIMARY KEY (group_id)); 
/*_________________________________________________END____________________________________________________________*/ 

/***************************************Table : sris_t_mtr_item_mapping ***********************************************/ 

CREATE TABLE sris_t_mtr_item_mapping (
	item_mapping_id INT  NOT NULL,
	item_code VARCHAR(20)  NOT NULL,
	item_group_id INT DEFAULT 1 NOT NULL,
	item_name VARCHAR(50)  NOT NULL,
	default_price INT DEFAULT 0,
	item_category_id INT DEFAULT 0,
	dbr_id INT DEFAULT 0,
PRIMARY KEY (item_mapping_id)); 
/*_________________________________________________END____________________________________________________________*/ 

