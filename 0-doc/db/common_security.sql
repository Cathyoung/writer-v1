DROP TABLE IF EXISTS `common_admin`;
CREATE TABLE `common_admin` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID（主键）',
  `username` VARCHAR(45) NOT NULL COMMENT '用户名',
  `password` VARCHAR(128) NOT NULL COMMENT '登录密码',
  `email` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '邮箱地址',
  `real_name` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '用户真实姓名',
  `mobile` VARCHAR(20) NOT NULL DEFAULT '',
  `status` VARCHAR(20) NOT NULL DEFAULT 'DISABLED' COMMENT 'ENABLED: 启用, DISABLED: 禁用',
  `create_timestamp` BIGINT(20) NOT NULL COMMENT '用户创建时间',
  `update_timestamp` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_username` (`username`)
) COMMENT='管理员表';

DROP TABLE IF EXISTS `common_role`;
CREATE TABLE `common_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID（主键）',
  `name` VARCHAR(45) NOT NULL COMMENT '角色名称',
  `status` VARCHAR(20) NOT NULL DEFAULT 'DISABLED' COMMENT 'ENABLED: 启用, DISABLED: 禁用',
  `description` TEXT COMMENT '角色描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_name` (`name`)
) COMMENT='角色表';

DROP TABLE IF EXISTS `common_authority`;
CREATE TABLE `common_authority` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID（主键）',
  `name` VARCHAR(191) NOT NULL COMMENT '权限名称',
  `authority` VARCHAR(191) NOT NULL COMMENT '权限URL Pattern',
  `description` TEXT COMMENT '权限描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_authority` (`authority`)
) COMMENT='权限表';

DROP TABLE IF EXISTS `common_admin_role`;
CREATE TABLE `common_admin_role` (
  `admin_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`admin_id`,`role_id`)
) COMMENT='用户角色关系表';

DROP TABLE IF EXISTS `common_role_authority`;
CREATE TABLE `common_role_authority` (
  `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
  `authority_id` BIGINT(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`,`authority_id`)
) COMMENT='角色权限关系表';
