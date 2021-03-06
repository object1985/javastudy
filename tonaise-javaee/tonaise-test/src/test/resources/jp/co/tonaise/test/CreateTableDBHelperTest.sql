--検証用コメント
  /* 検証用コメント */


/* ユーザーテーブル */
CREATE TABLE IF NOT EXISTS AP_USER
(
  USER_ID CHARACTER VARYING(20) NOT NULL,
  NAME CHARACTER VARYING(256) NOT NULL,
  BIRTHDAY DATE,
  AGE INT NOT NULL,
  ROLE_ID CHARACTER VARYING(1) NOT NULL,
  DELETE_FLG CHARACTER(1) NOT NULL DEFAULT '0',
  CREATED_AT TIMESTAMP,
  CREATED_BY CHARACTER VARYING(20),
  UPDATED_AT TIMESTAMP,
  UPDATED_BY CHARACTER VARYING(20),
  PRIMARY KEY (USER_ID)
);
/* 主要型一覧テーブル */
CREATE TABLE IF NOT EXISTS TYPELIST
(
  ID INT NOT NULL,
  TEST_ID BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (ID)
);
/* 別スキーマ作成 */
CREATE SCHEMA IF NOT EXISTS OTHERSCHEMA;
/* 別スキーマ作成へのテーブル作成 */
CREATE TABLE IF NOT EXISTS OTHERSCHEMA.AP_USER
(
  USER_ID CHARACTER VARYING(20) NOT NULL,
  NAME CHARACTER VARYING(256) NOT NULL,
  BIRTHDAY DATE,
  AGE INT NOT NULL,
  ROLE_ID CHARACTER VARYING(2) NOT NULL,
  DELETE_FLG CHARACTER(1) NOT NULL DEFAULT '0',
  CREATED_AT TIMESTAMP,
  CREATED_BY CHARACTER VARYING(20),
  UPDATED_AT TIMESTAMP,
  UPDATED_BY CHARACTER VARYING(20),
  PRIMARY KEY (USER_ID)
);

INSERT INTO AP_USER (USER_ID,NAME,BIRTHDAY,AGE,ROLE_ID,           CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES ('A1','ゆーざー1','1987-01-01',20,'1','2015-01-10'     ,'SYSTEM-USER','2015-01-10','SYSTEM-USER');
INSERT INTO AP_USER (USER_ID,NAME,BIRTHDAY,AGE,ROLE_ID,           CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES ('A2','ゆーざー2','1987-01-02',21,'0','2015-01-10'     ,'SYSTEM-USER','2015-01-10','SYSTEM-USER');
INSERT INTO AP_USER (USER_ID,NAME,BIRTHDAY,AGE,ROLE_ID,           CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES ('A3','ゆーざー3','1987-01-03',22,'1','2015-01-10'     ,'SYSTEM-USER','2015-01-10','SYSTEM-USER');
INSERT INTO AP_USER (USER_ID,NAME,BIRTHDAY,AGE,ROLE_ID,DELETE_FLG,CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES ('A4','ゆーざー4','1987-01-04',23,'0','0' ,'2015-01-10','SYSTEM-USER','2015-01-10','SYSTEM-USER');
INSERT INTO AP_USER (USER_ID,NAME,BIRTHDAY,AGE,ROLE_ID,DELETE_FLG,CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES ('A5','ゆーざー5','1987-01-05',24,'1','0' ,'2015-01-10','SYSTEM-USER','2015-01-10','SYSTEM-USER');

INSERT INTO OTHERSCHEMA.AP_USER (USER_ID,NAME,BIRTHDAY,AGE,ROLE_ID,           CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES ('A1','ゆーざー1','1987-01-01',20,'1'     ,'2015-01-10','SYSTEM-USER','2015-01-10','SYSTEM-USER');
INSERT INTO OTHERSCHEMA.AP_USER (USER_ID,NAME,BIRTHDAY,AGE,ROLE_ID,           CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES ('A2','ゆーざー2','1987-01-02',21,'0'     ,'2015-01-10','SYSTEM-USER','2015-01-10','SYSTEM-USER');
INSERT INTO OTHERSCHEMA.AP_USER (USER_ID,NAME,BIRTHDAY,AGE,ROLE_ID,           CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES ('A3','ゆーざー3','1987-01-03',22,'1'     ,'2015-01-10','SYSTEM-USER','2015-01-10','SYSTEM-USER');
INSERT INTO OTHERSCHEMA.AP_USER (USER_ID,NAME,BIRTHDAY,AGE,ROLE_ID,DELETE_FLG,CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES ('A4','ゆーざー4','1987-01-04',23,'0','0' ,'2015-01-10','SYSTEM-USER','2015-01-10','SYSTEM-USER');
INSERT INTO OTHERSCHEMA.AP_USER (USER_ID,NAME,BIRTHDAY,AGE,ROLE_ID,DELETE_FLG,CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES ('A5','ゆーざー5','1987-01-05',24,'1','0' ,'2015-01-10','SYSTEM-USER','2015-01-10','SYSTEM-USER');
