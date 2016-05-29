CREATE DATABASE `vinc_humanresource_utest`;
USE `vinc_humanresource_utest`;

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(25) NOT NULL,
  `LastName` varchar(25) NOT NULL,
  `Position` ENUM('Salesperson', 'Others') NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `EmployeeId` int(11) unsigned NOT NULL,
  `Name` varchar(25) NOT NULL,
  `PaymentMethod` ENUM('Cash', 'Credit card') NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Brooklyn','Lee','Salesperson');
INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Flower','Tucci','Others');
INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Leilani','Leeane','Salesperson');
INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Lizz','Tayler','Salesperson');
INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Justine','Joli','Others');
INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Bibi','Jones','Salesperson');

INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Brazzers', 'Cash' FROM `employee` WHERE `FirstName` = 'Brooklyn';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Diabolic', 'Credit card' FROM `employee` WHERE `FirstName` = 'Brooklyn';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Diva Futura', 'Credit card' FROM `employee` WHERE `FirstName` = 'Brooklyn';

INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Harmoney Film Limited', 'Cash' FROM `employee` WHERE `FirstName` = 'Leilani';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'JM Productions', 'Credit card' FROM `employee` WHERE `FirstName` = 'Leilani';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'John Thompson Productions', 'Credit card' FROM `employee` WHERE `FirstName` = 'Leilani';

INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Ninn Worx', 'Cash' FROM `employee` WHERE `FirstName` = 'Lizz';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Vouyer Media', 'Credit card' FROM `employee` WHERE `FirstName` = 'Lizz';

INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'VCA Pictures', 'Cash' FROM `employee` WHERE `FirstName` = 'Bibi';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Reality Kings', 'Credit card' FROM `employee` WHERE `FirstName` = 'Bibi';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Vivid Entertainment', 'Credit card' FROM `employee` WHERE `FirstName` = 'Bibi';


