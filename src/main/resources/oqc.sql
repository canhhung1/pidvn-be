-- MariaDB dump 10.19  Distrib 10.9.3-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: pidvn
-- ------------------------------------------------------
-- Server version	10.9.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `oqc_check_item`
--

DROP TABLE IF EXISTS `oqc_check_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oqc_check_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vi_name` varchar(45) NOT NULL,
  `en_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oqc_check_item`
--

LOCK TABLES `oqc_check_item` WRITE;
/*!40000 ALTER TABLE `oqc_check_item` DISABLE KEYS */;
INSERT INTO `oqc_check_item` VALUES
(1,'Kiểm tra ngoại quan',NULL),
(2,'Điện trở cách điện',NULL),
(3,'Điện áp chịu đựng',NULL),
(4,'Điện trở cuộn dây',NULL),
(5,'Điện áp đóng tiếp điểm',NULL),
(6,'Điện áp mở tiếp điểm',NULL),
(7,'Điện trở tiếp xúc của tiếp điểm',NULL),
(8,'Thời gian đóng tiếp điểm',NULL),
(9,'Thời gian mở tiếp điểm',NULL),
(10,'Thời gian nảy lên khi tiếp xúc',NULL),
(11,'Thời gian tiếp điểm nảy lên khi mở',NULL),
(12,'Dưỡng kiểm tra khoảng cách chân',NULL),
(13,'Kiểm tra dò khí',NULL),
(14,'Kiểm tra ống đóng gói',NULL),
(15,'Đóng gói thùng carton',NULL);
/*!40000 ALTER TABLE `oqc_check_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oqc_master_data`
--

DROP TABLE IF EXISTS `oqc_master_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oqc_master_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `req_no` varchar(45) NOT NULL,
  `qa_card` varchar(45) NOT NULL,
  `item` int(11) DEFAULT NULL,
  `value` varchar(5) DEFAULT NULL,
  `number_of_sampling` int(11) DEFAULT NULL,
  `reject_number` int(11) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oqc_master_data`
--

LOCK TABLES `oqc_master_data` WRITE;
/*!40000 ALTER TABLE `oqc_master_data` DISABLE KEYS */;
INSERT INTO `oqc_master_data` VALUES
(1,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002',1,'OK',50,0,'','2022-12-17 08:35:00','2022-12-17 08:35:00'),
(2,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002',2,'OK',10,0,NULL,'2022-12-17 08:35:00','2022-12-17 08:35:00'),
(3,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002',3,'NG',10,0,NULL,'2022-12-17 08:35:00','2022-12-17 08:35:00'),
(4,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002',4,'OK',10,0,NULL,'2022-12-17 08:35:00','2022-12-17 08:35:00'),
(5,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002',5,'OK',30,0,NULL,'2022-12-17 08:35:00','2022-12-17 08:35:00'),
(6,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002',6,'NA',10,0,NULL,'2022-12-17 08:35:00','2022-12-17 08:35:00'),
(7,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002',7,'OK',10,0,NULL,'2022-12-17 08:35:00','2022-12-17 08:35:00'),
(8,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002',8,'OK',20,0,NULL,'2022-12-17 08:35:00','2022-12-17 08:35:00'),
(9,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002',9,'OK',30,0,NULL,'2022-12-17 08:35:00','2022-12-17 08:35:00'),
(10,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002',10,'NG',10,0,NULL,'2022-12-17 08:35:00','2022-12-17 08:35:00'),
(11,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002',11,'OK',10,0,NULL,'2022-12-17 08:35:00','2022-12-17 08:35:00');
/*!40000 ALTER TABLE `oqc_master_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oqc_request`
--

DROP TABLE IF EXISTS `oqc_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oqc_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `req_no` varchar(45) NOT NULL,
  `qa_card` varchar(45) DEFAULT NULL,
  `qty` varchar(45) DEFAULT NULL,
  `customer` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `oqc_request_status` int(11) DEFAULT NULL,
  `temperature` varchar(45) DEFAULT NULL,
  `humidity` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `oqc_date` timestamp NULL DEFAULT NULL,
  `judgment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `req_no_UNIQUE` (`req_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oqc_request`
--

LOCK TABLES `oqc_request` WRITE;
/*!40000 ALTER TABLE `oqc_request` DISABLE KEYS */;
INSERT INTO `oqc_request` VALUES
(1,'ROQC-2022-12-15-3','ACTL3CR3*TL1*2022-12-15*A*002','5000',NULL,'3012982',2,NULL,NULL,'2022-12-18 09:56:55','2022-12-18 09:56:55',NULL,NULL),
(2,'ROQC-2022-12-15-2','ACTL3CR3*TL1*2022-12-15*B*002','6000',NULL,'3012982',1,NULL,NULL,'2022-12-18 08:56:55','2022-12-18 08:56:55',NULL,'NG');
/*!40000 ALTER TABLE `oqc_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oqc_request_status`
--

DROP TABLE IF EXISTS `oqc_request_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oqc_request_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oqc_request_status`
--

LOCK TABLES `oqc_request_status` WRITE;
/*!40000 ALTER TABLE `oqc_request_status` DISABLE KEYS */;
INSERT INTO `oqc_request_status` VALUES
(1,'Chờ xử lý'),
(2,'Đang xử lý'),
(3,'Đã xử lý');
/*!40000 ALTER TABLE `oqc_request_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-18 22:52:18
