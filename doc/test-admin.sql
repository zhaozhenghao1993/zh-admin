/*
Navicat MySQL Data Transfer

Source Server         : zhaozhenghao
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : test-admin

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-01-29 22:22:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名case insensitive即大小写不敏感',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '耗时,相应时间',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ip地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `result` tinyint(255) DEFAULT NULL COMMENT '操作结果 1-成功 0-失败',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作描述',
  `type` tinyint(255) DEFAULT NULL COMMENT '日志类型 1-登录 2-访问 3-操作 4-异常 5-授权 6-微信',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=latin1 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('173', '1', 'admin', '清空日志', '5', 'net.zhenghao.zh.common.controller.SysLogController.batchRemoveAll()', null, '0:0:0:0:0:0:0:1', '2018-01-09 13:39:26', '1', '响应时间：5ms', '3');
INSERT INTO `sys_log` VALUES ('174', '1', 'admin', '退出登陆', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.logout()', null, '0:0:0:0:0:0:0:1', '2018-01-09 13:39:55', '1', '退出系统', '1');
INSERT INTO `sys_log` VALUES ('175', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"pf42e\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-09 14:30:57', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('176', '1', 'admin', '退出登陆', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.logout()', null, '0:0:0:0:0:0:0:1', '2018-01-09 14:31:49', '1', '退出系统', '1');
INSERT INTO `sys_log` VALUES ('177', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"e2cyf\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-09 14:55:27', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('178', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"2nc43\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-11 13:51:44', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('179', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"awn6a\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-11 14:47:25', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('180', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"bwc6g\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-11 15:09:39', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('181', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"4b82c\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-11 15:10:42', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('182', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"5adxw\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-15 10:42:51', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('183', '1', 'admin', '退出登陆', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.logout()', null, '0:0:0:0:0:0:0:1', '2018-01-15 11:05:52', '1', '退出系统', '1');
INSERT INTO `sys_log` VALUES ('184', '-1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"bxw7w\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-15 11:06:09', '0', '登录失败：验证码不正确', '1');
INSERT INTO `sys_log` VALUES ('185', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"8857e\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-15 11:06:12', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('186', '1', 'admin', '退出登陆', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.logout()', null, '0:0:0:0:0:0:0:1', '2018-01-15 11:17:57', '1', '退出系统', '1');
INSERT INTO `sys_log` VALUES ('187', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"m24wn\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-15 11:18:11', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('188', '2', 'ceshi', '退出登陆', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.logout()', null, '0:0:0:0:0:0:0:1', '2018-01-15 13:10:58', '1', '退出系统', '1');
INSERT INTO `sys_log` VALUES ('189', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"nm5cf\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-15 13:11:03', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('190', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"an2yy\",\"password\":\"123\",\"username\":\"admin\"}', '192.168.1.142', '2018-01-15 14:57:01', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('191', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"cbx6c\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-16 11:46:35', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('192', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"3acnd\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-16 12:43:10', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('193', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"77wc7\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-17 15:13:27', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('194', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"naaac\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-17 15:23:37', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('195', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"7ageb\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-22 09:11:30', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('196', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"fpyyg\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-22 11:05:41', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('197', '1', 'admin', '退出登陆', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.logout()', null, '0:0:0:0:0:0:0:1', '2018-01-22 11:24:33', '1', '退出系统', '1');
INSERT INTO `sys_log` VALUES ('198', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"33gc6\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-22 11:24:37', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('199', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"5fnyc\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-22 13:11:04', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('200', '1', 'admin', '清空定时任务日志', '2', 'net.zhenghao.zh.quartz.controller.QuartzJobLogController.batchRemoveAll()', null, '0:0:0:0:0:0:0:1', '2018-01-22 14:24:51', '0', '操作失败', '3');
INSERT INTO `sys_log` VALUES ('201', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"w7g28\",\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', '2018-01-23 10:02:40', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('202', '1', 'admin', '登陆', '85', 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '\"admin\"', '0:0:0:0:0:0:0:1', '2018-01-23 10:16:31', null, null, null);
INSERT INTO `sys_log` VALUES ('203', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"pcam6\",\"password\":\"123\",\"username\":\"admin\"}', '192.168.1.122', '2018-01-23 16:18:59', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('204', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"y58p2\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-23 16:20:57', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('205', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"pnem2\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-23 16:22:34', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('206', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"pnaaa\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-23 16:51:42', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('207', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"myfbn\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-23 17:02:35', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('208', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"bn7yw\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-23 17:28:50', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('209', '-1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"ndag4\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-24 08:43:13', '0', '登录失败：验证码不正确', '1');
INSERT INTO `sys_log` VALUES ('210', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"ee2da\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-24 08:43:17', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('211', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"gn7d6\",\"password\":\"123\",\"username\":\"admin\"}', '192.168.1.131', '2018-01-24 08:46:28', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('212', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"fwnnn\",\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', '2018-01-24 09:57:32', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('213', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"beee8\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 08:50:56', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('214', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"a4f3e\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 08:53:27', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('215', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"3pn36\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 08:54:28', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('216', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"55dxn\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 09:00:18', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('217', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"c2ymb\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 09:01:15', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('218', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"75y43\",\"password\":\"123\",\"username\":\"admin\"}', '192.168.1.137', '2018-01-25 09:02:36', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('219', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"6n7e5\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 09:05:11', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('220', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"mnne3\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 09:11:23', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('221', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"b87ad\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 09:24:19', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('222', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"3g6b8\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 09:33:24', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('223', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"e45g5\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 09:34:02', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('224', '-1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"7\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 09:36:55', '0', '登录失败：验证码不正确', '1');
INSERT INTO `sys_log` VALUES ('225', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"a6c4c\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 09:37:00', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('226', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"x3cyn\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 09:41:15', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('227', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"na4f4\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-25 09:54:30', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('228', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"pw77a\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-26 16:12:54', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('229', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"nyy3g\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-26 16:13:01', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('230', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"5p86x\",\"password\":\"123\",\"username\":\"admin\"}', '192.168.1.122', '2018-01-26 16:13:48', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('231', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"fcaan\",\"password\":\"123\",\"username\":\"admin\"}', '192.168.1.122', '2018-01-26 16:14:59', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('232', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"exa7c\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-29 16:44:56', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('233', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"3gb3n\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-31 14:26:07', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('234', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"2xb7e\",\"password\":\"123\",\"username\":\"admin\"}', '0:0:0:0:0:0:0:1', '2018-01-31 16:09:45', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('235', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"xd7ax\",\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', '2018-01-31 16:25:42', '1', '登录成功', '1');
INSERT INTO `sys_log` VALUES ('236', '1', 'admin', '登录', null, 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '{\"captcha\":\"8dbed\",\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', '2018-06-06 15:57:08', '1', '登录成功', '1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮  3：uri',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '最后修改人name',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '【系统内置】', '1', '2017-12-20 14:34:21', null, null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1 COMMENT='角色菜单关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像url',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '941f0f074eeeb6ffbf1abb4d22a6be97', '736720794@qq.com', '17621776714', null, '1', '1', '2017-12-08 10:56:35', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='用户角色关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
