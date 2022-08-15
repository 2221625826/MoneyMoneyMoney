CREATE TABLE `pay_earn_record` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '账单id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `amount` bigint NOT NULL COMMENT '金额',
  `reverse` int NOT NULL DEFAULT '0' COMMENT '收支 0-支出 1-收入',
  `category_id` bigint NOT NULL COMMENT '账单类别',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `pay_time` bigint DEFAULT NULL COMMENT '交易时间',
  `create_time` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
