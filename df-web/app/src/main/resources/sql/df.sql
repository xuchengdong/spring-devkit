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

# SHOW CHARACTER SET;
# SHOW VARIABLES LIKE 'character_set%';
# SHOW VARIABLES LIKE 'collation%';

# SHOW CREATE DATABASE df;
# SHOW CREATE TABLE df.t_user;

-- drop db
DROP DATABASE IF EXISTS df;
DROP DATABASE IF EXISTS df_user01;
DROP DATABASE IF EXISTS df_user02;
DROP DATABASE IF EXISTS df_user03;

-- create db
CREATE DATABASE IF NOT EXISTS df CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
CREATE DATABASE IF NOT EXISTS df_user01 CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
CREATE DATABASE IF NOT EXISTS df_user02 CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
CREATE DATABASE IF NOT EXISTS df_user03 CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

-- create table
CREATE TABLE df.`t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `user_id` int(11) NOT NULL COMMENT '用户唯一标识id',
  `user_name` varchar(30) NOT NULL COMMENT '用户名',
  `password` char(32) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_user_id_uindex` (`user_id`),
  UNIQUE KEY `t_user_user_name_index` (`user_name`),
  UNIQUE KEY `t_user_mobile_uindex` (`mobile`),
  UNIQUE KEY `t_user_email_uindex` (`email`),
  KEY `t_user_create_time_index` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户路由表';

CREATE TABLE df.`t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_role_role_name_uindex` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE df_user01.`t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `user_id` int(11) NOT NULL,
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别: 0:未知 1:男 2:女',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `avatar_url` varchar(50) DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_info_user_id_uindex` (`user_id`),
  UNIQUE KEY `t_user_info_mobile_uindex` (`mobile`),
  UNIQUE KEY `t_user_info_email_uindex` (`email`),
  KEY `t_user_info_nick_name_index` (`nick_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE df_user01.`t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `t_user_role_user_id_index` (`user_id`),
  KEY `t_user_role_role_id_index` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

CREATE TABLE df_user02.`t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `user_id` int(11) NOT NULL,
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别: 0:未知 1:男 2:女',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `avatar_url` varchar(50) DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_info_user_id_uindex` (`user_id`),
  UNIQUE KEY `t_user_info_mobile_uindex` (`mobile`),
  UNIQUE KEY `t_user_info_email_uindex` (`email`),
  KEY `t_user_info_nick_name_index` (`nick_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE df_user02.`t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `t_user_role_user_id_index` (`user_id`),
  KEY `t_user_role_role_id_index` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

CREATE TABLE df_user03.`t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `user_id` int(11) NOT NULL,
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别: 0:未知 1:男 2:女',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `avatar_url` varchar(50) DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_info_user_id_uindex` (`user_id`),
  UNIQUE KEY `t_user_info_mobile_uindex` (`mobile`),
  UNIQUE KEY `t_user_info_email_uindex` (`email`),
  KEY `t_user_info_nick_name_index` (`nick_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE df_user03.`t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `t_user_role_user_id_index` (`user_id`),
  KEY `t_user_role_role_id_index` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- insert data
INSERT INTO df.t_user (user_id, user_name, password, create_time) VALUES (1000000000, '小米', null, '2017-07-15 00:00:00');
INSERT INTO df.t_user (user_id, user_name, password, create_time) VALUES (1000000001, '红米', null, '2017-07-15 00:00:00');
INSERT INTO df.t_user (user_id, user_name, password, create_time) VALUES (1000000002, '红米note', null, '2017-07-15 00:00:00');
INSERT INTO df.t_user (user_id, user_name, password, create_time) VALUES (1000000003, '13000000000', null, '2017-08-15 11:03:00');
INSERT INTO df.t_user (user_id, user_name, password, create_time) VALUES (1000000004, 'u_5832347', null, '2017-08-15 14:02:39');
INSERT INTO df.t_user (user_id, user_name, password, mobile, email, create_time) VALUES (1000000005, '13057555156', null, null, null, '2017-08-15 17:58:49');

INSERT INTO df.t_role (role_name) VALUES ('ROLE_USER');
INSERT INTO df.t_role (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO df.t_role (role_name) VALUES ('ROLE_DBA');
INSERT INTO df.t_role (role_name) VALUES ('ACTUATOR');

INSERT INTO df_user01.t_user_info (user_id, mobile, email, nick_name, sex, age, avatar_url) VALUES (1000000000, '13065754987', null, null, 0, null, null);
INSERT INTO df_user01.t_user_info (user_id, mobile, email, nick_name, sex, age, avatar_url) VALUES (1000000004, '18200007777', null, null, 0, null, null);
INSERT INTO df_user02.t_user_info (user_id, mobile, email, nick_name, sex, age, avatar_url) VALUES (1000000001, '18067650945', null, null, 0, null, null);
INSERT INTO df_user03.t_user_info (user_id, mobile, email, nick_name, sex, age, avatar_url) VALUES (1000000002, '13598672309', null, null, 0, null, null);

INSERT INTO df_user01.t_user_role (user_id, role_id) VALUES (1000000000, 1);
INSERT INTO df_user01.t_user_role (user_id, role_id) VALUES (1000000003, 1);
INSERT INTO df_user01.t_user_role (user_id, role_id) VALUES (1000000003, 2);
INSERT INTO df_user01.t_user_role (user_id, role_id) VALUES (1000000003, 3);
INSERT INTO df_user02.t_user_role (user_id, role_id) VALUES (1000000001, 2);
INSERT INTO df_user02.t_user_role (user_id, role_id) VALUES (1000000004, 2);
INSERT INTO df_user03.t_user_role (user_id, role_id) VALUES (1000000002, 3);
INSERT INTO df_user03.t_user_role (user_id, role_id) VALUES (1000000005, 1);
INSERT INTO df_user03.t_user_role (user_id, role_id) VALUES (1000000005, 4);

-- dml
-- SELECT * FROM df_user01.t_user_info WHERE id=?;

# SELECT a.*,b.role_name FROM df_user01.t_user_role a, df.t_role b WHERE a.role_id = b.id;