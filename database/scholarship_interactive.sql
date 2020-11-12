-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 12, 2020 lúc 10:50 AM
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

--
-- Đang đổ dữ liệu cho bảng `scholarship_interactive`
--

INSERT INTO `scholarship_interactive` (`id`, `scholarship_id`, `user_id`, `is_liked`, `rating`, `is_in_list_favorite`, `number_seen`, `number_share`, `number_comment`, `number_click_contact`, `number_compare`, `created_time`, `updated_time`, `updated_by_user_id`, `created_by_user_id`, `is_deleted`) VALUES
(2, 1, 3, NULL, NULL, NULL, 2, 0, 1, 1, 1, '2020-11-12 09:43:50', '2020-11-12 09:43:50', NULL, NULL, b'0');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `scholarship_interactive`
--
ALTER TABLE `scholarship_interactive`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `scholarship_interactive`
--
ALTER TABLE `scholarship_interactive`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
