-- MySQL dump 10.13  Distrib 5.5.25a, for Win64 (x86)
--
-- Host: localhost    Database: scheduelpdb
-- ------------------------------------------------------
-- Server version	5.5.25a

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_code` varchar(45) NOT NULL,
  `course_name` varchar(100) NOT NULL,
  `course_description` varchar(500) DEFAULT NULL,
  `units` int(11) NOT NULL,
  PRIMARY KEY (`course_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('COEN 210','Computer Architecture','CPU design. Datapath. Control unit. Instruction cycle. Finite state machine and microprogramming',4),('COEN 218','Input-Output Structures','I/O architecture overview. I/O programming: dedicated versus memory-mapped I/O addresses',2),('COEN 225','Secure Coding in C and C++','Writing secure code in C, C++',2),('COEN 233','Computer Networks','Fundamentals of computer networks',4),('COEN 235','Client/Server Programming','Client/server paradigm in the context of the Web and the Internet',4),('COEN 250','Information Security Management','Techniques and technologies of information and data security',2),('COEN 259','Compilers','Principles and practice of the design and implementation of a compiler',4),('COEN 275','Object-Oriented Analysis Design, and Programming','Important aspects of object-oriented application development are covered',4),('COEN 276','Web Programming I','XHTML, CSS, JavaScript, AJAX, XML, and XSLT to build software tools for distributed Web applications',4),('COEN 279','Design and Analysis of Algorithms','Techniques of design and analysis of algorithms',4),('COEN 280','Database Systems','Data models. Relational databases. Database design ',4),('COEN 283','Operating Systems','Fundamentals of operating systems',4),('COEN 285','Software Engineering','Systematic approaches to software design, project management, implementation, documentation, and maintenance.',4),('COEN 286','Software Quality Assurance and Testing','Software quality control and process analysis',2),('COEN 287','Software Development Process Management','Management of the software development process at both the project and organization levels',2),('COEN 289','Energy Efficient Computing','Energy-efficient software practices',2),('COEN 313','Advanced Computer Architecture','Advanced system architectures',2),('COEN 317','Distributed Systems','Fundamental algorithms for distributed system architectures',4),('COEN 359','Design Patterns','Software design patterns and their application in developing reusable software components.',4);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_prerequisite`
--

DROP TABLE IF EXISTS `course_prerequisite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_prerequisite` (
  `prerequisite_id` int(11) NOT NULL AUTO_INCREMENT,
  `course` varchar(45) DEFAULT NULL,
  `prerequisite` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`prerequisite_id`),
  KEY `course_prerequisite_course_fk1` (`course`),
  KEY `course_prerequisite_prereq_fk2` (`prerequisite`),
  CONSTRAINT `course_prerequisite_course_fk1` FOREIGN KEY (`course`) REFERENCES `course` (`course_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `course_prerequisite_prereq_fk2` FOREIGN KEY (`prerequisite`) REFERENCES `course` (`course_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_prerequisite`
--

LOCK TABLES `course_prerequisite` WRITE;
/*!40000 ALTER TABLE `course_prerequisite` DISABLE KEYS */;
INSERT INTO `course_prerequisite` VALUES (1,'COEN 218','COEN 210'),(2,'COEN 259','COEN 283'),(3,'COEN 259','COEN 210'),(4,'COEN 280','COEN 283'),(5,'COEN 286','COEN 285'),(6,'COEN 287','COEN 285'),(7,'COEN 289','COEN 233'),(8,'COEN 289','COEN 283'),(9,'COEN 313','COEN 210'),(10,'COEN 317','COEN 233'),(11,'COEN 317','COEN 283'),(12,'COEN 359','COEN 275');
/*!40000 ALTER TABLE `course_prerequisite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_review`
--

DROP TABLE IF EXISTS `course_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_code` varchar(45) NOT NULL,
  `student_id` varchar(45) NOT NULL,
  `rating` int(11) NOT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `review_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `course_review_student_id_fk2` (`student_id`),
  KEY `course_review_course_code_fk1` (`course_code`),
  CONSTRAINT `course_review_course_code_fk1` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `course_review_student_id_fk2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_review`
--

LOCK TABLES `course_review` WRITE;
/*!40000 ALTER TABLE `course_review` DISABLE KEYS */;
INSERT INTO `course_review` VALUES (1,'COEN 210','W9999999',4,'Very interesting class. I initially wanted to waive this course, but I am glad I took it.','2012-10-28 19:04:12'),(2,'COEN 233','W9999999',5,'Great course. Fundamental course that every computer science student must take!','2012-10-28 19:44:02'),(3,'COEN 210','W1111111',5,'Good course. Exams are tricky. But if you understand the concepts well, you will certainly get an A.','2012-10-27 17:45:12'),(4,'COEN 233','W1111111',4,'I thoroughly enjoyed every bit of this course! The Professor is a really busy person, but takes time to answer questions through emails. ','2012-10-29 11:15:49'),(5,'COEN 210','W5555555',4,'I am writing this review on behalf on a friend. Initially, she wanted to waive this course. She was very impressed with the knowledge of the Professor and she is happy she took this course.','2012-11-06 15:52:39'),(6,'COEN 210','W5555555',3,'This class rocks! I was able to use what I learnt and apply it directly to my job','2012-11-24 18:31:47'),(7,'COEN 233','W5555555',4,'I will have to agree with other students, very enjoyable course indeed!','2012-11-27 15:28:03');
/*!40000 ALTER TABLE `course_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_schedule`
--

DROP TABLE IF EXISTS `course_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_schedule` (
  `course_code` varchar(45) NOT NULL,
  `quarter` varchar(45) NOT NULL,
  `course_days` varchar(45) NOT NULL,
  `course_start_time` time DEFAULT NULL,
  `course_end_time` time DEFAULT NULL,
  PRIMARY KEY (`course_code`,`quarter`),
  KEY `course_schedule_course_code_fk` (`course_code`),
  CONSTRAINT `course_schedule_course_code_fk` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_schedule`
--

LOCK TABLES `course_schedule` WRITE;
/*!40000 ALTER TABLE `course_schedule` DISABLE KEYS */;
INSERT INTO `course_schedule` VALUES ('COEN 210','Fall','TR','17:10:00','19:00:00'),('COEN 210','Spring','MW','07:10:00','09:00:00'),('COEN 218','Spring','Sa','08:10:00','10:00:00'),('COEN 225','Winter','M','17:10:00','19:00:00'),('COEN 233','Fall','MW','19:10:00','21:00:00'),('COEN 233','Spring','TR','17:10:00','19:00:00'),('COEN 233','Winter','MW','19:10:00','21:00:00'),('COEN 235','Fall','MW','17:10:00','19:00:00'),('COEN 250','Fall','T','17:10:00','19:00:00'),('COEN 250','Winter','MW','17:10:00','19:00:00'),('COEN 259','Winter','MW','19:10:00','21:00:00'),('COEN 275','Summer','TR','17:10:00','19:00:00'),('COEN 275','Winter','TR','17:10:00','19:00:00'),('COEN 276','Fall','MW','19:10:00','21:00:00'),('COEN 279','Fall','MW','17:10:00','19:00:00'),('COEN 279','Spring','MW','17:10:00','19:00:00'),('COEN 279','Winter','TR','17:10:00','19:00:00'),('COEN 280','Winter','TR','07:10:00','09:00:00'),('COEN 283','Fall','TR','19:10:00','21:00:00'),('COEN 283','Spring','TR','19:10:00','21:00:00'),('COEN 283','Winter','WF','07:10:00','09:00:00'),('COEN 285','Fall','TR','19:10:00','21:00:00'),('COEN 286','Spring','W','07:10:00','09:00:00'),('COEN 286','Winter','T','07:10:00','09:00:00'),('COEN 287','Winter','M','17:10:00','19:00:00'),('COEN 289','Fall','W','17:10:00','19:00:00'),('COEN 289','Spring','W','17:10:00','19:00:00'),('COEN 313','Spring','W','07:10:00','09:00:00'),('COEN 317','Fall','MW','17:10:00','19:00:00'),('COEN 359','Summer','TR','19:10:00','21:00:00');
/*!40000 ALTER TABLE `course_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_spec_reqt`
--

DROP TABLE IF EXISTS `course_spec_reqt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_spec_reqt` (
  `course_reqt_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_code` varchar(45) NOT NULL,
  `degree` varchar(45) DEFAULT NULL,
  `special_requirement` int(11) NOT NULL,
  PRIMARY KEY (`course_reqt_id`),
  KEY `course_spec_reqt_special_reqmt` (`special_requirement`),
  KEY `course_spec_reqt_course_code` (`course_code`),
  KEY `course_spec_reqt_degree` (`degree`),
  CONSTRAINT `course_spec_reqt_course_code` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `course_spec_reqt_degree` FOREIGN KEY (`degree`) REFERENCES `degree_program` (`degree_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `course_spec_reqt_special_reqmt` FOREIGN KEY (`special_requirement`) REFERENCES `special_requirement` (`requirement_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_spec_reqt`
--

LOCK TABLES `course_spec_reqt` WRITE;
/*!40000 ALTER TABLE `course_spec_reqt` DISABLE KEYS */;
INSERT INTO `course_spec_reqt` VALUES (1,'COEN 210','MSCE',3),(3,'COEN 279','MSCE',3),(4,'COEN 283','MSCE',3),(5,'COEN 250',NULL,7),(9,'COEN 275','MSSE',4),(10,'COEN 285','MSSE',4),(11,'COEN 286','MSSE',4),(12,'COEN 287',NULL,7),(13,'COEN 289',NULL,7);
/*!40000 ALTER TABLE `course_spec_reqt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `degree_program`
--

DROP TABLE IF EXISTS `degree_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `degree_program` (
  `degree_code` varchar(45) NOT NULL,
  `degree_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`degree_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `degree_program`
--

LOCK TABLES `degree_program` WRITE;
/*!40000 ALTER TABLE `degree_program` DISABLE KEYS */;
INSERT INTO `degree_program` VALUES ('MSCE','M.S. Computer Science & Engineering'),('MSEM','M.S. Engineering Management'),('MSSE','M.S. Software Engineering');
/*!40000 ALTER TABLE `degree_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program_of_studies`
--

DROP TABLE IF EXISTS `program_of_studies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_of_studies` (
  `student_id` varchar(45) NOT NULL,
  `course_code` varchar(45) NOT NULL,
  PRIMARY KEY (`student_id`,`course_code`),
  KEY `program_of_study_course_code_fk1` (`course_code`),
  KEY `program_of_study_student_id_fk2` (`student_id`),
  CONSTRAINT `program_of_study_course_code_fk1` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `program_of_study_student_id_fk2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program_of_studies`
--

LOCK TABLES `program_of_studies` WRITE;
/*!40000 ALTER TABLE `program_of_studies` DISABLE KEYS */;
INSERT INTO `program_of_studies` VALUES ('W9999999','COEN 233'),('W1111111','COEN 250'),('W9999999','COEN 250'),('W9999999','COEN 275'),('W9999999','COEN 283'),('W5555555','COEN 286');
/*!40000 ALTER TABLE `program_of_studies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `special_requirement`
--

DROP TABLE IF EXISTS `special_requirement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `special_requirement` (
  `requirement_id` int(11) NOT NULL AUTO_INCREMENT,
  `requirement_desc` varchar(200) DEFAULT NULL,
  `units` int(11) DEFAULT NULL,
  PRIMARY KEY (`requirement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `special_requirement`
--

LOCK TABLES `special_requirement` WRITE;
/*!40000 ALTER TABLE `special_requirement` DISABLE KEYS */;
INSERT INTO `special_requirement` VALUES (1,'Approved Transfer Credit',9),(2,'Foundation',NULL),(3,'MSCE Core',NULL),(4,'MSSE Core',NULL),(5,'Engineering Management Stem',20),(6,'Technical Stem',19),(7,'SCU Graduate Core',6),(8,'Capstone Project',NULL);
/*!40000 ALTER TABLE `special_requirement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` varchar(45) NOT NULL,
  `student_pwd` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) NOT NULL,
  `phone_number` varchar(12) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `degree` varchar(45) NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `student_degree_fk` (`degree`),
  CONSTRAINT `student_degree_fk` FOREIGN KEY (`degree`) REFERENCES `degree_program` (`degree_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('W1111111','test1','Sheila',NULL,'Castillo','','scastillo@scu.edu','MSSE'),('W5555555','test2','Shradha',NULL,'Cripe',NULL,'scripe@scu.edu','MSEM'),('W9999999','test3','Poornima',NULL,'Ganesan','444-444-444','pganesan@scu.edu','MSCE');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-28 11:51:34
