-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2018 at 11:49 PM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movie_maniacs`
--

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `Movie_ID` int(11) NOT NULL,
  `Movie_Name` varchar(50) NOT NULL,
  `Movie_Description` varchar(500) NOT NULL,
  `Rating` varchar(5) NOT NULL,
  `Duration` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`Movie_ID`, `Movie_Name`, `Movie_Description`, `Rating`, `Duration`) VALUES
(1, 'Fantastic Beasts: The Crimes of Grindelwald', 'In an effort to thwart Grindelwald\'s plans of raising pure-blood wizards to rule over all non-magical beings, Albus Dumbledore enlists his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided world.', 'PG-13', '02:14:00'),
(2, 'Interstellar', 'In Earth\'s future, a global crop blight and second Dust Bowl are slowly rendering the planet uninhabitable. Professor Brand (Michael Caine), a brilliant NASA physicist, is working on plans to save mankind by transporting Earth\'s population to a new home via a wormhole. But first, Brand must send former NASA pilot Cooper (Matthew McConaughey) and a team of researchers through the wormhole and across the galaxy to find out which of three planets could be mankind\'s new home.', 'PG-13', '02:49:00'),
(3, 'A Star Is Born', 'Seasoned musician Jackson Maine discovers -- and falls in love with -- struggling artist Ally. She has just about given up on her dream to make it big as a singer until Jackson coaxes her into the spotlight. But even as Ally\'s career takes off, the personal side of their relationship is breaking down, as Jackson fights an ongoing battle with his own internal demons.', 'R', '02:14:00'),
(4, 'Spider-Man', '\"Spider-Man\" centers on student Peter Parker (Tobey Maguire) who, after being bitten by a genetically-altered spider, gains superhuman strength and the spider-like ability to cling to any surface. He vows to use his abilities to fight crime, coming to understand the words of his beloved Uncle Ben: \"With great power comes great responsibility.\"', 'PG-13', '02:01:00'),
(5, 'Incredibles 2 ', 'Everyone’s favorite family of superheroes is back in “Incredibles 2” – but this time Helen (voice of Holly Hunter) is in the spotlight, leaving Bob (voice of Craig T. Nelson) at home with Violet (voice of Sarah Vowell) and Dash (voice of Huck Milner) to navigate the day-to-day heroics of “normal” life. It’s a tough transistion for everyone, made tougher by the fact that the family is still unaware of baby Jack-Jack’s emerging superpowers. When a new villain hatches a brilliant and dangerous plot', 'PG', '02:05:00');

-- --------------------------------------------------------

--
-- Table structure for table `showtime`
--

CREATE TABLE `showtime` (
  `Show_ID` int(11) NOT NULL,
  `Movie_ID` int(11) NOT NULL,
  `Showtime` varchar(50) NOT NULL,
  `Room_Number` int(11) NOT NULL,
  `Available_Seats` int(11) NOT NULL,
  `Seats` varchar(56) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `showtime`
--

INSERT INTO `showtime` (`Show_ID`, `Movie_ID`, `Showtime`, `Room_Number`, `Available_Seats`, `Seats`) VALUES
(1, 1, '2:00 PM', 1, 46, '00100000000000000010100001000000000000000000000000'),
(2, 3, '2:30 PM', 2, 50, '00000000000000000000000000000000000000000000000000'),
(3, 5, '5:00 PM', 1, 50, '00000000000000000000000000000000000000000000000000'),
(4, 4, '3:30 PM', 3, 49, '00000000000000000000000000000000000000000000000001'),
(5, 2, '6:00 PM', 2, 49, '00000000000000001000000000000000000000000000000000'),
(6, 1, '3:30 PM', 2, 50, '00000000000000000000000000000000000000000000000000'),
(7, 4, '8:00 PM', 4, 50, '00000000000000000000000000000000000000000000000000'),
(8, 5, '5:00 PM', 4, 50, '00000000000000000000000000000000000000000000000000'),
(9, 2, '4:30 PM', 5, 49, '00000000000000010000000000000000000000000000000000'),
(10, 3, '1:30 PM', 2, 49, '00000000000000000000000000000001000000000000000000'),
(11, 1, '10:00 PM', 6, 50, '00000000000000000000000000000000000000000000000000');

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `Ticket_ID` int(11) NOT NULL,
  `Show_ID` int(11) NOT NULL,
  `Seat` varchar(3) NOT NULL,
  `Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`Ticket_ID`, `Show_ID`, `Seat`, `Name`) VALUES
(6, 1, 'C6', 'test'),
(7, 1, 'A3', 'Person'),
(8, 1, 'B9', 'Test person'),
(9, 1, 'C1', 'Michael Narine'),
(10, 9, 'B6', 'Jane Doe'),
(11, 4, 'E10', 'John Doe'),
(12, 10, 'D2', 'Another Person'),
(13, 5, 'B7', 'per son');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`Movie_ID`);

--
-- Indexes for table `showtime`
--
ALTER TABLE `showtime`
  ADD PRIMARY KEY (`Show_ID`),
  ADD KEY `Movie_ID` (`Movie_ID`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`Ticket_ID`),
  ADD KEY `Show_ID` (`Show_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `Movie_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `showtime`
--
ALTER TABLE `showtime`
  MODIFY `Show_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `Ticket_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `showtime`
--
ALTER TABLE `showtime`
  ADD CONSTRAINT `showtime_ibfk_1` FOREIGN KEY (`Movie_ID`) REFERENCES `movies` (`Movie_ID`);

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`Show_ID`) REFERENCES `showtime` (`Show_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
