/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : etsy

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-01-03 15:46:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `links`
-- ----------------------------
DROP TABLE IF EXISTS `links`;
CREATE TABLE `links` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) NOT NULL COMMENT 'link to etsy game',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `count` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of links
-- ----------------------------
INSERT INTO `links` VALUES ('1', 'https://www.etsy.com/teams/10931/avid-treasurers/discuss/16005043/?ref=team_page', '2015-12-27 16:11:42', '2015-12-27 16:11:49', '0');
INSERT INTO `links` VALUES ('2', 'https://www.etsy.com/teams/10112/promotion-locomotion/discuss/15522894/?ref=team_page', '2015-12-27 16:22:21', '2015-12-27 16:22:26', '0');
INSERT INTO `links` VALUES ('3', 'https://www.etsy.com/teams/15279/for-all-members-on-etsy/discuss/15550574/page/6565?ref=team_page', '2015-12-27 16:22:56', '2015-12-27 16:23:00', '0');
INSERT INTO `links` VALUES ('4', 'https://www.etsy.com/teams/21653/daily-promotion/discuss/16560979/page/1307?ref=team_page', '2015-12-27 16:23:19', '2015-12-27 16:23:22', '0');
