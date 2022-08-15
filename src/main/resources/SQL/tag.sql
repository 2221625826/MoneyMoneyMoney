CREATE TABLE `tag` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `desc` varchar(50) NOT NULL COMMENT '描述',
  `create_time` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
