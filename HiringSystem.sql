/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 5.7.39-log : Database - hiring_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hiring_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hiring_system`;

/*Table structure for table `candidate_table` */

DROP TABLE IF EXISTS `candidate_table`;

CREATE TABLE `candidate_table` (
  `candidate_id` int(11) NOT NULL AUTO_INCREMENT,
  `applied_date` datetime DEFAULT NULL,
  `candidate_name` varchar(255) DEFAULT NULL,
  `candidate_resume` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `level1date` datetime DEFAULT NULL,
  `level2date` datetime DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `selection_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `human_resource_employee_id` int(11) DEFAULT NULL,
  `requisition_name_job_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`candidate_id`),
  KEY `FKe8060luv6pj4lbu2fc03p1ark` (`human_resource_employee_id`),
  KEY `FK434oh64lygt1gmcc6rednocv7` (`requisition_name_job_id`),
  CONSTRAINT `FK434oh64lygt1gmcc6rednocv7` FOREIGN KEY (`requisition_name_job_id`) REFERENCES `requisition_table` (`job_id`),
  CONSTRAINT `FKe8060luv6pj4lbu2fc03p1ark` FOREIGN KEY (`human_resource_employee_id`) REFERENCES `employee_table` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `comments_table` */

DROP TABLE IF EXISTS `comments_table`;

CREATE TABLE `comments_table` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `level_name` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `candidate_candidate_id` int(11) DEFAULT NULL,
  `employee_employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK6xni2r0pjn22w7b84w6dsx4o0` (`candidate_candidate_id`),
  KEY `FKjk6ncqfx0yv1ncdy1iqjfxcpm` (`employee_employee_id`),
  CONSTRAINT `FK6xni2r0pjn22w7b84w6dsx4o0` FOREIGN KEY (`candidate_candidate_id`) REFERENCES `candidate_table` (`candidate_id`),
  CONSTRAINT `FKjk6ncqfx0yv1ncdy1iqjfxcpm` FOREIGN KEY (`employee_employee_id`) REFERENCES `employee_table` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Table structure for table `employee_role` */

DROP TABLE IF EXISTS `employee_role`;

CREATE TABLE `employee_role` (
  `employee_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`employee_id`,`role_id`),
  KEY `FK87elfmyk8k7hab73ua1f8oxna` (`role_id`),
  CONSTRAINT `FK87elfmyk8k7hab73ua1f8oxna` FOREIGN KEY (`role_id`) REFERENCES `role_table` (`role_id`),
  CONSTRAINT `FKmngt360803jmqoendxt4fcgfy` FOREIGN KEY (`employee_id`) REFERENCES `employee_table` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `employee_table` */

DROP TABLE IF EXISTS `employee_table`;

CREATE TABLE `employee_table` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `employee_password` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `FKiwqptlhw43mu4vhgqgp176a1x` (`manager_id`),
  CONSTRAINT `FKiwqptlhw43mu4vhgqgp176a1x` FOREIGN KEY (`manager_id`) REFERENCES `employee_table` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `interview_table` */

DROP TABLE IF EXISTS `interview_table`;

CREATE TABLE `interview_table` (
  `interview_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`interview_id`,`employee_id`),
  KEY `FKn9bp55pi6ak41hunew2ls0v6x` (`employee_id`),
  CONSTRAINT `FK7en1hy7wcs8jlewx74mhg227v` FOREIGN KEY (`interview_id`) REFERENCES `schedule_interview_table` (`interview_id`),
  CONSTRAINT `FKn9bp55pi6ak41hunew2ls0v6x` FOREIGN KEY (`employee_id`) REFERENCES `employee_table` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `requisition_table` */

DROP TABLE IF EXISTS `requisition_table`;

CREATE TABLE `requisition_table` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `additional_condition` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `job_description` varchar(255) DEFAULT NULL,
  `job_profile` varchar(255) DEFAULT NULL,
  `no_panels` int(11) NOT NULL,
  `partner_name` varchar(255) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `timeslot` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `manager_employee_id` int(11) DEFAULT NULL,
  `specific_panel_employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`job_id`),
  KEY `FKgfpu9n1njitn7w1rmxg3da2hs` (`manager_employee_id`),
  KEY `FKadyhi1dj8fb04eglg5fksybi6` (`specific_panel_employee_id`),
  CONSTRAINT `FKadyhi1dj8fb04eglg5fksybi6` FOREIGN KEY (`specific_panel_employee_id`) REFERENCES `employee_table` (`employee_id`),
  CONSTRAINT `FKgfpu9n1njitn7w1rmxg3da2hs` FOREIGN KEY (`manager_employee_id`) REFERENCES `employee_table` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `role_table` */

DROP TABLE IF EXISTS `role_table`;

CREATE TABLE `role_table` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `schedule_interview_table` */

DROP TABLE IF EXISTS `schedule_interview_table`;

CREATE TABLE `schedule_interview_table` (
  `interview_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `interview_time` datetime DEFAULT NULL,
  `level_of_interview` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `candidate_candidate_id` int(11) DEFAULT NULL,
  `human_resource_employee_id` int(11) DEFAULT NULL,
  `manager_employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`interview_id`),
  KEY `FKl7fl88us27xjsqy74wxtmm6d6` (`candidate_candidate_id`),
  KEY `FKkjedh8f3hxyiok0t9ensgnysb` (`human_resource_employee_id`),
  KEY `FK31tdge6kcfnt59q57etsstlrf` (`manager_employee_id`),
  CONSTRAINT `FK31tdge6kcfnt59q57etsstlrf` FOREIGN KEY (`manager_employee_id`) REFERENCES `employee_table` (`employee_id`),
  CONSTRAINT `FKkjedh8f3hxyiok0t9ensgnysb` FOREIGN KEY (`human_resource_employee_id`) REFERENCES `employee_table` (`employee_id`),
  CONSTRAINT `FKl7fl88us27xjsqy74wxtmm6d6` FOREIGN KEY (`candidate_candidate_id`) REFERENCES `candidate_table` (`candidate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
