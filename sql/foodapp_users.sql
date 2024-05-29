-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 128.199.64.24    Database: foodapp
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `registdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastlogin` timestamp NULL DEFAULT NULL,
  `password` varchar(1024) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_email` (`email`),
  UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,'admin','123451',1,NULL,'2024-05-15 11:23:36',NULL,'$2a$10$ashawMxAZyJqZ.Fp1pcu5eSCwJOrb/VBJb4xqGXZTQS6KRg2KvA.u'),(9,'usernoshop','12d',1,NULL,'2024-05-15 11:24:04',NULL,'$2a$10$WnztYXHYWLI2/in26lsjbeHAN3AwNtyF1lLIrUmiuwy833.NxVygW'),(10,'usershop1','12dq',1,NULL,'2024-05-15 11:24:14','2024-05-21 15:23:48','$2a$10$uOuCJQb92yUcYl6rYt/feu.gw9HyRzPB6E41sq3Y8Xy7RhtcBWnIG'),(11,'usershop2','12dwq',1,NULL,'2024-05-15 11:24:22','2024-05-15 13:22:52','$2a$10$.OtxF4BUNRFUE0KrvmdGieARh97SMzZbga6Ii2dPtcbxjKf.EtRmm'),(12,'usershop3','12dwwq',1,NULL,'2024-05-15 11:24:27','2024-05-15 13:30:07','$2a$10$rQ6GVQTOhk18GVWzrrRuKemFumT6aVhzQLtz5XSQ5yESx9MzMFk.u'),(13,'usershop4','12dqwwq',1,NULL,'2024-05-15 11:24:32','2024-05-15 13:32:40','$2a$10$AqBcPysK/.Hb1SktnPCa..C1EbiyhASL16PbaMcwOmOYoDgsjsWEO'),(14,'usershop5','12dqwwqq',1,NULL,'2024-05-15 11:24:37','2024-05-15 12:39:53','$2a$10$R4QrwQUCHlzrn7FPvttFRudkcIwdsRLXX3s2.BseqsYw3cEx1GttG'),(15,'hiukon','abc',1,NULL,'2024-05-16 01:12:23','2024-05-16 01:12:24','$2a$10$WoNc9YeVv70zOZdf4FLxN.jj1gmKRuYWjx6dp7nR7Z1QRR8EoqX1C'),(16,'abc','ghgfgg',1,NULL,'2024-05-16 01:24:12',NULL,'$2a$10$iCya5stgs8gX7DJyhIDmd.veTeyXAC5hUyvSxGWMxgLxREvDjTSMi'),(17,'hiu','hiu',1,NULL,'2024-05-16 01:30:35','2024-05-16 01:30:36','$2a$10$sJmlBFcEAytsMenYZie1kO4EJLc8LMP31SSuv.OcF09y1CTO29h0W'),(18,'dmanhthien2','123',1,NULL,'2024-05-17 15:44:01','2024-05-17 15:44:02','$2a$10$BgxKj3uoNTXV6gDa3YNvOu/LUv5JmjFAR50motOkjp9ZWRN0Ng6CG'),(19,'hiuk','fghhgf',1,NULL,'2024-05-17 15:49:52','2024-05-21 09:35:55','$2a$10$H6pqTAwJANvYKT2bhrADIOKOv8HBqTuAyO6e9gxVVI0kYIOzGt9IG'),(20,'hehe','dddddd',1,NULL,'2024-05-17 17:45:29',NULL,'$2a$10$tABT8lfeFEcSgeujPTfHAuPNOkU1q0gIHjPtADZtDCel5Cmu0fcdq'),(21,'tao','tao@gmail.com',1,NULL,'2024-05-18 02:30:17','2024-05-18 02:31:48','$2a$10$0/j4cXwMH7lWpgwEiKCibuHtDkVq9CbWAuToGIu97qFjrlbpl8XHK'),(22,'phuc','1234',1,NULL,'2024-05-18 02:44:33',NULL,'$2a$10$Y4oUjAfgED6jhl.V9AwgYOA0iTK0xmB0pJzAwouZKojq8qgfseU0y'),(23,'mnh','mnh',1,NULL,'2024-05-18 02:56:42','2024-05-18 02:56:43','$2a$10$dbBQDv0M.kJTocT9BoUM3.RSGmqianSDOaXvhyXk7CcTvxLW4tA1q'),(24,'hehe111','agin0969@gmail.com',1,NULL,'2024-05-20 08:34:38','2024-05-20 13:32:33','$2a$10$nzP1sAsilO0Fi0v6RUa9XOv/lmX4NYHgTSNH42TIKlp4Upp4U/8de');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-21 23:09:27
