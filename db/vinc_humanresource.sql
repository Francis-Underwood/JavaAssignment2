CREATE DATABASE `vinc_humanresource`;
USE `vinc_humanresource`;

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

INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Vic','Stone','Others');
INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Oliver','Queen','Salesperson');
INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Hal','Jordan','Salesperson');
INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Jay','Garrick','Salesperson');
INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Ray','Palmer','Others');
INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) VALUES ('Barry','Allen','Salesperson');

INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Merlyn', 'Cash' FROM `employee` WHERE `FirstName` = 'Oliver';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Count Vertigo', 'Credit card' FROM `employee` WHERE `FirstName` = 'Oliver';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Brick', 'Credit card' FROM `employee` WHERE `FirstName` = 'Oliver';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Constantine', 'Cash' FROM `employee` WHERE `FirstName` = 'Oliver';

INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Sky Pirate', 'Cash' FROM `employee` WHERE `FirstName` = 'Hal';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Knodar', 'Credit card' FROM `employee` WHERE `FirstName` = 'Hal';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Vandal Savage', 'Credit card' FROM `employee` WHERE `FirstName` = 'Hal';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Sportsmaster', 'Cash' FROM `employee` WHERE `FirstName` = 'Hal';

INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Peek-a-Boo', 'Cash' FROM `employee` WHERE `FirstName` = 'Jay';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Magenta', 'Credit card' FROM `employee` WHERE `FirstName` = 'Jay';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Savitar', 'Credit card' FROM `employee` WHERE `FirstName` = 'Jay';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Cicada', 'Cash' FROM `employee` WHERE `FirstName` = 'Jay';

INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Spectro', 'Cash' FROM `employee` WHERE `FirstName` = 'Barry';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Imperiex', 'Credit card' FROM `employee` WHERE `FirstName` = 'Barry';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Marvin Noronsa', 'Credit card' FROM `employee` WHERE `FirstName` = 'Barry';
INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) SELECT `Id`, 'Nekron', 'Cash' FROM `employee` WHERE `FirstName` = 'Barry';


