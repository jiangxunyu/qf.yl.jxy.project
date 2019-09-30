/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : qf-yl

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-09-29 22:47:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `image` varchar(255) NOT NULL,
  `type_id` bigint(20) NOT NULL,
  `type_name` varchar(20) DEFAULT NULL,
  `sale_point` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('9', '防火电缆', '600', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2FqbWABoZyAAD5CAJFb_c577.jpg', '3', '耐火电缆', '耐高温');
INSERT INTO `product` VALUES ('10', '控制电缆', '600', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2Fqe6ACCJUAABipTK2tWA968.jpg', '5', '网络电缆', '传输安全');
INSERT INTO `product` VALUES ('11', '无机复合绝', '500', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2FqjaAWkXcAAAsu_23ahM580.jpg', '5', '网络电缆', '信号传输快');
INSERT INTO `product` VALUES ('12', '绝缘电缆', '850', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2FqluANKSKAABI5S2fGIQ967.jpg', '3', '耐火电缆', '不漏电');
INSERT INTO `product` VALUES ('13', '聚氯乙烯绝', '750', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2Fqn2AYSOfAABMeWptpAs320.jpg', '4', '电话电缆', '耐腐蚀');
INSERT INTO `product` VALUES ('14', '缘矿物绝缘', '900', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2FqrSAROsVAABOTTPHCaU863.jpg', '4', '电话电缆', '防雷');
INSERT INTO `product` VALUES ('20', '缘耐高温控制电缆', '800', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2IORCAU-S3AAKkn1v1Efg138.jpg', '3', '耐火电缆', '没有卖点');
INSERT INTO `product` VALUES ('21', '三无电缆', '400', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2IOT6AUh9OAAAsu_23ahM008.jpg', '4', '电话电缆', '价格便宜');
INSERT INTO `product` VALUES ('22', '想不出叫什么名字', '500', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2IOYeAQUVhAAD5CAJFb_c001.jpg', '5', '网络电缆', '。。。。');
INSERT INTO `product` VALUES ('23', '好电缆', '650', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2IOjKASLdVAABipTK2tWA442.jpg', '3', '耐火电缆', '不错哟');
INSERT INTO `product` VALUES ('24', '发电站用电缆', '2000', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2IOmmAL7_pAABOTTPHCaU592.jpg', '3', '耐火电缆', '载荷高');
INSERT INTO `product` VALUES ('25', '没有名字', '450', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2IPquANw99AABI5S2fGIQ957.jpg', '5', '网络电缆', '跳楼价');
INSERT INTO `product` VALUES ('26', '航空电缆', '3000', 'http://192.168.2.133:8888/group1/M00/00/01/wKgChV2IQB6AAJACAABMeWptpAs753.jpg', '5', '网络电缆', '高品质');
INSERT INTO `product` VALUES ('27', '高压电缆', '2500', 'http://192.168.2.133:8888/group1/M00/00/01/wKgChV2IpT-ALP_PAAAsu_23ahM544.jpg', '3', '耐火电缆', '耐高压');
INSERT INTO `product` VALUES ('50', '华为电缆', '800', 'http://192.168.2.133:8888/group1/M00/00/01/wKgChV2JkGWAfIdHAABipTK2tWA395.jpg', '5', '网络电缆', '安全');
INSERT INTO `product` VALUES ('51', '国家用电缆', '3500', 'http://192.168.2.133:8888/group1/M00/00/01/wKgChV2JkaiACO7RAAAsu_23ahM575.jpg', '4', '电话电缆', '量子传输');
INSERT INTO `product` VALUES ('52', '工程专用', '1200', 'http://192.168.2.133:8888/group1/M00/00/01/wKgChV2KHCyAcVuhAABOTTPHCaU502.jpg', '3', '耐火电缆', '耐磨');
INSERT INTO `product` VALUES ('53', '公司用电缆', '1500', 'http://192.168.2.133:8888/group1/M00/00/01/wKgChV2KIqmAMw-WAAKkn1v1Efg304.jpg', '5', '网络电缆', '耐用');
INSERT INTO `product` VALUES ('59', '测试电缆2', '500', 'http://192.168.2.133:8888/group1/M00/00/01/wKgChV2MnYeAD-_kAAD5CAJFb_c608.jpg', '5', '网络电缆', '测试用电缆');
INSERT INTO `product` VALUES ('60', '测试用电缆3', '520', 'http://192.168.2.133:8888/group1/M00/00/01/wKgChV2Mpa-ATap3AABipTK2tWA277.jpg', '3', '耐火电缆', '测试专用3');

-- ----------------------------
-- Table structure for `product_type`
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `pid` bigint(20) NOT NULL,
  `flag` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `create_user` bigint(20) NOT NULL,
  `updata_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_type
-- ----------------------------
INSERT INTO `product_type` VALUES ('1', '电气电缆', '0', '1', '2019-09-20 14:30:42', '2019-09-20 14:30:45', '1', '1');
INSERT INTO `product_type` VALUES ('2', '通信、光缆', '0', '1', '2019-09-20 14:31:18', '2019-09-20 14:31:21', '1', '1');
INSERT INTO `product_type` VALUES ('3', '家庭装线', '0', '1', '2019-09-20 14:32:55', '2019-09-20 14:32:58', '1', '1');
INSERT INTO `product_type` VALUES ('4', '耐火电缆', '1', '1', '2019-09-20 14:45:21', '2019-09-20 14:45:26', '1', '1');
INSERT INTO `product_type` VALUES ('5', '阻燃电缆', '1', '1', '2019-09-20 14:45:59', '2019-09-20 14:46:02', '1', '1');
INSERT INTO `product_type` VALUES ('6', '电话电缆', '2', '1', '2019-09-20 14:47:53', '2019-09-20 14:47:55', '1', '1');
INSERT INTO `product_type` VALUES ('7', '电视电缆', '2', '1', '2019-09-20 14:48:20', '2019-09-20 14:48:22', '1', '1');
INSERT INTO `product_type` VALUES ('8', '网络电缆', '3', '1', '2019-09-20 14:50:10', '2019-09-20 14:50:13', '1', '1');
INSERT INTO `product_type` VALUES ('9', '家庭用电', '3', '1', '2019-09-20 14:51:52', '2019-09-20 14:51:55', '1', '1');

-- ----------------------------
-- Table structure for `shopping_cart`
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_image` varchar(255) NOT NULL,
  `product_price` decimal(10,0) NOT NULL,
  `product_mount` int(11) NOT NULL,
  `total_price` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES ('16', '13', '聚氯乙烯绝', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2Fqn2AYSOfAABMeWptpAs320.jpg', '750', '1', '750');
INSERT INTO `shopping_cart` VALUES ('20', '52', '工程专用', 'http://192.168.2.133:8888/group1/M00/00/01/wKgChV2KHCyAcVuhAABOTTPHCaU502.jpg', '1200', '1', '1200');
INSERT INTO `shopping_cart` VALUES ('21', '53', '公司用电缆', 'http://192.168.2.133:8888/group1/M00/00/01/wKgChV2KIqmAMw-WAAKkn1v1Efg304.jpg', '1500', '1', '1500');
INSERT INTO `shopping_cart` VALUES ('23', '12', '绝缘电缆', 'http://192.168.2.133:8888/group1/M00/00/00/wKgChV2FqluANKSKAABI5S2fGIQ967.jpg', '850', '1', '850');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `statu` varchar(2) DEFAULT 'no',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '啊湫的C和弦', '$2a$10$gqTcptGZ1Dq/62ncOPxI3uxEaLfQMxaGJDNT6x5d1kd6yHygs2S6i', '2469193981@qq.com', null, '1');
INSERT INTO `user` VALUES ('2', '小明', '$2a$10$gqTcptGZ1Dq/62ncOPxI3uxEaLfQMxaGJDNT6x5d1kd6yHygs2S6i', '15949538610@qq.com', null, 'ok');
