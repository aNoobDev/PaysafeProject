DROP SCHEMA IF EXISTS `customer_database`;

CREATE SCHEMA `customer_database`;

use `customer_database`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `customer_detail`;

CREATE TABLE `customer_detail` (
  `id` varchar(100) Default NULL,
  `paymentToken` varchar(128) Default NULL,
  `firstName` varchar(128) Default NULL,
  `emailId` varchar(128) Default NULL,
  `phone` varchar(128) Default NULL,
  `merchantCustId` varchar(45) DEFAULT NULL
  
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

