CREATE DATABASE  IF NOT EXISTS `pizzadienst` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pizzadienst`;
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
  `preis` int DEFAULT NULL,
  `Baguette_Groesse` int NOT NULL,
  `produktID` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Baguette_Baguette_Groesse1_idx` (`Baguette_Groesse`),
  KEY `fk_Baguette_Produkte1_idx` (`produktID`),
  CONSTRAINT `fk_Baguette_Baguette_Groesse1` FOREIGN KEY (`Baguette_Groesse`) REFERENCES `baguette_groesse` (`id`),
  CONSTRAINT `fk_Baguette_Produkte1` FOREIGN KEY (`produktID`) REFERENCES `produkte` (`produktID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baguette`
--

LOCK TABLES `baguette` WRITE;
/*!40000 ALTER TABLE `baguette` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baguette_groesse`
--

LOCK TABLES `baguette_groesse` WRITE;
/*!40000 ALTER TABLE `baguette_groesse` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belaege`
--

LOCK TABLES `belaege` WRITE;
/*!40000 ALTER TABLE `belaege` DISABLE KEYS */;
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
  `menge` int DEFAULT NULL,
  `gesamtpreis` int DEFAULT NULL,
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
  `telefon_1` int DEFAULT NULL,
  `telefon_2` int DEFAULT NULL,
  PRIMARY KEY (`kundenNummer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kunde`
--

LOCK TABLES `kunde` WRITE;
/*!40000 ALTER TABLE `kunde` DISABLE KEYS */;
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
  `telefon_1` int DEFAULT NULL,
  `telefon_2` int DEFAULT NULL,
  PRIMARY KEY (`mitarbeiterNummer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mitarbeiter`
--

LOCK TABLES `mitarbeiter` WRITE;
/*!40000 ALTER TABLE `mitarbeiter` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mitarbeitertyp`
--

LOCK TABLES `mitarbeitertyp` WRITE;
/*!40000 ALTER TABLE `mitarbeitertyp` DISABLE KEYS */;
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
  `preis` int DEFAULT NULL,
  `Pizza_Groesse` int NOT NULL,
  `produktID` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Pizza_Pizza_Groesse1_idx` (`Pizza_Groesse`),
  KEY `fk_Pizza_Produkte1_idx` (`produktID`),
  CONSTRAINT `fk_Pizza_Pizza_Groesse1` FOREIGN KEY (`Pizza_Groesse`) REFERENCES `pizza_groesse` (`id`),
  CONSTRAINT `fk_Pizza_Produkte1` FOREIGN KEY (`produktID`) REFERENCES `produkte` (`produktID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza_groesse`
--

LOCK TABLES `pizza_groesse` WRITE;
/*!40000 ALTER TABLE `pizza_groesse` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produkte`
--

LOCK TABLES `produkte` WRITE;
/*!40000 ALTER TABLE `produkte` DISABLE KEYS */;
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

-- Dump completed on 2020-05-19 11:10:48
