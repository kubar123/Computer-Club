-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 14, 2015 at 12:54 PM
-- Server version: 5.5.34
-- PHP Version: 5.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `computerclub`
--

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE `device` (
  `deviceID` int(11) NOT NULL AUTO_INCREMENT,
  `memberID` int(11) NOT NULL,
  `cpu` varchar(30) COLLATE utf8_bin NOT NULL,
  `dedicatedGPU` tinyint(1) NOT NULL,
  `GPU` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`deviceID`),
  UNIQUE KEY `deviceID` (`deviceID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=45 ;

--
-- Dumping data for table `device`
--

INSERT INTO `device` (`deviceID`, `memberID`, `cpu`, `dedicatedGPU`, `GPU`) VALUES
(28, 1, 'hdgrgrrwerggwer ', 0, NULL),
(43, 12, 'core i7 @2.5ghz', 1, 'GTX 780ti SC'),
(35, 1, 'hdgrgrrwerggwer ', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `memberID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) COLLATE utf8_bin NOT NULL,
  `lastName` varchar(20) COLLATE utf8_bin NOT NULL,
  `isMale` tinyint(1) NOT NULL,
  `age` int(11) NOT NULL,
  `streetNo` varchar(20) COLLATE utf8_bin NOT NULL,
  `streetName` varchar(20) COLLATE utf8_bin NOT NULL,
  `suburb` varchar(20) COLLATE utf8_bin NOT NULL,
  `state` varchar(10) COLLATE utf8_bin NOT NULL,
  `fee` decimal(10,0) NOT NULL,
  `isGamer` tinyint(1) NOT NULL,
  `favouriteGame` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `favouriteGenre` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `favouriteSoftware` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`memberID`),
  UNIQUE KEY `memberID` (`memberID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=23 ;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`memberID`, `firstName`, `lastName`, `isMale`, `age`, `streetNo`, `streetName`, `suburb`, `state`, `fee`, `isGamer`, `favouriteGame`, `favouriteGenre`, `favouriteSoftware`) VALUES
(1, 'huihuhu', ';uoh;ouhj', 1, 12, '122', 'fdsasfdsfd', 'adsdasads', 'VIC', '122', 1, 'fdsfd', 'sdfsdffsd', NULL),
(11, 'fgdgdfdgf', 'dgfdgfdgfdgf', 1, 22, 'fsd', 'sdf', 'sdf', 'NSW', '140', 1, 'sdf', 'sdf', 'NULL'),
(12, 'gdfscas', 'jnhbgfds', 1, 33, 'hgfd', 'gfds', 'hgfds', 'VIC', '140', 1, 'gfds', 'gfsa', 'NULL'),
(15, 'dsffds', 'fdsfds', 1, 22, 'dfsdfs', 'fsdsdf', 'sdfsdf', 'NSW', '140', 1, 'sdf', 'sdf', 'NULL'),
(17, 'dfssdfdf', 'dsfsdf', 1, 33, 'sdffsd', 'sdfsdf', 'dfssfd', 'QLD', '350', 0, '', '', 'dfsfsd'),
(18, 'dfssfdsfd', 'sdfsdfsdf', 0, 22, 'fsdsfdsdf', 'sdfsdfsfd', 'sdffds', 'QLD', '350', 0, '', '', 'sdfdfssdf'),
(21, 'Wu', 'ss', 1, 150, '444', 'hhtht', 'hdfdfh', 'VIC', '140', 1, '', '', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
