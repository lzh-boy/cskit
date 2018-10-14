CREATE TABLE `user_infoss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_addr` varchar(50) NOT NULL,
  `user_sex` tinyint(255) DEFAULT '1',
  `add_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sys_company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;