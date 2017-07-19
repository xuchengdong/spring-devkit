/*
[mysqld]
character-set-server=utf8
collation-server=utf8_general_ci
[client]
default-character-set=utf8
--------------------------------------------
https://dev.mysql.com/doc/refman/5.7/en/charset-connection.html

SET NAMES 'charset_name'
||
SET character_set_client = charset_name;
SET character_set_results = charset_name;
SET character_set_connection = charset_name;

SET CHARACTER SET 'charset_name'
||
SET character_set_client = charset_name;
SET character_set_results = charset_name;
SET collation_connection = @@collation_database;
*/

SHOW CHARACTER SET;
SHOW VARIABLES LIKE 'character_set%';
SHOW VARIABLES LIKE 'collation%';

SHOW CREATE DATABASE df;
SHOW CREATE TABLE df.t_user_router;

-- drop db
# DROP DATABASE IF EXISTS df;
# DROP DATABASE IF EXISTS df_user01;
# DROP DATABASE IF EXISTS df_user02;
# DROP DATABASE IF EXISTS df_user03;

-- drop table
# DROP TABLE IF EXISTS df.t_user_router;
# DROP TABLE IF EXISTS df_user01.t_user_info;

-- create db
# CREATE DATABASE IF NOT EXISTS df CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
# CREATE DATABASE IF NOT EXISTS df_user01 CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
# CREATE DATABASE IF NOT EXISTS df_user02 CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
# CREATE DATABASE IF NOT EXISTS df_user03 CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

-- create table
USE df;
create table t_user_router
(
  id int auto_increment comment '主键自增id'
    primary key,
  user_id int not null comment '用户唯一标识id',
  user_name varchar(30) not null comment '用户名',
  password char(32) null comment '密码',
  create_time datetime not null comment '创建时间',
  constraint t_user_router_user_id_uindex
  unique (user_id)
)
  comment '用户路由表'
;

create index t_user_router_create_time_index
  on t_user_router (create_time)
;

USE df_user01;
create table df_user01.t_user_info
(
  id int auto_increment comment '主键自增id'
    primary key,
  user_id int not null,
  mobile varchar(11) null comment '手机号',
  email varchar(20) null comment '邮箱',
  nick_name varchar(20) null comment '昵称',
  sex tinyint default '0' null comment '性别: 0:未知 1:男 2:女',
  age tinyint null comment '年龄',
  avatar_url varchar(50) null comment '头像地址',
  constraint t_user_info_user_id_uindex
  unique (user_id),
  constraint t_user_info_mobile_uindex
  unique (mobile),
  constraint t_user_info_email_uindex
  unique (email)
)
  comment '用户信息表'
;

create index t_user_info_nick_name_index
  on t_user_info (nick_name)
;

USE df_user02;
create table df_user02.t_user_info
(
  id int auto_increment comment '主键自增id'
    primary key,
  user_id int not null,
  mobile varchar(11) null comment '手机号',
  email varchar(20) null comment '邮箱',
  nick_name varchar(20) null comment '昵称',
  sex tinyint default '0' null comment '性别: 0:未知 1:男 2:女',
  age tinyint null comment '年龄',
  avatar_url varchar(50) null comment '头像地址',
  constraint t_user_info_user_id_uindex
  unique (user_id),
  constraint t_user_info_mobile_uindex
  unique (mobile),
  constraint t_user_info_email_uindex
  unique (email)
)
  comment '用户信息表'
;

create index t_user_info_nick_name_index
  on t_user_info (nick_name)
;

USE df_user03;
create table df_user03.t_user_info
(
  id int auto_increment comment '主键自增id'
    primary key,
  user_id int not null,
  mobile varchar(11) null comment '手机号',
  email varchar(20) null comment '邮箱',
  nick_name varchar(20) null comment '昵称',
  sex tinyint default '0' null comment '性别: 0:未知 1:男 2:女',
  age tinyint null comment '年龄',
  avatar_url varchar(50) null comment '头像地址',
  constraint t_user_info_user_id_uindex
  unique (user_id),
  constraint t_user_info_mobile_uindex
  unique (mobile),
  constraint t_user_info_email_uindex
  unique (email)
)
  comment '用户信息表'
;

create index t_user_info_nick_name_index
  on t_user_info (nick_name)
;

-- insert data
INSERT INTO df.t_user_router (user_id, user_name, password, create_time) VALUES (1000000000, '小米', null, '2017-07-15 00:00:00');
INSERT INTO df.t_user_router (user_id, user_name, password, create_time) VALUES (1000000001, '红米', null, '2017-07-15 00:00:00');
INSERT INTO df.t_user_router (user_id, user_name, password, create_time) VALUES (1000000002, '红米note', null, '2017-07-15 00:00:00');
INSERT INTO df_user01.t_user_info (user_id, mobile, email, nick_name, sex, age, avatar_url) VALUES (1000000000, '13065754987', null, null, 0, null, null);
INSERT INTO df_user02.t_user_info (user_id, mobile, email, nick_name, sex, age, avatar_url) VALUES (1000000001, '18067650945', null, null, 0, null, null);
INSERT INTO df_user03.t_user_info (user_id, mobile, email, nick_name, sex, age, avatar_url) VALUES (1000000002, '13598672309', null, null, 0, null, null);


-- dml
SELECT * FROM df_user01.t_user_info WHERE id=?;