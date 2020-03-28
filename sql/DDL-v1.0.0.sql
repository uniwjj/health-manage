CREATE TABLE `hm_step_stat` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `stat_day` varchar(32) NOT NULL COMMENT '统计日期',
  `step_amount` int(11) NOT NULL DEFAULT '0' COMMENT '当天步数总量',
  `stat_type` smallint(6) NOT NULL DEFAULT '0' COMMENT '统计类型，0：正常，1：来亲戚啦，2：协商休息',
  `step_profit` int(11) NOT NULL DEFAULT '0' COMMENT '步数利润',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '结算状态，0：未结算，1：已结算',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_stat_day` (`stat_day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='hm-步数统计';

