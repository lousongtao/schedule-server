-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: schedule
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `user_time`
--

DROP TABLE IF EXISTS `user_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `time_slot_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Staff is available or not in this period,\ndefault 0;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_time`
--

LOCK TABLES `user_time` WRITE;
/*!40000 ALTER TABLE `user_time` DISABLE KEYS */;
INSERT INTO `user_time` VALUES (1,1,1,'2020-07-07',0),(2,1,1,'2020-07-06',0),(3,1,2,'2020-07-06',0),(4,1,2,'2020-07-07',0),(5,1,1,'2020-07-08',0),(6,1,2,'2020-07-08',0),(7,1,1,'2020-07-09',0),(8,1,2,'2020-07-09',0),(9,1,1,'2020-07-10',0),(10,1,2,'2020-07-10',0),(11,1,1,'2020-07-11',0),(12,1,2,'2020-07-11',0),(13,1,1,'2020-07-12',0),(14,1,2,'2020-07-12',0),(15,2,1,'2020-07-06',1),(16,2,1,'2020-07-07',1),(17,2,2,'2020-07-07',1),(18,2,2,'2020-07-08',1),(19,2,1,'2020-07-08',1),(20,2,1,'2020-07-10',1),(21,2,1,'2020-07-13',1),(22,1,1,'2020-07-14',1),(23,1,2,'2020-07-20',1);
/*!40000 ALTER TABLE `user_time` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-13  0:09:07
