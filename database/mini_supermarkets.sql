-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2023 at 03:59 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mini_supermarkets`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `staff_id` bigint(20) DEFAULT NULL,
  `last_signed_in` datetime(6) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `role_id`, `staff_id`, `last_signed_in`, `deleted`) VALUES
(1, 'admin', 'Admin123', 1, 1, '2023-10-23 07:49:42.983793', b'0'),
(2, 'longbott', 'Long123', 2, 4, '2023-11-08 22:52:03.000000', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`id`, `name`, `supplier_id`, `deleted`) VALUES
(1, 'Vfresh', 1, b'0'),
(2, 'Sure Prevent Gold', 1, b'0'),
(3, 'Vinamilk', 1, b'0'),
(4, 'TH true Milk', 2, b'0'),
(5, 'TH true Yogurt', 2, b'0'),
(6, 'Coca Cola', 3, b'0'),
(7, 'Fanta', 3, b'0'),
(8, 'Aquarius', 3, b'0'),
(9, 'Samurai', 3, b'0'),
(10, 'Nutriboost', 3, b'0'),
(11, 'Fuzetea', 3, b'0'),
(12, 'Modern', 4, b'0'),
(13, 'Hảo Hảo', 4, b'0'),
(14, 'Miến Phú Hương', 4, b'0'),
(15, 'Udon Sưki Sưki', 4, b'0'),
(16, 'Đệ nhất', 4, b'0'),
(17, 'TH true Juice Milk', 2, b'0'),
(18, 'xyz', 1, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `quantity`, `deleted`) VALUES
(1, 'Sữa nước', 16, b'0'),
(2, 'Mì ăn liền', 2, b'0'),
(3, 'Nước ngọt', 8, b'0'),
(4, 'Sữa chua', 6, b'0'),
(5, 'Sữa bột', 2, b'0'),
(6, 'Nước trái cây, nước trà', 6, b'0'),
(7, 'Miến ăn liền', 4, b'0'),
(8, 'xyz', 0, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `membership` bit(1) DEFAULT NULL,
  `signed_up_date` date DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `gender`, `birthdate`, `phone`, `membership`, `signed_up_date`, `point`, `deleted`) VALUES
(1, 'VÃNG LAI', b'0', '1000-01-01', '', b'0', '1000-01-01', 0, b'0'),
(2, 'NGUYỄN VĂN NAM', b'1', '2000-12-01', '0963333946', b'1', '2020-09-08', 1000, b'0'),
(3, 'HOÀNG XUÂN BẮC', b'1', '2001-09-03', '0967563268', b'1', '2021-02-07', 123, b'0'),
(4, 'NGUYỄN THỊ THU HIỀN', b'0', '2004-05-04', '0981485618', b'1', '2021-05-06', 500, b'0'),
(5, 'NGUYỄN VĂN THẮNG', b'1', '1999-08-10', '0861149539', b'1', '2021-08-03', 2300, b'0'),
(6, 'NGUYỄN THỊ YẾN NHI', b'0', '1000-01-01', '', b'0', '1000-01-01', 0, b'0'),
(7, 'ĐẶNG NGUYỄN GIA HUY', b'1', '1000-01-01', '', b'0', '1000-01-01', 0, b'0'),
(8, 'NGUYỄN THI DIỆU CHI', b'0', '2000-04-09', '0378367833', b'1', '2022-05-05', 450, b'0'),
(9, 'NGUYỄN THỊ THANH NHÀN', b'0', '2001-08-03', '0323373316', b'1', '2022-09-08', 3000, b'0'),
(10, 'NGUYỄN TRUNG TÍN', b'1', '1000-01-01', '', b'0', '1000-01-01', 0, b'0'),
(11, 'ĐINH XUÂN HOÀI', b'1', '2004-07-06', '0964745278', b'1', '2023-03-07', 200, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `decentralization`
--

CREATE TABLE `decentralization` (
  `role_id` bigint(20) NOT NULL,
  `module_id` bigint(20) NOT NULL,
  `function_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `decentralization`
--

INSERT INTO `decentralization` (`role_id`, `module_id`, `function_id`) VALUES
(1, 1, 1),
(1, 2, 1),
(1, 3, 1),
(1, 4, 1),
(1, 4, 2),
(1, 4, 3),
(1, 4, 4),
(1, 5, 1),
(1, 5, 2),
(1, 5, 3),
(1, 5, 4),
(1, 6, 1),
(1, 6, 2),
(1, 7, 1),
(1, 7, 2),
(1, 8, 1),
(1, 8, 2),
(1, 9, 1),
(1, 9, 2),
(1, 9, 3),
(1, 9, 4),
(1, 10, 1),
(1, 10, 2),
(1, 10, 3),
(1, 10, 4),
(1, 11, 1),
(1, 11, 2),
(1, 11, 3),
(1, 11, 4),
(1, 12, 1),
(1, 12, 2),
(1, 12, 3),
(1, 12, 4),
(1, 13, 1),
(1, 13, 2),
(1, 13, 3),
(1, 13, 4),
(1, 14, 1),
(1, 14, 2),
(1, 14, 3),
(1, 14, 4);

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `id` bigint(20) NOT NULL,
  `percent` double DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `discount`
--

INSERT INTO `discount` (`id`, `percent`, `start_date`, `end_date`, `status`) VALUES
(1, 10, '2023-09-09', '2023-09-16', b'1'),
(2, 15, '2023-09-20', '2023-09-26', b'1'),
(3, 30, '2023-11-16', '2023-11-18', b'1'),
(4, 15, '2023-11-15', '2023-11-30', b'1'),
(5, 50, '2023-11-22', '2023-11-30', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `discount_detail`
--

CREATE TABLE `discount_detail` (
  `discount_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `status` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `discount_detail`
--

INSERT INTO `discount_detail` (`discount_id`, `product_id`, `status`) VALUES
(1, 1, b'0'),
(1, 2, b'0'),
(3, 1, b'0'),
(3, 2, b'0'),
(3, 3, b'0'),
(3, 9, b'0'),
(3, 11, b'0'),
(3, 12, b'0'),
(4, 5, b'0'),
(4, 6, b'0'),
(5, 5, b'0'),
(5, 6, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `export`
--

CREATE TABLE `export` (
  `id` bigint(20) NOT NULL,
  `staff_id` bigint(20) DEFAULT NULL,
  `invoice_date` date DEFAULT NULL,
  `total` bigint(20) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `export`
--

INSERT INTO `export` (`id`, `staff_id`, `invoice_date`, `total`, `reason`, `deleted`) VALUES
(1, 1, '2023-01-01', 150, 'Bán', b'0'),
(2, 2, '2023-01-02', 200, 'Huỷ', b'0'),
(3, 3, '2023-01-03', 300, 'Bán', b'0'),
(4, 4, '2023-01-04', 180, 'Huỷ', b'0'),
(5, 5, '2023-01-05', 250, 'Bán', b'0'),
(6, 6, '2023-01-06', 120, 'Huỷ', b'0'),
(7, 7, '2023-01-07', 350, 'Bán', b'0'),
(8, 8, '2023-01-08', 170, 'Huỷ', b'0'),
(9, 9, '2023-01-09', 280, 'Bán', b'0'),
(10, 10, '2023-01-10', 200, 'Huỷ', b'0'),
(11, 1, '2023-11-27', 3, 'Bán', b'0'),
(12, 1, '2023-11-27', 300000, 'Bán', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `export_detail`
--

CREATE TABLE `export_detail` (
  `export_id` bigint(20) NOT NULL,
  `shipment_id` bigint(20) NOT NULL,
  `quantity` double DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `export_detail`
--

INSERT INTO `export_detail` (`export_id`, `shipment_id`, `quantity`, `total`) VALUES
(1, 1, 2, 1690000),
(2, 2, 3, 615000),
(3, 2, 3, 660000),
(4, 1, 2, 400000),
(5, 2, 3, 900000),
(11, 14, 1, 3),
(12, 1, 10, 100000),
(12, 2, 20, 200000);

-- --------------------------------------------------------

--
-- Table structure for table `function`
--

CREATE TABLE `function` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `function`
--

INSERT INTO `function` (`id`, `name`, `deleted`) VALUES
(1, 'Xem', b'0'),
(2, 'Thêm', b'0'),
(3, 'Sửa', b'0'),
(4, 'Xóa', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `import`
--

CREATE TABLE `import` (
  `id` bigint(20) NOT NULL,
  `staff_id` bigint(20) DEFAULT NULL,
  `received_date` date DEFAULT NULL,
  `total` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `import`
--

INSERT INTO `import` (`id`, `staff_id`, `received_date`, `total`, `supplier_id`) VALUES
(4, 2, '2023-11-19', 200000, 1),
(5, 3, '2023-11-17', 200000, 2),
(6, 3, '2023-11-14', 200000, 1),
(7, 1, '2023-11-12', 200000, 3),
(8, 2, '2023-11-10', 200000, 1),
(9, 1, '2023-11-08', 200000, 2),
(10, 3, '2023-11-06', 200000, 1),
(11, 2, '2023-11-04', 200000, 3),
(12, 1, '2023-11-02', 200000, 2),
(13, 3, '2023-10-31', 200000, 1),
(14, 1, '2023-10-29', 200000, 3),
(15, 2, '2023-10-27', 200000, 1),
(16, 1, '2023-10-25', 100000, 2),
(17, 3, '2023-10-23', 100000, 3),
(18, 2, '2023-10-21', 100000, 1),
(19, 1, '2023-10-19', 100000, 2),
(20, 3, '2023-10-17', 100000, 3),
(21, 2, '2023-10-15', 100000, 1),
(22, 1, '2023-10-13', 100000, 2),
(23, 3, '2023-10-11', 100000, 3),
(24, 2, '2023-10-09', 200000, 1),
(25, 1, '2023-10-07', 200000, 2),
(26, 3, '2023-10-05', 200000, 3),
(27, 2, '2023-10-03', 200000, 1),
(28, 1, '2023-10-01', 200000, 2),
(29, 3, '2023-09-29', 200000, 3),
(30, 2, '2023-09-27', 200000, 1),
(31, 1, '2023-09-25', 200000, 2),
(32, 3, '2023-09-23', 200000, 3),
(33, 2, '2023-09-21', 200000, 1),
(34, 1, '2023-11-27', 14, 3),
(35, 1, '2023-11-27', 14, 3);

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`id`, `name`, `deleted`) VALUES
(1, 'Quản lý bán hàng', b'0'),
(2, 'Quản lý kho', b'0'),
(3, 'Quản lý thống kê', b'0'),
(4, 'Quản lý giảm giá', b'0'),
(5, 'Quản lý khuyến mãi', b'0'),
(6, 'Quản lý hóa đơn', b'0'),
(7, 'Quản lý phiếu xuất', b'0'),
(8, 'Quản lý phiếu nhập', b'0'),
(9, 'Quản lý sản phẩm', b'0'),
(10, 'Quản lý nhà cung cấp', b'0'),
(11, 'Quản lý khách hàng', b'0'),
(12, 'Quản lý nhân viên', b'0'),
(13, 'Quản lý tài khoản', b'0'),
(14, 'Quản lý phân quyền', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `cost` bigint(20) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `barcode` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `brand_id`, `category_id`, `unit`, `cost`, `quantity`, `image`, `barcode`, `deleted`) VALUES
(1, 'Lốc 4 hộp sữa tươi 110ml Vinamilk có đường', 3, 1, 'Lốc', 21500, 10, 'img/Pro1.svg', '', b'0'),
(2, 'Lốc 4 hộp sữa tươi 110ml Vinamilk ít đường', 3, 1, 'Lốc', 21500, 20, 'img/Pro2.svg', '', b'0'),
(3, 'Lốc 4 hộp sữa tươi 110ml Vinamilk không đường', 3, 1, 'Lốc', 21500, 0, 'img/Pro3.svg', '', b'0'),
(4, 'Lốc 4 hộp sữa tươi 180ml Vinamilk có đường', 3, 1, 'Lốc', 29900, 0, 'img/Pro4.svg', '', b'0'),
(5, 'Lốc 4 hộp sữa tươi 180ml Vinamilk ít đường', 3, 1, 'Lốc', 29900, 4, 'img/Pro5.svg', '', b'0'),
(6, 'Lốc 4 hộp sữa tươi 180ml Vinamilk không đường', 3, 1, 'Lốc', 29900, 4, 'img/Pro6.svg', '', b'0'),
(7, 'Sữa bột Vinamilk Sure Prevent Gold lon 900g', 2, 5, 'Lon', 625000, 0, 'img/Pro7.svg', '', b'0'),
(8, '6 chai sữa pha sẵn Sure Prevent Gold 200ml', 2, 5, 'Lốc', 168000, 0, 'img/Pro8.svg', '', b'0'),
(9, 'Nước ép cam Vfresh 1 lít', 1, 6, 'Hộp', 50000, 4, 'img/Pro9.svg', '', b'0'),
(10, 'Nước ép cam không đường Vfresh 1 lít', 1, 6, 'Hộp', 50000, 4, 'img/Pro10.svg', '', b'0'),
(11, 'Nước ép đào Vfresh 1 lít', 1, 6, 'Hộp', 42000, 0, 'img/Pro11.svg', '', b'0'),
(12, 'Nước ép đào không đường Vfresh 1 lít', 1, 6, 'Hộp', 42000, 0, 'img/Pro12.svg', '', b'0'),
(13, 'Trà Atiso Vfresh 1 lít', 1, 6, 'Hộp', 20000, 0, 'img/Pro1.svg', '', b'0'),
(14, 'Trà Atiso ít đường Vfresh 1 lít', 1, 6, 'Hộp', 20000, 0, 'img/Pro1.svg', '', b'0'),
(15, 'Lốc 4 hộp sữa tươi 110ml TH true Milk có đường ', 4, 1, 'Lốc', 24000, 0, 'img/Pro1.svg', '', b'0'),
(16, 'Lốc 4 hộp sữa tươi 110ml TH true Milk ít đường ', 4, 1, 'Lốc', 24000, 0, 'img/Pro1.svg', '', b'0'),
(17, 'Lốc 4 hộp sữa tươi 110ml TH true Milk không đường ', 4, 1, 'Lốc', 24000, 0, 'img/Pro1.svg', '', b'0'),
(18, 'Lốc 4 hộp sữa tươi 180ml TH true Milk có đường ', 4, 1, 'Lốc', 36000, 0, 'img/Pro1.svg', '', b'0'),
(19, 'Lốc 4 hộp sữa tươi 180ml TH true Milk ít đường ', 4, 1, 'Lốc', 36000, 0, 'img/Pro1.svg', '', b'0'),
(20, 'Lốc 4 hộp sữa tươi 180ml TH true Milk không đường ', 4, 1, 'Lốc', 36000, 0, 'img/Pro1.svg', '', b'0'),
(21, 'Lốc 4 hộp sữa tươi 180ml TH true Milk dâu', 4, 1, 'Lốc', 36000, 0, 'img/Pro1.svg', '', b'0'),
(22, 'Lốc 4 hộp sữa tươi 180ml TH true Milk socola', 4, 1, 'Lốc', 36000, 0, 'img/Pro1.svg', '', b'0'),
(23, 'Sữa tươi TH true Milk có đường hộp 1 lít', 4, 1, 'Hộp', 37000, 0, 'img/Pro1.svg', '', b'0'),
(24, 'Sữa tười TH true Milk ít đường hộp 1 lít ', 4, 1, 'Hộp', 37000, 0, 'img/Pro1.svg', '', b'0'),
(25, 'Lốc 4 hộp sữa chua 180ml TH true Yogurt việt quất', 5, 4, 'Lốc', 32500, 0, 'img/Pro1.svg', '', b'0'),
(26, 'Lốc 4 hộp sữa chua 180ml TH true Yogurt cam', 5, 4, 'Lốc', 32500, 0, 'img/Pro1.svg', '', b'0'),
(27, 'Lốc 4 hộp sữa chua 180ml TH true Yogurt dâu', 5, 4, 'Lốc', 32500, 0, 'img/Pro1.svg', '', b'0'),
(28, 'Lốc 4 chai sữa chua 180ml TH true Yogourt việt quất', 5, 4, 'Lốc', 31000, 0, 'img/Pro1.svg', '', b'0'),
(29, 'Lốc 4 chai sữa chua 180ml TH true Yogurt cam', 5, 4, 'Lốc', 31000, 0, 'img/Pro1.svg', '', b'0'),
(30, 'Lốc 4 chai sữa chua 180ml TH true Yogurt dâu', 5, 4, 'Lốc', 31000, 0, 'img/Pro1.svg', '', b'0'),
(31, 'Nước ngọt Coca Cola lon 320ml', 6, 3, 'Lon', 10600, 0, 'img/Pro1.svg', '', b'0'),
(32, 'Nước ngọt Coca Cola chai 390ml', 6, 3, 'Chai', 7800, 0, 'img/Pro1.svg', '', b'0'),
(33, 'Nước ngọt Coca Cola chai 1.5 lít', 6, 3, 'Chai', 20200, 0, 'img/Pro1.svg', '', b'0'),
(34, 'Nước ngọt Coca Cola Zero lon 320ml', 6, 3, 'Lon', 10600, 0, 'img/Pro1.svg', '', b'0'),
(35, 'Nước ngọt Coca Cola Zero chai 390ml', 6, 3, 'Chai', 7800, 0, 'img/Pro1.svg', '', b'0'),
(36, 'Nước ngọt Coca Cola Zero chai 1.5 lít', 6, 3, 'Chai', 20200, 0, 'img/Pro1.svg', '', b'0'),
(37, 'Nước ngọt Fanta cam chai 390ml', 7, 3, 'Chai', 7800, 0, 'img/Pro1.svg', '', b'0'),
(38, 'Nước ngọt Fanta xá xị chai 390ml', 7, 3, 'Chai', 7800, 0, 'img/Pro1.svg', '', b'0'),
(39, 'Mì Hảo Hảo tôm chua cay gói 75g', 13, 2, 'Gói', 4400, 0, 'img/Pro1.svg', '', b'0'),
(40, 'Mì Handy Hảo Hảo tôm chua cay ly 67g', 13, 2, 'Ly', 9500, 0, 'img/Pro1.svg', '', b'0'),
(41, 'abc', 9, 7, 'Lốc', 3, 3333, 'Pro7.svg', 'asd', b'1'),
(42, 'asd', 14, 7, 'Lốc', 33, 333, 'img/Pro1.svg', '3333fafs', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `id` bigint(20) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`id`, `start_date`, `end_date`, `status`) VALUES
(1, '2023-09-25', '2023-10-01', b'0'),
(2, '2023-09-27', '2023-10-01', b'1'),
(3, '2023-09-22', '2023-09-29', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `promotion_gift`
--

CREATE TABLE `promotion_gift` (
  `promotion_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promotion_gift`
--

INSERT INTO `promotion_gift` (`promotion_id`, `product_id`, `quantity`) VALUES
(1, 9, 1),
(1, 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `promotion_item`
--

CREATE TABLE `promotion_item` (
  `promotion_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promotion_item`
--

INSERT INTO `promotion_item` (`promotion_id`, `product_id`, `quantity`) VALUES
(1, 5, 2),
(1, 6, 2);

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `id` bigint(20) NOT NULL,
  `staff_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `invoice_date` date DEFAULT NULL,
  `total` bigint(20) DEFAULT NULL,
  `received` bigint(20) DEFAULT NULL,
  `excess` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`id`, `staff_id`, `customer_id`, `invoice_date`, `total`, `received`, `excess`) VALUES
(1, 4, 2, '2023-11-15', 150000, 150000, 0),
(2, 4, 3, '2023-11-16', 200000, 200000, 0),
(3, 4, 4, '2023-11-17', 300000, 300000, 0),
(4, 4, 5, '2023-11-18', 250000, 250000, 0),
(5, 4, 6, '2023-11-19', 180000, 180000, 0),
(6, 4, 7, '2023-11-20', 120000, 120000, 0),
(7, 4, 8, '2023-11-21', 350000, 350000, 0),
(8, 4, 9, '2023-11-22', 400000, 400000, 0),
(9, 4, 10, '2023-11-23', 280000, 280000, 0),
(10, 4, 11, '2023-11-24', 320000, 320000, 0),
(11, 4, 2, '2023-11-15', 180000, 180000, 0),
(12, 4, 3, '2023-11-16', 210000, 210000, 0),
(13, 4, 4, '2023-11-17', 320000, 320000, 0),
(14, 4, 5, '2023-11-18', 280000, 280000, 0),
(31, 4, 2, '2023-09-05', 120000, 120000, 0),
(32, 4, 3, '2023-09-12', 180000, 180000, 0),
(33, 4, 4, '2023-09-20', 250000, 250000, 0),
(34, 4, 5, '2023-09-25', 300000, 300000, 0),
(35, 4, 6, '2023-10-05', 200000, 200000, 0),
(36, 4, 7, '2023-10-10', 150000, 150000, 0),
(37, 4, 8, '2023-10-15', 280000, 280000, 0),
(38, 4, 9, '2023-10-20', 320000, 320000, 0),
(39, 4, 10, '2023-10-25', 180000, 180000, 0),
(40, 4, 11, '2023-10-30', 220000, 220000, 0),
(41, 4, 2, '2023-09-02', 150000, 150000, 0),
(42, 4, 3, '2023-09-08', 220000, 220000, 0),
(43, 4, 4, '2023-09-15', 180000, 180000, 0),
(44, 4, 5, '2023-09-22', 250000, 250000, 0),
(45, 4, 6, '2023-10-01', 100000, 120000, 0),
(46, 4, 7, '2023-10-08', 190000, 190000, 0),
(47, 4, 8, '2023-10-14', 280000, 280000, 0),
(48, 4, 9, '2023-10-21', 320000, 320000, 0),
(49, 4, 10, '2023-10-27', 180000, 180000, 0),
(50, 4, 11, '2023-11-03', 220000, 220000, 0),
(51, 4, 2, '2023-11-09', 150000, 150000, 0),
(52, 4, 3, '2023-11-16', 220000, 220000, 0),
(53, 4, 4, '2023-11-23', 180000, 180000, 0),
(54, 4, 5, '2023-11-30', 250000, 250000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `receipt_detail`
--

CREATE TABLE `receipt_detail` (
  `receipt_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` double DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `receipt_detail`
--

INSERT INTO `receipt_detail` (`receipt_id`, `product_id`, `quantity`, `total`) VALUES
(1, 1, 2, 43000),
(1, 3, 1, 21500),
(2, 2, 3, 89700),
(2, 4, 2, 59800),
(3, 5, 1, 29900),
(3, 6, 1, 29900),
(4, 7, 2, 125000),
(4, 8, 1, 168000),
(5, 9, 4, 200000),
(5, 10, 3, 150000),
(6, 11, 2, 84000),
(6, 12, 1, 42000),
(7, 7, 2, 40000),
(7, 13, 3, 132000),
(8, 7, 1, 36000),
(8, 20, 1, 72000),
(9, 7, 2, 220000),
(9, 27, 1, 180000),
(10, 7, 3, 240000),
(10, 16, 2, 160000),
(11, 1, 2, 43000),
(11, 3, 1, 21500),
(12, 2, 3, 89700),
(12, 4, 2, 59800),
(13, 6, 1, 29900),
(13, 7, 1, 29900),
(14, 7, 2, 125000),
(14, 8, 1, 168000),
(31, 1, 2, 43000),
(31, 3, 1, 21500),
(32, 2, 3, 89700),
(32, 4, 2, 59800),
(33, 5, 1, 29900),
(33, 6, 1, 29900),
(34, 7, 2, 125000),
(34, 8, 1, 168000),
(35, 7, 4, 200000),
(35, 10, 3, 150000),
(36, 11, 2, 84000),
(36, 12, 1, 42000),
(37, 13, 3, 132000),
(37, 14, 2, 40000),
(38, 15, 1, 36000),
(38, 16, 1, 72000),
(39, 17, 2, 220000),
(39, 18, 1, 180000),
(40, 19, 3, 240000),
(40, 20, 2, 160000),
(41, 1, 2, 43000),
(41, 3, 1, 21500),
(42, 2, 3, 89700),
(42, 4, 2, 59800),
(43, 5, 1, 29900),
(43, 6, 1, 29900),
(44, 7, 2, 125000),
(44, 8, 1, 168000),
(45, 9, 4, 200000),
(45, 10, 3, 150000),
(46, 11, 2, 84000),
(46, 12, 1, 42000),
(47, 13, 3, 132000),
(47, 14, 2, 40000),
(48, 15, 1, 36000),
(48, 16, 1, 72000),
(49, 17, 2, 220000),
(49, 18, 1, 180000),
(50, 19, 3, 240000),
(50, 20, 2, 160000),
(51, 1, 2, 43000),
(51, 3, 1, 21500),
(52, 2, 3, 89700),
(52, 4, 2, 59800),
(53, 5, 1, 29900),
(53, 6, 1, 29900),
(54, 7, 2, 125000),
(54, 8, 1, 168000);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`, `deleted`) VALUES
(1, 'Admin', b'0'),
(2, 'Quản lý cửa hàng', b'0'),
(3, 'Nhân viên bán hàng', b'0'),
(4, 'Nhân viên kho', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `shipment`
--

CREATE TABLE `shipment` (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `unit_price` bigint(20) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `remain` double DEFAULT NULL,
  `mfg` date DEFAULT NULL,
  `exp` date DEFAULT NULL,
  `sku` varchar(255) DEFAULT NULL,
  `import_id` bigint(20) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shipment`
--

INSERT INTO `shipment` (`id`, `product_id`, `unit_price`, `quantity`, `remain`, `mfg`, `exp`, `sku`, `import_id`, `deleted`) VALUES
(1, 1, 10000, 50, 40, '2023-01-01', '2023-12-31', '', 11, b'0'),
(2, 2, 10000, 30, 10, '2023-02-01', '2023-11-08', '', 12, b'0'),
(3, 3, 10000, 75, 75, '2023-03-01', '2023-10-31', '', 13, b'0'),
(4, 4, 10000, 40, 40, '2023-04-01', '2023-09-30', '', 4, b'0'),
(5, 5, 10000, 60, 60, '2023-05-01', '2023-08-31', '', 5, b'0'),
(6, 6, 10000, 25, 25, '2023-06-01', '2023-07-31', '', 6, b'0'),
(7, 7, 10000, 45, 45, '2023-07-01', '2023-06-30', '', 7, b'0'),
(8, 8, 10000, 35, 35, '2023-08-01', '2023-05-31', '', 8, b'0'),
(9, 9, 10000, 55, 55, '2023-09-01', '2023-04-30', '', 9, b'0'),
(10, 10, 10000, 20, 20, '2023-10-01', '2023-03-31', '', 10, b'0'),
(11, 33, 1, 1, 1, '2023-11-01', '2023-11-01', '00110033', 34, b'0'),
(12, 34, 2, 2, 2, '2023-11-01', '2023-11-01', '00120034', 35, b'0'),
(13, 35, 1, 1, 1, '2023-11-02', '2023-11-02', '00120035', 35, b'0'),
(14, 36, 3, 3, 3, '2023-11-03', '2023-11-03', '00120036', 35, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `entry_date` date DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `name`, `gender`, `birthdate`, `phone`, `address`, `email`, `entry_date`, `deleted`) VALUES
(1, 'ADMIN', b'0', '1000-01-01', '096333946', '514/26 Lê Đức Thọ P17 Gò Vấp TPHCM', 'colong30082003@gmail.com', '1000-01-01', b'0'),
(2, 'NGUYỄN TIẾN DŨNG', b'1', '2003-12-19', '0834527892', '531 Nguyễn Oanh, Phường 17, Gò Vấp, Thành phố Hồ Chí Minh', 'dungboi@gmail.com', '1000-01-01', b'0'),
(3, 'ĐINH QUANG DUY', b'1', '2003-01-20', '0359872569', '1A Lê Đức Thọ, Phường 17, Gò Vấp, Thành phố Hồ Chí Minh', 'quangduy@gmail.com', '1000-01-01', b'0'),
(4, 'NGUYỄN HOÀNG LONG', b'1', '2003-08-30', '0970352875', '514/26 Lê Đức Thọ, Phường 17, Gò Vấp, Thành phố Hồ Chí Minh', 'colong30082003@gmail.com', '1000-01-01', b'0'),
(5, 'NGUYỄN HOÀNG MINH', b'1', '2003-03-06', '0367834257', '153 Lê Hoàng Phái, Phường 17, Gò Vấp, Thành phố Hồ Chí Minh', 'hoangminh@gmail.com', '1000-01-01', b'0'),
(6, 'NGUYỄN PHƯỚC SANG', b'1', '2002-09-20', '0935627488', '242 Nguyễn Văn Lượng, Phường 10, Gò Vấp, Thành phố Hồ Chí Minh', 'phuocsang@gmail.com', '1000-01-01', b'0'),
(7, 'NGUYỄN THỊ XUÂN MAI', b'0', '2002-06-19', '0825367498', '168 Lê Đức Thọ, Phường 15, Gò Vấp, Thành phố Hồ Chí Minh', 'xuanmai@gmail.com', '2023-09-15', b'0'),
(8, 'NGUYỄN THỊ LỆ GIANG', b'0', '2000-05-27', '0963527895', '190 Quang Trung, Phường 10, Gò Vấp, Thành phố Hồ Chí Minh', 'legiang@gmail.com', '2023-09-28', b'0'),
(9, 'ĐẶNG VĂN LÂM', b'1', '2001-02-18', '0340734629', '7 Phan Văn Trị, Phường 10, Gò Vấp, Thành phố Hồ Chí Minh', 'vanlam@gmail.com', '2023-06-27', b'0'),
(10, 'HOÀNG XUÂN PHÚC', b'1', '2001-04-11', '0812535278', '526 Lê Quang Định, Phường 1, Gò Vấp, Thành phố Hồ Chí Minh', 'xuanphuc@gmail.com', '2023-08-17', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `statistic`
--

CREATE TABLE `statistic` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `expenses` double DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `name`, `phone`, `address`, `email`, `deleted`) VALUES
(1, 'Vinamilk', '02854155555', 'Số 10, Đường Tân Trào, phường Tân Phú, quận 7, Tp. HCM', 'vinamilk@vinamilk.com.vn', b'0'),
(2, 'TH true Milk', '02862918888', 'Tháp B, Tầng 6 - Tòa nhà Viettel, Số 285 Cách Mạng Tháng 8, P.12, Q.10, TP.HCM', 'chamsockhachhang@thmilk.vn', b'0'),
(3, 'Coca-Cola', '1900555584', 'Xa lộ Hà Nội, Phường Linh Trung, Quận Thủ Đức, Thành phố Hồ Chí Minh', 'trucle@coca-cola.com', b'0'),
(4, 'Acecook', '02838154064', 'Lô số II-3,Đường số 11,Nhóm CN II, Khu Công nghiệp Tân Bình, Phường Tây Thạnh, Quận Tân Phú, Thành phố Hồ Chí Minh', 'info@acecookvietnam.com', b'0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_STAFF` (`staff_id`),
  ADD KEY `FK_ROLE` (`role_id`);

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_SUPPLIER` (`supplier_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `decentralization`
--
ALTER TABLE `decentralization`
  ADD PRIMARY KEY (`role_id`,`module_id`,`function_id`),
  ADD KEY `FK_MODULE` (`module_id`),
  ADD KEY `FK_FUNCTION` (`function_id`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `discount_detail`
--
ALTER TABLE `discount_detail`
  ADD PRIMARY KEY (`discount_id`,`product_id`),
  ADD KEY `FK_PRODUCT` (`product_id`);

--
-- Indexes for table `export`
--
ALTER TABLE `export`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_STAFF1` (`staff_id`);

--
-- Indexes for table `export_detail`
--
ALTER TABLE `export_detail`
  ADD PRIMARY KEY (`export_id`,`shipment_id`),
  ADD KEY `FK_SHIPMENT` (`shipment_id`);

--
-- Indexes for table `function`
--
ALTER TABLE `function`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `import`
--
ALTER TABLE `import`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_STAFF2` (`staff_id`),
  ADD KEY `FK_SUPPLIER1` (`supplier_id`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_BRAND` (`brand_id`),
  ADD KEY `FK_CATEGORY` (`category_id`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `promotion_gift`
--
ALTER TABLE `promotion_gift`
  ADD PRIMARY KEY (`promotion_id`,`product_id`),
  ADD KEY `FK_PRODUCT1` (`product_id`);

--
-- Indexes for table `promotion_item`
--
ALTER TABLE `promotion_item`
  ADD PRIMARY KEY (`promotion_id`,`product_id`),
  ADD KEY `FK_PRODUCT2` (`product_id`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_STAFF3` (`staff_id`),
  ADD KEY `FK_CUSTOMER` (`customer_id`);

--
-- Indexes for table `receipt_detail`
--
ALTER TABLE `receipt_detail`
  ADD PRIMARY KEY (`receipt_id`,`product_id`),
  ADD KEY `FK_PRODUCT3` (`product_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shipment`
--
ALTER TABLE `shipment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_IMPORT` (`import_id`),
  ADD KEY `FK_PRODUCT4` (`product_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `statistic`
--
ALTER TABLE `statistic`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `discount`
--
ALTER TABLE `discount`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `export`
--
ALTER TABLE `export`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `function`
--
ALTER TABLE `function`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `import`
--
ALTER TABLE `import`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `receipt`
--
ALTER TABLE `receipt`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `shipment`
--
ALTER TABLE `shipment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `statistic`
--
ALTER TABLE `statistic`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FK_STAFF` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`);

--
-- Constraints for table `brand`
--
ALTER TABLE `brand`
  ADD CONSTRAINT `FK_SUPPLIER` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`);

--
-- Constraints for table `decentralization`
--
ALTER TABLE `decentralization`
  ADD CONSTRAINT `FK_FUNCTION` FOREIGN KEY (`function_id`) REFERENCES `function` (`id`),
  ADD CONSTRAINT `FK_MODULE` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`),
  ADD CONSTRAINT `FK_ROLE1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Constraints for table `discount_detail`
--
ALTER TABLE `discount_detail`
  ADD CONSTRAINT `FK_DISCOUNT` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`id`),
  ADD CONSTRAINT `FK_PRODUCT` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `export`
--
ALTER TABLE `export`
  ADD CONSTRAINT `FK_STAFF1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`);

--
-- Constraints for table `export_detail`
--
ALTER TABLE `export_detail`
  ADD CONSTRAINT `FK_EXPORT` FOREIGN KEY (`export_id`) REFERENCES `export` (`id`),
  ADD CONSTRAINT `FK_SHIPMENT` FOREIGN KEY (`shipment_id`) REFERENCES `shipment` (`id`);

--
-- Constraints for table `import`
--
ALTER TABLE `import`
  ADD CONSTRAINT `FK_STAFF2` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`),
  ADD CONSTRAINT `FK_SUPPLIER1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_BRAND` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  ADD CONSTRAINT `FK_CATEGORY` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `promotion_gift`
--
ALTER TABLE `promotion_gift`
  ADD CONSTRAINT `FK_PRODUCT1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK_PROMOTION` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`);

--
-- Constraints for table `promotion_item`
--
ALTER TABLE `promotion_item`
  ADD CONSTRAINT `FK_PRODUCT2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK_PROMOTION1` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`);

--
-- Constraints for table `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `FK_CUSTOMER` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `FK_STAFF3` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`);

--
-- Constraints for table `receipt_detail`
--
ALTER TABLE `receipt_detail`
  ADD CONSTRAINT `FK_PRODUCT3` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK_RECEIPT` FOREIGN KEY (`receipt_id`) REFERENCES `receipt` (`id`);

--
-- Constraints for table `shipment`
--
ALTER TABLE `shipment`
  ADD CONSTRAINT `FK_IMPORT` FOREIGN KEY (`import_id`) REFERENCES `import` (`id`),
  ADD CONSTRAINT `FK_PRODUCT4` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
