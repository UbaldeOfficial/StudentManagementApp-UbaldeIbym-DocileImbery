-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2025 at 11:52 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `students_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `student_id` varchar(20) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `course` varchar(50) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `enrollment_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `student_id`, `full_name`, `email`, `phone`, `course`, `semester`, `enrollment_date`) VALUES
(2, 'ST004', 'Jane Smith', 'jane@email.com', '0987654321', 'Information Technology', 2, '2024-02-20'),
(5, 'RP2405770', 'Ubalde Official', 'uba@gmail.com', '1076844507', 'Software Engineering', 1, '2025-12-11'),
(6, 'RP250768', 'Samuel HABINEZA', 'sam@gmail.com', '07892738987', 'Cybersecurity', 1, '2025-12-12'),
(7, 'RP07837378', 'GPT', 'gpt@gmai.com', '01783636537392', 'Cybersecurity', 1, '2025-12-11'),
(12, 'RP07282738', 'Kel I h', 'ia@gmail.com', '07829363827', 'Computer Science', 1, '2025-12-12'),
(13, 'RP24RP05770', 'Ublade IBYIMANIKORA', 'ibyimanikora@gmail.com', '0781955047', 'Information Technology', 1, '2024-12-11'),
(15, 'RP078193736', 'Docile IMBEREYIMASO', 'docile@gmail.com', '078263483738', 'Software Engineering', 1, '2024-12-11');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `student_id` (`student_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
