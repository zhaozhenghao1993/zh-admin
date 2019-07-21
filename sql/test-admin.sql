/*
Navicat MySQL Data Transfer

Source Server         : zhaozhenghao
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : test-admin

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-07-21 19:11:49
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
) ENGINE=InnoDB AUTO_INCREMENT=834 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统日志';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统菜单';

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
INSERT INTO `sys_menu` VALUES ('135', '109', '数据库监控', '', '', 'monitor:druid', '1', '3', 'druid数据库监控', '1', '2019-05-18 21:31:49', '1', '2019-07-21 15:59:02');
INSERT INTO `sys_menu` VALUES ('136', '135', 'druid静态资源', '/druid/*', 'GET', 'monitor:druid:static', '3', '0', 'druid静态资源', '1', '2019-05-18 21:33:22', null, null);
INSERT INTO `sys_menu` VALUES ('137', '135', 'druid动态数据', '/druid/*', 'POST', 'monitor:druid:data', '3', '1', 'druid动态数据', '1', '2019-05-18 21:34:16', null, null);
INSERT INTO `sys_menu` VALUES ('138', '91', '再次测试下1', '/sys/test1', 'PUT', 'sys:test:again1', '3', '4', '再次测试下', '1', '2019-05-31 23:58:01', '1', '2019-05-31 23:58:39');
INSERT INTO `sys_menu` VALUES ('140', '92', '链接名称哈哈哈', '/test/test/test', 'POST', 'test:test', '3', '1', 'asdasdasd撒大苏打', '1', '2019-07-20 00:38:59', '1', '2019-07-20 00:39:18');
INSERT INTO `sys_menu` VALUES ('141', '109', '性能监控', '', '', 'monitor:performance', '1', '2', null, '1', '2019-07-21 15:58:53', null, null);
INSERT INTO `sys_menu` VALUES ('142', '141', '系统信息', '', '', 'monitor:performance:system', '1', '0', null, '1', '2019-07-21 15:59:28', null, null);
INSERT INTO `sys_menu` VALUES ('143', '141', 'JVM信息', '', '', 'monitor:performance:jvm', '1', '1', null, '1', '2019-07-21 16:00:06', null, null);
INSERT INTO `sys_menu` VALUES ('144', '141', 'Tomcat信息', '', '', 'monitor:performance:tomcat', '1', '2', null, '1', '2019-07-21 16:00:23', '1', '2019-07-21 16:00:29');
INSERT INTO `sys_menu` VALUES ('145', '142', '系统信息资源', '/monitor/performance/system/*', 'GET', 'monitor:performance:system:resource', '3', '0', null, '1', '2019-07-21 16:02:49', '1', '2019-07-21 19:04:39');
INSERT INTO `sys_menu` VALUES ('146', '143', 'JVM信息资源', '/monitor/performance/jvm/*', 'GET', 'monitor:performance:jvm:resource', '3', '0', null, '1', '2019-07-21 16:03:37', '1', '2019-07-21 19:04:47');
INSERT INTO `sys_menu` VALUES ('147', '144', 'Tomcat信息资源', '/monitor/performance/tomcat/*', 'GET', 'monitor:performance:tomcat:resource', '3', '0', null, '1', '2019-07-21 16:04:07', '1', '2019-07-21 19:04:53');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `parent_id` bigint(20) DEFAULT '0',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '祖级列表',
  `org_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '组织名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `creator_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modifier_id` bigint(20) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织管理';

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
INSERT INTO `sys_org` VALUES ('21', '0', '0', '测试下', '1', '1', '2019-05-31 23:59:24', null, null);
INSERT INTO `sys_org` VALUES ('22', '21', '0,21', '再次测试下', '1', '1', '2019-05-31 23:59:38', null, null);
INSERT INTO `sys_org` VALUES ('25', '21', '0,21', 'sad', '1', '1', '2019-07-07 17:47:59', '1', '2019-07-07 17:47:59');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '岗位名称',
  `post_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '岗位编码',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='岗位管理';

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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '【系统内置】', '1', '2017-12-20 14:34:21', null, null);
INSERT INTO `sys_role` VALUES ('29', '只读角色', 'read-only', null, '1', '2019-03-30 15:29:25', '1', '2019-04-05 00:36:35');
INSERT INTO `sys_role` VALUES ('30', '测试角色', 'test', '测试角色1', '1', '2019-05-31 22:52:37', '1', '2019-05-31 22:54:24');
INSERT INTO `sys_role` VALUES ('31', 'zaishiyici', 'zaishiyici', '', '1', '2019-07-07 17:44:45', '1', '2019-07-07 17:44:59');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1417 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色菜单关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1046', '30', '66');
INSERT INTO `sys_role_menu` VALUES ('1047', '30', '125');
INSERT INTO `sys_role_menu` VALUES ('1048', '30', '126');
INSERT INTO `sys_role_menu` VALUES ('1049', '30', '127');
INSERT INTO `sys_role_menu` VALUES ('1268', '1', '66');
INSERT INTO `sys_role_menu` VALUES ('1269', '1', '67');
INSERT INTO `sys_role_menu` VALUES ('1270', '1', '68');
INSERT INTO `sys_role_menu` VALUES ('1271', '1', '69');
INSERT INTO `sys_role_menu` VALUES ('1272', '1', '70');
INSERT INTO `sys_role_menu` VALUES ('1273', '1', '71');
INSERT INTO `sys_role_menu` VALUES ('1274', '1', '72');
INSERT INTO `sys_role_menu` VALUES ('1275', '1', '85');
INSERT INTO `sys_role_menu` VALUES ('1276', '1', '86');
INSERT INTO `sys_role_menu` VALUES ('1277', '1', '87');
INSERT INTO `sys_role_menu` VALUES ('1278', '1', '88');
INSERT INTO `sys_role_menu` VALUES ('1279', '1', '134');
INSERT INTO `sys_role_menu` VALUES ('1280', '1', '73');
INSERT INTO `sys_role_menu` VALUES ('1281', '1', '74');
INSERT INTO `sys_role_menu` VALUES ('1282', '1', '75');
INSERT INTO `sys_role_menu` VALUES ('1283', '1', '76');
INSERT INTO `sys_role_menu` VALUES ('1284', '1', '77');
INSERT INTO `sys_role_menu` VALUES ('1285', '1', '78');
INSERT INTO `sys_role_menu` VALUES ('1286', '1', '89');
INSERT INTO `sys_role_menu` VALUES ('1287', '1', '99');
INSERT INTO `sys_role_menu` VALUES ('1288', '1', '100');
INSERT INTO `sys_role_menu` VALUES ('1289', '1', '79');
INSERT INTO `sys_role_menu` VALUES ('1290', '1', '80');
INSERT INTO `sys_role_menu` VALUES ('1291', '1', '81');
INSERT INTO `sys_role_menu` VALUES ('1292', '1', '82');
INSERT INTO `sys_role_menu` VALUES ('1293', '1', '83');
INSERT INTO `sys_role_menu` VALUES ('1294', '1', '84');
INSERT INTO `sys_role_menu` VALUES ('1295', '1', '90');
INSERT INTO `sys_role_menu` VALUES ('1296', '1', '101');
INSERT INTO `sys_role_menu` VALUES ('1297', '1', '102');
INSERT INTO `sys_role_menu` VALUES ('1298', '1', '103');
INSERT INTO `sys_role_menu` VALUES ('1299', '1', '104');
INSERT INTO `sys_role_menu` VALUES ('1300', '1', '105');
INSERT INTO `sys_role_menu` VALUES ('1301', '1', '106');
INSERT INTO `sys_role_menu` VALUES ('1302', '1', '107');
INSERT INTO `sys_role_menu` VALUES ('1303', '1', '125');
INSERT INTO `sys_role_menu` VALUES ('1304', '1', '126');
INSERT INTO `sys_role_menu` VALUES ('1305', '1', '127');
INSERT INTO `sys_role_menu` VALUES ('1306', '1', '128');
INSERT INTO `sys_role_menu` VALUES ('1307', '1', '129');
INSERT INTO `sys_role_menu` VALUES ('1308', '1', '130');
INSERT INTO `sys_role_menu` VALUES ('1309', '1', '131');
INSERT INTO `sys_role_menu` VALUES ('1310', '1', '133');
INSERT INTO `sys_role_menu` VALUES ('1311', '1', '110');
INSERT INTO `sys_role_menu` VALUES ('1312', '1', '117');
INSERT INTO `sys_role_menu` VALUES ('1313', '1', '118');
INSERT INTO `sys_role_menu` VALUES ('1314', '1', '119');
INSERT INTO `sys_role_menu` VALUES ('1315', '1', '112');
INSERT INTO `sys_role_menu` VALUES ('1316', '1', '115');
INSERT INTO `sys_role_menu` VALUES ('1317', '1', '116');
INSERT INTO `sys_role_menu` VALUES ('1318', '1', '135');
INSERT INTO `sys_role_menu` VALUES ('1319', '1', '136');
INSERT INTO `sys_role_menu` VALUES ('1320', '1', '137');
INSERT INTO `sys_role_menu` VALUES ('1321', '1', '120');
INSERT INTO `sys_role_menu` VALUES ('1322', '1', '132');
INSERT INTO `sys_role_menu` VALUES ('1323', '1', '122');
INSERT INTO `sys_role_menu` VALUES ('1324', '1', '123');
INSERT INTO `sys_role_menu` VALUES ('1325', '1', '124');
INSERT INTO `sys_role_menu` VALUES ('1326', '1', '141');
INSERT INTO `sys_role_menu` VALUES ('1327', '1', '142');
INSERT INTO `sys_role_menu` VALUES ('1328', '1', '145');
INSERT INTO `sys_role_menu` VALUES ('1329', '1', '143');
INSERT INTO `sys_role_menu` VALUES ('1330', '1', '146');
INSERT INTO `sys_role_menu` VALUES ('1331', '1', '144');
INSERT INTO `sys_role_menu` VALUES ('1332', '1', '147');
INSERT INTO `sys_role_menu` VALUES ('1333', '1', '109');
INSERT INTO `sys_role_menu` VALUES ('1375', '29', '79');
INSERT INTO `sys_role_menu` VALUES ('1376', '29', '80');
INSERT INTO `sys_role_menu` VALUES ('1377', '29', '66');
INSERT INTO `sys_role_menu` VALUES ('1378', '29', '67');
INSERT INTO `sys_role_menu` VALUES ('1379', '29', '68');
INSERT INTO `sys_role_menu` VALUES ('1380', '29', '102');
INSERT INTO `sys_role_menu` VALUES ('1381', '29', '103');
INSERT INTO `sys_role_menu` VALUES ('1382', '29', '107');
INSERT INTO `sys_role_menu` VALUES ('1383', '29', '81');
INSERT INTO `sys_role_menu` VALUES ('1384', '29', '74');
INSERT INTO `sys_role_menu` VALUES ('1385', '29', '75');
INSERT INTO `sys_role_menu` VALUES ('1386', '29', '100');
INSERT INTO `sys_role_menu` VALUES ('1387', '29', '73');
INSERT INTO `sys_role_menu` VALUES ('1388', '29', '90');
INSERT INTO `sys_role_menu` VALUES ('1389', '29', '69');
INSERT INTO `sys_role_menu` VALUES ('1390', '29', '101');
INSERT INTO `sys_role_menu` VALUES ('1391', '29', '112');
INSERT INTO `sys_role_menu` VALUES ('1392', '29', '115');
INSERT INTO `sys_role_menu` VALUES ('1393', '29', '116');
INSERT INTO `sys_role_menu` VALUES ('1394', '29', '109');
INSERT INTO `sys_role_menu` VALUES ('1395', '29', '110');
INSERT INTO `sys_role_menu` VALUES ('1396', '29', '117');
INSERT INTO `sys_role_menu` VALUES ('1397', '29', '120');
INSERT INTO `sys_role_menu` VALUES ('1398', '29', '122');
INSERT INTO `sys_role_menu` VALUES ('1399', '29', '123');
INSERT INTO `sys_role_menu` VALUES ('1400', '29', '125');
INSERT INTO `sys_role_menu` VALUES ('1401', '29', '126');
INSERT INTO `sys_role_menu` VALUES ('1402', '29', '127');
INSERT INTO `sys_role_menu` VALUES ('1403', '29', '132');
INSERT INTO `sys_role_menu` VALUES ('1404', '29', '133');
INSERT INTO `sys_role_menu` VALUES ('1405', '29', '134');
INSERT INTO `sys_role_menu` VALUES ('1406', '29', '135');
INSERT INTO `sys_role_menu` VALUES ('1407', '29', '136');
INSERT INTO `sys_role_menu` VALUES ('1408', '29', '137');
INSERT INTO `sys_role_menu` VALUES ('1409', '29', '141');
INSERT INTO `sys_role_menu` VALUES ('1410', '29', '142');
INSERT INTO `sys_role_menu` VALUES ('1411', '29', '143');
INSERT INTO `sys_role_menu` VALUES ('1412', '29', '144');
INSERT INTO `sys_role_menu` VALUES ('1413', '29', '145');
INSERT INTO `sys_role_menu` VALUES ('1414', '29', '147');
INSERT INTO `sys_role_menu` VALUES ('1415', '29', '146');
INSERT INTO `sys_role_menu` VALUES ('1416', '29', '124');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '3', 'admin', '941f0f074eeeb6ffbf1abb4d22a6be97', 'Administrator', '736720794@qq.com', '17621776714', '/avatar.png', 'dark', '6', '0', '1', '2017-12-08 10:56:35', '1', '2019-07-20 00:53:06');
INSERT INTO `sys_user` VALUES ('3', null, 'zhangsan', '111', null, null, null, null, null, null, '1', '1', null, '1', '2019-03-28 23:11:01');
INSERT INTO `sys_user` VALUES ('5', null, 'wangwu11', '333', null, '736720794@qq.com', '17621776714', null, null, null, '0', null, null, '1', '2019-03-16 15:40:04');
INSERT INTO `sys_user` VALUES ('6', null, 'zhaoliu', '444', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('8', null, 'tongzhou', '666', null, null, null, null, null, null, '1', null, null, '1', '2019-03-16 17:33:01');
INSERT INTO `sys_user` VALUES ('9', null, 'zhuhaihui', '444', null, null, null, null, null, null, '0', null, null, null, null);
INSERT INTO `sys_user` VALUES ('10', null, 'baiyunfei', '888', null, null, null, null, null, null, '0', null, null, null, null);
INSERT INTO `sys_user` VALUES ('42', null, 'root', '80ff5f651f60c4156ee73e47c88defbd', 'root', '736720794@qq.com', '17621776714', null, null, null, '1', '1', '2019-03-12 22:13:11', '1', '2019-04-03 00:59:15');
INSERT INTO `sys_user` VALUES ('51', null, 'zhao7367201', '80835b28d5bf834c2f0963bb2868bca9', '赵正浩', '736720794@qq.com', '17621776714', null, null, null, '1', '1', '2019-03-16 15:09:11', '1', '2019-04-03 00:44:21');
INSERT INTO `sys_user` VALUES ('52', null, 'zhao7367202', '654b6ae60f71f879548fe3235480b985', null, '736720794@qq.com', '17621776714', null, null, null, '1', '1', '2019-03-16 15:38:20', null, null);
INSERT INTO `sys_user` VALUES ('53', '0', 'superadmin', '9aecc0033519b67dd62926d778c8f53c', '哈哈哈哈hahaha', '736720794@qq.com', null, null, null, null, '0', '1', '2019-03-16 15:39:16', '1', '2019-05-06 21:46:18');
INSERT INTO `sys_user` VALUES ('54', null, 'superadmin01', '9aecc0033519b67dd62926d778c8f53c', null, '736720794@qq.cn', '17621776714', null, null, null, '0', '1', '2019-03-16 15:39:41', '1', '2019-03-16 16:34:15');
INSERT INTO `sys_user` VALUES ('56', null, 'superadmin02', 'fa369ed06209bed827521b5b6701b2ec', null, '736720794@qq.com', '17621776714', null, null, null, '0', '1', '2019-03-16 16:30:36', '1', '2019-03-16 16:53:56');
INSERT INTO `sys_user` VALUES ('57', null, 'root01', '81423985da4b8f0543d657651294f003', null, null, null, null, null, null, '0', '1', '2019-03-16 17:32:54', null, null);
INSERT INTO `sys_user` VALUES ('58', '6', 'tangxiaolu', 'c3b9df74a1ab68052d240eeb0053a1f5', '糖葫芦', '736720794@qq.com', null, '/uploads/-/system/user/avatar/58/avatar.jpg', 'light', '1', '0', '1', '2019-03-28 22:47:10', '1', '2019-05-22 22:20:30');
INSERT INTO `sys_user` VALUES ('59', null, 'superadmin-001', 'f081134f60dfc5b2620eaa9590c9b59b', '赵正浩', '736720794@qq.com', null, null, null, null, '0', '1', '2019-03-30 15:55:12', null, null);
INSERT INTO `sys_user` VALUES ('62', '3', 'hahaha', 'dc5b0d0a5fdf0fd2c26d046a018b332b', 'hahaha', '736720794@qq.com', '17621776714', null, null, null, '0', '1', '2019-04-03 00:44:06', '1', '2019-07-19 21:44:58');
INSERT INTO `sys_user` VALUES ('63', '3', 'zhaozhenghao', '5876302d3ef940ecdf9c729df3f42a7e', '赵正浩', '736720794@qq.com', '17621776714', '/uploads/-/system/user/avatar/63/avatar.jpg', 'light', '8', '0', '1', '2019-04-04 23:19:18', '63', '2019-07-20 22:07:38');
INSERT INTO `sys_user` VALUES ('65', '20', 'test', '5bbc8e52e9486d60015db33fcf33487f', '测试账号', '736720794@qq.com', '17621776714', '/avatar.png', null, '4', '0', '1', '2019-05-31 22:42:27', '1', '2019-05-31 22:53:20');
INSERT INTO `sys_user` VALUES ('66', '3', 'release', '4152b19ff712dcd036a9073b013445da', '最后一次测试', '736720794@qq.com', '17621776714', '/avatar.png', null, null, '0', '1', '2019-06-08 22:19:12', '1', '2019-06-08 22:24:05');
INSERT INTO `sys_user` VALUES ('67', '0', 'zhaozhenghaotest', '49be13a6143c0439f21faaf90cd90544', 'aaa', null, null, '/avatar.png', null, null, '0', '1', '2019-07-07 15:50:28', null, null);
INSERT INTO `sys_user` VALUES ('68', '22', 'demo2', '85df9a7e85cdba561cd0c1d748330d0a', 'demo2', '736720794@qq.com', '13612345676', '/avatar.png', null, null, '0', '1', '2019-07-07 17:40:48', '1', '2019-07-07 17:41:26');
INSERT INTO `sys_user` VALUES ('77', '0', 'hhhhh', 'd09b6695accffbc654e918354276b370', 'aaaaa', null, null, '/avatar.png', null, null, '0', '1', '2019-07-11 21:17:50', null, null);
INSERT INTO `sys_user` VALUES ('78', '0', 'asdasda', 'c049a4132303b3409ba99ce19a0b1142', 'asdsad', null, null, '/avatar.png', null, null, '0', '1', '2019-07-11 21:36:39', null, null);
INSERT INTO `sys_user` VALUES ('79', '22', 'test01', '409c37d139129425f978bd989eb59924', '赵正浩', '736720794@qq.com', '17621666666', '/uploads/-/system/user/avatar/79/avatar.png', 'light', '4', '0', '1', '2019-07-20 00:28:41', '79', '2019-07-20 00:49:53');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('9', '63', '4');
INSERT INTO `sys_user_post` VALUES ('10', '63', '9');
INSERT INTO `sys_user_post` VALUES ('11', '58', '8');
INSERT INTO `sys_user_post` VALUES ('19', '65', '6');
INSERT INTO `sys_user_post` VALUES ('20', '65', '5');
INSERT INTO `sys_user_post` VALUES ('21', '65', '7');
INSERT INTO `sys_user_post` VALUES ('24', '66', '9');
INSERT INTO `sys_user_post` VALUES ('25', '66', '6');
INSERT INTO `sys_user_post` VALUES ('28', '68', '4');
INSERT INTO `sys_user_post` VALUES ('29', '68', '5');
INSERT INTO `sys_user_post` VALUES ('30', '62', '6');
INSERT INTO `sys_user_post` VALUES ('31', '62', '7');
INSERT INTO `sys_user_post` VALUES ('42', '79', '7');
INSERT INTO `sys_user_post` VALUES ('43', '79', '6');
INSERT INTO `sys_user_post` VALUES ('45', '1', '3');
INSERT INTO `sys_user_post` VALUES ('46', '1', '9');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('27', '63', '29');
INSERT INTO `sys_user_role` VALUES ('28', '58', '29');
INSERT INTO `sys_user_role` VALUES ('33', '65', '29');
INSERT INTO `sys_user_role` VALUES ('34', '65', '30');
INSERT INTO `sys_user_role` VALUES ('36', '66', '30');
INSERT INTO `sys_user_role` VALUES ('37', '66', '29');
INSERT INTO `sys_user_role` VALUES ('39', '68', '30');
INSERT INTO `sys_user_role` VALUES ('40', '62', '29');
INSERT INTO `sys_user_role` VALUES ('50', '79', '29');
INSERT INTO `sys_user_role` VALUES ('51', '79', '1');
INSERT INTO `sys_user_role` VALUES ('52', '1', '1');
INSERT INTO `sys_user_role` VALUES ('53', '1', '30');
