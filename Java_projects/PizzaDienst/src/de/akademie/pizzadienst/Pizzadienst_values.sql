-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pizzadienst
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `baguette`
--

DROP TABLE IF EXISTS `baguette`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baguette` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Baguette_Groesse` int NOT NULL,
  `produktID` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Baguette_Baguette_Groesse1_idx` (`Baguette_Groesse`),
  KEY `fk_Baguette_Produkte1_idx` (`produktID`),
  CONSTRAINT `fk_Baguette_Baguette_Groesse1` FOREIGN KEY (`Baguette_Groesse`) REFERENCES `baguette_groesse` (`id`),
  CONSTRAINT `fk_Baguette_Produkte1` FOREIGN KEY (`produktID`) REFERENCES `produkte` (`produktID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baguette`
--

LOCK TABLES `baguette` WRITE;
/*!40000 ALTER TABLE `baguette` DISABLE KEYS */;
INSERT INTO `baguette` VALUES (1,1,10),(2,2,10),(3,3,10),(4,1,11),(5,2,11),(6,3,11),(7,1,12),(8,2,12),(9,3,12),(10,1,13),(11,2,13),(12,3,13),(13,1,14),(14,2,14),(15,3,14),(16,1,15),(17,2,15),(18,3,15);
/*!40000 ALTER TABLE `baguette` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baguette_groesse`
--

DROP TABLE IF EXISTS `baguette_groesse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baguette_groesse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `groesse` varchar(45) DEFAULT NULL,
  `preis` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baguette_groesse`
--

LOCK TABLES `baguette_groesse` WRITE;
/*!40000 ALTER TABLE `baguette_groesse` DISABLE KEYS */;
INSERT INTO `baguette_groesse` VALUES (1,'viertel',399),(2,'halb',599),(3,'ganz',799);
/*!40000 ALTER TABLE `baguette_groesse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `belaege`
--

DROP TABLE IF EXISTS `belaege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `belaege` (
  `belagID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `preis` int DEFAULT NULL,
  PRIMARY KEY (`belagID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belaege`
--

LOCK TABLES `belaege` WRITE;
/*!40000 ALTER TABLE `belaege` DISABLE KEYS */;
INSERT INTO `belaege` VALUES (1,'Tomate',100),(2,'Käse',100),(3,'Mozzarella',100),(4,'Salat',100),(5,'Salami',100),(6,'Schinken',100),(7,'Zwiebeln',100),(8,'Rucola',100);
/*!40000 ALTER TABLE `belaege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bestellungen`
--

DROP TABLE IF EXISTS `bestellungen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bestellungen` (
  `bestellNummer` int NOT NULL AUTO_INCREMENT,
  `bestellZeit` datetime DEFAULT NULL,
  `auslieferZeit` datetime DEFAULT NULL,
  `abnahmeZeit` datetime DEFAULT NULL,
  `Mitarbeiter_mitarbeiterNummer` int NOT NULL,
  `kundenNummer` int NOT NULL,
  PRIMARY KEY (`bestellNummer`),
  KEY `fk_Bestellungen_Mitarbeiter1_idx` (`Mitarbeiter_mitarbeiterNummer`),
  KEY `fk_Bestellungen_Kunde1_idx` (`kundenNummer`),
  CONSTRAINT `fk_Bestellungen_Kunde1` FOREIGN KEY (`kundenNummer`) REFERENCES `kunde` (`kundenNummer`),
  CONSTRAINT `fk_Bestellungen_Mitarbeiter1` FOREIGN KEY (`Mitarbeiter_mitarbeiterNummer`) REFERENCES `mitarbeiter` (`mitarbeiterNummer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bestellungen`
--

LOCK TABLES `bestellungen` WRITE;
/*!40000 ALTER TABLE `bestellungen` DISABLE KEYS */;
/*!40000 ALTER TABLE `bestellungen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `einzelbestellung`
--

DROP TABLE IF EXISTS `einzelbestellung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `einzelbestellung` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bestellNummer` int NOT NULL,
  `produktID` int NOT NULL,
  `menge` int DEFAULT '0',
  `einzelpreis` int DEFAULT '0',
  `gesamtpreis` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_Einzelbestellung_Bestellungen1_idx` (`bestellNummer`),
  KEY `fk_Einzelbestellung_Produkte1_idx` (`produktID`),
  CONSTRAINT `fk_Einzelbestellung_Bestellungen1` FOREIGN KEY (`bestellNummer`) REFERENCES `bestellungen` (`bestellNummer`),
  CONSTRAINT `fk_Einzelbestellung_Produkte1` FOREIGN KEY (`produktID`) REFERENCES `produkte` (`produktID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `einzelbestellung`
--

LOCK TABLES `einzelbestellung` WRITE;
/*!40000 ALTER TABLE `einzelbestellung` DISABLE KEYS */;
/*!40000 ALTER TABLE `einzelbestellung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kunde`
--

DROP TABLE IF EXISTS `kunde`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kunde` (
  `kundenNummer` int NOT NULL AUTO_INCREMENT,
  `geschlecht` enum('M','W','D') DEFAULT NULL,
  `vorname` varchar(45) DEFAULT NULL,
  `nachname` varchar(45) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `postleitzahl` int DEFAULT NULL,
  `wohnort` varchar(45) DEFAULT NULL,
  `telefon_1` varchar(255) DEFAULT NULL,
  `telefon_2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`kundenNummer`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kunde`
--

LOCK TABLES `kunde` WRITE;
/*!40000 ALTER TABLE `kunde` DISABLE KEYS */;
INSERT INTO `kunde` VALUES (1,'M','Franco','Romero','Helmer 20',28359,'Bremen','0421/108778','0176/98655096'),(2,'M','Max','Mustermann','Musterstraße 1',12345,'Musterhausen','0421/123456789','0176/123456789'),(3,'W','Miriam','Musterfrau','Musterstraße 2',12345,'Musterhausen','0421/987654321','0152/987654321'),(4,'M','Antonio','Greco','Helmer 22',28359,'Bremen','0421/108765',''),(5,'M','Franco','Greco','Helmer 202',28359,'Bremen','0421/108777','0176/98655090');
/*!40000 ALTER TABLE `kunde` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mitarbeiter`
--

DROP TABLE IF EXISTS `mitarbeiter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mitarbeiter` (
  `mitarbeiterNummer` int NOT NULL AUTO_INCREMENT,
  `geschlecht` enum('M','W','D') DEFAULT NULL,
  `vorname` varchar(45) DEFAULT NULL,
  `nachname` varchar(45) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `postleitzahl` int DEFAULT NULL,
  `wohnort` varchar(45) DEFAULT NULL,
  `telefon_1` varchar(255) DEFAULT NULL,
  `telefon_2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mitarbeiterNummer`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mitarbeiter`
--

LOCK TABLES `mitarbeiter` WRITE;
/*!40000 ALTER TABLE `mitarbeiter` DISABLE KEYS */;
INSERT INTO `mitarbeiter` VALUES (1,'M','Franco','Romero','Helmer 20',28359,'Bremen','0421/108778','0176/98655096'),(2,'M','Max','Mustermann','Musterstraße 1',12345,'Musterhausen','0421/123456789','0176/123456789'),(3,'W','Miriam','Musterfrau','Musterstraße 2',12345,'Musterhausen','0421/987654321','0152/987654321'),(4,'M','Antonio','Greco','Helmer 22',28359,'Bremen','0421/108765',NULL),(5,'M','Franco','Greco','Helmer 202',28359,'Bremen','0421/108777','0176/98655090');
/*!40000 ALTER TABLE `mitarbeiter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mitarbeitertyp`
--

DROP TABLE IF EXISTS `mitarbeitertyp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mitarbeitertyp` (
  `id` int NOT NULL AUTO_INCREMENT,
  `beruf` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mitarbeitertyp`
--

LOCK TABLES `mitarbeitertyp` WRITE;
/*!40000 ALTER TABLE `mitarbeitertyp` DISABLE KEYS */;
INSERT INTO `mitarbeitertyp` VALUES (1,'Fahrer'),(2,'Koch'),(3,'Kassierer'),(4,'Lagerist'),(5,'Geschäftsführer'),(6,'Teamleiter'),(7,'Staff'),(8,'Senior Staff');
/*!40000 ALTER TABLE `mitarbeitertyp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mitarbeitertypzuweisung`
--

DROP TABLE IF EXISTS `mitarbeitertypzuweisung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mitarbeitertypzuweisung` (
  `mitarbeiterNummer` int NOT NULL,
  `mitarbeiterTyp_id` int NOT NULL,
  PRIMARY KEY (`mitarbeiterNummer`,`mitarbeiterTyp_id`),
  KEY `fk_Mitarbeiter_has_MitarbeiterTyp_MitarbeiterTyp1_idx` (`mitarbeiterTyp_id`),
  KEY `fk_Mitarbeiter_has_MitarbeiterTyp_Mitarbeiter1_idx` (`mitarbeiterNummer`),
  CONSTRAINT `fk_Mitarbeiter_has_MitarbeiterTyp_Mitarbeiter1` FOREIGN KEY (`mitarbeiterNummer`) REFERENCES `mitarbeiter` (`mitarbeiterNummer`),
  CONSTRAINT `fk_Mitarbeiter_has_MitarbeiterTyp_MitarbeiterTyp1` FOREIGN KEY (`mitarbeiterTyp_id`) REFERENCES `mitarbeitertyp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mitarbeitertypzuweisung`
--

LOCK TABLES `mitarbeitertypzuweisung` WRITE;
/*!40000 ALTER TABLE `mitarbeitertypzuweisung` DISABLE KEYS */;
INSERT INTO `mitarbeitertypzuweisung` VALUES (2,1),(5,1),(3,2),(5,2),(4,3),(2,4),(1,5);
/*!40000 ALTER TABLE `mitarbeitertypzuweisung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza`
--

DROP TABLE IF EXISTS `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizza` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Pizza_Groesse` int NOT NULL,
  `produktID` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Pizza_Pizza_Groesse1_idx` (`Pizza_Groesse`),
  KEY `fk_Pizza_Produkte1_idx` (`produktID`),
  CONSTRAINT `fk_Pizza_Pizza_Groesse1` FOREIGN KEY (`Pizza_Groesse`) REFERENCES `pizza_groesse` (`id`),
  CONSTRAINT `fk_Pizza_Produkte1` FOREIGN KEY (`produktID`) REFERENCES `produkte` (`produktID`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` VALUES (45,1,1),(46,2,1),(47,3,1),(48,4,1),(49,5,1),(50,2,2),(51,3,2),(52,4,2),(53,5,2),(54,1,3),(55,2,3),(56,3,3),(57,4,3),(58,5,3),(59,1,4),(60,2,4),(61,3,4),(62,4,4),(63,5,4),(64,1,5),(65,2,5),(66,3,5),(67,4,5),(68,5,5),(69,1,6),(70,2,6),(71,3,6),(72,4,6),(73,5,6),(74,1,7),(75,2,7),(76,3,7),(77,4,7),(78,5,7),(79,1,8),(80,2,8),(81,3,8),(82,4,8),(83,5,8),(84,1,9),(85,2,9),(86,3,9),(87,4,9),(88,5,9),(89,1,2);
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza_groesse`
--

DROP TABLE IF EXISTS `pizza_groesse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizza_groesse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `groesse` int DEFAULT NULL,
  `preis` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza_groesse`
--

LOCK TABLES `pizza_groesse` WRITE;
/*!40000 ALTER TABLE `pizza_groesse` DISABLE KEYS */;
INSERT INTO `pizza_groesse` VALUES (1,20,399),(2,26,499),(3,32,599),(4,40,699),(5,60,799);
/*!40000 ALTER TABLE `pizza_groesse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produktbelaege`
--

DROP TABLE IF EXISTS `produktbelaege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produktbelaege` (
  `produktID` int NOT NULL,
  `belagID` int NOT NULL,
  PRIMARY KEY (`produktID`,`belagID`),
  KEY `fk_Produkte_has_Belaege_Belaege1_idx` (`belagID`),
  KEY `fk_Produkte_has_Belaege_Produkte1_idx` (`produktID`),
  CONSTRAINT `fk_Produkte_has_Belaege_Belaege1` FOREIGN KEY (`belagID`) REFERENCES `belaege` (`belagID`),
  CONSTRAINT `fk_Produkte_has_Belaege_Produkte1` FOREIGN KEY (`produktID`) REFERENCES `produkte` (`produktID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produktbelaege`
--

LOCK TABLES `produktbelaege` WRITE;
/*!40000 ALTER TABLE `produktbelaege` DISABLE KEYS */;
INSERT INTO `produktbelaege` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(6,3),(10,3),(10,4),(11,4),(12,4),(13,4),(14,4),(15,4),(2,5),(13,5),(14,5),(3,6),(13,6),(15,6),(11,7);
/*!40000 ALTER TABLE `produktbelaege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produkte`
--

DROP TABLE IF EXISTS `produkte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produkte` (
  `produktID` int NOT NULL AUTO_INCREMENT,
  `produktName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`produktID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produkte`
--

LOCK TABLES `produkte` WRITE;
/*!40000 ALTER TABLE `produkte` DISABLE KEYS */;
INSERT INTO `produkte` VALUES (1,'Pizza Margherita'),(2,'Pizza Salami'),(3,'Pizza Schinken'),(4,'Pizza Tonno'),(5,'Pizza Funghi'),(6,'Pizza Quattro Formaggi'),(7,'Pizza Mamamia'),(8,'Pizza Boston'),(9,'Pizza Marinara'),(10,'Baguette Tomate-Mozzarella'),(11,'Baguette Vegetariana'),(12,'Baguette Chicken Terriyaki'),(13,'Baguette Italian BMT'),(14,'Baguette Salami'),(15,'Baguette Schinken');
/*!40000 ALTER TABLE `produkte` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-20 12:31:13
