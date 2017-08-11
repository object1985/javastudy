--コメント
  /* コメント */


/* ユーザーテーブル */
CREATE TABLE IF NOT EXISTS ap_user
(
  user_id character varying(12) NOT NULL,
  name character varying(256) NOT NULL,
  lock_flg character(1) NOT NULL DEFAULT '0',
  valid_from date,
  valid_to date,
  unit_id character varying(3) NOT NULL,
  role_id character varying(2) NOT NULL,
  version timestamp,
  created_at timestamp,
  created_by character varying(12),
  updated_at timestamp,
  updated_by character varying(12),
  PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS uniform
(
  id bigint auto_increment,
  uniform_name character varying(256) NOT NULL,
  uniform_kind character varying(32) NOT NULL,
  u_date date,
  u_time time ,
  u_timestamp timestamp,
  u_localdate date,
  u_localtime time ,
  u_localdatetime timestamp,
  u_float number(1,1),
  u_double number(1,1),
  u_bigDecimal number(1,1),
  price integer NOT NULL,
  PRIMARY KEY (id)
);

CREATE SCHEMA IF NOT EXISTS other;
CREATE TABLE IF NOT EXISTS other.ap_user2
(
  user_id character varying(12) NOT NULL,
  name character varying(256) NOT NULL,
  lock_flg character(1) NOT NULL DEFAULT '0',
  valid_from date,
  valid_to date,
  unit_id character varying(3) NOT NULL,
  role_id character varying(2) NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS type_model
(
  string varchar(256),
  boolean1 char(1),
  byte1 char(1),
  byte2 char(1),
  short1 smallint,
  int1 integer,
  long1 bigint,
  float1 number(1,1),
  double1 number(1,1),
  bigInteger1 bigint,
  big_decimal number(1,1),
  clob1 clob,
  blob1 blob,
  enum1 char(1),
  date1 date,
  time1 time,
  timestamp1 timestamp
);

CREATE TABLE IF NOT EXISTS generated_id_table
(
  id bigint NOT NULL auto_increment,
  name character varying(32) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS numeric_pattern
(
  id numeric(10) NOT NULL,
  num numeric(9) NOT NULL,
  dec numeric(9,1),
  updated_at timestamp,
  PRIMARY KEY (id, num)
);

INSERT INTO type_model values('もじ','1','1','1',1,2,3,0.1,0.2,0.3,0.4,'hogehogehogeohgeohgeohgeoh','1013','1','2015-07-01','12:13:14','2015-07-01 12:13:14.555');
INSERT INTO type_model values('もじ2','2','2','2',10,20,30,0.01,0.02,0.03,0.04,'fugafugafugafugafu','1014','2','2015-07-02','12:13:15','2015-07-02 12:13:14.555');

INSERT INTO ap_user (user_id,name,lock_flg,valid_from,valid_to,unit_id,role_id) VALUES ('A1','ゆーざー1','1','2015-01-10',null,'U1','R1');
INSERT INTO ap_user (user_id,name,lock_flg,valid_from,valid_to,unit_id,role_id) VALUES ('A2','ゆーざー2','0',null,'2015-01-10','U1','R1');
INSERT INTO ap_user (user_id,name,lock_flg,valid_from,valid_to,unit_id,role_id) VALUES ('A3','ゆーざー3','1',null,'2015-01-10','U1','R1');
INSERT INTO ap_user (user_id,name,lock_flg,valid_from,valid_to,unit_id,role_id) VALUES ('A4','ゆーざー4','0','2015-01-10','2015-01-10','U2','R2');
INSERT INTO ap_user (user_id,name,lock_flg,valid_from,valid_to,unit_id,role_id) VALUES ('A5','ゆーざー5','1','2015-01-10','2015-01-10','U2','R2');

INSERT INTO uniform (uniform_name,uniform_kind,price,u_date,u_time,u_timestamp,u_localdate,u_localtime,u_localdatetime,u_float,u_double,u_bigDecimal) VALUES ('ダウン','2',5000,'2016-01-02','11:11:11','2016-01-01 11:11:11.111','2016-01-02','11:11:11','2016-01-01 11:11:11.111',0.3,0.4,0.8);
INSERT INTO uniform (uniform_name,uniform_kind,price,u_date,u_time,u_timestamp,u_localdate,u_localtime,u_localdatetime,u_float,u_double,u_bigDecimal) VALUES ('ポロシャツ','3',1500,'2016-01-02','11:11:11','2016-01-01 11:11:11.111','2016-01-02','11:11:11','2016-01-01 11:11:11.111',0.3,0.4,0.8);
INSERT INTO uniform (uniform_name,uniform_kind,price,u_date,u_time,u_timestamp,u_localdate,u_localtime,u_localdatetime,u_float,u_double,u_bigDecimal) VALUES ('Yシャツ','3',2500,'2016-01-02','11:11:11','2016-01-01 11:11:11.111','2016-01-02','11:11:11','2016-01-01 11:11:11.111',0.3,0.4,0.8);
INSERT INTO uniform (uniform_name,uniform_kind,price,u_date,u_time,u_timestamp,u_localdate,u_localtime,u_localdatetime,u_float,u_double,u_bigDecimal) VALUES ('ジャケット','2',4500,'2016-01-02','11:11:11','2016-01-01 11:11:11.111','2016-01-02','11:11:11','2016-01-01 11:11:11.111',0.3,0.4,0.8);
INSERT INTO uniform (uniform_name,uniform_kind,price,u_date,u_time,u_timestamp,u_localdate,u_localtime,u_localdatetime,u_float,u_double,u_bigDecimal) VALUES ('Tシャツ','3',500,'2016-01-02','11:11:11','2016-01-01 11:11:11.111','2016-01-02','11:11:11','2016-01-01 11:11:11.111',0.3,0.4,0.8);
INSERT INTO uniform (uniform_name,uniform_kind,price,u_date,u_time,u_timestamp,u_localdate,u_localtime,u_localdatetime,u_float,u_double,u_bigDecimal) VALUES ('uniformシャツ','3',500,'2016-01-02','11:11:11','2016-01-01 11:11:11.111','2016-01-02','11:11:11','2016-01-01 11:11:11.111',0.3,0.4,0.8);

INSERT INTO other.ap_user2 (user_id,name,lock_flg,valid_from,valid_to,unit_id,role_id) VALUES ('A1','ゆーざー1','1','2015-01-10',null,'U1','R1');

INSERT INTO generated_id_table (name) VALUES ('自動生成1');
INSERT INTO generated_id_table (name) VALUES ('自動生成2');
INSERT INTO generated_id_table (name) VALUES ('自動生成3');
INSERT INTO generated_id_table (name) VALUES ('自動生成3');
INSERT INTO generated_id_table (name) VALUES ('自動生成3');
INSERT INTO generated_id_table (name) VALUES ('自動生成3');
INSERT INTO generated_id_table (name) VALUES ('自動生成4');
INSERT INTO generated_id_table (name) VALUES ('自動生成5');
INSERT INTO generated_id_table (name) VALUES ('自動生成6');

INSERT INTO numeric_pattern VALUES (1111111111,222222222,33333333.3,null);
INSERT INTO numeric_pattern VALUES (1111111112,222222223,33333333.4,null);