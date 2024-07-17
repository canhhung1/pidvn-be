-- MariaDB dump 10.19  Distrib 10.11.2-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: pidvn
-- ------------------------------------------------------
-- Server version	10.11.2-MariaDB

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
-- Table structure for table `ie_drawing`
--

DROP TABLE IF EXISTS `ie_drawing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ie_drawing` (
  `id` varchar(45) NOT NULL,
  `parent_id` varchar(45) DEFAULT NULL,
  `drawing_no` varchar(45) DEFAULT NULL,
  `drawing_name` varchar(45) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `unit` varchar(45) DEFAULT NULL,
  `material` varchar(45) DEFAULT NULL,
  `hardness` varchar(45) DEFAULT NULL,
  `polishing` varchar(45) DEFAULT NULL,
  `supplier` varchar(45) DEFAULT NULL,
  `version` varchar(45) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `progress_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_drawing`
--

LOCK TABLES `ie_drawing` WRITE;
/*!40000 ALTER TABLE `ie_drawing` DISABLE KEYS */;
INSERT INTO `ie_drawing` VALUES
('15d7b7b2-7e82-5f1c-14be-87f67ede0815','19356bcc-b305-7d7e-37bb-6ac8c985dfa6','TD-17005-02001','Coil feeder rail A',1,'Pcs',NULL,NULL,NULL,NULL,NULL,NULL,1,3,'2024-07-17 02:31:05','2024-07-17 02:31:05'),
('19356bcc-b305-7d7e-37bb-6ac8c985dfa6','72d3357a-4c54-c073-a914-23d3e7e47315','TD-17005-02000','Feeder Section',1,'Block',NULL,NULL,NULL,NULL,NULL,NULL,1,3,'2024-07-17 02:30:23','2024-07-17 02:30:23'),
('2038a573-bab4-113c-d391-7e27058591b7','19356bcc-b305-7d7e-37bb-6ac8c985dfa6','TD-17005-02002','Coil feeder rail B',2,'Pcs',NULL,NULL,NULL,NULL,NULL,NULL,1,3,'2024-07-17 02:34:01','2024-07-17 02:34:01'),
('5894433b-2552-6ac9-c8c2-33df390eebea','cbeade2c-f534-e347-4b02-48ed5493710d','TD-17005-01001','Yoke insertion rail A',1,'Pcs','S50C',NULL,'Ni Plating',NULL,NULL,NULL,1,3,NULL,'2024-07-17 03:27:15'),
('58998173-77a2-d419-99e5-725ea0724768','dda599ec-b509-7e9b-a6b1-5d41c98d2089',' TD-17005-04001','Core insert rail A',1,'Pcs','S50C',NULL,'Electroless nickel plating',NULL,NULL,NULL,1,3,NULL,'2024-07-17 03:28:16'),
('72d3357a-4c54-c073-a914-23d3e7e47315','ec583ae8-b586-7b0e-944b-de55025756d2','TD-17005-00000','Coil Yoke Ironcore Supply',1,'Machine',NULL,NULL,NULL,NULL,NULL,NULL,1,3,'2024-07-17 02:10:00','2024-07-17 02:10:00'),
('a6356dc3-531a-2dcd-dc66-83d5483e56c4','dda599ec-b509-7e9b-a6b1-5d41c98d2089','TD-17005-04002','Iron core insertion section rail C',1,'Pcs','SKS3','HRC 58','Hard Chrome Plating',NULL,NULL,NULL,1,3,NULL,'2024-07-17 03:28:45'),
('ae1d7231-c6a0-cc70-ae6d-e989333d75ec','cbeade2c-f534-e347-4b02-48ed5493710d','TD-17005-01002','Yoke insertion section rail B',1,'Pcs','SKS3','HRC 56-58','Chrome Plating',NULL,NULL,NULL,1,3,NULL,'2024-07-17 03:27:41'),
('cbeade2c-f534-e347-4b02-48ed5493710d','72d3357a-4c54-c073-a914-23d3e7e47315','TD-17005-01000','Cylinder Holding Coil Yoke Insert',1,'Block',NULL,NULL,NULL,NULL,NULL,NULL,1,3,'2024-07-17 02:14:37','2024-07-17 02:14:37'),
('dda599ec-b509-7e9b-a6b1-5d41c98d2089','72d3357a-4c54-c073-a914-23d3e7e47315','TD-17005-04000','Iron core insertion part',1,'Block',NULL,NULL,NULL,NULL,NULL,NULL,1,3,'2024-07-17 02:36:50','2024-07-17 02:36:50'),
('ec583ae8-b586-7b0e-944b-de55025756d2',NULL,'TD-1700-00000','TB1 Line No 03',1,'Line',NULL,'','',NULL,NULL,NULL,1,3,'2024-07-17 02:10:00','2024-07-17 02:10:00');
/*!40000 ALTER TABLE `ie_drawing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ie_project`
--

DROP TABLE IF EXISTS `ie_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ie_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(45) NOT NULL,
  `control_no` varchar(45) NOT NULL,
  `project_type_id` varchar(45) NOT NULL,
  `section_id` int(11) DEFAULT NULL,
  `created_by` varchar(8) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `project_name_UNIQUE` (`project_name`),
  UNIQUE KEY `control_no_UNIQUE` (`control_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_project`
--

LOCK TABLES `ie_project` WRITE;
/*!40000 ALTER TABLE `ie_project` DISABLE KEYS */;
INSERT INTO `ie_project` VALUES
(1,'PF Coil Supply Machine','RE-T0001','1',NULL,'3012982',NULL,NULL),
(2,'AAA','RE-T0002','1',NULL,NULL,'2024-07-17 10:16:33','2024-07-17 10:16:33');
/*!40000 ALTER TABLE `ie_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ie_project_progress`
--

DROP TABLE IF EXISTS `ie_project_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ie_project_progress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_project_progress`
--

LOCK TABLES `ie_project_progress` WRITE;
/*!40000 ALTER TABLE `ie_project_progress` DISABLE KEYS */;
INSERT INTO `ie_project_progress` VALUES
(1,'Disscusing','2024-07-03 03:10:10','2024-07-03 03:10:10'),
(2,'Design & review​','2024-07-03 03:10:10','2024-07-03 03:10:10'),
(3,'Extract Drawing & complete​','2024-07-03 03:10:10','2024-07-03 03:10:10'),
(4,'PO & QO','2024-07-03 03:10:10','2024-07-03 03:10:10'),
(5,'4M Change progress','2024-07-03 03:10:10','2024-07-03 03:10:10'),
(6,'Michining & Order','2024-07-03 03:10:10','2024-07-03 03:10:10'),
(7,'Set-up & trial','2024-07-03 03:10:10','2024-07-03 03:10:10'),
(8,'Approval & Mass Pro','2024-07-03 03:10:10','2024-07-03 03:10:10'),
(9,'Prepare Document','2024-07-03 03:10:10','2024-07-03 03:10:10'),
(10,'Review','2024-07-03 03:10:10','2024-07-03 03:10:10'),
(11,'Transfer','2024-07-03 03:10:10','2024-07-03 03:10:10'),
(12,'DR with supplier​','2024-07-03 03:10:10','2024-07-03 03:10:10');
/*!40000 ALTER TABLE `ie_project_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ie_project_type`
--

DROP TABLE IF EXISTS `ie_project_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ie_project_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_project_type`
--

LOCK TABLES `ie_project_type` WRITE;
/*!40000 ALTER TABLE `ie_project_type` DISABLE KEYS */;
INSERT INTO `ie_project_type` VALUES
(1,'New MC design​'),
(2,'Modify MC'),
(3,'Transfer MC​'),
(4,'Order New MC​');
/*!40000 ALTER TABLE `ie_project_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ie_project_type_progress`
--

DROP TABLE IF EXISTS `ie_project_type_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ie_project_type_progress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_type_id` int(11) DEFAULT NULL,
  `project_progress_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_project_type_progress`
--

LOCK TABLES `ie_project_type_progress` WRITE;
/*!40000 ALTER TABLE `ie_project_type_progress` DISABLE KEYS */;
INSERT INTO `ie_project_type_progress` VALUES
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,1,5),
(6,1,6),
(7,1,7),
(8,1,8),
(9,2,1),
(10,2,2),
(11,2,3),
(12,2,4),
(13,2,5),
(14,2,6),
(15,2,7),
(16,2,8),
(17,3,9),
(18,3,10),
(19,3,11),
(20,3,7),
(21,3,8),
(22,4,1),
(23,4,4),
(24,4,5),
(25,4,12),
(26,4,6),
(27,4,7),
(28,4,8);
/*!40000 ALTER TABLE `ie_project_type_progress` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-17 17:20:57
