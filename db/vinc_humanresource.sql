CREATE DATABASE `vinc_humanresource`;
USE `vinc_humanresource`;

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(25) NOT NULL,
  `LastName` varchar(25) NOT NULL,
  `Position` ENUM('Salesperson', 'Other stuff') NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) NOT NULL,
  `PaymentMethod` ENUM('Cash', 'Credit card') NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
