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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `img` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) NOT NULL,
  `createat` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `shop_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `shop_id` (`shop_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (8,'Nước chanh','Nước chanh dây tươi mát','nuocchanh.jpg',15000.00,'2024-05-15 13:19:09',NULL,6,1),(9,'Cà phê Việt','Cà phê đen đá, hương vị đậm đà','68220nuocchanh.jpg',15000.00,'2024-05-15 13:20:57',NULL,6,1),(10,'Bánh mì chảo','Bánh mì chảo 3 loại thịt','banhmichao.jpg',100000.00,'2024-05-15 13:22:24',NULL,7,1),(12,'Cơm chiên cá mặn','Cơm chiên cá mặn','comchiencaman.jpg',50000.00,'2024-05-15 13:28:07',NULL,7,1),(13,'Chè trôi nước','Chè trôi nước','chetroinuoc.jpg',50000.00,'2024-05-15 13:29:27',NULL,8,8),(14,'Bánh flan','Bánh flan caramen mềm mịn','banhflan.jpg',50000.00,'2024-05-15 13:29:57',NULL,8,8),(15,'Gỏi cuốn','Gỏi cuốn tôm thịt, rau sống sạch','phocuon.jpg',50000.00,'2024-05-15 13:31:04',NULL,9,1),(16,'Salad trái cây','Salad trái cây nhiệt đới','saladtraicay.jpg',50000.00,'2024-05-15 13:32:16',NULL,9,9),(17,'Khoai tây chiên','Khoai tây chiên giòn rụm','khoailangchien.jpg',50000.00,'2024-05-15 13:33:53',NULL,10,1),(18,'Bánh ran','Bánh ran nhân đường, nóng hổi','banhgatochocolate.jpg',50000.00,'2024-05-15 13:34:29',NULL,10,10),(19,'Trà Đào','Trà đào cam sả, thức uống giải khát','nuoccamtuoi.jpg',50000.00,'2024-05-21 12:18:35',NULL,6,1),(20,'Cơm gà xối mỡ','Cơm gà xối mỡ, da giòn, thịt mềm, ngon ngọt','comga.jpg',50000.00,'2024-05-21 12:20:25',NULL,8,1),(21,'Sữa chua mít','Sữa chua mít thơm ngon','trasua.jpg',50000.00,'2024-05-21 12:21:12',NULL,8,1),(22,'Bò tái chanh','Sữa chua mít thơm ngon','bokhobo.jpg',50000.00,'2024-05-21 12:21:33',NULL,9,1),(23,'Nem rán','Nem rán giòn tan, vị đậm đà','garankfc.jpg',50000.00,'2024-05-21 12:22:14',NULL,10,1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-21 23:09:08
