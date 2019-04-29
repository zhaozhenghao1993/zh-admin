/*
Navicat MySQL Data Transfer

Source Server         : zhaozhenghao
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : test-admin

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-04-29 08:38:39
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
) ENGINE=InnoDB AUTO_INCREMENT=303 DEFAULT CHARSET=latin1 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('173', '1', 'admin', '清空日志', '5', 'net.zhenghao.zh.common.controller.SysLogController.batchRemoveAll()', null, '0:0:0:0:0:0:0:1', null, null, '1', '响应时间：5ms', '3', '2018-01-09 13:39:26');
INSERT INTO `sys_log` VALUES ('200', '1', 'admin', '清空定时任务日志', '2', 'net.zhenghao.zh.quartz.controller.QuartzJobLogController.batchRemoveAll()', null, '0:0:0:0:0:0:0:1', null, null, '0', '操作失败', '3', '2018-01-22 14:24:51');
INSERT INTO `sys_log` VALUES ('202', '1', 'admin', '登陆', '85', 'net.zhenghao.zh.shiro.controller.SysLoginController.login()', '\"admin\"', '0:0:0:0:0:0:0:1', null, null, null, null, null, '2018-01-23 10:16:31');
INSERT INTO `sys_log` VALUES ('238', '1', 'admin', '修改用户', '28', 'net.zhenghao.zh.auth.controller.SysUserController.edit()', '[64,{\"userId\":64,\"orgId\":0,\"username\":\"111111\",\"password\":\"******\",\"name\":\"1111\",\"email\":\"736720794@qq.com\",\"mobile\":null,\"avatar\":null,\"status\":0,\"theme\":null,\"color\":null,\"creatorId\":null,\"createTime\":null,\"modifierId\":1,\"modifiedTime\":null,\"roleIdList\":[29],\"roles\":null,\"perms\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：28ms', '3', '2019-04-14 15:51:05');
INSERT INTO `sys_log` VALUES ('239', null, null, '注解日志异常测试', null, 'net.zhenghao.zh.auth.controller.TestController.exception()', '[]', '127.0.0.1', 'Unknown', 'Unknown', '1', '异常信息：java.lang.NullPointerException', '4', '2019-04-14 16:13:30');
INSERT INTO `sys_log` VALUES ('241', null, null, '注解日志异常测试', null, 'net.zhenghao.zh.auth.controller.TestController.exception()', '[]', '127.0.0.1', 'Unknown', 'Unknown', '1', '异常信息：java.lang.NullPointerException', '4', '2019-04-14 20:15:03');
INSERT INTO `sys_log` VALUES ('242', '1', 'admin', '修改用户', '48', 'net.zhenghao.zh.auth.controller.SysUserController.edit()', '[64,{\"userId\":64,\"orgId\":0,\"username\":\"111111\",\"password\":\"******\",\"name\":\"1111\",\"email\":\"736720794@qq.cn\",\"mobile\":null,\"avatar\":null,\"status\":0,\"theme\":null,\"color\":null,\"creatorId\":null,\"createTime\":null,\"modifierId\":1,\"modifiedTime\":null,\"roleIdList\":[29],\"roles\":null,\"perms\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：48ms', '3', '2019-04-14 22:29:26');
INSERT INTO `sys_log` VALUES ('243', '1', 'admin', '分配权限', '13', 'net.zhenghao.zh.auth.controller.SysRoleController.updateRoleAuthorization()', '[2,[90,79,66,80,81,82]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：13ms', '5', '2019-04-14 22:29:55');
INSERT INTO `sys_log` VALUES ('245', '1', 'admin', '新增菜单', '87', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":110,\"parentName\":\"日志管理\",\"name\":\"列表\",\"uri\":\"/monitor/log\",\"method\":\"GET\",\"perms\":\"monitor:log:view\",\"type\":3,\"orderNum\":0,\"description\":\"日志管理列表\",\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：87ms', '3', '2019-04-15 21:30:12');
INSERT INTO `sys_log` VALUES ('246', '1', 'admin', '新增菜单', '74', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":110,\"parentName\":\"日志管理\",\"name\":\"批量删除\",\"uri\":\"/monitor/log\",\"method\":\"DELETE\",\"perms\":\"monitor:log:batch\",\"type\":2,\"orderNum\":1,\"description\":\"日志管理批量删除\",\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：74ms', '3', '2019-04-15 21:41:35');
INSERT INTO `sys_log` VALUES ('247', '1', 'admin', '新增菜单', '38', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":110,\"parentName\":\"日志管理\",\"name\":\"清空\",\"uri\":\"/monitor/log/{type}/clear\",\"method\":\"DELETE\",\"perms\":\"monitor:log:clear\",\"type\":2,\"orderNum\":2,\"description\":\"日志管理清空日志\",\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：38ms', '3', '2019-04-15 21:43:03');
INSERT INTO `sys_log` VALUES ('248', '1', 'admin', '分配权限', '69', 'net.zhenghao.zh.auth.controller.SysRoleController.updateRoleAuthorization()', '[1,[66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,99,100,101,103,104,105,106,107,102,109,112,115,116,110,117,118,119]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：69ms', '5', '2019-04-15 21:43:24');
INSERT INTO `sys_log` VALUES ('249', '1', 'admin', '分配权限', '77', 'net.zhenghao.zh.auth.controller.SysRoleController.updateRoleAuthorization()', '[29,[79,80,66,67,68,102,103,107,81,74,75,100,73,90,69,101,112,115,116,109,110,117]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：77ms', '5', '2019-04-15 21:44:00');
INSERT INTO `sys_log` VALUES ('250', '1', 'admin', '删除日志', '99', 'net.zhenghao.zh.monitor.controller.MonitorLogController.batchRemove()', '[[183,182,181,174]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：99ms', '3', '2019-04-15 21:44:27');
INSERT INTO `sys_log` VALUES ('251', '1', 'admin', '删除日志', '36', 'net.zhenghao.zh.monitor.controller.MonitorLogController.batchRemove()', '[[175]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：36ms', '3', '2019-04-15 21:44:40');
INSERT INTO `sys_log` VALUES ('252', '1', 'admin', '删除日志', '29', 'net.zhenghao.zh.monitor.controller.MonitorLogController.batchRemove()', '[[177,178]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：29ms', '3', '2019-04-15 21:45:48');
INSERT INTO `sys_log` VALUES ('253', '1', 'admin', '删除日志', '20', 'net.zhenghao.zh.monitor.controller.MonitorLogController.batchRemove()', '[[233]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：20ms', '3', '2019-04-15 21:45:58');
INSERT INTO `sys_log` VALUES ('254', '1', 'admin', '删除日志', '77', 'net.zhenghao.zh.monitor.controller.MonitorLogController.batchRemove()', '[[176]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：77ms', '3', '2019-04-15 21:47:39');
INSERT INTO `sys_log` VALUES ('255', '1', 'admin', '删除日志', '33', 'net.zhenghao.zh.monitor.controller.MonitorLogController.batchRemove()', '[[187]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：33ms', '3', '2019-04-15 21:53:06');
INSERT INTO `sys_log` VALUES ('256', '1', 'admin', '删除日志', '83', 'net.zhenghao.zh.monitor.controller.MonitorLogController.batchRemove()', '[[192]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：83ms', '3', '2019-04-15 21:53:55');
INSERT INTO `sys_log` VALUES ('257', '1', 'admin', '清空日志', '52', 'net.zhenghao.zh.monitor.controller.MonitorLogController.batchRemoveAll()', '[1]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：52ms', '3', '2019-04-15 21:58:41');
INSERT INTO `sys_log` VALUES ('258', '1', 'admin', '新增用户', '4', 'net.zhenghao.zh.auth.controller.SysUserController.save()', '[{\"userId\":0,\"orgId\":0,\"username\":\"admin\",\"password\":\"admin\",\"name\":\"admin\",\"email\":null,\"mobile\":null,\"avatar\":null,\"status\":0,\"theme\":null,\"color\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"modifiedTime\":null,\"roleIdList\":[],\"roles\":null,\"perms\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '1', 'The username already exists !', '3', '2019-04-15 22:00:17');
INSERT INTO `sys_log` VALUES ('259', '58', 'tangxiaolu', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"tangxiaolu\",\"username\":\"tangxiaolu\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-15 22:03:42');
INSERT INTO `sys_log` VALUES ('260', '1', 'admin', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-15 22:08:17');
INSERT INTO `sys_log` VALUES ('261', '1', 'admin', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-16 20:57:32');
INSERT INTO `sys_log` VALUES ('262', '1', 'admin', '删除角色', '1', 'net.zhenghao.zh.auth.controller.SysRoleController.remove()', '[1]', '127.0.0.1', 'Chrome', 'Windows 10', '1', '内置admin角色不能删除!', '3', '2019-04-16 22:39:45');
INSERT INTO `sys_log` VALUES ('263', '1', 'admin', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-20 17:57:40');
INSERT INTO `sys_log` VALUES ('264', '1', 'admin', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-20 21:57:48');
INSERT INTO `sys_log` VALUES ('265', '58', 'tangxiaolu', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"tangxiaolu\",\"username\":\"tangxiaolu\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-20 22:10:23');
INSERT INTO `sys_log` VALUES ('266', '1', 'admin', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-20 22:12:46');
INSERT INTO `sys_log` VALUES ('267', '1', 'admin', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-25 21:46:26');
INSERT INTO `sys_log` VALUES ('268', '1', 'admin', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-26 20:34:55');
INSERT INTO `sys_log` VALUES ('269', '1', 'admin', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-26 22:39:49');
INSERT INTO `sys_log` VALUES ('270', '1', 'admin', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-27 18:44:42');
INSERT INTO `sys_log` VALUES ('271', '1', 'admin', '删除角色', '2', 'net.zhenghao.zh.auth.controller.SysRoleController.remove()', '[1]', '127.0.0.1', 'Chrome', 'Windows 10', '1', '内置admin角色不能删除!', '3', '2019-04-27 21:07:50');
INSERT INTO `sys_log` VALUES ('272', '1', 'admin', '修改菜单', '92', 'net.zhenghao.zh.auth.controller.SysMenuController.update()', '[91,{\"menuId\":91,\"parentId\":0,\"parentName\":\"主目录\",\"name\":\"测试目录\",\"uri\":\"\",\"method\":\"\",\"perms\":\"test\",\"type\":0,\"orderNum\":10,\"description\":\"测试目录\",\"creatorId\":null,\"createTime\":null,\"modifierId\":1,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：92ms', '3', '2019-04-27 21:30:26');
INSERT INTO `sys_log` VALUES ('273', '1', 'admin', '新增菜单', '98', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":0,\"parentName\":\"主目录\",\"name\":\"开发工具\",\"uri\":\"\",\"method\":\"\",\"perms\":\"tool\",\"type\":0,\"orderNum\":2,\"description\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：98ms', '3', '2019-04-27 21:31:27');
INSERT INTO `sys_log` VALUES ('274', '1', 'admin', '新增菜单', '70', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":120,\"parentName\":\"开发工具\",\"name\":\"IconSelector\",\"uri\":\"\",\"method\":\"\",\"perms\":\"tool:icon\",\"type\":1,\"orderNum\":0,\"description\":\"图标选择\",\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：70ms', '3', '2019-04-27 21:34:14');
INSERT INTO `sys_log` VALUES ('275', '1', 'admin', '新增菜单', '59', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":120,\"parentName\":\"开发工具\",\"name\":\"代码生成器\",\"uri\":\"\",\"method\":\"\",\"perms\":\"tool:generator\",\"type\":0,\"orderNum\":1,\"description\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：59ms', '3', '2019-04-27 21:35:09');
INSERT INTO `sys_log` VALUES ('276', '1', 'admin', '修改菜单', '77', 'net.zhenghao.zh.auth.controller.SysMenuController.update()', '[122,{\"menuId\":122,\"parentId\":120,\"parentName\":\"开发工具\",\"name\":\"代码生成器\",\"uri\":\"\",\"method\":\"\",\"perms\":\"tool:generator\",\"type\":1,\"orderNum\":1,\"description\":null,\"creatorId\":null,\"createTime\":null,\"modifierId\":1,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：77ms', '3', '2019-04-27 21:35:16');
INSERT INTO `sys_log` VALUES ('277', '1', 'admin', '新增菜单', '56', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":122,\"parentName\":\"代码生成器\",\"name\":\"列表\",\"uri\":\"/tool/generator\",\"method\":\"GET\",\"perms\":\"tool:generator:view\",\"type\":3,\"orderNum\":0,\"description\":\"代码生成器列表，展示所有数据库表信息\",\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：56ms', '3', '2019-04-27 21:37:42');
INSERT INTO `sys_log` VALUES ('278', '1', 'admin', '新增菜单', '100', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":122,\"parentName\":\"代码生成器\",\"name\":\"生成代码\",\"uri\":\"/tool/generator/code\",\"method\":\"GET\",\"perms\":\"tool:generator:code\",\"type\":2,\"orderNum\":1,\"description\":\"生成代码\",\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：100ms', '3', '2019-04-27 21:41:42');
INSERT INTO `sys_log` VALUES ('279', '1', 'admin', '分配权限', '59', 'net.zhenghao.zh.auth.controller.SysRoleController.updateRoleAuthorization()', '[1,[66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,99,100,101,103,104,105,106,107,102,109,112,115,116,110,117,118,119,120,121,122,123,124]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：59ms', '5', '2019-04-27 21:46:16');
INSERT INTO `sys_log` VALUES ('280', '1', 'admin', '分配权限', '17', 'net.zhenghao.zh.auth.controller.SysRoleController.updateRoleAuthorization()', '[29,[79,80,66,67,68,102,103,107,81,74,75,100,73,90,69,101,112,115,116,109,110,117,120,121,122,123]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：17ms', '5', '2019-04-27 21:46:28');
INSERT INTO `sys_log` VALUES ('281', '58', 'tangxiaolu', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"tangxiaolu\",\"username\":\"tangxiaolu\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-27 21:46:54');
INSERT INTO `sys_log` VALUES ('282', '1', 'admin', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-27 21:47:21');
INSERT INTO `sys_log` VALUES ('283', '1', 'admin', '登录', null, 'net.zhenghao.zh.auth.controller.SysLoginController.login()', '{\"password\":\"123\",\"username\":\"admin\"}', '127.0.0.1', 'Chrome', 'Windows 10', '0', '登录成功', '1', '2019-04-27 22:39:22');
INSERT INTO `sys_log` VALUES ('284', '1', 'admin', '新增菜单', '32', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":66,\"parentName\":\"系统管理\",\"name\":\"岗位管理\",\"uri\":\"\",\"method\":\"\",\"perms\":\"sys:post\",\"type\":1,\"orderNum\":4,\"description\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：32ms', '3', '2019-04-27 22:40:18');
INSERT INTO `sys_log` VALUES ('285', '1', 'admin', '新增菜单', '62', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"列表\",\"uri\":\"/sys/post\",\"method\":\"GET\",\"perms\":\"sys:post:view\",\"type\":3,\"orderNum\":0,\"description\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：62ms', '3', '2019-04-27 22:41:10');
INSERT INTO `sys_log` VALUES ('286', '1', 'admin', '新增菜单', '70', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"展示\",\"uri\":\"/sys/post/{id}\",\"method\":\"GET\",\"perms\":\"sys:post:info\",\"type\":3,\"orderNum\":1,\"description\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：70ms', '3', '2019-04-27 22:41:53');
INSERT INTO `sys_log` VALUES ('287', '1', 'admin', '新增菜单', '40', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"新增\",\"uri\":\"/sys/post\",\"method\":\"POST\",\"perms\":\"sys:post:save\",\"type\":2,\"orderNum\":2,\"description\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：40ms', '3', '2019-04-27 22:42:54');
INSERT INTO `sys_log` VALUES ('288', '1', 'admin', '新增菜单', '34', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"修改\",\"uri\":\"/sys/post/{id}\",\"method\":\"PUT\",\"perms\":\"sys:post:edit\",\"type\":2,\"orderNum\":3,\"description\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：34ms', '3', '2019-04-27 22:43:37');
INSERT INTO `sys_log` VALUES ('289', '1', 'admin', '新增菜单', '21', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"删除\",\"uri\":\"/sys/post/{id}\",\"method\":\"DELETE\",\"perms\":\"sys:post:remove\",\"type\":2,\"orderNum\":4,\"description\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：21ms', '3', '2019-04-27 22:44:50');
INSERT INTO `sys_log` VALUES ('290', '1', 'admin', '新增菜单', '18', 'net.zhenghao.zh.auth.controller.SysMenuController.save()', '[{\"menuId\":0,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"批量删除\",\"uri\":\"/sys/post\",\"method\":\"DELETE\",\"perms\":\"sys:post:batch\",\"type\":2,\"orderNum\":5,\"description\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：18ms', '3', '2019-04-27 22:45:34');
INSERT INTO `sys_log` VALUES ('291', '1', 'admin', '分配权限', '40', 'net.zhenghao.zh.auth.controller.SysRoleController.updateRoleAuthorization()', '[1,[66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,99,100,101,103,104,105,106,107,102,109,112,115,116,110,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：40ms', '5', '2019-04-27 22:46:43');
INSERT INTO `sys_log` VALUES ('292', '1', 'admin', '分配权限', '26', 'net.zhenghao.zh.auth.controller.SysRoleController.updateRoleAuthorization()', '[29,[79,80,66,67,68,102,103,107,81,74,75,100,73,90,69,101,112,115,116,109,110,117,120,121,122,123,125,126,127]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：26ms', '5', '2019-04-27 22:46:55');
INSERT INTO `sys_log` VALUES ('293', '1', 'admin', '新增岗位管理', '52', 'net.zhenghao.zh.auth.controller.SysPostController.save()', '[{\"postId\":0,\"postCode\":null,\"postName\":null,\"orderNum\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：52ms', '3', '2019-04-27 22:49:14');
INSERT INTO `sys_log` VALUES ('294', '1', 'admin', '批量删除岗位管理', '39', 'net.zhenghao.zh.auth.controller.SysPostController.batchRemove()', '[[1]]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：39ms', '3', '2019-04-27 22:49:43');
INSERT INTO `sys_log` VALUES ('295', '1', 'admin', '新增岗位管理', '42', 'net.zhenghao.zh.auth.controller.SysPostController.save()', '[{\"postId\":0,\"postCode\":\"ceo1\",\"postName\":null,\"orderNum\":null,\"creatorId\":1,\"createTime\":null,\"modifierId\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：42ms', '3', '2019-04-27 22:49:48');
INSERT INTO `sys_log` VALUES ('296', '1', 'admin', '修改菜单', '12', 'net.zhenghao.zh.auth.controller.SysMenuController.update()', '[126,{\"menuId\":126,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"列表\",\"uri\":\"/sys/post\",\"method\":\"GET\",\"perms\":\"sys:post:view\",\"type\":3,\"orderNum\":0,\"description\":\"岗位管理列表\",\"creatorId\":null,\"createTime\":null,\"modifierId\":1,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：12ms', '3', '2019-04-27 22:50:47');
INSERT INTO `sys_log` VALUES ('297', '1', 'admin', '修改菜单', '85', 'net.zhenghao.zh.auth.controller.SysMenuController.update()', '[127,{\"menuId\":127,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"展示\",\"uri\":\"/sys/post/{id}\",\"method\":\"GET\",\"perms\":\"sys:post:info\",\"type\":3,\"orderNum\":1,\"description\":\"岗位管理展示\",\"creatorId\":null,\"createTime\":null,\"modifierId\":1,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：85ms', '3', '2019-04-27 22:50:56');
INSERT INTO `sys_log` VALUES ('298', '1', 'admin', '修改菜单', '12', 'net.zhenghao.zh.auth.controller.SysMenuController.update()', '[128,{\"menuId\":128,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"新增\",\"uri\":\"/sys/post\",\"method\":\"POST\",\"perms\":\"sys:post:save\",\"type\":2,\"orderNum\":2,\"description\":\"岗位管理新增\",\"creatorId\":null,\"createTime\":null,\"modifierId\":1,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：12ms', '3', '2019-04-27 22:51:03');
INSERT INTO `sys_log` VALUES ('299', '1', 'admin', '修改菜单', '87', 'net.zhenghao.zh.auth.controller.SysMenuController.update()', '[129,{\"menuId\":129,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"修改\",\"uri\":\"/sys/post/{id}\",\"method\":\"PUT\",\"perms\":\"sys:post:edit\",\"type\":2,\"orderNum\":3,\"description\":\"岗位管理修改\",\"creatorId\":null,\"createTime\":null,\"modifierId\":1,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：87ms', '3', '2019-04-27 22:51:11');
INSERT INTO `sys_log` VALUES ('300', '1', 'admin', '修改菜单', '26', 'net.zhenghao.zh.auth.controller.SysMenuController.update()', '[130,{\"menuId\":130,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"删除\",\"uri\":\"/sys/post/{id}\",\"method\":\"DELETE\",\"perms\":\"sys:post:remove\",\"type\":2,\"orderNum\":4,\"description\":\"岗位管理删除\",\"creatorId\":null,\"createTime\":null,\"modifierId\":1,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：26ms', '3', '2019-04-27 22:51:18');
INSERT INTO `sys_log` VALUES ('301', '1', 'admin', '修改菜单', '81', 'net.zhenghao.zh.auth.controller.SysMenuController.update()', '[131,{\"menuId\":131,\"parentId\":125,\"parentName\":\"岗位管理\",\"name\":\"批量删除\",\"uri\":\"/sys/post\",\"method\":\"DELETE\",\"perms\":\"sys:post:batch\",\"type\":2,\"orderNum\":5,\"description\":\"岗位管理批量删除\",\"creatorId\":null,\"createTime\":null,\"modifierId\":1,\"children\":null,\"modifiedTime\":null}]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：81ms', '3', '2019-04-27 22:51:26');
INSERT INTO `sys_log` VALUES ('302', '1', 'admin', '删除岗位管理', '62', 'net.zhenghao.zh.auth.controller.SysPostController.remove()', '[2]', '127.0.0.1', 'Chrome', 'Windows 10', '0', '响应时间：62ms', '3', '2019-04-27 22:53:49');

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
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=latin1 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('66', '0', '系统管理', null, null, 'sys', '0', '0', null, '1', '2019-02-08 15:29:18', null, null);
INSERT INTO `sys_menu` VALUES ('67', '66', '用户管理', null, null, 'sys:user', '1', '0', null, '1', '2019-02-08 15:34:43', null, null);
INSERT INTO `sys_menu` VALUES ('68', '67', '列表', '/sys/user', 'GET', 'sys:user:view', '3', '0', '系统用户列表', '1', '2019-02-09 17:46:31', null, null);
INSERT INTO `sys_menu` VALUES ('69', '67', '展示', '/sys/user/{id}', 'GET', 'sys:user:info', '3', '1', '系统用户详情', '1', '2019-02-09 17:48:20', null, null);
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
INSERT INTO `sys_menu` VALUES ('121', '120', 'IconSelector', '', '', 'tool:icon', '1', '0', '图标选择', '1', '2019-04-27 21:34:14', null, null);
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
  `post_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '岗位编码',
  `post_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '岗位名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
  `modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='岗位管理';

-- ----------------------------
-- Records of sys_post
-- ----------------------------

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
INSERT INTO `sys_role` VALUES ('2', '测试角色1', 'test1', '测试角色备注1', '1', '2019-03-19 22:17:01', '1', '2019-03-25 22:28:01');
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
) ENGINE=InnoDB AUTO_INCREMENT=665 DEFAULT CHARSET=latin1 COMMENT='角色菜单关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('439', '2', '90');
INSERT INTO `sys_role_menu` VALUES ('440', '2', '79');
INSERT INTO `sys_role_menu` VALUES ('441', '2', '66');
INSERT INTO `sys_role_menu` VALUES ('442', '2', '80');
INSERT INTO `sys_role_menu` VALUES ('443', '2', '81');
INSERT INTO `sys_role_menu` VALUES ('444', '2', '82');
INSERT INTO `sys_role_menu` VALUES ('582', '1', '66');
INSERT INTO `sys_role_menu` VALUES ('583', '1', '67');
INSERT INTO `sys_role_menu` VALUES ('584', '1', '68');
INSERT INTO `sys_role_menu` VALUES ('585', '1', '69');
INSERT INTO `sys_role_menu` VALUES ('586', '1', '70');
INSERT INTO `sys_role_menu` VALUES ('587', '1', '71');
INSERT INTO `sys_role_menu` VALUES ('588', '1', '72');
INSERT INTO `sys_role_menu` VALUES ('589', '1', '73');
INSERT INTO `sys_role_menu` VALUES ('590', '1', '74');
INSERT INTO `sys_role_menu` VALUES ('591', '1', '75');
INSERT INTO `sys_role_menu` VALUES ('592', '1', '76');
INSERT INTO `sys_role_menu` VALUES ('593', '1', '77');
INSERT INTO `sys_role_menu` VALUES ('594', '1', '78');
INSERT INTO `sys_role_menu` VALUES ('595', '1', '79');
INSERT INTO `sys_role_menu` VALUES ('596', '1', '80');
INSERT INTO `sys_role_menu` VALUES ('597', '1', '81');
INSERT INTO `sys_role_menu` VALUES ('598', '1', '82');
INSERT INTO `sys_role_menu` VALUES ('599', '1', '83');
INSERT INTO `sys_role_menu` VALUES ('600', '1', '84');
INSERT INTO `sys_role_menu` VALUES ('601', '1', '85');
INSERT INTO `sys_role_menu` VALUES ('602', '1', '86');
INSERT INTO `sys_role_menu` VALUES ('603', '1', '87');
INSERT INTO `sys_role_menu` VALUES ('604', '1', '88');
INSERT INTO `sys_role_menu` VALUES ('605', '1', '89');
INSERT INTO `sys_role_menu` VALUES ('606', '1', '90');
INSERT INTO `sys_role_menu` VALUES ('607', '1', '99');
INSERT INTO `sys_role_menu` VALUES ('608', '1', '100');
INSERT INTO `sys_role_menu` VALUES ('609', '1', '101');
INSERT INTO `sys_role_menu` VALUES ('610', '1', '103');
INSERT INTO `sys_role_menu` VALUES ('611', '1', '104');
INSERT INTO `sys_role_menu` VALUES ('612', '1', '105');
INSERT INTO `sys_role_menu` VALUES ('613', '1', '106');
INSERT INTO `sys_role_menu` VALUES ('614', '1', '107');
INSERT INTO `sys_role_menu` VALUES ('615', '1', '102');
INSERT INTO `sys_role_menu` VALUES ('616', '1', '109');
INSERT INTO `sys_role_menu` VALUES ('617', '1', '112');
INSERT INTO `sys_role_menu` VALUES ('618', '1', '115');
INSERT INTO `sys_role_menu` VALUES ('619', '1', '116');
INSERT INTO `sys_role_menu` VALUES ('620', '1', '110');
INSERT INTO `sys_role_menu` VALUES ('621', '1', '117');
INSERT INTO `sys_role_menu` VALUES ('622', '1', '118');
INSERT INTO `sys_role_menu` VALUES ('623', '1', '119');
INSERT INTO `sys_role_menu` VALUES ('624', '1', '120');
INSERT INTO `sys_role_menu` VALUES ('625', '1', '121');
INSERT INTO `sys_role_menu` VALUES ('626', '1', '122');
INSERT INTO `sys_role_menu` VALUES ('627', '1', '123');
INSERT INTO `sys_role_menu` VALUES ('628', '1', '124');
INSERT INTO `sys_role_menu` VALUES ('629', '1', '125');
INSERT INTO `sys_role_menu` VALUES ('630', '1', '126');
INSERT INTO `sys_role_menu` VALUES ('631', '1', '127');
INSERT INTO `sys_role_menu` VALUES ('632', '1', '128');
INSERT INTO `sys_role_menu` VALUES ('633', '1', '129');
INSERT INTO `sys_role_menu` VALUES ('634', '1', '130');
INSERT INTO `sys_role_menu` VALUES ('635', '1', '131');
INSERT INTO `sys_role_menu` VALUES ('636', '29', '79');
INSERT INTO `sys_role_menu` VALUES ('637', '29', '80');
INSERT INTO `sys_role_menu` VALUES ('638', '29', '66');
INSERT INTO `sys_role_menu` VALUES ('639', '29', '67');
INSERT INTO `sys_role_menu` VALUES ('640', '29', '68');
INSERT INTO `sys_role_menu` VALUES ('641', '29', '102');
INSERT INTO `sys_role_menu` VALUES ('642', '29', '103');
INSERT INTO `sys_role_menu` VALUES ('643', '29', '107');
INSERT INTO `sys_role_menu` VALUES ('644', '29', '81');
INSERT INTO `sys_role_menu` VALUES ('645', '29', '74');
INSERT INTO `sys_role_menu` VALUES ('646', '29', '75');
INSERT INTO `sys_role_menu` VALUES ('647', '29', '100');
INSERT INTO `sys_role_menu` VALUES ('648', '29', '73');
INSERT INTO `sys_role_menu` VALUES ('649', '29', '90');
INSERT INTO `sys_role_menu` VALUES ('650', '29', '69');
INSERT INTO `sys_role_menu` VALUES ('651', '29', '101');
INSERT INTO `sys_role_menu` VALUES ('652', '29', '112');
INSERT INTO `sys_role_menu` VALUES ('653', '29', '115');
INSERT INTO `sys_role_menu` VALUES ('654', '29', '116');
INSERT INTO `sys_role_menu` VALUES ('655', '29', '109');
INSERT INTO `sys_role_menu` VALUES ('656', '29', '110');
INSERT INTO `sys_role_menu` VALUES ('657', '29', '117');
INSERT INTO `sys_role_menu` VALUES ('658', '29', '120');
INSERT INTO `sys_role_menu` VALUES ('659', '29', '121');
INSERT INTO `sys_role_menu` VALUES ('660', '29', '122');
INSERT INTO `sys_role_menu` VALUES ('661', '29', '123');
INSERT INTO `sys_role_menu` VALUES ('662', '29', '125');
INSERT INTO `sys_role_menu` VALUES ('663', '29', '126');
INSERT INTO `sys_role_menu` VALUES ('664', '29', '127');

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
INSERT INTO `sys_user` VALUES ('1', null, 'admin', '941f0f074eeeb6ffbf1abb4d22a6be97', 'Administrator', '736720794@qq.com', '17621776714', '/avatar2.jpg', 'dark', '6', '0', '1', '2017-12-08 10:56:35', '1', '2019-03-30 21:47:33');
INSERT INTO `sys_user` VALUES ('3', null, 'zhangsan', '111', null, null, null, null, null, null, '1', '1', null, '1', '2019-03-28 23:11:01');
INSERT INTO `sys_user` VALUES ('5', null, 'wangwu11', '333', null, '736720794@qq.com', '17621776714', null, null, null, '0', null, null, '1', '2019-03-16 15:40:04');
INSERT INTO `sys_user` VALUES ('6', null, 'zhaoliu', '444', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('8', null, 'tongzhou', '666', null, null, null, null, null, null, '1', null, null, '1', '2019-03-16 17:33:01');
INSERT INTO `sys_user` VALUES ('9', null, 'zhuhaihui', '444', null, null, null, null, null, null, '0', null, null, null, null);
INSERT INTO `sys_user` VALUES ('10', null, 'baiyunfei', '888', null, null, null, null, null, null, '0', null, null, null, null);
INSERT INTO `sys_user` VALUES ('42', null, 'root', '80ff5f651f60c4156ee73e47c88defbd', 'root', '736720794@qq.com', '17621776714', null, null, null, '1', '1', '2019-03-12 22:13:11', '1', '2019-04-03 00:59:15');
INSERT INTO `sys_user` VALUES ('51', null, 'zhao7367201', '80835b28d5bf834c2f0963bb2868bca9', '赵正浩', '736720794@qq.com', '17621776714', null, null, null, '1', '1', '2019-03-16 15:09:11', '1', '2019-04-03 00:44:21');
INSERT INTO `sys_user` VALUES ('52', null, 'zhao7367202', '654b6ae60f71f879548fe3235480b985', null, '736720794@qq.com', '17621776714', null, null, null, '1', '1', '2019-03-16 15:38:20', null, null);
INSERT INTO `sys_user` VALUES ('53', null, 'superadmin', '9aecc0033519b67dd62926d778c8f53c', null, '736720794@qq.com', null, null, null, null, '0', '1', '2019-03-16 15:39:16', null, null);
INSERT INTO `sys_user` VALUES ('54', null, 'superadmin01', '9aecc0033519b67dd62926d778c8f53c', null, '736720794@qq.cn', '17621776714', null, null, null, '0', '1', '2019-03-16 15:39:41', '1', '2019-03-16 16:34:15');
INSERT INTO `sys_user` VALUES ('56', null, 'superadmin02', 'fa369ed06209bed827521b5b6701b2ec', null, '736720794@qq.com', '17621776714', null, null, null, '0', '1', '2019-03-16 16:30:36', '1', '2019-03-16 16:53:56');
INSERT INTO `sys_user` VALUES ('57', null, 'root01', '81423985da4b8f0543d657651294f003', null, null, null, null, null, null, '0', '1', '2019-03-16 17:32:54', null, null);
INSERT INTO `sys_user` VALUES ('58', '6', 'tangxiaolu', 'c3b9df74a1ab68052d240eeb0053a1f5', '糖葫芦', '736720794@qq.com', null, null, 'light', '1', '0', '1', '2019-03-28 22:47:10', '1', '2019-04-04 23:17:38');
INSERT INTO `sys_user` VALUES ('59', null, 'superadmin-001', 'f081134f60dfc5b2620eaa9590c9b59b', '赵正浩', '736720794@qq.com', null, null, null, null, '0', '1', '2019-03-30 15:55:12', null, null);
INSERT INTO `sys_user` VALUES ('60', null, 'admin12', '6b0229e9c94c6e623b16de38f7a96bd7', 'awe', null, null, null, null, null, '0', '1', '2019-03-30 16:05:08', null, null);
INSERT INTO `sys_user` VALUES ('61', null, 'wq', '0cc5c27acddc9767adeba915557fbf05', 'qwed', null, null, null, null, null, '0', '1', '2019-04-02 23:09:29', null, null);
INSERT INTO `sys_user` VALUES ('62', '3', 'hahaha', 'dc5b0d0a5fdf0fd2c26d046a018b332b', 'hahaha', null, null, null, null, null, '0', '1', '2019-04-03 00:44:06', '1', '2019-04-04 23:14:01');
INSERT INTO `sys_user` VALUES ('63', '3', 'zhaozhenghao', '8e0d2ba9b9fa6d731780800ce5e8038e', '赵正浩', '736720794@qq.com', '17621776714', null, null, null, '0', '1', '2019-04-04 23:19:18', null, null);
INSERT INTO `sys_user` VALUES ('64', '0', '111111', '45e80b6c8653f467fdcc4848edc786c0', '1111', '736720794@qq.cn', null, null, null, null, '0', '1', '2019-04-04 23:21:22', '1', '2019-04-14 22:29:26');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1 COMMENT='用户角色关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('14', '1', '1');
INSERT INTO `sys_user_role` VALUES ('16', '58', '29');
INSERT INTO `sys_user_role` VALUES ('17', '63', '29');
INSERT INTO `sys_user_role` VALUES ('20', '64', '29');
