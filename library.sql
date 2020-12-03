-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `birthday` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `city` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `country` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `background` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES ('3b35b8aa-56c2-4573-9334-f22eb722054b','Joanne K. Rowling ','Joanne K. Rowling','','Nữ','','','',''),('43ca0c8e-18ea-43cb-a3ae-499efeb737dc','Nguyễn Nhật Ánh','Nguyễn Nhật Ánh','','Nam','','VietNam','',NULL);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `publishing_company_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `publishing_year` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `entry_date` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_category_idx` (`category_id`),
  KEY `fk_book_publishing_company_idx` (`publishing_company_id`),
  KEY `fk_book_author_idx` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('088a5cee-4988-4de2-afc3-946e54359135','2fffffffffffff','43ca0c8e-18ea-43cb-a3ae-499efeb737dc','4b6de219-1d19-45ce-ae00-8af0a2ebe061','3333','1','','',''),('859f7811-fa29-4c42-813f-452aee408fc1','Harry Poster 5','3b35b8aa-56c2-4573-9334-f22eb722054b','8dd2ee11-7a42-4cae-949b-7e71553dde5c','69366dbd-1d60-478e-9320-7420e5c333f0','5','5','5','5'),('bc7d3f79-444d-47ca-a357-6da6cd6b409e','Harry Poster','3b35b8aa-56c2-4573-9334-f22eb722054b','8dd2ee11-7a42-4cae-949b-7e71553dde5c','69366dbd-1d60-478e-9320-7420e5c333f0','','','',''),('f985a35f-5225-438d-807e-ad07d90a6210','2ggg','3b35b8aa-56c2-4573-9334-f22eb722054b','4b6de219-1d19-45ce-ae00-8af0a2ebe061','1d07ec92-a047-41b8-9231-dab8c24070c1','1','','','');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('4b6de219-1d19-45ce-ae00-8af0a2ebe061','Xã hội',NULL),('69d36bc3-8b37-49a7-9337-917546944749','Truyện ngắn',''),('88124f18-c03f-4b44-bea1-dcbc04b9856e','Thiếu nhi',''),('8ac2f726-1031-471b-a7ad-4259b941531d','Văn học',NULL),('8dd2ee11-7a42-4cae-949b-7e71553dde5c','Tiểu thuyết','aa');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_card`
--

DROP TABLE IF EXISTS `member_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_card` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `object_id` int DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone_number` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_people_object_idx` (`object_id`),
  CONSTRAINT `fk_people_object` FOREIGN KEY (`object_id`) REFERENCES `object` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_card`
--

LOCK TABLES `member_card` WRITE;
/*!40000 ALTER TABLE `member_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `object`
--

DROP TABLE IF EXISTS `object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `object` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `object`
--

LOCK TABLES `object` WRITE;
/*!40000 ALTER TABLE `object` DISABLE KEYS */;
/*!40000 ALTER TABLE `object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishing_company`
--

DROP TABLE IF EXISTS `publishing_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publishing_company` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` char(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishing_company`
--

LOCK TABLES `publishing_company` WRITE;
/*!40000 ALTER TABLE `publishing_company` DISABLE KEYS */;
INSERT INTO `publishing_company` VALUES ('1d07ec92-a047-41b8-9231-dab8c24070c1','Thanh niên','1','1','1'),('2401668c-7f78-4f98-94b7-b4770d75043b','Thanh niên','','',''),('3333','Giáo dục',NULL,NULL,NULL),('69366dbd-1d60-478e-9320-7420e5c333f0','Kim Đồng','1','1','1');
/*!40000 ALTER TABLE `publishing_company` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-03 20:26:41
