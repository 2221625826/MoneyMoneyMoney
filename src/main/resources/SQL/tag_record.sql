CREATE TABLE `tag_record` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '标签记录id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `tag_id` bigint NOT NULL COMMENT '标签id',
  `create_time` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
