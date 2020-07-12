
/****************** Table : track_t_mtr_distributor_mgr    View : view_t_mtr_distributor_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_distributor_mgr;

DROP TABLE IF EXISTS track_t_mtr_distributor_mgr;


CREATE TABLE track_t_mtr_distributor_mgr (
	track_distributor_id serial);

CREATE VIEW view_t_mtr_distributor_mgr AS
SELECT *
FROM t_mtr_distributor
WHERE t_mtr_distributor.distributor_id NOT IN(SELECT track_t_mtr_distributor_mgr.track_distributor_id FROM track_t_mtr_distributor_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_item_mgr    View : view_t_mtr_item_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_item_mgr;

DROP TABLE IF EXISTS track_t_mtr_item_mgr;


CREATE TABLE track_t_mtr_item_mgr (
	track_item_id serial);

CREATE VIEW view_t_mtr_item_mgr AS
SELECT *
FROM t_mtr_item
WHERE t_mtr_item.item_id NOT IN(SELECT track_t_mtr_item_mgr.track_item_id FROM track_t_mtr_item_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_item_category_mgr    View : view_t_mtr_item_category_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_item_category_mgr;

DROP TABLE IF EXISTS track_t_mtr_item_category_mgr;


CREATE TABLE track_t_mtr_item_category_mgr (
	track_dbr_id serial);

CREATE VIEW view_t_mtr_item_category_mgr AS
SELECT *
FROM t_mtr_item_category
WHERE t_mtr_item_category.dbr_id NOT IN(SELECT track_t_mtr_item_category_mgr.track_dbr_id FROM track_t_mtr_item_category_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_territory_type_mgr    View : view_t_mtr_territory_type_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_territory_type_mgr;

DROP TABLE IF EXISTS track_t_mtr_territory_type_mgr;


CREATE TABLE track_t_mtr_territory_type_mgr (
	track_territory_type_id serial);

CREATE VIEW view_t_mtr_territory_type_mgr AS
SELECT *
FROM t_mtr_territory_type
WHERE t_mtr_territory_type.territory_type_id NOT IN(SELECT track_t_mtr_territory_type_mgr.track_territory_type_id FROM track_t_mtr_territory_type_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_territory_mgr    View : view_t_mtr_territory_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_territory_mgr;

DROP TABLE IF EXISTS track_t_mtr_territory_mgr;


CREATE TABLE track_t_mtr_territory_mgr (
	track_territory_id serial);

CREATE VIEW view_t_mtr_territory_mgr AS
SELECT *
FROM t_mtr_territory
WHERE t_mtr_territory.territory_id NOT IN(SELECT track_t_mtr_territory_mgr.track_territory_id FROM track_t_mtr_territory_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_warehouse_mgr    View : view_t_mtr_warehouse_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_warehouse_mgr;

DROP TABLE IF EXISTS track_t_mtr_warehouse_mgr;


CREATE TABLE track_t_mtr_warehouse_mgr (
	track_warehouse_id serial);

CREATE VIEW view_t_mtr_warehouse_mgr AS
SELECT *
FROM t_mtr_warehouse
WHERE t_mtr_warehouse.warehouse_id NOT IN(SELECT track_t_mtr_warehouse_mgr.track_warehouse_id FROM track_t_mtr_warehouse_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_item_extra_mgr    View : view_t_mtr_item_extra_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_item_extra_mgr;

DROP TABLE IF EXISTS track_t_mtr_item_extra_mgr;


CREATE TABLE track_t_mtr_item_extra_mgr (
	track_item_id int4);

CREATE VIEW view_t_mtr_item_extra_mgr AS
SELECT *
FROM t_mtr_item_extra
WHERE t_mtr_item_extra.item_id NOT IN(SELECT track_t_mtr_item_extra_mgr.track_item_id FROM track_t_mtr_item_extra_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_item_group_mgr    View : view_t_mtr_item_group_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_item_group_mgr;

DROP TABLE IF EXISTS track_t_mtr_item_group_mgr;


CREATE TABLE track_t_mtr_item_group_mgr (
	track_item_group_id serial);

CREATE VIEW view_t_mtr_item_group_mgr AS
SELECT *
FROM t_mtr_item_group
WHERE t_mtr_item_group.item_group_id NOT IN(SELECT track_t_mtr_item_group_mgr.track_item_group_id FROM track_t_mtr_item_group_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_item_package_mgr    View : view_t_mtr_item_package_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_item_package_mgr;

DROP TABLE IF EXISTS track_t_mtr_item_package_mgr;


CREATE TABLE track_t_mtr_item_package_mgr (
	track_item_id int4);

CREATE VIEW view_t_mtr_item_package_mgr AS
SELECT *
FROM t_mtr_item_package
WHERE t_mtr_item_package.item_id NOT IN(SELECT track_t_mtr_item_package_mgr.track_item_id FROM track_t_mtr_item_package_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_item_paket_mgr    View : view_t_mtr_item_paket_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_item_paket_mgr;

DROP TABLE IF EXISTS track_t_mtr_item_paket_mgr;


CREATE TABLE track_t_mtr_item_paket_mgr (
	track_detail_paket_id int4);

CREATE VIEW view_t_mtr_item_paket_mgr AS
SELECT *
FROM t_mtr_item_paket
WHERE t_mtr_item_paket.detail_paket_id NOT IN(SELECT track_t_mtr_item_paket_mgr.track_detail_paket_id FROM track_t_mtr_item_paket_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_user_group_mgr    View : view_t_mtr_user_group_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_user_group_mgr;

DROP TABLE IF EXISTS track_t_mtr_user_group_mgr;


CREATE TABLE track_t_mtr_user_group_mgr (
	track_user_group_id serial);

CREATE VIEW view_t_mtr_user_group_mgr AS
SELECT *
FROM t_mtr_user_group
WHERE t_mtr_user_group.user_group_id NOT IN(SELECT track_t_mtr_user_group_mgr.track_user_group_id FROM track_t_mtr_user_group_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_user_mgr    View : view_t_mtr_user_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_user_mgr;

DROP TABLE IF EXISTS track_t_mtr_user_mgr;


CREATE TABLE track_t_mtr_user_mgr (
	track_user_id varchar);

CREATE VIEW view_t_mtr_user_mgr AS
SELECT *
FROM t_mtr_user
WHERE t_mtr_user.user_id NOT IN(SELECT track_t_mtr_user_mgr.track_user_id FROM track_t_mtr_user_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_handset_mgr    View : view_t_mtr_handset_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_handset_mgr;

DROP TABLE IF EXISTS track_t_mtr_handset_mgr;


CREATE TABLE track_t_mtr_handset_mgr (
	track_handset_id serial);

CREATE VIEW view_t_mtr_handset_mgr AS
SELECT *
FROM t_mtr_handset
WHERE t_mtr_handset.handset_id NOT IN(SELECT track_t_mtr_handset_mgr.track_handset_id FROM track_t_mtr_handset_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_grid_outlet_mgr    View : view_t_grid_outlet_mgr ******************/

DROP VIEW IF EXISTS view_t_grid_outlet_mgr;

DROP TABLE IF EXISTS track_t_grid_outlet_mgr;


CREATE TABLE track_t_grid_outlet_mgr (
	track_id serial);

CREATE VIEW view_t_grid_outlet_mgr AS
SELECT *
FROM t_grid_outlet
WHERE t_grid_outlet.id NOT IN(SELECT track_t_grid_outlet_mgr.track_id FROM track_t_grid_outlet_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_distributor_type_mgr    View : view_t_mtr_distributor_type_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_distributor_type_mgr;

DROP TABLE IF EXISTS track_t_mtr_distributor_type_mgr;


CREATE TABLE track_t_mtr_distributor_type_mgr (
	track_id_seq int4);

CREATE VIEW view_t_mtr_distributor_type_mgr AS
SELECT *
FROM t_mtr_distributor_type
WHERE t_mtr_distributor_type.id_seq NOT IN(SELECT track_t_mtr_distributor_type_mgr.track_id_seq FROM track_t_mtr_distributor_type_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_outlet_mgr    View : view_t_mtr_outlet_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_outlet_mgr;

DROP TABLE IF EXISTS track_t_mtr_outlet_mgr;


CREATE TABLE track_t_mtr_outlet_mgr (
	track_outlet_id varchar);

CREATE VIEW view_t_mtr_outlet_mgr AS
SELECT *
FROM t_mtr_outlet
WHERE t_mtr_outlet.outlet_id NOT IN(SELECT track_t_mtr_outlet_mgr.track_outlet_id FROM track_t_mtr_outlet_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_request_change_mdn_mgr    View : view_t_mtr_request_change_mdn_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_request_change_mdn_mgr;

DROP TABLE IF EXISTS track_t_mtr_request_change_mdn_mgr;


CREATE TABLE track_t_mtr_request_change_mdn_mgr (
	track_id_change_mdn serial);

CREATE VIEW view_t_mtr_request_change_mdn_mgr AS
SELECT *
FROM t_mtr_request_change_mdn
WHERE t_mtr_request_change_mdn.id_change_mdn NOT IN(SELECT track_t_mtr_request_change_mdn_mgr.track_id_change_mdn FROM track_t_mtr_request_change_mdn_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_mtr_sell_event_mgr    View : view_t_mtr_sell_event_mgr ******************/

DROP VIEW IF EXISTS view_t_mtr_sell_event_mgr;

DROP TABLE IF EXISTS track_t_mtr_sell_event_mgr;


CREATE TABLE track_t_mtr_sell_event_mgr (
	track_sell_event_id serial);

CREATE VIEW view_t_mtr_sell_event_mgr AS
SELECT *
FROM t_mtr_sell_event
WHERE t_mtr_sell_event.sell_event_id NOT IN(SELECT track_t_mtr_sell_event_mgr.track_sell_event_id FROM track_t_mtr_sell_event_mgr);

/*_________________________________________________END____________________________________________________________*/ 

/****************** Table : track_t_trx_sales_order_mgr    View : view_t_trx_sales_order_mgr ******************/

DROP VIEW IF EXISTS view_t_trx_sales_order_mgr;

DROP TABLE IF EXISTS track_t_trx_sales_order_mgr;


CREATE TABLE track_t_trx_sales_order_mgr (
	track_so_id bigserial);

CREATE VIEW view_t_trx_sales_order_mgr AS
SELECT *
FROM t_trx_sales_order
WHERE t_trx_sales_order.so_id NOT IN(SELECT track_t_trx_sales_order_mgr.track_so_id FROM track_t_trx_sales_order_mgr);

/*_________________________________________________END____________________________________________________________*/ 
