-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 07, 2017 at 08:08 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blackjek_account`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `uname` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(20) NOT NULL,
  `access_token` varchar(50) DEFAULT '',
  `expiry_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='utf8_general_ci';

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `uname`, `email`, `password`, `access_token`, `expiry_time`) VALUES
(1, 'emon', 'dika.kusuma23@gmail.com', 'passdika', NULL, NULL),
(2, 'jek', 'blackjek@gmail.com', 'passjek', NULL, NULL),
(3, 'guy', 'guy@new.com', 'newguy', NULL, NULL),
(4, 'emo', 'dika@gm.com', 'haha', NULL, NULL),
(5, 'aa', 'aa@aa.com', 'aa', NULL, NULL),
(6, 'diakgant', 'dikea@asd.sad', 'dika', NULL, NULL),
(7, 'diakgante', 'dikena@asd.sad', 'ee', NULL, NULL),
(8, 'diakganten', 'diksena@asd.sad', 'nn', NULL, NULL),
(9, 'diakgantena', 'diksesna@asd.sad', 'asd', NULL, NULL),
(10, 'diakgantenas', 'diksessna@asd.sad', 'asd', NULL, NULL),
(11, 'alskda', 'dika.kusuma23@gmail.comx', 'asd', NULL, NULL),
(12, 'siganteng', 'siganteng@ganteng.bgt', 'ganteng', NULL, NULL),
(13, 'kdk', 'kdk@kdk.ss', '11', 'wx9V:QM7KEp7xV1Ludk2-7x', '2017-11-06 21:47:09'),
(14, 'debug', 'debug@deb.dd', 'debug', 'CLW1cpe3EUYAds1LA:dV3sM', '2017-11-06 22:13:12'),
(15, 'asadasd', 'asdasd@asdas.asda', 'asd', NULL, NULL),
(16, 'asd', 'asd@asdasd.asdas', '1', NULL, NULL),
(17, 'SOAP', 'aaaaa@asd.co', '123123', '672013:c1jiuuvQ0Ar30Xxr', '2017-11-07 13:36:58'),
(18, 'SOAP1', 'SOAP1@gmal.coc', '123123', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `uname` (`uname`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
