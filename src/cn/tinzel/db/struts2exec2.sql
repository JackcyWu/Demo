/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : struts2exec2

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-04-11 13:11:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `s_user`
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `logonName` varchar(50) DEFAULT NULL,
  `logonPwd` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `education` varchar(20) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `interest` varchar(20) DEFAULT NULL,
  `path` varchar(500) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', '超级管理员', 'tinzel', '123', null, null, null, null, null, null, null, null);
INSERT INTO `s_user` VALUES ('15', '刘哥, ', 'ccliu', '123', '男', '2018-04-04', '本科', '', '看电影, 旅游', null, null, '天智立大BOSS!!!!!!!!!!');
INSERT INTO `s_user` VALUES ('16', '老吴, ', 'jackcy', '123', '男', '2008-04-09', '本科', '13545656578', '旅游, 健身', '/WEB-INF/upload/be3f28a5-d767-4b08-8385-716f8f6fba7e', '7月百日计划书.txt', '我是一名程序员！！！！！！！');
