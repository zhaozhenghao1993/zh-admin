/*
Navicat MySQL Data Transfer

Source Server         : zhaozhenghao
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : test-admin

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-05-27 20:28:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '耗时,相应时间',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'ip地址',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '浏览器',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '操作系统',
  `status` tinyint(255) DEFAULT NULL COMMENT '操作状态 0-成功 1-失败',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '操作描述',
  `type` tinyint(255) DEFAULT NULL COMMENT '日志类型 1-登录 2-访问 3-操作 4-异常 5-授权',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COMMENT='系统日志';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单URI',
  `method` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '请求method',
  `perms` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮  3：uri',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '最后修改人name',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=latin1 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('66', '0', '系统管理', null, null, 'sys', '0', '0', null, '1', '2019-02-08 15:29:18', null, null);
INSERT INTO `sys_menu` VALUES ('67', '66', '用户管理', null, null, 'sys:user', '1', '0', null, '1', '2019-02-08 15:34:43', null, null);
INSERT INTO `sys_menu` VALUES ('68', '67', '列表', '/sys/user', 'GET', 'sys:user:view', '3', '0', '系统用户列表', '1', '2019-02-09 17:46:31', null, null);
INSERT INTO `sys_menu` VALUES ('69', '67', '展示', '/sys/user/{id}', 'GET', 'sys:user:info', '3', '1', '系统用户展示', '1', '2019-02-09 17:48:20', '1', '2019-05-05 23:02:43');
INSERT INTO `sys_menu` VALUES ('70', '67', '新增', '/sys/user', 'POST', 'sys:user:save', '2', '2', '系统用户新增', '1', '2019-02-15 22:20:55', null, null);
INSERT INTO `sys_menu` VALUES ('71', '67', '修改', '/sys/user/{id}', 'PUT', 'sys:user:edit', '2', '3', '系统用户修改', '1', '2019-02-15 22:20:58', null, null);
INSERT INTO `sys_menu` VALUES ('72', '67', '删除', '/sys/user/{id}', 'DELETE', 'sys:user:remove', '2', '4', '系统用户删除', '1', '2019-02-15 22:21:00', null, null);
INSERT INTO `sys_menu` VALUES ('73', '66', '角色管理', null, null, 'sys:role', '1', '1', null, '1', '2019-02-15 22:21:03', null, null);
INSERT INTO `sys_menu` VALUES ('74', '73', '列表', '/sys/role', 'GET', 'sys:role:view', '3', '0', '系统角色列表', '1', '2019-02-15 22:21:05', null, null);
INSERT INTO `sys_menu` VALUES ('75', '73', '展示', '/sys/role/{id}', 'GET', 'sys:role:info', '3', '1', '系统角色详情', '1', '2019-02-15 22:21:09', null, null);
INSERT INTO `sys_menu` VALUES ('76', '73', '新增', '/sys/role', 'POST', 'sys:role:save', '2', '2', '系统角色新增', '1', '2019-02-15 22:21:11', null, null);
INSERT INTO `sys_menu` VALUES ('77', '73', '修改', '/sys/role/{id}', 'PUT', 'sys:role:edit', '2', '3', '系统角色修改', '1', '2019-02-15 22:21:13', null, null);
INSERT INTO `sys_menu` VALUES ('78', '73', '删除', '/sys/role/{id}', 'DELETE', 'sys:role:remove', '2', '4', '系统角色删除', '1', '2019-02-15 22:21:16', null, null);
INSERT INTO `sys_menu` VALUES ('79', '66', '权限管理', null, null, 'sys:menu', '1', '2', null, '1', '2019-02-15 22:21:18', null, null);
INSERT INTO `sys_menu` VALUES ('80', '79', '列表', '/sys/menu', 'GET', 'sys:menu:view', '3', '0', '系统菜单列表', '1', '2019-02-15 22:21:22', null, null);
INSERT INTO `sys_menu` VALUES ('81', '79', '展示', '/sys/menu/{id}', 'GET', 'sys:menu:info', '3', '1', '系统菜单详情', '1', '2019-02-15 22:21:24', null, null);
INSERT INTO `sys_menu` VALUES ('82', '79', '新增', '/sys/menu', 'POST', 'sys:menu:save', '2', '2', '系统菜单新增', '1', '2019-02-15 22:21:26', null, null);
INSERT INTO `sys_menu` VALUES ('83', '79', '修改', '/sys/menu/{id}', 'PUT', 'sys:menu:edit', '2', '3', '系统菜单修改', '1', '2019-02-15 22:21:27', null, null);
INSERT INTO `sys_menu` VALUES ('84', '79', '删除', '/sys/menu/{id}', 'DELETE', 'sys:menu:remove', '2', '4', '系统菜单删除', '1', '2019-02-15 22:21:29', null, null);
INSERT INTO `sys_menu` VALUES ('85', '67', '批量删除', '/sys/user', 'DELETE', 'sys:user:batch', '2', '5', '系统用户批量删除', '1', '2019-03-10 22:13:05', null, null);
INSERT INTO `sys_menu` VALUES ('86', '67', '重置密码', '/sys/user/{id}/reset', 'PUT', 'sys:user:reset', '2', '6', '系统用户重置密码', '1', '2019-03-12 22:45:36', null, null);
INSERT INTO `sys_menu` VALUES ('87', '67', '批量启用', '/sys/user/enable', 'PUT', 'sys:user:enable', '2', '7', '系统用户启用账号', '1', '2019-03-13 21:06:03', null, null);
INSERT INTO `sys_menu` VALUES ('88', '67', '批量锁定', '/sys/user/disable', 'PUT', 'sys:user:disable', '2', '8', '系统用户锁定账号', '1', '2019-03-13 21:07:20', null, null);
INSERT INTO `sys_menu` VALUES ('89', '73', '批量删除', '/sys/role', 'DELETE', 'sys:role:batch', '2', '5', '系统角色批量删除', '1', '2019-03-21 21:34:58', null, null);
INSERT INTO `sys_menu` VALUES ('90', '79', '菜单树列表', '/sys/menu/tree', 'GET', 'sys:menu:tree', '3', '5', '系统菜单树列表', '1', '2019-03-23 15:13:17', null, null);
INSERT INTO `sys_menu` VALUES ('91', '0', '测试目录', '', '', 'test', '0', '10', '测试目录', '1', '2019-03-23 23:38:48', '1', '2019-04-27 21:30:26');
INSERT INTO `sys_menu` VALUES ('92', '91', '测试管理', null, null, 'test:manager', '1', '0', '测试管理', '1', '2019-03-23 23:44:25', null, null);
INSERT INTO `sys_menu` VALUES ('93', '92', '测试接口', '/test/manager', 'GET', 'test:manager:view', '3', '0', '测试接口', '1', '2019-03-23 23:45:45', '1', '2019-03-23 23:46:54');
INSERT INTO `sys_menu` VALUES ('94', '92', '测试按钮', '/test/manager/{id}', 'PUT', 'test:manager:edit', '2', '1', '测试按钮', '1', '2019-03-23 23:46:43', '1', '2019-03-23 23:52:05');
INSERT INTO `sys_menu` VALUES ('98', '91', '测试', null, null, 'sd', '3', null, null, '1', '2019-03-24 21:19:01', '1', '2019-03-24 21:19:19');
INSERT INTO `sys_menu` VALUES ('99', '73', '分配权限', '/sys/role/{id}/authorize', 'PUT', 'sys:role:authorize', '2', '6', '系统角色授权', '1', '2019-03-25 22:22:17', null, null);
INSERT INTO `sys_menu` VALUES ('100', '73', '角色选择', '/sys/role/select', 'GET', 'sys:role:select', '3', '7', '角色select选择列表', '1', '2019-03-26 21:35:57', null, null);
INSERT INTO `sys_menu` VALUES ('101', '66', '组织管理', '', '', 'sys:org', '1', '3', null, '1', '2019-04-03 20:58:39', null, null);
INSERT INTO `sys_menu` VALUES ('102', '101', '列表', '/sys/org', 'GET', 'sys:org:view', '3', '0', '系统组织列表', '1', '2019-04-03 20:59:43', null, null);
INSERT INTO `sys_menu` VALUES ('103', '101', '展示', '/sys/org/{id}', 'GET', 'sys:org:info', '3', '1', '系统组织详情', '1', '2019-04-03 21:01:03', null, null);
INSERT INTO `sys_menu` VALUES ('104', '101', '新增', '/sys/org', 'POST', 'sys:org:save', '2', '2', '系统组织新增', '1', '2019-04-03 21:02:06', '1', '2019-04-03 21:04:23');
INSERT INTO `sys_menu` VALUES ('105', '101', '修改', '/sys/org/{id}', 'PUT', 'sys:org:edit', '2', '3', '系统组织修改', '1', '2019-04-03 21:04:15', null, null);
INSERT INTO `sys_menu` VALUES ('106', '101', '删除', '/sys/org/{id}', 'DELETE', 'sys:org:remove', '2', '4', '系统组织删除', '1', '2019-04-03 21:05:28', null, null);
INSERT INTO `sys_menu` VALUES ('107', '101', '组织树列表', '/sys/org/tree', 'GET', 'sys:org:tree', '3', '5', '系统组织树列表', '1', '2019-04-03 21:06:24', null, null);
INSERT INTO `sys_menu` VALUES ('108', '91', '再次测试', '', '', 'aa', '1', '3', null, '1', '2019-04-05 13:10:47', null, null);
INSERT INTO `sys_menu` VALUES ('109', '0', '系统监控', '', '', 'monitor', '0', '1', null, '1', '2019-04-05 13:53:02', null, null);
INSERT INTO `sys_menu` VALUES ('110', '109', '日志管理', '', '', 'monitor:log', '1', '0', null, '1', '2019-04-05 14:00:30', null, null);
INSERT INTO `sys_menu` VALUES ('112', '109', '服务器监控', '', '', 'monitor:server', '1', '1', null, '1', '2019-04-05 14:14:36', '1', '2019-04-05 20:11:30');
INSERT INTO `sys_menu` VALUES ('115', '112', '基础信息', '/monitor/server/base', 'GET', 'monitor:server:base', '3', '0', '服务器监控基础数据', '1', '2019-04-07 18:37:14', null, null);
INSERT INTO `sys_menu` VALUES ('116', '112', '实时信息', '/monitor/server/instant', 'GET', 'monitor:server:instant', '3', '1', '服务器监控实时数据', '1', '2019-04-07 18:38:31', '1', '2019-04-07 18:38:53');
INSERT INTO `sys_menu` VALUES ('117', '110', '列表', '/monitor/log', 'GET', 'monitor:log:view', '3', '0', '日志管理列表', '1', '2019-04-15 21:30:12', null, null);
INSERT INTO `sys_menu` VALUES ('118', '110', '批量删除', '/monitor/log', 'DELETE', 'monitor:log:batch', '2', '1', '日志管理批量删除', '1', '2019-04-15 21:41:34', null, null);
INSERT INTO `sys_menu` VALUES ('119', '110', '清空', '/monitor/log/{type}/clear', 'DELETE', 'monitor:log:clear', '2', '2', '日志管理清空日志', '1', '2019-04-15 21:43:03', null, null);
INSERT INTO `sys_menu` VALUES ('120', '0', '开发工具', '', '', 'tool', '0', '2', null, '1', '2019-04-27 21:31:27', null, null);
INSERT INTO `sys_menu` VALUES ('122', '120', '代码生成器', '', '', 'tool:generator', '1', '1', null, '1', '2019-04-27 21:35:09', '1', '2019-04-27 21:35:16');
INSERT INTO `sys_menu` VALUES ('123', '122', '列表', '/tool/generator', 'GET', 'tool:generator:view', '3', '0', '代码生成器列表，展示所有数据库表信息', '1', '2019-04-27 21:37:42', null, null);
INSERT INTO `sys_menu` VALUES ('124', '122', '生成代码', '/tool/generator/code', 'GET', 'tool:generator:code', '2', '1', '生成代码', '1', '2019-04-27 21:41:42', null, null);
INSERT INTO `sys_menu` VALUES ('125', '66', '岗位管理', '', '', 'sys:post', '1', '4', null, '1', '2019-04-27 22:40:18', null, null);
INSERT INTO `sys_menu` VALUES ('126', '125', '列表', '/sys/post', 'GET', 'sys:post:view', '3', '0', '岗位管理列表', '1', '2019-04-27 22:41:10', '1', '2019-04-27 22:50:47');
INSERT INTO `sys_menu` VALUES ('127', '125', '展示', '/sys/post/{id}', 'GET', 'sys:post:info', '3', '1', '岗位管理展示', '1', '2019-04-27 22:41:53', '1', '2019-04-27 22:50:56');
INSERT INTO `sys_menu` VALUES ('128', '125', '新增', '/sys/post', 'POST', 'sys:post:save', '2', '2', '岗位管理新增', '1', '2019-04-27 22:42:54', '1', '2019-04-27 22:51:03');
INSERT INTO `sys_menu` VALUES ('129', '125', '修改', '/sys/post/{id}', 'PUT', 'sys:post:edit', '2', '3', '岗位管理修改', '1', '2019-04-27 22:43:37', '1', '2019-04-27 22:51:10');
INSERT INTO `sys_menu` VALUES ('130', '125', '删除', '/sys/post/{id}', 'DELETE', 'sys:post:remove', '2', '4', '岗位管理删除', '1', '2019-04-27 22:44:50', '1', '2019-04-27 22:51:18');
INSERT INTO `sys_menu` VALUES ('131', '125', '批量删除', '/sys/post', 'DELETE', 'sys:post:batch', '2', '5', '岗位管理批量删除', '1', '2019-04-27 22:45:34', '1', '2019-04-27 22:51:26');
INSERT INTO `sys_menu` VALUES ('132', '120', 'IconSelector', '', '', 'tool:icon', '1', '0', null, '1', '2019-04-29 22:25:35', null, null);
INSERT INTO `sys_menu` VALUES ('133', '125', '岗位选择', '/sys/post/select', 'GET', 'sys:post:select', '3', '6', '岗位管理岗位选择列表', '1', '2019-05-01 11:08:44', null, null);
INSERT INTO `sys_menu` VALUES ('134', '67', '详情', '/sys/user/{id}/detail', 'GET', 'sys:user:detail', '3', '9', '用户管理详情', '1', '2019-05-05 23:04:00', null, null);
INSERT INTO `sys_menu` VALUES ('135', '109', '数据库监控', '', '', 'monitor:druid', '1', '2', 'druid数据库监控', '1', '2019-05-18 21:31:49', null, null);
INSERT INTO `sys_menu` VALUES ('136', '135', 'druid静态资源', '/druid/*', 'GET', 'monitor:druid:static', '3', '0', 'druid静态资源', '1', '2019-05-18 21:33:22', null, null);
INSERT INTO `sys_menu` VALUES ('137', '135', 'druid动态数据', '/druid/*', 'POST', 'monitor:druid:data', '3', '1', 'druid动态数据', '1', '2019-05-18 21:34:16', null, null);

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `org_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `parent_id` bigint(20) DEFAULT '0',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '祖级列表',
  `org_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '组织名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `creator_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modifier_id` bigint(20) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织管理';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('1', '0', '0', '研发中心', '0', '1', '2019-04-03 22:05:49', null, null);
INSERT INTO `sys_org` VALUES ('2', '1', '0,1', '后端组', '0', '1', '2019-04-03 22:13:12', null, null);
INSERT INTO `sys_org` VALUES ('3', '2', '0,1,2', 'JAVA', '0', '1', '2019-04-03 22:13:37', null, null);
INSERT INTO `sys_org` VALUES ('4', '2', '0,1,2', 'PHP', '1', '1', '2019-04-03 22:14:14', null, null);
INSERT INTO `sys_org` VALUES ('5', '2', '0,1,2', 'Golang', '2', '1', '2019-04-03 22:14:36', null, null);
INSERT INTO `sys_org` VALUES ('6', '1', '0,1', '前端组', '1', '1', '2019-04-03 22:15:22', null, null);
INSERT INTO `sys_org` VALUES ('7', '6', '0,1,6', 'React', '0', '1', '2019-04-03 22:15:39', null, null);
INSERT INTO `sys_org` VALUES ('8', '6', '0,1,6', 'Vue', '1', '1', '2019-04-03 22:15:59', null, null);
INSERT INTO `sys_org` VALUES ('9', '6', '0,1,6', 'Angular', '2', '1', '2019-04-03 22:16:12', null, null);
INSERT INTO `sys_org` VALUES ('10', '0', '0', '财务部', '1', '1', '2019-04-03 22:16:44', null, null);
INSERT INTO `sys_org` VALUES ('11', '10', '0,10', '会计核算', '0', '1', '2019-04-03 22:17:01', null, null);
INSERT INTO `sys_org` VALUES ('12', '10', '0,10', '成本控制', '1', '1', '2019-04-03 22:17:21', null, null);
INSERT INTO `sys_org` VALUES ('13', '10', '0,10', '内部控制', '2', '1', '2019-04-03 22:17:43', null, null);
INSERT INTO `sys_org` VALUES ('14', '13', '0,10,13', '财务制度建设', '0', '1', '2019-04-03 22:18:04', null, null);
INSERT INTO `sys_org` VALUES ('15', '13', '0,10,13', '会计核算', '1', '1', '2019-04-03 22:18:18', null, null);
INSERT INTO `sys_org` VALUES ('16', '1', '0,1', '测试组', '2', '1', '2019-04-03 22:21:55', '1', '2019-04-03 22:21:55');
INSERT INTO `sys_org` VALUES ('17', '16', '0,1,16', '功能测试', '0', '1', '2019-04-03 22:21:55', null, '2019-04-03 22:21:55');
INSERT INTO `sys_org` VALUES ('18', '16', '0,1,16', '安全测试', '1', '1', '2019-04-03 22:21:55', null, '2019-04-03 22:21:55');
INSERT INTO `sys_org` VALUES ('20', '10', '0,10', '审计', '3', '1', '2019-04-05 13:44:48', null, null);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '岗位名称',
  `post_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '岗位编码',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='岗位管理';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('3', '董事长', 'ceo', '0', '1', '2019-04-29 23:19:42', null, null);
INSERT INTO `sys_post` VALUES ('4', '项目经理', 'pm', '1', '1', '2019-04-29 23:19:45', null, null);
INSERT INTO `sys_post` VALUES ('5', '人力资源', 'hr', '2', '1', '2019-04-29 23:19:48', null, null);
INSERT INTO `sys_post` VALUES ('6', '普通员工', 'user', '3', '1', '2019-04-29 23:19:51', null, null);
INSERT INTO `sys_post` VALUES ('7', '会计', 'accountant', '4', '1', '2019-04-29 23:19:53', null, null);
INSERT INTO `sys_post` VALUES ('8', '前端工程师', 'fe', '5', '1', '2019-04-29 23:19:55', null, null);
INSERT INTO `sys_post` VALUES ('9', '后端工程师', 'rd', '6', '1', '2019-04-29 23:19:57', null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '【系统内置】', '1', '2017-12-20 14:34:21', null, null);
INSERT INTO `sys_role` VALUES ('29', '只读角色', 'read-only', null, '1', '2019-03-30 15:29:25', '1', '2019-04-05 00:36:35');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1046 DEFAULT CHARSET=latin1 COMMENT='角色菜单关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('920', '1', '66');
INSERT INTO `sys_role_menu` VALUES ('921', '1', '67');
INSERT INTO `sys_role_menu` VALUES ('922', '1', '68');
INSERT INTO `sys_role_menu` VALUES ('923', '1', '69');
INSERT INTO `sys_role_menu` VALUES ('924', '1', '70');
INSERT INTO `sys_role_menu` VALUES ('925', '1', '71');
INSERT INTO `sys_role_menu` VALUES ('926', '1', '72');
INSERT INTO `sys_role_menu` VALUES ('927', '1', '73');
INSERT INTO `sys_role_menu` VALUES ('928', '1', '74');
INSERT INTO `sys_role_menu` VALUES ('929', '1', '75');
INSERT INTO `sys_role_menu` VALUES ('930', '1', '76');
INSERT INTO `sys_role_menu` VALUES ('931', '1', '77');
INSERT INTO `sys_role_menu` VALUES ('932', '1', '78');
INSERT INTO `sys_role_menu` VALUES ('933', '1', '79');
INSERT INTO `sys_role_menu` VALUES ('934', '1', '80');
INSERT INTO `sys_role_menu` VALUES ('935', '1', '81');
INSERT INTO `sys_role_menu` VALUES ('936', '1', '82');
INSERT INTO `sys_role_menu` VALUES ('937', '1', '83');
INSERT INTO `sys_role_menu` VALUES ('938', '1', '84');
INSERT INTO `sys_role_menu` VALUES ('939', '1', '85');
INSERT INTO `sys_role_menu` VALUES ('940', '1', '86');
INSERT INTO `sys_role_menu` VALUES ('941', '1', '87');
INSERT INTO `sys_role_menu` VALUES ('942', '1', '88');
INSERT INTO `sys_role_menu` VALUES ('943', '1', '89');
INSERT INTO `sys_role_menu` VALUES ('944', '1', '90');
INSERT INTO `sys_role_menu` VALUES ('945', '1', '99');
INSERT INTO `sys_role_menu` VALUES ('946', '1', '100');
INSERT INTO `sys_role_menu` VALUES ('947', '1', '101');
INSERT INTO `sys_role_menu` VALUES ('948', '1', '103');
INSERT INTO `sys_role_menu` VALUES ('949', '1', '104');
INSERT INTO `sys_role_menu` VALUES ('950', '1', '105');
INSERT INTO `sys_role_menu` VALUES ('951', '1', '106');
INSERT INTO `sys_role_menu` VALUES ('952', '1', '107');
INSERT INTO `sys_role_menu` VALUES ('953', '1', '102');
INSERT INTO `sys_role_menu` VALUES ('954', '1', '109');
INSERT INTO `sys_role_menu` VALUES ('955', '1', '112');
INSERT INTO `sys_role_menu` VALUES ('956', '1', '115');
INSERT INTO `sys_role_menu` VALUES ('957', '1', '116');
INSERT INTO `sys_role_menu` VALUES ('958', '1', '110');
INSERT INTO `sys_role_menu` VALUES ('959', '1', '117');
INSERT INTO `sys_role_menu` VALUES ('960', '1', '118');
INSERT INTO `sys_role_menu` VALUES ('961', '1', '119');
INSERT INTO `sys_role_menu` VALUES ('962', '1', '120');
INSERT INTO `sys_role_menu` VALUES ('963', '1', '122');
INSERT INTO `sys_role_menu` VALUES ('964', '1', '123');
INSERT INTO `sys_role_menu` VALUES ('965', '1', '124');
INSERT INTO `sys_role_menu` VALUES ('966', '1', '125');
INSERT INTO `sys_role_menu` VALUES ('967', '1', '126');
INSERT INTO `sys_role_menu` VALUES ('968', '1', '127');
INSERT INTO `sys_role_menu` VALUES ('969', '1', '128');
INSERT INTO `sys_role_menu` VALUES ('970', '1', '129');
INSERT INTO `sys_role_menu` VALUES ('971', '1', '130');
INSERT INTO `sys_role_menu` VALUES ('972', '1', '131');
INSERT INTO `sys_role_menu` VALUES ('973', '1', '132');
INSERT INTO `sys_role_menu` VALUES ('974', '1', '133');
INSERT INTO `sys_role_menu` VALUES ('975', '1', '134');
INSERT INTO `sys_role_menu` VALUES ('976', '1', '135');
INSERT INTO `sys_role_menu` VALUES ('977', '1', '136');
INSERT INTO `sys_role_menu` VALUES ('978', '1', '137');
INSERT INTO `sys_role_menu` VALUES ('1012', '29', '79');
INSERT INTO `sys_role_menu` VALUES ('1013', '29', '80');
INSERT INTO `sys_role_menu` VALUES ('1014', '29', '66');
INSERT INTO `sys_role_menu` VALUES ('1015', '29', '67');
INSERT INTO `sys_role_menu` VALUES ('1016', '29', '68');
INSERT INTO `sys_role_menu` VALUES ('1017', '29', '102');
INSERT INTO `sys_role_menu` VALUES ('1018', '29', '103');
INSERT INTO `sys_role_menu` VALUES ('1019', '29', '107');
INSERT INTO `sys_role_menu` VALUES ('1020', '29', '81');
INSERT INTO `sys_role_menu` VALUES ('1021', '29', '74');
INSERT INTO `sys_role_menu` VALUES ('1022', '29', '75');
INSERT INTO `sys_role_menu` VALUES ('1023', '29', '100');
INSERT INTO `sys_role_menu` VALUES ('1024', '29', '73');
INSERT INTO `sys_role_menu` VALUES ('1025', '29', '90');
INSERT INTO `sys_role_menu` VALUES ('1026', '29', '69');
INSERT INTO `sys_role_menu` VALUES ('1027', '29', '101');
INSERT INTO `sys_role_menu` VALUES ('1028', '29', '112');
INSERT INTO `sys_role_menu` VALUES ('1029', '29', '115');
INSERT INTO `sys_role_menu` VALUES ('1030', '29', '116');
INSERT INTO `sys_role_menu` VALUES ('1031', '29', '109');
INSERT INTO `sys_role_menu` VALUES ('1032', '29', '110');
INSERT INTO `sys_role_menu` VALUES ('1033', '29', '117');
INSERT INTO `sys_role_menu` VALUES ('1034', '29', '120');
INSERT INTO `sys_role_menu` VALUES ('1035', '29', '122');
INSERT INTO `sys_role_menu` VALUES ('1036', '29', '123');
INSERT INTO `sys_role_menu` VALUES ('1037', '29', '125');
INSERT INTO `sys_role_menu` VALUES ('1038', '29', '126');
INSERT INTO `sys_role_menu` VALUES ('1039', '29', '127');
INSERT INTO `sys_role_menu` VALUES ('1040', '29', '132');
INSERT INTO `sys_role_menu` VALUES ('1041', '29', '133');
INSERT INTO `sys_role_menu` VALUES ('1042', '29', '134');
INSERT INTO `sys_role_menu` VALUES ('1043', '29', '135');
INSERT INTO `sys_role_menu` VALUES ('1044', '29', '136');
INSERT INTO `sys_role_menu` VALUES ('1045', '29', '137');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_id` bigint(20) DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '头像url',
  `theme` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '个人主题，dark（默认），light',
  `color` tinyint(10) DEFAULT NULL COMMENT '主题色: 1  薄暮  ,2  火山  ,3  日暮  ,4  明青  ,5  极光绿  ,6  拂晓蓝（默认）,7  极客蓝 ,8 酱紫  ',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:正常，1:锁定',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '3', 'admin', '941f0f074eeeb6ffbf1abb4d22a6be97', 'Administrator', '736720794@qq.com', '17621776714', '/avatar.png', 'dark', '6', '0', '1', '2017-12-08 10:56:35', '1', '2019-05-14 22:15:46');
INSERT INTO `sys_user` VALUES ('63', '3', 'readonly', '941f0f074eeeb6ffbf1abb4d22a6be97', 'readonly', '736720794@qq.com', '17621776714', '/avatar.png', null, null, '0', '1', '2019-04-04 23:19:18', '63', '2019-05-23 22:02:00');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('9', '63', '4');
INSERT INTO `sys_user_post` VALUES ('10', '63', '9');
INSERT INTO `sys_user_post` VALUES ('12', '1', '3');
INSERT INTO `sys_user_post` VALUES ('13', '1', '9');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 COMMENT='用户角色关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('27', '63', '29');
INSERT INTO `sys_user_role` VALUES ('29', '1', '1');
