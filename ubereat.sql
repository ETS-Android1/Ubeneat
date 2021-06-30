-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- 主機： localhost
-- 產生時間： 
-- 伺服器版本： 8.0.17
-- PHP 版本： 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `ubereat`
--

-- --------------------------------------------------------

--
-- 資料表結構 `customer`
--

CREATE TABLE `customer` (
  `customer_number` int(11) NOT NULL,
  `name` text NOT NULL,
  `phone` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `customer_address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `meal`
--

CREATE TABLE `meal` (
  `meal_N` int(11) NOT NULL,
  `store_N` int(11) NOT NULL,
  `meal_name` text NOT NULL,
  `meal_price` int(11) NOT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `meal`
--

INSERT INTO `meal` (`meal_N`, `store_N`, `meal_name`, `meal_price`, `image`) VALUES
(1, 1, 'Sashimi', 200, 'https://i.imgur.com/UDixmME.jpg'),
(2, 1, 'Salmon Donburi', 150, 'https://i.imgur.com/eK4yHCG.jpg'),
(7, 2, 'Xiao Long Bao', 160, 'https://i.imgur.com/Hk1ucEx.jpg'),
(9, 2, 'Fried rice', 100, 'https://i.imgur.com/qpwTwqJ.jpg'),
(10, 4, 'Buble milk tea', 50, 'https://i.imgur.com/qCtHKa2.jpg'),
(14, 3, 'Bibimbap', 160, 'https://i.imgur.com/xiBBK6Z.jpg'),
(15, 3, 'Korean chicken rice', 140, 'https://i.imgur.com/4DTiF8b.jpg'),
(16, 3, 'pickle', 50, 'https://i.imgur.com/bfHS1a9.jpg');

-- --------------------------------------------------------

--
-- 資料表結構 `orderlist`
--

CREATE TABLE `orderlist` (
  `order_num` int(11) NOT NULL,
  `customer_num` int(11) NOT NULL,
  `store_num` int(11) NOT NULL,
  `meal1` int(11) NOT NULL,
  `meal2` int(11) NOT NULL,
  `meal3` int(11) NOT NULL,
  `note` text NOT NULL,
  `total_price` int(11) NOT NULL,
  `state` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `store`
--

CREATE TABLE `store` (
  `store_number` int(11) NOT NULL,
  `store_name` text NOT NULL,
  `store_address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `store`
--

INSERT INTO `store` (`store_number`, `store_name`, `store_address`, `type`) VALUES
(1, 'Oishi', '公館路54號', 'Japanese'),
(2, 'Mom dish', '復興北路159號', 'Chinese'),
(3, 'Aniasayo', '林森北路46號', 'Korean'),
(4, 'Fifty Blue', '中華路245號', 'Drink');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_number`);

--
-- 資料表索引 `meal`
--
ALTER TABLE `meal`
  ADD PRIMARY KEY (`meal_N`);

--
-- 資料表索引 `orderlist`
--
ALTER TABLE `orderlist`
  ADD PRIMARY KEY (`order_num`);

--
-- 資料表索引 `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`store_number`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_number` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `meal`
--
ALTER TABLE `meal`
  MODIFY `meal_N` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `orderlist`
--
ALTER TABLE `orderlist`
  MODIFY `order_num` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
