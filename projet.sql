-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 13, 2023 at 01:58 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projet`
--

-- --------------------------------------------------------

--
-- Table structure for table `regmovie`
--

CREATE TABLE `regmovie` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `billcast` text DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `releasedate` date DEFAULT NULL,
  `duration` time DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `rating` varchar(10) DEFAULT NULL,
  `distributor` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `synopsis` text DEFAULT NULL,
  `userid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `regmovie`
--

INSERT INTO `regmovie` (`id`, `title`, `billcast`, `director`, `releasedate`, `duration`, `genre`, `rating`, `distributor`, `website`, `synopsis`, `userid`) VALUES
(7, 'Film 1', '44test', '4', '2023-10-10', NULL, NULL, NULL, NULL, 'google', NULL, NULL),
(8, 'Film 2', 'Acteur3, Acteur4', 'Réalisateur2', '2023-02-15', '00:00:02', 'Comédie', '4.5', 'Distributeur2', '', 'Description du film 2', NULL),
(9, 'Film 3', 'Acteur5, Acteur6', 'Réalisateur3', '2023-03-20', '00:00:02', 'Drame', '4.8', 'Distributeur3', 'site', 'Description du film 3', NULL),
(10, 'Film 4', 'dgdg', 'gdfgd', '2002-10-20', '10:10:45', 'ggg', '4.1', '', '', 'ggg', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `reserv`
--

CREATE TABLE `reserv` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `name` varchar(255) NOT NULL,
  `regmovie_id` int(11) NOT NULL,
  `secmovie_id` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `reserv`
--

INSERT INTO `reserv` (`id`, `date`, `name`, `regmovie_id`, `secmovie_id`, `userid`) VALUES
(4, '2023-02-23', 'cc', 2, 2, NULL),
(6, '2023-05-12', 'Client4', 4, 4, NULL),
(7, '2023-11-17', 'n', 1, 4, NULL),
(45, '2002-10-10', 'jj', 5, 4, NULL),
(47, '2023-11-20', '02', 1, 1, NULL),
(49, '2023-12-13', 'haron', 7, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `secmovie`
--

CREATE TABLE `secmovie` (
  `id` int(11) NOT NULL,
  `MovieId` int(11) DEFAULT NULL,
  `SecDate` date DEFAULT NULL,
  `SecTime` time DEFAULT NULL,
  `MovieTitle` varchar(40) NOT NULL,
  `userid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `secmovie`
--

INSERT INTO `secmovie` (`id`, `MovieId`, `SecDate`, `SecTime`, `MovieTitle`, `userid`) VALUES
(11, 9, '2023-01-15', '15:30:00', 'Film 1', NULL),
(12, 9, '2023-02-28', '18:00:00', 'Film 2', NULL),
(13, 9, '2023-04-01', '12:12:00', 'Film 3', NULL),
(14, 9, '2023-05-12', '12:12:00', 'Film 4', NULL),
(18, 9, '2002-02-02', '10:10:00', 'Film 1', NULL),
(19, 9, '2000-12-12', '10:14:00', 'Film 3', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'root', 'root');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `regmovie`
--
ALTER TABLE `regmovie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userid` (`userid`);

--
-- Indexes for table `reserv`
--
ALTER TABLE `reserv`
  ADD PRIMARY KEY (`id`),
  ADD KEY `regmovie_id` (`regmovie_id`),
  ADD KEY `secmovie_id` (`secmovie_id`),
  ADD KEY `fk_reserv_userid` (`userid`);

--
-- Indexes for table `secmovie`
--
ALTER TABLE `secmovie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `MovieId` (`MovieId`),
  ADD KEY `fk_secmovie_userid` (`userid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `regmovie`
--
ALTER TABLE `regmovie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `reserv`
--
ALTER TABLE `reserv`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `secmovie`
--
ALTER TABLE `secmovie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `regmovie`
--
ALTER TABLE `regmovie`
  ADD CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`);

--
-- Constraints for table `reserv`
--
ALTER TABLE `reserv`
  ADD CONSTRAINT `fk_reserv_userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`);

--
-- Constraints for table `secmovie`
--
ALTER TABLE `secmovie`
  ADD CONSTRAINT `fk_secmovie_userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `secmovie_ibfk_1` FOREIGN KEY (`MovieId`) REFERENCES `regmovie` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
