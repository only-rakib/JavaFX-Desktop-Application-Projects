-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2018 at 10:33 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `admissiontestsysytem`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_info`
--

CREATE TABLE `admin_info` (
  `uname` varchar(500) NOT NULL,
  `password` varchar(500) NOT NULL,
  `email` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_info`
--

INSERT INTO `admin_info` (`uname`, `password`, `email`) VALUES
('cse', '1234', 'www@dd.co');

-- --------------------------------------------------------

--
-- Table structure for table `studreg_info`
--

CREATE TABLE `studreg_info` (
  `merit_pos` int(11) DEFAULT NULL,
  `Roll` varchar(500) NOT NULL,
  `Unit` text NOT NULL,
  `Name` varchar(500) DEFAULT NULL,
  `C_O` varchar(500) DEFAULT NULL,
  `hsc_roll` varchar(500) DEFAULT NULL,
  `hsc_reg` varchar(500) DEFAULT NULL,
  `hsc_pass_year` varchar(500) DEFAULT NULL,
  `hsc_gpa` varchar(500) DEFAULT NULL,
  `ssc_roll` varchar(500) DEFAULT NULL,
  `ssc_reg` varchar(500) DEFAULT NULL,
  `ssc_pass_year` varchar(500) DEFAULT NULL,
  `ssc_gpa` varchar(500) DEFAULT NULL,
  `division` varchar(500) DEFAULT NULL,
  `district` varchar(500) DEFAULT NULL,
  `DOB` varchar(500) DEFAULT NULL,
  `pic` varchar(500) DEFAULT NULL,
  `sig` varchar(500) DEFAULT NULL,
  `total_marks` varchar(100) DEFAULT NULL,
  `sub1` varchar(100) DEFAULT NULL,
  `sub2` varchar(100) DEFAULT NULL,
  `sub3` varchar(100) DEFAULT NULL,
  `sub4` varchar(100) DEFAULT NULL,
  `sub5` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studreg_info`
--

INSERT INTO `studreg_info` (`merit_pos`, `Roll`, `Unit`, `Name`, `C_O`, `hsc_roll`, `hsc_reg`, `hsc_pass_year`, `hsc_gpa`, `ssc_roll`, `ssc_reg`, `ssc_pass_year`, `ssc_gpa`, `division`, `district`, `DOB`, `pic`, `sig`, `total_marks`, `sub1`, `sub2`, `sub3`, `sub4`, `sub5`) VALUES
(5, '62', 'A', 'Rakib', 'Hasan', '123', '234', '2018', '5.00,', '1234', '234', '2016', '5.00', 'Dhaka', 'Gazipur', '2018-04-23', 'photos62.jpg', 'Signeture62.jpg', '60.0', 'ICT', 'CSE', 'Textile', 'null', 'null'),
(4, '63', 'A', 'Fahim', 'Shahriar', '12', '13', '2018', '5.00,', '12', '13', '2016', '5.00', 'Dhaka', 'Gazipur', '2018-04-23', 'photos63.jpg', 'Signeture63.jpg', '63.0', 'CSE', 'ICT', 'Textile', 'null', 'null'),
(6, '64', 'A', 'asasd', 'as', '12', '12', '2018', '5.00,', '12', '13', '2016', '5.00', 'Chattagram', 'Comilla', '2018-04-23', 'photos64.jpg', 'Signeture64.jpg', '-5.0', NULL, NULL, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `studreg_info`
--
ALTER TABLE `studreg_info`
  ADD PRIMARY KEY (`Roll`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
