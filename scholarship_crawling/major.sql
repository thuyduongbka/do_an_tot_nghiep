-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 01, 2020 lúc 05:56 PM
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
-- Cơ sở dữ liệu: `sc`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `major`
--

CREATE TABLE `major` (
  `id` int(6) NOT NULL,
  `parent_id` int(6) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `level` int(3) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `major`
--

INSERT INTO `major` (`id`, `parent_id`, `name`, `level`) VALUES
(1, NULL, 'Khoa học tự nhiên', 0),
(2, NULL, 'Khoa học Xã hội và Nhân Văn', 0),
(3, NULL, 'Kỹ thuật', 0),
(4, NULL, 'Kiến trúc', 0),
(5, NULL, 'Ngoại ngữ', 0),
(6, NULL, 'Sư phạm', 0),
(7, NULL, 'Công nghệ', 0),
(8, NULL, 'Kinh tế và Quản lý', 0),
(9, NULL, 'Nông - Lâm - Ngư Nghiệp', 0),
(10, NULL, 'Văn hóa - Nghệ thuật', 0),
(11, NULL, 'Y Dược', 0),
(12, NULL, 'Du lịch', 0),
(13, NULL, 'Giao thông Vân Tải', 0),
(156, 1, 'Các ngành liên quan đến lĩnh vực Khoa học', 1),
(157, 1, 'Địa lý', 1),
(158, 1, 'Hải dương học', 1),
(159, 1, 'Hóa học', 1),
(160, 1, 'Hóa học-Sinh hóa học', 1),
(161, 1, 'Khoa học', 1),
(162, 1, 'Khoa học cơ bản', 1),
(163, 1, 'Khoa học Môi trường', 1),
(164, 1, 'Khoa học tự nhiên', 1),
(165, 1, 'Khoa học Vật lý', 1),
(166, 1, 'Lý thuyết học thống kê', 1),
(167, 1, 'Môi trường', 1),
(168, 1, 'Nghiên cứu', 1),
(14, 1, 'Sinh học', 1),
(15, 1, 'Toán học', 1),
(16, 1, 'Sinh học tế bào và phân tử', 1),
(17, 1, 'Thiên văn học', 1),
(18, 13, 'Giao thông vận tải', 1),
(19, 13, 'Logistics', 1),
(20, 12, 'Các ngành thuộc khối ngành Quản lí Du lịch', 1),
(21, 12, 'Du lịch - Nhà Hàng - Khách sạn', 1),
(22, 12, 'Du lịch và Du lịch Nhà hàng - Khách sạn', 1),
(23, 11, 'Bệnh truyền nhiễm', 1),
(24, 11, 'Chăm sóc cá nhân và Thể dục', 1),
(25, 11, 'Dược', 1),
(26, 11, 'Khoa học Dược', 1),
(27, 11, 'Khoa học Sức khỏe', 1),
(28, 11, 'Khoa học thần kinh', 1),
(29, 11, 'Khoa học thể chất', 1),
(30, 11, 'Khoa học Thực phẩm và Dinh dưỡng', 1),
(31, 11, 'Nha khoa và Thú y', 1),
(32, 11, 'Y khoa', 1),
(33, 11, 'Y sinh', 1),
(34, 11, 'Y tế công cộng', 1),
(35, 11, 'Y tế và Sức khỏe', 1),
(36, 11, 'Y-Dược', 1),
(37, 11, 'Vật lí Y khoa', 1),
(38, 11, 'Sức khỏe-Y tế công cộng', 1),
(39, 10, 'Âm nhạc', 1),
(40, 10, 'Âm nhạc - Nghệ thuật biễu diễn', 1),
(41, 10, 'Nghệ thuật sáng tạo và Thiết kế', 1),
(42, 10, 'Nghệ thuật', 1),
(43, 10, 'Phim ảnh', 1),
(44, 10, 'Thiết kế& sáng tạo', 1),
(45, 9, 'Khoa học nông nghiệp', 1),
(46, 9, 'Khoa học Nông nghiệp và Quản lý', 1),
(47, 9, 'Nông nghiệp và Thú y', 1),
(48, 9, 'Nông nghiệp&thú y', 1),
(49, 8, 'Khoa học Kinh tế', 1),
(50, 8, 'Chính sách tài khóa và tiền tệ', 1),
(51, 8, 'Các ngành học liên quan đến sự phát triển bền vững', 1),
(52, 8, 'Digital Marketing', 1),
(53, 8, 'Kế toán', 1),
(54, 8, 'Kế toán và kiểm toán', 1),
(55, 8, 'Kinh doanh', 1),
(56, 8, 'Kinh doanh và Chính trị tại Châu Á hiện đại', 1),
(57, 8, 'Kinh doanh và Quản lý', 1),
(58, 8, 'Kinh tế', 1),
(59, 8, 'Kinh tế học hành vi', 1),
(60, 8, 'Kinh tế học thực nghiệm', 1),
(61, 8, 'Kinh tế tài chính', 1),
(62, 8, 'Kinh tế và Tài chính Quốc tế', 1),
(63, 8, 'Kinh tế-chính trị', 1),
(64, 8, 'Marketing', 1),
(65, 8, 'MBA', 1),
(66, 8, 'Những ngành học liên quan đến phát triển kinh tế', 1),
(67, 8, 'Quản lí quan hệ khách hàng (CRM)', 1),
(68, 8, 'Quản lí Rủi ro và Bảo hiểm', 1),
(69, 8, 'Quản lý', 1),
(70, 8, 'Quản lý và Phân tích Kinh tế', 1),
(71, 8, 'Quản trị', 1),
(72, 8, 'Quản trị kinh doanh', 1),
(73, 8, 'Quản trị kinh doanh (MBA)', 1),
(74, 8, 'Quản trị năng lượng', 1),
(75, 8, 'Quản trị nhân sự', 1),
(76, 8, 'Tài chính', 1),
(77, 8, 'Tài chính doanh nghiệp chiến lược', 1),
(78, 8, 'Tài chính_ngân hàng', 1),
(79, 8, 'Tâm lí học xã hội ứng dụng', 1),
(80, 8, 'Tâm lý học', 1),
(81, 8, 'Thạc sĩ quản trị kinh doanh', 1),
(82, 7, 'Cảm biến quán tính và lưới trong Công nghệ lượng tử', 1),
(83, 7, 'AI/Machine learning', 1),
(84, 7, 'Công nghệ', 1),
(85, 7, 'Công nghệ sinh học', 1),
(86, 7, 'Công nghệ thông tin', 1),
(87, 7, 'Công nghệ thực phẩm', 1),
(88, 7, 'công nghệ và đổi mới', 1),
(89, 7, 'Hệ thống Big Data', 1),
(90, 7, 'Hình ảnh', 1),
(91, 7, 'Khoa học dữ liệu', 1),
(92, 7, 'Khoa học máy tính', 1),
(93, 7, 'Khoa học máy tính ứng dụng', 1),
(94, 7, 'Khoa học máy tính và Công nghệ thông tin', 1),
(95, 7, 'Khoa học nhận thức và công nghệ: từ nơ-ron đến nhận thức', 1),
(96, 7, 'Kỹ thuật hệ thống và phần mềm', 1),
(97, 6, 'Giáo dục', 1),
(98, 6, 'Giáo dục và Đào tạo', 1),
(99, 6, 'Giáo dục&đào tạo', 1),
(100, 5, 'Nhật Bản đương thời', 1),
(101, 4, 'Kiến trúc', 1),
(102, 4, 'Kiến trúc và Môi trường xây dựng', 1),
(103, 4, 'Kiến trúc và Xây dựng', 1),
(104, 4, 'Kiến trúc& Quy hoạch', 1),
(105, 4, 'Xây dựng', 1),
(106, 4, 'Thiết kế', 1),
(107, 3, 'Các ngành học thuộc Khoa Kỹ thuật', 1),
(108, 3, 'Cơ học', 1),
(109, 3, 'Cơ khí - Tự động hóa', 1),
(110, 3, 'Cơ khí và Công nghệ', 1),
(111, 3, 'Điện tử& Viễn thông', 1),
(112, 3, 'Hàng không-Không gian', 1),
(113, 3, 'Khoa học Hoá và Kỹ thuật', 1),
(114, 3, 'Khoa học kĩ thuật', 1),
(115, 3, 'Khoa học tự nhiên và kĩ thuật', 1),
(116, 3, 'Khoa học ứng dụng và Khoa học cơ bản', 1),
(117, 3, 'Khoa học vật liệu', 1),
(118, 3, 'Khoa học vật chất', 1),
(119, 3, 'Kỹ sư dân dụng', 1),
(120, 3, 'Kỹ sư dự báo (Forecast Engineering)', 1),
(121, 3, 'Kỹ thuật', 1),
(122, 3, 'Kỹ thuật hóa học', 1),
(123, 3, 'Kỹ thuật Kết cấu', 1),
(124, 3, 'Kỹ thuật y sinh', 1),
(125, 3, 'Năng lượng', 1),
(126, 2, 'Chính sách', 1),
(127, 2, 'Chính sách công', 1),
(128, 2, 'Chính trị so sánh Á - Âu', 1),
(129, 2, 'Chính trị và nghiên cứu quốc tế', 1),
(130, 2, 'Khởi nghiệp & Đổi mới', 1),
(131, 2, 'Khảo cổ học', 1),
(132, 2, 'Khoa học chính trị', 1),
(133, 2, 'Khoa học xã hội', 1),
(134, 2, 'Khoa học Xã hội và Nhân văn', 1),
(135, 2, 'Báo chí', 1),
(136, 2, 'Báo chí&truyền thông', 1),
(137, 2, 'Khoa học xã hội và Truyền thông', 1),
(138, 2, 'Khoa học xã hội&nhân văn', 1),
(139, 2, 'Lịch sử', 1),
(140, 2, 'Lịch sử nhân loại', 1),
(141, 2, 'Lịch sử ứng dụng và liên ngành', 1),
(142, 2, 'Luật', 1),
(143, 2, 'Luật Nhân đạo quốc tế và Nhân quyền', 1),
(144, 2, 'Nhân văn', 1),
(145, 2, 'Nhân văn môi trường', 1),
(146, 2, 'Lý thuyết ngôn ngữ học và mô tả ngôn ngữ', 1),
(147, 2, 'Nghiên cứu xã hội so sánh', 1),
(148, 2, 'Ngôn ngữ&văn chương', 1),
(149, 2, 'Nhân quyền và Pháp quyền', 1),
(150, 2, 'Phân tính chính trị và Chính sách công', 1),
(151, 2, 'Phát triển dân số', 1),
(152, 2, 'Quốc tế học', 1),
(153, 2, 'Triết học', 1),
(154, 2, 'Truyền thông đại chúng', 1),
(155, 2, 'Sự phát triển kinh tế xã hội và Châu Á hiện đại', 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
