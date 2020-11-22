-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 19, 2020 lúc 11:54 AM
-- Phiên bản máy phục vụ: 10.1.38-MariaDB
-- Phiên bản PHP: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `scholarship_db`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `role`, `is_active`, `created_time`, `updated_time`, `updated_by_user_id`, `created_by_user_id`, `is_deleted`) VALUES
(1, 'thuyduong', '$2a$10$9PUY/mVZ2noX4Rs/.lV8C.IwFdZegw432w5W/PWT5yPB2Z3xkx8TC', 'ADMIN', b'1', '2020-10-19 09:40:06', '2020-10-19 09:40:06', 1, 1, b'0'),
(2, 'kientt', '$2a$10$9PUY/mVZ2noX4Rs/.lV8C.IwFdZegw432w5W/PWT5yPB2Z3xkx8TC', 'ADMIN', b'1', '2020-10-19 09:40:06', '2020-10-19 09:40:06', 1, 1, b'0'),
(3, 'datnh', '$2a$10$9PUY/mVZ2noX4Rs/.lV8C.IwFdZegw432w5W/PWT5yPB2Z3xkx8TC', 'END_USER', b'1', '2020-10-19 09:40:06', '2020-10-19 09:40:06', 1, 1, b'0'),
(4, 'anh', '$2a$10$Vie4S.cjyJXjSgqakqLGu.18lrRbWWd.xEaP4xSVlqHjnRaHZvCI2', 'ADMIN', b'1', '2020-10-29 13:27:51', '2020-10-29 14:29:25', 1, 1, b'0'),
(5, 'look', '$2a$10$PVdNZFJpjAHD2vrpvji5venl7U95y6ha.SzWBaQ5QLobGpxDR73gC', 'ADMIN', b'1', '2020-10-29 14:24:07', '2020-10-29 15:17:44', 2, 1, b'0'),
(6, 'love', '$2a$10$Ru6Lc/j8H9LVZnINsPG6tu71XgT8pSxdYY0a0qF5gAXoyyKAamhBe', 'ADMIN', b'0', '2020-10-29 14:33:42', '2020-10-29 15:32:40', 1, 2, b'0'),
(9, 'thuyduongg', '$2a$10$eGMBByj4VEb8DiEAlJyFG.Y1IDuCJBeYOXGXPy6iut.HtDo9ERtHC', 'END_USER', b'1', '2020-11-09 17:16:40', '2020-11-09 17:16:40', NULL, NULL, b'0'),
(10, 'thuyduong1', '$2a$10$X.EGbMcfKMUJLGXf67Mfc.ZXgKlDOIC9FW3YERdtXVDM3s142BHNC', 'END_USER', b'1', '2020-11-09 17:24:07', '2020-11-17 16:46:35', NULL, NULL, b'0'),
(11, 'nguyenhaison', '$2a$10$6eN/yeuGT9v0Kw5kYcT5M.VI/GsVu/CSTghICyqzmkhhwmflztuIC', 'END_USER', b'1', '2020-11-16 10:01:29', '2020-11-16 10:01:29', NULL, NULL, b'0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `last_login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `admin`
--

INSERT INTO `admin` (`id`, `account_id`, `last_login`, `created_time`, `updated_time`, `updated_by_user_id`, `created_by_user_id`, `is_deleted`) VALUES
(1, 1, '2020-11-16 17:24:36', '2020-10-27 17:00:00', '2020-11-16 17:24:36', 1, NULL, 0),
(2, 4, '2020-10-29 14:30:13', '2020-10-29 13:27:51', '2020-10-29 14:30:13', 2, NULL, 0),
(3, 5, '2020-10-29 14:24:07', '2020-10-29 14:24:07', '2020-10-29 14:24:07', NULL, NULL, 0),
(4, 6, '2020-10-29 14:33:42', '2020-10-29 14:33:42', '2020-10-29 14:33:42', 2, 2, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `auth_token`
--

CREATE TABLE `auth_token` (
  `id` int(11) NOT NULL,
  `token` varchar(500) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `scholarship_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `message` text COLLATE utf8_unicode_ci,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `country`
--

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `area` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `country_favorite`
--

CREATE TABLE `country_favorite` (
  `id` int(11) NOT NULL,
  `country_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `end_user`
--

CREATE TABLE `end_user` (
  `id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `access_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `level` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `graduation_date` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `end_user`
--

INSERT INTO `end_user` (`id`, `account_id`, `access_token`, `name`, `phone`, `gender`, `level`, `country`, `birthday`, `graduation_date`, `created_time`, `updated_time`, `updated_by_user_id`, `created_by_user_id`, `is_deleted`) VALUES
(1, 8, NULL, '', '', NULL, '', NULL, NULL, NULL, '2020-11-09 17:05:29', '2020-11-09 17:05:29', NULL, NULL, 0),
(2, 9, NULL, 'Thuy Duong', '0123456', 'Nam', 'THPT', NULL, '1998-12-05 00:00:00', '2020-11-10 00:00:00', '2020-11-09 17:16:41', '2020-11-09 17:16:41', NULL, NULL, 0),
(3, 10, NULL, 'Duong', '0123', NULL, 'THPT', NULL, '2020-11-09 17:00:00', '2020-11-18 17:00:00', '2020-11-09 17:24:07', '2020-11-19 06:51:14', NULL, NULL, 0),
(4, 11, NULL, 'H?i S?n', '', NULL, '', NULL, NULL, NULL, '2020-11-16 10:01:29', '2020-11-16 10:01:29', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `level`
--

CREATE TABLE `level` (
  `id` int(11) NOT NULL,
  `scholarship_id` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `name` text COLLATE utf8_unicode_ci,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `major`
--

CREATE TABLE `major` (
  `id` int(6) NOT NULL,
  `parent_id` int(6) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `level` int(3) DEFAULT '1',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `major`
--

INSERT INTO `major` (`id`, `parent_id`, `name`, `level`, `created_time`, `updated_time`, `updated_by_user_id`, `created_by_user_id`, `is_deleted`) VALUES
(1, NULL, 'Khoa học tự nhiên', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(2, NULL, 'Khoa học Xã hội và Nhân Văn', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(3, NULL, 'Kỹ thuật', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(4, NULL, 'Kiến trúc', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(5, NULL, 'Ngoại ngữ', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(6, NULL, 'Sư phạm', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(7, NULL, 'Công nghệ', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(8, NULL, 'Kinh tế và Quản lý', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(9, NULL, 'Nông - Lâm - Ngư Nghiệp', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(10, NULL, 'Văn hóa - Nghệ thuật', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(11, NULL, 'Y Dược', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(12, NULL, 'Du lịch', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(13, NULL, 'Giao thông Vân Tải', 0, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(156, 1, 'Các ngành liên quan đến lĩnh vực Khoa học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(157, 1, 'Địa lý', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(158, 1, 'Hải dương học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(159, 1, 'Hóa học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(160, 1, 'Hóa học-Sinh hóa học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(161, 1, 'Khoa học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(162, 1, 'Khoa học cơ bản', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(163, 1, 'Khoa học Môi trường', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(164, 1, 'Khoa học tự nhiên', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(165, 1, 'Khoa học Vật lý', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(166, 1, 'Lý thuyết học thống kê', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(167, 1, 'Môi trường', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(168, 1, 'Nghiên cứu', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(14, 1, 'Sinh học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(15, 1, 'Toán học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(16, 1, 'Sinh học tế bào và phân tử', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(17, 1, 'Thiên văn học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(18, 13, 'Giao thông vận tải', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(19, 13, 'Logistics', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(20, 12, 'Các ngành thuộc khối ngành Quản lí Du lịch', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(21, 12, 'Du lịch - Nhà Hàng - Khách sạn', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(22, 12, 'Du lịch và Du lịch Nhà hàng - Khách sạn', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(23, 11, 'Bệnh truyền nhiễm', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(24, 11, 'Chăm sóc cá nhân và Thể dục', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(25, 11, 'Dược', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(26, 11, 'Khoa học Dược', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(27, 11, 'Khoa học Sức khỏe', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(28, 11, 'Khoa học thần kinh', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(29, 11, 'Khoa học thể chất', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(30, 11, 'Khoa học Thực phẩm và Dinh dưỡng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(31, 11, 'Nha khoa và Thú y', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(32, 11, 'Y khoa', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(33, 11, 'Y sinh', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(34, 11, 'Y tế công cộng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(35, 11, 'Y tế và Sức khỏe', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(36, 11, 'Y-Dược', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(37, 11, 'Vật lí Y khoa', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(38, 11, 'Sức khỏe-Y tế công cộng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(39, 10, 'Âm nhạc', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(40, 10, 'Âm nhạc - Nghệ thuật biễu diễn', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(41, 10, 'Nghệ thuật sáng tạo và Thiết kế', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(42, 10, 'Nghệ thuật', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(43, 10, 'Phim ảnh', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(44, 10, 'Thiết kế& sáng tạo', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(45, 9, 'Khoa học nông nghiệp', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(46, 9, 'Khoa học Nông nghiệp và Quản lý', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(47, 9, 'Nông nghiệp và Thú y', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(48, 9, 'Nông nghiệp&thú y', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(49, 8, 'Khoa học Kinh tế', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(50, 8, 'Chính sách tài khóa và tiền tệ', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(51, 8, 'Các ngành học liên quan đến sự phát triển bền vững', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(52, 8, 'Digital Marketing', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(53, 8, 'Kế toán', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(54, 8, 'Kế toán và kiểm toán', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(55, 8, 'Kinh doanh', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(56, 8, 'Kinh doanh và Chính trị tại Châu Á hiện đại', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(57, 8, 'Kinh doanh và Quản lý', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(58, 8, 'Kinh tế', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(59, 8, 'Kinh tế học hành vi', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(60, 8, 'Kinh tế học thực nghiệm', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(61, 8, 'Kinh tế tài chính', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(62, 8, 'Kinh tế và Tài chính Quốc tế', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(63, 8, 'Kinh tế-chính trị', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(64, 8, 'Marketing', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(65, 8, 'MBA', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(66, 8, 'Những ngành học liên quan đến phát triển kinh tế', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(67, 8, 'Quản lí quan hệ khách hàng (CRM)', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(68, 8, 'Quản lí Rủi ro và Bảo hiểm', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(69, 8, 'Quản lý', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(70, 8, 'Quản lý và Phân tích Kinh tế', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(71, 8, 'Quản trị', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(72, 8, 'Quản trị kinh doanh', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(73, 8, 'Quản trị kinh doanh (MBA)', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(74, 8, 'Quản trị năng lượng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(75, 8, 'Quản trị nhân sự', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(76, 8, 'Tài chính', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(77, 8, 'Tài chính doanh nghiệp chiến lược', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(78, 8, 'Tài chính_ngân hàng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(79, 8, 'Tâm lí học xã hội ứng dụng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(80, 8, 'Tâm lý học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(81, 8, 'Thạc sĩ quản trị kinh doanh', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(82, 7, 'Cảm biến quán tính và lưới trong Công nghệ lượng tử', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(83, 7, 'AI/Machine learning', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(84, 7, 'Công nghệ', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(85, 7, 'Công nghệ sinh học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(86, 7, 'Công nghệ thông tin', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(87, 7, 'Công nghệ thực phẩm', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(88, 7, 'công nghệ và đổi mới', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(89, 7, 'Hệ thống Big Data', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(90, 7, 'Hình ảnh', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(91, 7, 'Khoa học dữ liệu', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(92, 7, 'Khoa học máy tính', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(93, 7, 'Khoa học máy tính ứng dụng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(94, 7, 'Khoa học máy tính và Công nghệ thông tin', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(95, 7, 'Khoa học nhận thức và công nghệ: từ nơ-ron đến nhận thức', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(96, 7, 'Kỹ thuật hệ thống và phần mềm', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(97, 6, 'Giáo dục', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(98, 6, 'Giáo dục và Đào tạo', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(99, 6, 'Giáo dục&đào tạo', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(100, 5, 'Nhật Bản đương thời', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(101, 4, 'Kiến trúc', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(102, 4, 'Kiến trúc và Môi trường xây dựng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(103, 4, 'Kiến trúc và Xây dựng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(104, 4, 'Kiến trúc& Quy hoạch', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(105, 4, 'Xây dựng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(106, 4, 'Thiết kế', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(107, 3, 'Các ngành học thuộc Khoa Kỹ thuật', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(108, 3, 'Cơ học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(109, 3, 'Cơ khí - Tự động hóa', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(110, 3, 'Cơ khí và Công nghệ', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(111, 3, 'Điện tử& Viễn thông', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(112, 3, 'Hàng không-Không gian', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(113, 3, 'Khoa học Hoá và Kỹ thuật', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(114, 3, 'Khoa học kĩ thuật', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(115, 3, 'Khoa học tự nhiên và kĩ thuật', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(116, 3, 'Khoa học ứng dụng và Khoa học cơ bản', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(117, 3, 'Khoa học vật liệu', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(118, 3, 'Khoa học vật chất', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(119, 3, 'Kỹ sư dân dụng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(120, 3, 'Kỹ sư dự báo (Forecast Engineering)', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(121, 3, 'Kỹ thuật', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(122, 3, 'Kỹ thuật hóa học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(123, 3, 'Kỹ thuật Kết cấu', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(124, 3, 'Kỹ thuật y sinh', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(125, 3, 'Năng lượng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(126, 2, 'Chính sách', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(127, 2, 'Chính sách công', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(128, 2, 'Chính trị so sánh Á - Âu', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(129, 2, 'Chính trị và nghiên cứu quốc tế', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(130, 2, 'Khởi nghiệp & Đổi mới', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(131, 2, 'Khảo cổ học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(132, 2, 'Khoa học chính trị', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(133, 2, 'Khoa học xã hội', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(134, 2, 'Khoa học Xã hội và Nhân văn', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(135, 2, 'Báo chí', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(136, 2, 'Báo chí&truyền thông', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(137, 2, 'Khoa học xã hội và Truyền thông', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(138, 2, 'Khoa học xã hội&nhân văn', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(139, 2, 'Lịch sử', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(140, 2, 'Lịch sử nhân loại', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(141, 2, 'Lịch sử ứng dụng và liên ngành', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(142, 2, 'Luật', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(143, 2, 'Luật Nhân đạo quốc tế và Nhân quyền', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(144, 2, 'Nhân văn', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(145, 2, 'Nhân văn môi trường', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(146, 2, 'Lý thuyết ngôn ngữ học và mô tả ngôn ngữ', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(147, 2, 'Nghiên cứu xã hội so sánh', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(148, 2, 'Ngôn ngữ&văn chương', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(149, 2, 'Nhân quyền và Pháp quyền', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(150, 2, 'Phân tính chính trị và Chính sách công', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(151, 2, 'Phát triển dân số', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(152, 2, 'Quốc tế học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(153, 2, 'Triết học', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(154, 2, 'Truyền thông đại chúng', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0),
(155, 2, 'Sự phát triển kinh tế xã hội và Châu Á hiện đại', 1, '2020-11-08 16:26:11', '2020-11-08 16:24:33', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `major_favorite`
--

CREATE TABLE `major_favorite` (
  `id` int(11) NOT NULL,
  `major_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `major_scholarship`
--

CREATE TABLE `major_scholarship` (
  `id` int(11) NOT NULL,
  `scholarship_id` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `major_id` int(11) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `money`
--

CREATE TABLE `money` (
  `id` int(11) NOT NULL,
  `scholarship_id` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `value` text COLLATE utf8_unicode_ci,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `requirement`
--

CREATE TABLE `requirement` (
  `id` int(11) NOT NULL,
  `scholarship_id` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `name` text COLLATE utf8_unicode_ci,
  `value` text COLLATE utf8_unicode_ci,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `scholarship`
--

CREATE TABLE `scholarship` (
  `id` int(11) NOT NULL,
  `web_id` int(11) NOT NULL,
  `country_id` int(11) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  `name` text COLLATE utf8_unicode_ci,
  `time` date DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `url` text COLLATE utf8_unicode_ci,
  `rating` float NOT NULL,
  `number_rating` int(11) NOT NULL DEFAULT '0',
  `number_seen` int(11) DEFAULT '0',
  `number_share` int(11) DEFAULT '0',
  `number_comment` int(11) DEFAULT '0',
  `is_expired` tinyint(1) DEFAULT '0',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `scholarship_interactive`
--

CREATE TABLE `scholarship_interactive` (
  `id` int(11) NOT NULL,
  `scholarship_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `is_liked` tinyint(1) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `is_in_list_favorite` tinyint(1) DEFAULT NULL,
  `number_seen` int(11) NOT NULL DEFAULT '0',
  `number_share` int(11) NOT NULL DEFAULT '0',
  `number_comment` int(11) NOT NULL DEFAULT '0',
  `number_click_contact` int(11) NOT NULL DEFAULT '0',
  `number_compare` int(11) NOT NULL DEFAULT '0',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `school`
--

CREATE TABLE `school` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `school_favorite`
--

CREATE TABLE `school_favorite` (
  `id` int(11) NOT NULL,
  `school_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `web`
--

CREATE TABLE `web` (
  `id` int(11) NOT NULL,
  `url` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `type_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_crawled` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(1) DEFAULT '1',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by_user_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `web`
--

INSERT INTO `web` (`id`, `url`, `time`, `type_time`, `last_crawled`, `is_active`, `created_time`, `updated_time`, `updated_by_user_id`, `created_by_user_id`, `is_deleted`) VALUES
(1, 'scholarshipplanet.info', 2, 'DAY', '2020-10-30 08:32:45', 1, '2020-10-29 15:50:00', '2020-10-30 02:27:45', 1, NULL, b'0'),
(2, 'sanhocbong.net', 1, 'MONTH', '2020-10-29 16:05:00', 1, '2020-10-29 15:50:00', '2020-10-30 10:24:54', 1, NULL, b'0'),
(3, 'nguonhocbong.com', 1, 'DAY', '2020-10-29 16:05:00', 0, '2020-10-29 15:50:00', '2020-10-29 17:28:42', 1, NULL, b'0');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQUE_Username` (`username`) USING BTREE;

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `auth_token`
--
ALTER TABLE `auth_token`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `token_unique` (`token`),
  ADD KEY `account_id_index` (`account_id`,`type`);

--
-- Chỉ mục cho bảng `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `country_favorite`
--
ALTER TABLE `country_favorite`
  ADD PRIMARY KEY (`id`),
  ADD KEY `country_id` (`country_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `end_user`
--
ALTER TABLE `end_user`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `level`
--
ALTER TABLE `level`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `major_favorite`
--
ALTER TABLE `major_favorite`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `major_scholarship`
--
ALTER TABLE `major_scholarship`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `money`
--
ALTER TABLE `money`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `requirement`
--
ALTER TABLE `requirement`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `scholarship`
--
ALTER TABLE `scholarship`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `scholarship_interactive`
--
ALTER TABLE `scholarship_interactive`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `school`
--
ALTER TABLE `school`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `school_favorite`
--
ALTER TABLE `school_favorite`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `web`
--
ALTER TABLE `web`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `url` (`url`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `auth_token`
--
ALTER TABLE `auth_token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `country`
--
ALTER TABLE `country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `country_favorite`
--
ALTER TABLE `country_favorite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `end_user`
--
ALTER TABLE `end_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `level`
--
ALTER TABLE `level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `major_favorite`
--
ALTER TABLE `major_favorite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `major_scholarship`
--
ALTER TABLE `major_scholarship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `money`
--
ALTER TABLE `money`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `requirement`
--
ALTER TABLE `requirement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `scholarship`
--
ALTER TABLE `scholarship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `scholarship_interactive`
--
ALTER TABLE `scholarship_interactive`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `school`
--
ALTER TABLE `school`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `school_favorite`
--
ALTER TABLE `school_favorite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `web`
--
ALTER TABLE `web`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `country_favorite`
--
ALTER TABLE `country_favorite`
  ADD CONSTRAINT `country_favorite_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  ADD CONSTRAINT `country_favorite_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `end_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
