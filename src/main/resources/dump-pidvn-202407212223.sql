-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: pidvn
-- ------------------------------------------------------
-- Server version	11.4.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ie_dc_001`
--

DROP TABLE IF EXISTS `ie_dc_001`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ie_dc_001` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_no` varchar(45) NOT NULL,
  `project_name` varchar(45) DEFAULT NULL,
  `project_type_id` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `supplier` varchar(45) DEFAULT NULL,
  `line` varchar(45) DEFAULT NULL,
  `tact_time` varchar(45) DEFAULT NULL,
  `capacity` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `control_no_UNIQUE` (`project_no`),
  KEY `f_status_id_idx` (`status_id`),
  CONSTRAINT `f_status_id` FOREIGN KEY (`status_id`) REFERENCES `ie_dc_002` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='bảng này lưu trữ thông tin của project';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_dc_001`
--

LOCK TABLES `ie_dc_001` WRITE;
/*!40000 ALTER TABLE `ie_dc_001` DISABLE KEYS */;
INSERT INTO `ie_dc_001` VALUES (1,'RE-T0001','PF Coil Supply',1,3012982,NULL,'2024-07-20 14:40:31','2024-07-21 10:53:55','Osco','PF Line no 01','0.9s/pcs',NULL),(2,'RE-T0002','PF Line 01',3,3012982,NULL,'2024-07-20 10:36:03','2024-07-20 10:36:03',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ie_dc_001` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ie_dc_005`
--

DROP TABLE IF EXISTS `ie_dc_005`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ie_dc_005` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Các progress của project';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_dc_005`
--

LOCK TABLES `ie_dc_005` WRITE;
/*!40000 ALTER TABLE `ie_dc_005` DISABLE KEYS */;
INSERT INTO `ie_dc_005` VALUES (1,'Disscusing','2024-07-02 20:10:10','2024-07-02 20:10:10'),(2,'Design & Review​','2024-07-02 20:10:10','2024-07-02 20:10:10'),(3,'Extract Drawing & Complete​','2024-07-02 20:10:10','2024-07-02 20:10:10'),(4,'PO & QO','2024-07-02 20:10:10','2024-07-02 20:10:10'),(5,'4M Change Progress','2024-07-02 20:10:10','2024-07-02 20:10:10'),(6,'Michining & Order','2024-07-02 20:10:10','2024-07-02 20:10:10'),(7,'Setup & Trial','2024-07-02 20:10:10','2024-07-02 20:10:10'),(8,'Approval & Mass Pro','2024-07-02 20:10:10','2024-07-02 20:10:10'),(9,'Prepare Document','2024-07-02 20:10:10','2024-07-02 20:10:10'),(10,'Review','2024-07-02 20:10:10','2024-07-02 20:10:10'),(11,'Transfer','2024-07-02 20:10:10','2024-07-02 20:10:10'),(12,'DR with supplier​','2024-07-02 20:10:10','2024-07-02 20:10:10');
/*!40000 ALTER TABLE `ie_dc_005` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ie_dc_008`
--

DROP TABLE IF EXISTS `ie_dc_008`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ie_dc_008` (
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
  `project_progress_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_dc_008`
--

LOCK TABLES `ie_dc_008` WRITE;
/*!40000 ALTER TABLE `ie_dc_008` DISABLE KEYS */;
/*!40000 ALTER TABLE `ie_dc_008` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ie_dc_004`
--

DROP TABLE IF EXISTS `ie_dc_004`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ie_dc_004` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT 'project type name: loại project',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Các loại project';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_dc_004`
--

LOCK TABLES `ie_dc_004` WRITE;
/*!40000 ALTER TABLE `ie_dc_004` DISABLE KEYS */;
INSERT INTO `ie_dc_004` VALUES (1,'New MC design​'),(2,'Modify MC'),(3,'Transfer MC​'),(4,'Order New MC​');
/*!40000 ALTER TABLE `ie_dc_004` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ie_dc_006`
--

DROP TABLE IF EXISTS `ie_dc_006`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ie_dc_006` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_type_id` int(11) DEFAULT NULL,
  `project_progress_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `f_project_type_id_006_004_idx` (`project_type_id`),
  KEY `f_project_progress_id_006_004_idx` (`project_progress_id`),
  CONSTRAINT `f_project_progress_id_006_004` FOREIGN KEY (`project_progress_id`) REFERENCES `ie_dc_005` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `f_project_type_id_006_004` FOREIGN KEY (`project_type_id`) REFERENCES `ie_dc_004` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bảng liên kết giữa project_type và project_progress';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_dc_006`
--

LOCK TABLES `ie_dc_006` WRITE;
/*!40000 ALTER TABLE `ie_dc_006` DISABLE KEYS */;
INSERT INTO `ie_dc_006` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,2,1),(10,2,2),(11,2,3),(12,2,4),(13,2,5),(14,2,6),(15,2,7),(16,2,8),(17,3,9),(18,3,10),(19,3,11),(20,3,7),(21,3,8),(22,4,1),(23,4,4),(24,4,5),(25,4,12),(26,4,6),(27,4,7),(28,4,8);
/*!40000 ALTER TABLE `ie_dc_006` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ie_dc_002`
--

DROP TABLE IF EXISTS `ie_dc_002`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ie_dc_002` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Các trạng thái của 1 project';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_dc_002`
--

LOCK TABLES `ie_dc_002` WRITE;
/*!40000 ALTER TABLE `ie_dc_002` DISABLE KEYS */;
/*!40000 ALTER TABLE `ie_dc_002` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ie_dc_003`
--

DROP TABLE IF EXISTS `ie_dc_003`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ie_dc_003` (
  `id` varchar(45) NOT NULL,
  `parent_id` varchar(45) DEFAULT NULL,
  `drawing_no` varchar(45) DEFAULT NULL,
  `drawing_name` varchar(45) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `unit` varchar(45) DEFAULT NULL,
  `material` varchar(45) DEFAULT NULL,
  `hard_ness` varchar(45) DEFAULT NULL,
  `polishing` varchar(45) DEFAULT NULL,
  `supplier` varchar(45) DEFAULT NULL,
  `version` varchar(45) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='bảng này lưu trữ thông tin các bản vẽ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_dc_003`
--

LOCK TABLES `ie_dc_003` WRITE;
/*!40000 ALTER TABLE `ie_dc_003` DISABLE KEYS */;
/*!40000 ALTER TABLE `ie_dc_003` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ie_dc_007`
--

DROP TABLE IF EXISTS `ie_dc_007`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ie_dc_007` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `project_progress_id` int(11) NOT NULL,
  `progress` int(11) DEFAULT NULL,
  `start_plan` date DEFAULT NULL,
  `end_plan` date DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `f_project_id_007_001_idx` (`project_id`),
  KEY `f_project_progress_id_007_005_idx` (`project_progress_id`),
  CONSTRAINT `f_project_id_007_001` FOREIGN KEY (`project_id`) REFERENCES `ie_dc_001` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `f_project_progress_id_007_005` FOREIGN KEY (`project_progress_id`) REFERENCES `ie_dc_005` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ie_dc_007`
--

LOCK TABLES `ie_dc_007` WRITE;
/*!40000 ALTER TABLE `ie_dc_007` DISABLE KEYS */;
INSERT INTO `ie_dc_007` VALUES (1,1,3,20,NULL,NULL,'2024-07-21 17:54:26','2024-07-21 17:54:26'),(2,1,1,100,NULL,NULL,'2024-07-21 21:12:39','2024-07-21 21:12:39');
/*!40000 ALTER TABLE `ie_dc_007` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-21 22:23:13
