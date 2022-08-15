CREATE TABLE `account` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'userId',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `mail` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` char(13) DEFAULT NULL COMMENT '手机号',
  `create_time` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
