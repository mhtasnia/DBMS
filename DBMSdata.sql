CREATE DATABASE  IF NOT EXISTS `signup` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `signup`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: signup
-- ------------------------------------------------------
-- Server version	5.7.43-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allocationdetails`
--

DROP TABLE IF EXISTS `allocationdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allocationdetails` (
  `route` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `buscode` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allocationdetails`
--

LOCK TABLES `allocationdetails` WRITE;
/*!40000 ALTER TABLE `allocationdetails` DISABLE KEYS */;
INSERT INTO `allocationdetails` VALUES ('Mirpur','2023-11-14','09:00:00','d3'),('Narayanganj','2023-11-18','06:30:00','d5'),('Narayanganj','2023-11-17','06:30:00','g4'),('Narayanganj','2023-12-02','06:30:00','D16'),('Uttara','2023-12-04','06:30:00','D16'),('Mughda','2023-12-14','06:30:00','g4'),('Mughda','2023-12-14','06:30:00','D1');
/*!40000 ALTER TABLE `allocationdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `busbookinginfo`
--

DROP TABLE IF EXISTS `busbookinginfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `busbookinginfo` (
  `student_id` varchar(50) DEFAULT NULL,
  `bus_route` varchar(50) DEFAULT NULL,
  `booking_date` date DEFAULT NULL,
  `booking_time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `busbookinginfo`
--

LOCK TABLES `busbookinginfo` WRITE;
/*!40000 ALTER TABLE `busbookinginfo` DISABLE KEYS */;
INSERT INTO `busbookinginfo` VALUES (NULL,'Dhanmondi','2023-10-12','09:00:00'),(NULL,'Dhanmondi','2023-10-12','09:00:00'),(NULL,'Dhanmondi','2023-10-12','09:00:00'),(NULL,'Dhanmondi','2023-10-12','09:00:00'),(NULL,'Kashimpur','2023-10-03','09:00:00'),(NULL,'Kashimpur','2023-10-03','09:00:00'),(NULL,'Kashimpur','2023-10-03','09:00:00'),(NULL,'Kashimpur','2023-10-03','09:00:00'),(NULL,'Kashimpur','2023-10-03','09:00:00'),(NULL,'Kashimpur','2023-10-03','09:00:00'),(NULL,'Mughda','2023-10-03','10:30:00'),(NULL,'Mughda','2023-10-03','10:30:00'),(NULL,'Mirpur','2023-10-05','09:00:00'),(NULL,'Mirpur','2023-10-05','09:00:00'),('0242220005341191','Mughda','2023-10-31','09:00:00'),('','Dhanmondi','2023-10-10','09:00:00'),('342','Dhanmondi','2023-10-18','09:00:00'),('','Uttara','2023-10-18','06:30:00'),('23','Mirpur','2023-11-04','06:30:00'),('','Dhanmondi','2023-11-24','06:30:00'),('123','Dhanmondi','2023-11-16','10:30:00'),('237','Kashimpur','2023-11-07','09:00:00'),('1256','Narayanganj','2023-11-07','09:00:00'),('0242220005341199','Narayanganj','2023-11-10','06:30:00'),('1239','Kashimpur','2023-11-16','06:30:00'),('0242220005341199','Narayanganj','2023-11-18','06:30:00'),('0242220005341175','Kashimpur','2023-11-17','09:00:00'),('0242220005341199','Narayanganj','2023-11-17','06:30:00'),('0242220005341191','Mughda','2023-12-02','06:30:00'),('0242220005341199','Narayanganj','2023-12-02','09:00:00'),('0242220005341191','Mughda','2023-12-12','06:30:00'),('0242220005341199','Mughda','2023-12-14','06:30:00'),('0242220005341201','Mughda','2023-12-14','06:30:00');
/*!40000 ALTER TABLE `busbookinginfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buslist`
--

DROP TABLE IF EXISTS `buslist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buslist` (
  `buscode` varchar(50) DEFAULT NULL,
  `reg_no` varchar(20) DEFAULT NULL,
  `drivername` varchar(30) DEFAULT NULL,
  `driver_contact` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buslist`
--

LOCK TABLES `buslist` WRITE;
/*!40000 ALTER TABLE `buslist` DISABLE KEYS */;
INSERT INTO `buslist` VALUES ('D16','g-4389','sd sdf','018363284'),('d3','35345','hfgh','43545'),('g4','543','34dfgfdg','464567578'),('d5','5436','dghfd','e6546'),('h7','6478j','fjghkdjf','875609'),('D27','g-24734','Mr. abc','0135896457'),('D1','G-45231','Mr. abcd xyz','01922135633');
/*!40000 ALTER TABLE `buslist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancellation`
--

DROP TABLE IF EXISTS `cancellation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cancellation` (
  `student_id` varchar(50) DEFAULT NULL,
  `bus_route` varchar(50) DEFAULT NULL,
  `booking_date` date DEFAULT NULL,
  `booking_time` time DEFAULT NULL,
  `cancellation_reason` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancellation`
--

LOCK TABLES `cancellation` WRITE;
/*!40000 ALTER TABLE `cancellation` DISABLE KEYS */;
INSERT INTO `cancellation` VALUES ('678','Uttara','2023-10-18','06:30:00','06:30:00'),('678','Uttara','2023-10-18','06:30:00','06:30:00'),('678','Uttara','2023-10-18','06:30:00','06:30:00'),('678','Uttara','2023-10-18','06:30:00','06:30:00'),('0242220005341191','Mughda','2023-11-16','09:00:00','09:00:00'),('1238','Kashimpur','2023-11-16','06:30:00','06:30:00'),('1239','Mirpur','2023-11-16','06:30:00','Medical Emergency'),('0242220005341191','Narayanganj','2023-12-02','06:30:00','Class Canceled'),('0242220005341199','Mughda','2023-12-14','06:30:00','Class Canceled'),('0242220005341191','Mughda','2023-12-13','06:30:00','Class Canceled'),('0242220005341191','Mughda','2023-12-14','06:30:00','Class Canceled');
/*!40000 ALTER TABLE `cancellation` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER delete_booking_trigger
AFTER INSERT ON cancellation
FOR EACH ROW
BEGIN
    DELETE FROM busbookinginfo
    WHERE 
        student_id = NEW.student_id AND
        bus_route = NEW.bus_route AND
        booking_date = NEW.booking_date AND
        booking_time = NEW.booking_time;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER delete_booking_trigger_678 AFTER INSERT ON cancellation FOR EACH ROW BEGIN     DELETE FROM busbookinginfo     WHERE student_id = NEW.student_id AND           bus_route = NEW.bus_route AND           booking_date = NEW.booking_date AND           booking_time = NEW.booking_time; END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `signupinfo`
--

DROP TABLE IF EXISTS `signupinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `signupinfo` (
  `Student_id` varchar(20) DEFAULT NULL,
  `Stdent_mail` varchar(50) DEFAULT NULL,
  `account_pass` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `signupinfo`
--

LOCK TABLES `signupinfo` WRITE;
/*!40000 ALTER TABLE `signupinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `signupinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `signupstat`
--

DROP TABLE IF EXISTS `signupstat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `signupstat` (
  `Student_id` varchar(20) NOT NULL,
  `Stdent_mail` varchar(45) DEFAULT NULL,
  `account_pass` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `signupstat`
--

LOCK TABLES `signupstat` WRITE;
/*!40000 ALTER TABLE `signupstat` DISABLE KEYS */;
INSERT INTO `signupstat` VALUES ('0242220005341191','tasnia@diu.edu.bd','12345678'),('0242220005341199','nusrat@gmail.com','1356'),('0242220005341200','sh@gmail.com','13456789'),('0242220005341201','shuvo@diu.edu.bd','23456789'),('123','355','ewrt');
/*!40000 ALTER TABLE `signupstat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verifier`
--

DROP TABLE IF EXISTS `verifier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verifier` (
  `v_name` varchar(50) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `route` varchar(20) DEFAULT '1234',
  `contact_info` varchar(45) DEFAULT '1234',
  `Pass` varchar(45) DEFAULT '1234'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verifier`
--

LOCK TABLES `verifier` WRITE;
/*!40000 ALTER TABLE `verifier` DISABLE KEYS */;
INSERT INTO `verifier` VALUES ('Abcd efgh','abcd','Dhanmondi',NULL,NULL),('pqrs tuvw','pqrs','Mirpur',NULL,NULL),('pqrrsab cd','pcd','Narayanganj',NULL,NULL),('opqr','op','Mughda','0192364645','34ghsf');
/*!40000 ALTER TABLE `verifier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'signup'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-16 21:28:31
