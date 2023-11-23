-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 188.121.44.179:3306
-- Generation Time: Mar 27, 2020 at 08:33 PM
-- Server version: 5.5.51-38.1-log
-- PHP Version: 7.1.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `LAO`
--

-- --------------------------------------------------------

--
-- Table structure for table `android_metadata`
--

CREATE TABLE `android_metadata` (
  `locale` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `android_metadata`
--

INSERT INTO `android_metadata` (`locale`) VALUES
('en_US');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryid` int(11) NOT NULL,
  `catname` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryid`, `catname`) VALUES
(3, 'Limited Time Offer'),
(4, 'Beverages'),
(5, 'Meal'),
(6, 'Sandwich'),
(7, 'Condiments'),
(8, 'Dessert'),
(9, 'Sides');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `_id` tinyint(4) DEFAULT NULL,
  `name` varchar(26) DEFAULT NULL,
  `price` varchar(3) DEFAULT NULL,
  `photo` varchar(13) DEFAULT NULL,
  `restID` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`_id`, `name`, `price`, `photo`, `restID`) VALUES
(1, 'Chicken Burger', '16$', 'mac_burger_1', 0),
(2, 'Diet Chicken Burger', '22$', 'mac_burger_2', 0),
(3, 'Double Burger with Cheese', '25$', 'mac_burger_3', 0),
(4, 'Burger with Cheese', '20$', 'mac_burger_4', 0),
(5, 'Chicken Burger', '15$', 'herfy_menu_1', 1),
(6, 'Kids meal', '10$', 'herfy_menu_2', 1),
(7, 'Chicken Burger with Cheese', '25$', 'herfy_menu_3', 1),
(8, 'Fish', '20$', 'herfy_menu_4', 1),
(9, 'Big Chicken With Cheese', '30$', 'herfy_menu_5', 1),
(10, 'Tortilla Chicken', '20$', 'herfy_menu_6', 1),
(11, 'Salade', '13$', 'herfy_menu_7', 1),
(12, 'Marguerita', '15$', 'marguerita01', 3),
(13, 'tropical hawaiian', '15$', 'pwahawaian', 3),
(14, 'Pepperoni', '15$', 'pepperoni01', 3),
(15, 'Cheesy Pops', '20$', 'cheesy_zft', 3),
(16, 'Chicken Bites', '25$', 'cheesy_pops01', 3),
(17, 'Pepsi', '1$', 'pep', 3),
(18, '7Up', '1$', 'up', 3),
(19, 'Water', '1$', 'water', 3);

-- --------------------------------------------------------

--
-- Table structure for table `name`
--

CREATE TABLE `name` (
  `_id` varchar(0) DEFAULT NULL,
  `item_name` varchar(0) DEFAULT NULL,
  `item_price` varchar(0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productid` int(11) NOT NULL,
  `categoryid` int(1) NOT NULL,
  `productname` varchar(30) NOT NULL,
  `price` double NOT NULL,
  `photo` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productid`, `categoryid`, `productname`, `price`, `photo`) VALUES
(14, 5, 'BOX MASTER ORIGINAL', 10, 'upload/box-master-mob_1583299967.png'),
(15, 3, 'Zinger + Twister offer', 25, 'upload/twister-zingeroffer-mob_1583300154.png'),
(19, 4, 'Pepsi', 4, 'upload/drinks-mob_1583303337.png'),
(45, 8, 'Chocolate Cake', 15, 'upload/chocolate-cake-mob_1583299952.png');

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `purchaseid` int(11) NOT NULL,
  `customer` varchar(50) NOT NULL,
  `total` double NOT NULL,
  `date_purchase` datetime NOT NULL,
  `Phone_No` varchar(10) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  `Address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`purchaseid`, `customer`, `total`, `date_purchase`, `Phone_No`, `Address`) VALUES
(1, 'Fahad', 20, '2020-03-04 00:15:37', '0544558828', 'riyadh'),
(8, 'sara', 600, '2017-12-06 15:29:00', '0544558828', 'riyadh'),
(10, 'raneem1', 0, '2020-03-01 11:34:53', '', ''),
(11, 'amjad', 620000, '2020-03-01 11:35:44', '', ''),
(33, 'noura', 600, '2020-03-04 00:03:20', '0544558828', 'riyadh'),
(34, 'maha', 1820, '2020-03-04 00:09:16', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `purchase_detail`
--

CREATE TABLE `purchase_detail` (
  `pdid` int(11) NOT NULL,
  `purchaseid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `AppName` varchar(50) NOT NULL,
  `StoreName` varchar(50) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Payment_info` varchar(50) NOT NULL,
  `Payment_method` varchar(50) NOT NULL,
  `VAT` varchar(50) NOT NULL,
  `Delivery_cost` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase_detail`
--

INSERT INTO `purchase_detail` (`pdid`, `purchaseid`, `productid`, `quantity`, `AppName`, `StoreName`, `Address`, `Payment_info`, `Payment_method`, `VAT`, `Delivery_cost`) VALUES
(13, 8, 15, 2, 'Jahez', 'KFC Alwaha', 'riyadh', 'Card', 'Visa', '5', '14'),
(14, 8, 17, 2, '', '', '', '', '', '', ''),
(15, 8, 18, 2, '', '', '', '', '', '', ''),
(16, 9, 15, 3, '', '', '', '', '', '', ''),
(17, 10, 15, 0, '', '', '', '', '', '', ''),
(18, 10, 21, 0, '', '', '', '', '', '', ''),
(19, 11, 15, 20, '', '', '', '', '', '', ''),
(20, 34, 15, 2, '', '', '', '', '', '', ''),
(21, 34, 22, 2, '', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `restaurants`
--

CREATE TABLE `restaurants` (
  `_id` tinyint(4) DEFAULT NULL,
  `name` varchar(11) DEFAULT NULL,
  `destination` varchar(16) DEFAULT NULL,
  `logo` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `restaurants`
--

INSERT INTO `restaurants` (`_id`, `name`, `destination`, `logo`) VALUES
(1, 'McDonald\'s', 'Al-malqa branch', 'mcdonalds_icon'),
(2, 'Herfy', 'Al-narjis branch', 'herfy_icon'),
(3, 'Kfc', 'Al-yasmin branch', 'kfc_iconic'),
(4, 'Pizza hut', 'Al-yasmin branch', 'hut_iconic'),
(5, 'Burger King', 'Al-yasmin branch', 'king_logo'),
(6, 'Kudu', 'Al-yasmin branch', 'kudu_logo');

-- --------------------------------------------------------

--
-- Table structure for table `restaurants_items`
--

CREATE TABLE `restaurants_items` (
  `_id` tinyint(4) DEFAULT NULL,
  `restID` tinyint(4) DEFAULT NULL,
  `itemID` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `restaurants_items`
--

INSERT INTO `restaurants_items` (`_id`, `restID`, `itemID`) VALUES
(1, 0, 1),
(2, 0, 2),
(3, 0, 3),
(4, 0, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 7),
(8, 1, 8),
(9, 1, 9),
(10, 1, 10),
(11, 1, 11),
(12, 3, 12),
(13, 3, 13),
(14, 3, 14),
(15, 3, 15),
(16, 3, 16),
(17, 3, 17),
(18, 3, 18),
(19, 3, 19);

-- --------------------------------------------------------

--
-- Table structure for table `sqlite_sequence`
--

CREATE TABLE `sqlite_sequence` (
  `name` varchar(17) DEFAULT NULL,
  `seq` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sqlite_sequence`
--

INSERT INTO `sqlite_sequence` (`name`, `seq`) VALUES
('users', 5),
('restaurants', 6),
('items', 19),
('restaurants_items', 19);

-- --------------------------------------------------------

--
-- Table structure for table `uesers`
--

CREATE TABLE `uesers` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `_id` tinyint(4) DEFAULT NULL,
  `username` varchar(6) DEFAULT NULL,
  `password` mediumint(9) DEFAULT NULL,
  `email` varchar(16) DEFAULT NULL,
  `phone_number` int(11) DEFAULT NULL,
  `region` varchar(6) DEFAULT NULL,
  `street` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`_id`, `username`, `password`, `email`, `phone_number`, `region`, `street`) VALUES
(1, 'raghad', 321, 'raghad@gmail.com', 123456, 'narjis', 22),
(2, 'raneem', 123, 'raneem@gmail.com', 54321, 'yasmin', 15),
(3, 'anfal', 1234, 'anfal@gmail.com', 1234567, 'narjis', 20),
(4, 'amjad', 54321, 'amjad@gmail.com', 654321, 'yasmin', 26),
(5, 'medad', 4321, 'medad@gmail.com', 7654321, 'narjis', 24);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryid`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productid`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`purchaseid`);

--
-- Indexes for table `purchase_detail`
--
ALTER TABLE `purchase_detail`
  ADD PRIMARY KEY (`pdid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `categoryid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `purchase`
--
ALTER TABLE `purchase`
  MODIFY `purchaseid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `purchase_detail`
--
ALTER TABLE `purchase_detail`
  MODIFY `pdid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
