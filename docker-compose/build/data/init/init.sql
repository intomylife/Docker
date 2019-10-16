-- 创建数据库
DROP database IF EXISTS `base_db`;
CREATE DATABASE `base_db` default character set utf8mb4 collate utf8mb4_unicode_ci;
 
-- 切换数据库
USE base_db;

-- 创建表
DROP TABLE IF EXISTS `base_table` ;
CREATE TABLE `base_table` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增 ID',
  `session_id` varchar(200) DEFAULT NULL COMMENT '会话 ID',
  `create_datetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='测试表';
 
INSERT INTO base_table ( session_id ) VALUES ('Lets Begin Now（让我们开始）');