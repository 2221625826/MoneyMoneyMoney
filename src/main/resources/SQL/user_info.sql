CREATE TABLE `user_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'user_info表Id',
  `user_id` int unsigned NOT NULL COMMENT 'userId',
  `sex` int DEFAULT NULL COMMENT '性别 0-女 1-男',
  `birthday` char(10) NOT NULL COMMENT '出生年月',
  `introduce` varchar(500) DEFAULT NULL COMMENT '自我介绍',
  `create_time` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
