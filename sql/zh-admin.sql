/*
Navicat MySQL Data Transfer

Source Server         : zhaozhenghao
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : zh-admin

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-06-01 21:05:00
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, 'sys', '0', '0', null, '1', '2019-06-01 18:34:08', null, null);
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', null, null, 'sys:user', '1', '0', null, '1', '2019-06-01 18:35:30', null, null);
INSERT INTO `sys_menu` VALUES ('3', '2', '列表', '/api/admin/sys/user', 'GET', 'sys:user:view', '3', '0', '系统用户列表', '1', '2019-06-01 20:14:04', null, null);
INSERT INTO `sys_menu` VALUES ('4', '2', '展示', '/api/admin/sys/user/{id}', 'GET', 'sys:user:info', '3', '1', '系统用户展示', '1', '2019-06-01 20:14:06', null, null);
INSERT INTO `sys_menu` VALUES ('5', '2', '详情', '/api/admin/sys/user/{id}/detail', 'GET', 'sys:user:detail', '3', '2', '系统用户详情', '1', '2019-06-01 20:14:08', null, null);
INSERT INTO `sys_menu` VALUES ('6', '2', '新增', '/api/admin/sys/user', 'POST', 'sys:user:save', '2', '3', '系统用户新增', '1', '2019-06-01 20:14:10', null, null);
INSERT INTO `sys_menu` VALUES ('7', '2', '修改', '/api/admin/sys/user/{id}', 'PUT', 'sys:user:edit', '2', '4', '系统用户修改', '1', '2019-06-01 20:14:12', null, null);
INSERT INTO `sys_menu` VALUES ('8', '2', '删除', '/api/admin/sys/user/{id}', 'DELETE', 'sys:user:remove', '2', '5', '系统用户删除', '1', '2019-06-01 20:14:16', null, null);
INSERT INTO `sys_menu` VALUES ('9', '2', '批量删除', '/api/admin/sys/user', 'DELETE', 'sys:user:batch', '2', '6', '系统用户批量删除', '1', '2019-06-01 20:14:23', null, null);
INSERT INTO `sys_menu` VALUES ('10', '2', '批量启用', '/api/admin/sys/user/enable', 'PUT', 'sys:user:enable', '2', '7', '系统用户启用账号', '1', '2019-06-01 20:14:25', null, null);
INSERT INTO `sys_menu` VALUES ('11', '2', '批量锁定', '/api/admin/sys/user/disable', 'PUT', 'sys:user:disable', '2', '8', '系统用户锁定账号', '1', '2019-06-01 20:14:28', null, null);
INSERT INTO `sys_menu` VALUES ('12', '2', '重置密码', '/api/admin/sys/user/{id}/reset', 'PUT', 'sys:user:reset', '2', '9', '系统用户重置密码', '1', '2019-06-01 20:14:30', null, null);
INSERT INTO `sys_menu` VALUES ('13', '1', '角色管理', null, null, 'sys:role', '1', '1', null, '1', '2019-06-01 20:14:33', null, null);
INSERT INTO `sys_menu` VALUES ('14', '13', '列表', '/api/admin/sys/role', 'GET', 'sys:role:view', '3', '0', '系统角色列表', '1', '2019-06-01 20:14:35', null, null);
INSERT INTO `sys_menu` VALUES ('15', '13', '展示', '/api/admin/sys/role/{id}', 'GET', 'sys:role:info', '3', '1', '系统角色详情', '1', '2019-06-01 20:14:37', null, null);
INSERT INTO `sys_menu` VALUES ('16', '13', '角色选择', '/api/admin/sys/role/select', 'GET', 'sys:role:select', '3', '2', '系统角色选择列表', '1', '2019-06-01 20:14:40', null, null);
INSERT INTO `sys_menu` VALUES ('17', '13', '新增', '/api/admin/sys/role', 'POST', 'sys:role:save', '2', '3', '系统角色新增', '1', '2019-06-01 20:14:42', null, null);
INSERT INTO `sys_menu` VALUES ('18', '13', '修改', '/api/admin/sys/role/{id}', 'PUT', 'sys:role:edit', '2', '4', '系统角色修改', '1', '2019-06-01 20:14:44', null, null);
INSERT INTO `sys_menu` VALUES ('19', '13', '删除', '/api/admin/sys/role/{id}', 'DELETE', 'sys:role:remove', '2', '5', '系统角色删除', '1', '2019-06-01 20:14:47', null, null);
INSERT INTO `sys_menu` VALUES ('20', '13', '批量删除', '/api/admin/sys/role', 'DELETE', 'sys:role:batch', '2', '6', '系统角色批量删除', '1', '2019-06-01 20:14:49', null, null);
INSERT INTO `sys_menu` VALUES ('21', '13', '分配权限', '/api/admin/sys/role/{id}/authorize', 'PUT', 'sys:role:authorize', '2', '7', '系统角色授权', '1', '2019-06-01 20:14:53', null, null);
INSERT INTO `sys_menu` VALUES ('22', '1', '权限管理', null, null, 'sys:menu', '1', '2', null, '1', '2019-06-01 20:14:56', null, null);
INSERT INTO `sys_menu` VALUES ('23', '22', '列表', '/api/admin/sys/menu', 'GET', 'sys:menu:view', '3', '0', '系统菜单列表', '1', '2019-06-01 20:14:58', null, null);
INSERT INTO `sys_menu` VALUES ('24', '22', '展示', '/api/admin/sys/menu/{id}', 'GET', 'sys:menu:info', '3', '1', '系统菜单详情', '1', '2019-06-01 20:15:01', null, null);
INSERT INTO `sys_menu` VALUES ('25', '22', '菜单树列表', '/api/sys/menu/tree', 'GET', 'sys:menu:tree', '3', '2', '系统菜单树列表', '1', '2019-06-01 20:15:05', null, null);
INSERT INTO `sys_menu` VALUES ('26', '22', '新增', '/api/admin/sys/menu', 'POST', 'sys:menu:save', '2', '3', '系统菜单新增', '1', '2019-06-01 20:15:07', null, null);
INSERT INTO `sys_menu` VALUES ('27', '22', '修改', '/api/admin/sys/menu/{id}', 'PUT', 'sys:menu:edit', '2', '4', '系统菜单修改', '1', '2019-06-01 20:15:10', null, null);
INSERT INTO `sys_menu` VALUES ('28', '22', '删除', '/api/admin/sys/menu/{id}', 'DELETE', 'sys:menu:remove', '2', '5', '系统菜单删除', '1', '2019-06-01 20:15:13', null, null);
INSERT INTO `sys_menu` VALUES ('29', '1', '组织管理', null, null, 'sys:org', '1', '3', null, '1', '2019-06-01 20:15:15', null, null);
INSERT INTO `sys_menu` VALUES ('30', '29', '列表', '/api/admin/sys/org', 'GET', 'sys:org:view', '3', '0', '系统组织列表', '1', '2019-06-01 20:15:17', null, null);
INSERT INTO `sys_menu` VALUES ('31', '29', '展示', '/api/admin/sys/org/{id}', 'GET', 'sys:org:info', '3', '1', '系统组织详情', '1', '2019-06-01 20:15:19', null, null);
INSERT INTO `sys_menu` VALUES ('32', '29', '组织树列表', '/api/admin/sys/org/tree', 'GET', 'sys:org:tree', '3', '2', '系统组织树列表', '1', '2019-06-01 20:15:22', null, null);
INSERT INTO `sys_menu` VALUES ('33', '29', '新增', '/api/admin/sys/org', 'POST', 'sys:org:save', '2', '3', '系统组织新增', '1', '2019-06-01 20:15:24', null, null);
INSERT INTO `sys_menu` VALUES ('34', '29', '修改', '/api/admin/sys/org/{id}', 'PUT', 'sys:org:edit', '2', '4', '系统组织修改', '1', '2019-06-01 20:15:26', null, null);
INSERT INTO `sys_menu` VALUES ('35', '29', '删除', '/api/admin/sys/org/{id}', 'DELETE', 'sys:org:remove', '2', '5', '系统组织删除', '1', '2019-06-01 20:15:28', null, null);
INSERT INTO `sys_menu` VALUES ('36', '1', '岗位管理', null, null, 'sys:post', '1', '4', null, '1', '2019-06-01 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('37', '36', '列表', '/api/admin/sys/post', 'GET', 'sys:post:view', '3', '0', '系统岗位列表', '1', '2019-06-01 20:15:35', null, null);
INSERT INTO `sys_menu` VALUES ('38', '36', '展示', '/api/admin/sys/post/{id}', 'GET', 'sys:post:info', '3', '1', '系统岗位展示', '1', '2019-06-01 20:15:37', null, null);
INSERT INTO `sys_menu` VALUES ('39', '36', '岗位选择', '/api/admin/sys/post/select', 'GET', 'sys:post:select', '3', '2', '系统岗位选择列表', '1', '2019-06-01 20:15:39', null, null);
INSERT INTO `sys_menu` VALUES ('40', '36', '新增', '/api/admin/sys/post', 'POST', 'sys:post:save', '2', '3', '系统岗位新增', '1', '2019-06-01 20:15:42', null, null);
INSERT INTO `sys_menu` VALUES ('41', '36', '修改', '/api/admin/sys/post/{id}', 'PUT', 'sys:post:edit', '2', '4', '系统岗位修改', '1', '2019-06-01 20:15:45', null, null);
INSERT INTO `sys_menu` VALUES ('42', '36', '删除', '/api/admin/sys/post/{id}', 'DELETE', 'sys:post:remove', '2', '5', '系统岗位删除', '1', '2019-06-01 20:15:47', null, null);
INSERT INTO `sys_menu` VALUES ('43', '36', '批量删除', '/api/admin/sys/post', 'DELETE', 'sys:post:batch', '2', '6', '系统岗位批量删除', '1', '2019-06-01 20:15:50', null, null);
INSERT INTO `sys_menu` VALUES ('44', '1', '数据字典', null, null, 'sys:dict', '1', '4', null, '1', '2021-10-23 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('45', '44', '列表', '/api/admin/sys/dict', 'GET', 'sys:dict:view', '3', '0', '数据字典列表', '1', '2021-10-23 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('46', '44', '展示', '/api/admin/sys/dict/{id}', 'GET', 'sys:dict:info', '3', '1', '数据字典展示', '1', '2021-10-23 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('48', '44', '新增', '/api/admin/sys/dict', 'POST', 'sys:dict:save', '2', '3', '数据字典新增', '1', '2021-10-23 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('49', '44', '修改', '/api/admin/sys/dict/{id}', 'PUT', 'sys:dict:edit', '2', '4', '数据字典修改', '1', '2021-10-23 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('50', '44', '删除', '/api/admin/sys/dict/{id}', 'DELETE', 'sys:dict:remove', '2', '5', '数据字典删除', '1', '2021-10-23 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('51', '44', '字典项列表', '/api/admin/sys/dict/{dictCode}/item', 'GET', 'sys:dict:item:view', '3', '0', '数据字典项列表', '1', '2021-10-23 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('52', '44', '字典项展示', '/api/admin/sys/dict/{dictCode}/item/{id}', 'GET', 'sys:dict:item:info', '3', '1', '数据字典项展示', '1', '2021-10-23 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('53', '44', '字典项新增', '/api/admin/sys/dict/{dictCode}/item', 'POST', 'sys:dict:item:save', '2', '3', '数据字典项新增', '1', '2021-10-23 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('54', '44', '字典项修改', '/api/admin/sys/dict/{dictCode}/item/{id}', 'PUT', 'sys:dict:item:edit', '2', '4', '数据字典项修改', '1', '2021-10-23 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('55', '44', '字典项删除', '/api/admin/sys/dict/{dictCode}/item/{id}', 'DELETE', 'sys:dict:item:remove', '2', '5', '数据字典项删除', '1', '2021-10-23 20:15:32', null, null);
INSERT INTO `sys_menu` VALUES ('56', '0', '系统监控', null, null, 'monitor', '0', '1', null, '1', '2019-06-01 20:15:53', null, null);
INSERT INTO `sys_menu` VALUES ('57', '56', '日志管理', null, null, 'monitor:log', '1', '0', null, '1', '2019-06-01 20:15:55', null, null);
INSERT INTO `sys_menu` VALUES ('58', '57', '列表', '/api/admin/monitor/log', 'GET', 'monitor:log:view', '3', '0', '系统日志列表', '1', '2019-06-01 20:15:57', null, null);
INSERT INTO `sys_menu` VALUES ('59', '57', '批量删除', '/api/admin/monitor/log', 'DELETE', 'monitor:log:batch', '2', '1', '系统日志批量删除', '1', '2019-06-01 20:16:00', null, null);
INSERT INTO `sys_menu` VALUES ('60', '57', '清空', '/api/admin/monitor/log/{type}/clear', 'DELETE', 'monitor:log:clear', '2', '2', '系统日志清空日志', '1', '2019-06-01 20:16:02', null, null);
INSERT INTO `sys_menu` VALUES ('61', '56', '服务器监控', null, null, 'monitor:server', '1', '1', null, '1', '2019-06-01 20:16:05', null, null);
INSERT INTO `sys_menu` VALUES ('62', '61', '基础信息', '/api/admin/monitor/server/base', 'GET', 'monitor:server:base', '3', '0', '服务器监控基础数据', '1', '2019-06-01 20:16:08', null, null);
INSERT INTO `sys_menu` VALUES ('63', '61', '实时信息', '/api/admin/monitor/server/instant', 'GET', 'monitor:server:instant', '3', '1', '服务器监控实时数据', '1', '2019-06-01 20:16:10', null, null);
INSERT INTO `sys_menu` VALUES ('64', '56', '性能监控', '', '', 'monitor:performance', '1', '2', null, '1', '2019-07-21 15:58:53', null, null);
INSERT INTO `sys_menu` VALUES ('65', '64', '系统信息', '', '', 'monitor:performance:system', '1', '0', null, '1', '2019-07-21 15:59:28', null, null);
INSERT INTO `sys_menu` VALUES ('66', '64', 'JVM信息', '', '', 'monitor:performance:jvm', '1', '1', null, '1', '2019-07-21 16:00:06', null, null);
INSERT INTO `sys_menu` VALUES ('67', '64', 'Tomcat信息', '', '', 'monitor:performance:tomcat', '1', '2', null, '1', '2019-07-21 16:00:23', '1', '2019-07-21 16:00:29');
INSERT INTO `sys_menu` VALUES ('68', '65', '系统信息资源', '/api/admin/monitor/performance/system/*', 'GET', 'monitor:performance:system:resource', '3', '0', null, '1', '2019-07-21 16:02:49', '1', '2019-07-21 19:04:39');
INSERT INTO `sys_menu` VALUES ('69', '66', 'JVM信息资源', '/api/admin/monitor/performance/jvm/*', 'GET', 'monitor:performance:jvm:resource', '3', '0', null, '1', '2019-07-21 16:03:37', '1', '2019-07-21 19:04:47');
INSERT INTO `sys_menu` VALUES ('70', '67', 'Tomcat信息资源', '/api/admin/monitor/performance/tomcat/*', 'GET', 'monitor:performance:tomcat:resource', '3', '0', null, '1', '2019-07-21 16:04:07', '1', '2019-07-21 19:04:53');
INSERT INTO `sys_menu` VALUES ('71', '0', '开发工具', null, null, 'tool', '0', '2', null, '1', '2019-06-01 20:16:19', null, null);
INSERT INTO `sys_menu` VALUES ('72', '71', 'IconSelector', null, null, 'tool:icon', '1', '0', null, '1', '2019-06-01 20:16:21', null, null);
INSERT INTO `sys_menu` VALUES ('73', '71', '代码生成器', null, null, 'tool:generator', '1', '1', null, '1', '2019-06-01 20:16:23', null, null);
INSERT INTO `sys_menu` VALUES ('74', '73', '列表', '/api/admin/tool/generator', 'GET', 'tool:generator:view', '3', '0', '代码生成器列表，展示所有数据库表信息', '1', '2019-06-01 20:16:25', null, null);
INSERT INTO `sys_menu` VALUES ('75', '73', '生成代码', '/api/admin/tool/generator/code', 'GET', 'tool:generator:code', '2', '1', '生成代码', '1', '2019-06-01 20:16:28', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织管理';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('1', '0', '0', '研发中心', '0', '1', '2019-06-01 20:35:00', null, '2019-06-01 20:35:00');
INSERT INTO `sys_org` VALUES ('2', '1', '0,1', '后端组', '0', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('3', '2', '0,1,2', 'JAVA', '0', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('4', '2', '0,1,2', 'PHP', '1', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('5', '2', '0,1,2', 'Golang', '2', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('6', '1', '0,1', '前端组', '1', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('7', '6', '0,1,6', 'React', '0', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('8', '6', '0,1,6', 'Vue', '1', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('9', '6', '0,1,6', 'Angular', '2', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('10', '1', '0,1', '测试组', '2', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('11', '10', '0,1,10', '功能测试', '0', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('12', '10', '0,1,10', '安全测试', '1', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('13', '0', '0', '财务部', '1', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('14', '13', '0,13', '会计核算', '0', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('15', '13', '0,13', '成本控制', '1', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('16', '13', '0,13', '内部控制', '2', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('17', '16', '0,13,16', '财务制度建设', '0', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');
INSERT INTO `sys_org` VALUES ('18', '16', '0,13,16', '审计', '1', '1', '2019-06-01 20:35:06', null, '2019-06-01 20:35:06');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '岗位名称',
  `post_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '岗位编码',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='岗位管理';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', '董事长', 'ceo', '0', '1', '2019-06-01 20:40:00', null, null);
INSERT INTO `sys_post` VALUES ('2', '项目经理', 'pm', '1', '1', '2019-06-01 20:40:02', null, null);
INSERT INTO `sys_post` VALUES ('3', '人力资源', 'hr', '2', '1', '2019-06-01 20:40:06', null, null);
INSERT INTO `sys_post` VALUES ('4', '普通员工', 'user', '3', '1', '2019-06-01 20:40:08', null, null);
INSERT INTO `sys_post` VALUES ('5', '会计', 'accountant', '4', '1', '2019-06-01 20:40:10', null, null);
INSERT INTO `sys_post` VALUES ('6', '前端工程师', 'fe', '5', '1', '2019-06-01 20:40:13', null, null);
INSERT INTO `sys_post` VALUES ('7', '后端工程师', 'rd', '6', '1', '2019-06-01 20:40:15', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '【系统内置】', '1', '2019-06-01 15:58:35', null, null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色菜单关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('13', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('14', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('15', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('16', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('17', '1', '17');
INSERT INTO `sys_role_menu` VALUES ('18', '1', '18');
INSERT INTO `sys_role_menu` VALUES ('19', '1', '19');
INSERT INTO `sys_role_menu` VALUES ('20', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('21', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('22', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('23', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('24', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('25', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('26', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('27', '1', '27');
INSERT INTO `sys_role_menu` VALUES ('28', '1', '28');
INSERT INTO `sys_role_menu` VALUES ('29', '1', '29');
INSERT INTO `sys_role_menu` VALUES ('30', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('31', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('32', '1', '32');
INSERT INTO `sys_role_menu` VALUES ('33', '1', '33');
INSERT INTO `sys_role_menu` VALUES ('34', '1', '34');
INSERT INTO `sys_role_menu` VALUES ('35', '1', '35');
INSERT INTO `sys_role_menu` VALUES ('36', '1', '36');
INSERT INTO `sys_role_menu` VALUES ('37', '1', '37');
INSERT INTO `sys_role_menu` VALUES ('38', '1', '38');
INSERT INTO `sys_role_menu` VALUES ('39', '1', '39');
INSERT INTO `sys_role_menu` VALUES ('40', '1', '40');
INSERT INTO `sys_role_menu` VALUES ('41', '1', '41');
INSERT INTO `sys_role_menu` VALUES ('42', '1', '42');
INSERT INTO `sys_role_menu` VALUES ('43', '1', '43');
INSERT INTO `sys_role_menu` VALUES ('44', '1', '44');
INSERT INTO `sys_role_menu` VALUES ('45', '1', '45');
INSERT INTO `sys_role_menu` VALUES ('46', '1', '46');
INSERT INTO `sys_role_menu` VALUES ('47', '1', '47');
INSERT INTO `sys_role_menu` VALUES ('48', '1', '48');
INSERT INTO `sys_role_menu` VALUES ('49', '1', '49');
INSERT INTO `sys_role_menu` VALUES ('50', '1', '50');
INSERT INTO `sys_role_menu` VALUES ('51', '1', '51');
INSERT INTO `sys_role_menu` VALUES ('52', '1', '52');
INSERT INTO `sys_role_menu` VALUES ('53', '1', '53');
INSERT INTO `sys_role_menu` VALUES ('54', '1', '54');
INSERT INTO `sys_role_menu` VALUES ('55', '1', '55');
INSERT INTO `sys_role_menu` VALUES ('56', '1', '56');
INSERT INTO `sys_role_menu` VALUES ('57', '1', '57');
INSERT INTO `sys_role_menu` VALUES ('58', '1', '58');
INSERT INTO `sys_role_menu` VALUES ('59', '1', '59');
INSERT INTO `sys_role_menu` VALUES ('60', '1', '60');
INSERT INTO `sys_role_menu` VALUES ('61', '1', '61');
INSERT INTO `sys_role_menu` VALUES ('62', '1', '62');
INSERT INTO `sys_role_menu` VALUES ('63', '1', '63');
INSERT INTO `sys_role_menu` VALUES ('64', '1', '64');
INSERT INTO `sys_role_menu` VALUES ('65', '1', '65');
INSERT INTO `sys_role_menu` VALUES ('66', '1', '66');
INSERT INTO `sys_role_menu` VALUES ('67', '1', '67');
INSERT INTO `sys_role_menu` VALUES ('68', '1', '68');
INSERT INTO `sys_role_menu` VALUES ('69', '1', '69');
INSERT INTO `sys_role_menu` VALUES ('70', '1', '70');
INSERT INTO `sys_role_menu` VALUES ('71', '1', '71');
INSERT INTO `sys_role_menu` VALUES ('72', '1', '72');
INSERT INTO `sys_role_menu` VALUES ('73', '1', '73');
INSERT INTO `sys_role_menu` VALUES ('74', '1', '74');
INSERT INTO `sys_role_menu` VALUES ('75', '1', '75');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 3, 'admin', '941f0f074eeeb6ffbf1abb4d22a6be97', 'Administrator', '736720794@qq.com', '17621776714', '/avatar.png', 'dark', '6', '0', '1', '2019-06-01 15:52:15', null, null);
INSERT INTO `sys_user` VALUES ('2', 8, 'readonly', 'eb44e3cc27cc106ac8f8496655bb399d', '只读用户', '736720794@qq.com', '17621776714', '/avatar.png', 'light', '1', '0', '1', '2019-06-01 15:57:52', null, null);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('1', '1', '1');
INSERT INTO `sys_user_post` VALUES ('2', '1', '7');
INSERT INTO `sys_user_post` VALUES ('3', '2', '2');
INSERT INTO `sys_user_post` VALUES ('4', '2', '6');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '字典名称',
  `code` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '字典编码',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='数据字典';

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_code` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '字典code',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '字典元素名称',
  `value` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '字典元素值',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `status` tinyint(5) DEFAULT NULL COMMENT '字典元素状态，0：有效，1：无效',
  `order` int(10) DEFAULT NULL COMMENT '排序',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_dict_code` (`dict_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='数据字典项目';
