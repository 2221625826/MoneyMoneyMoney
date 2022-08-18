CREATE TABLE `user_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'user_info表Id',
  `user_id` bigint unsigned NOT NULL COMMENT 'userId',
  `sex` int DEFAULT 2 COMMENT '性别 0-女 1-男 2-未知',
  `birthday` bigint  COMMENT '出生年月',
  `introduce` varchar(500) DEFAULT '' COMMENT '自我介绍',
  `create_time` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
