-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 22, 2020 lúc 07:55 AM
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
-- Chỉ mục cho bảng `end_user`
--
ALTER TABLE `end_user`
  ADD PRIMARY KEY (`id`);

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
-- AUTO_INCREMENT cho bảng `end_user`
--
ALTER TABLE `end_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
