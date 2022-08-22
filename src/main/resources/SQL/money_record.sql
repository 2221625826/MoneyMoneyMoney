CREATE TABLE `money_record` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '账单id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `amount` bigint NOT NULL COMMENT '金额',
  `reverse` int NOT NULL DEFAULT '1' COMMENT '收支 0-收入 1-支出',
  `category_id` bigint NOT NULL DEFAULT '0' COMMENT '账单类别',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `pay_time` bigint DEFAULT NULL COMMENT '交易时间',
  `create_time` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
