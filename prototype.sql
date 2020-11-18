-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 24, 2020 at 06:35 AM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prototype`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id_client` varchar(45) NOT NULL,
  `company_name` varchar(45) NOT NULL,
  `staff_name` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `addres` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id_client`, `company_name`, `staff_name`, `email`, `phone`, `addres`) VALUES
('CL_001', 'PT. Anugrah Kharisma Jaya', 'Dodo', 'doli607@yahoo.com', '+62 21 5835 5020', 'Sunrise Garden Complex No. 8-C,Jl. Surya Mandala I,Jakarta Barat '),
('CL_002', 'PT. Anugrah Parmindo Lestari', 'Yolland', 'mitu114@yahoo.com', '+62 21 4608 820', ' Industrial Estate Pulogadung,Jl. Pulo Lentut Kav. II-E/4, Pulogadung,Jakarta Timur'),
('CL_003', 'Cibaliung Sumber Daya PT ', 'Angga', ' corsec@antam.com', '(021) 7827020', 'Jl Raya Cilandak KKO\nCilandak Timur, Pasar Minggu'),
('CL_004', 'PT Mega Citra Utama', 'Rudy', 'corsec@mcu.co.id', '021 - 841 5380', 'Ged. Plaza PP Lantai 5\nJl. Let. Jend.Tb. Simatupang No. 57, Pasar Rebo\nJakarta Timur'),
('CL_005', 'PT Indonesia Chemical Alumina', 'Trianto', 'teguh.pratomo@pt-ica.com', '(021) 7801811, 7801786', 'Antam Building 4th Floor\nJl TB Simatupang No 1 Lingkar Selatan, Tanjung Barat,'),
('CL_006', 'PT. Istaka Karya', 'Rino', 'istaka@istaka.co.id', ' 021-7258686', 'Graha Iskandarsyah Lt. 9 Jln. Iskandarsyah Raya no. 66 Kebayoran baru'),
('CL_007', 'PT. Nindya Karya', 'Julian', ' 021-8093276 ', ' 021-8093276 ', ' Jl. Letjend Haryono MT. Kav 22 Jakarta'),
('CL_008', 'PT. PP', 'Anto', 'pp1@pt-pp.com', '021-8403883', 'Plaza PP, Wisma Subiyanto Building\nJl. Letjend. TB Simatupang No. 57 Pasar Rebo'),
('CL_009', 'PT. Virama Karya', 'Cosmas', 'drvirama@cbn.net.id', '021-7397545', 'Jl. Hang Tuah Raya No. 26 Kebayoran Baru Jakarta Selatan '),
('CL_010', 'PT. BHANDA GHARA REKSA', 'Rizaldy', 'sesper@bgrindonesia.com', '+6221 691 6666', 'Jl. Kali Besar Timur No. 57'),
('CL_011', 'PT. Angkasa Pura II', 'Rachadian', 'webmaster@angkasapura2.co.id', '021 – 550 5079', 'Soekarno Hatta International Airport\nJakarta'),
('CL_012', 'PT. Wijaya Karya', 'Wulan', 'adiwijaya@wika.co.id', ' 021-8192808', ' Jl. D. I. Panjaitan Kav. 9 Jakarta');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id_admin` int(11) NOT NULL,
  `username` varchar(35) NOT NULL,
  `password` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id_admin`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `report_teknisi`
--

CREATE TABLE `report_teknisi` (
  `id_report` varchar(34) NOT NULL,
  `date` varchar(200) NOT NULL,
  `id_schedule` varchar(35) NOT NULL,
  `company_name` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `technician` varchar(200) NOT NULL,
  `activity` varchar(50) NOT NULL,
  `error_code` varchar(35) NOT NULL,
  `code_machine` varchar(35) NOT NULL,
  `addres` varchar(200) NOT NULL,
  `information` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `report_teknisi`
--

INSERT INTO `report_teknisi` (`id_report`, `date`, `id_schedule`, `company_name`, `status`, `technician`, `activity`, `error_code`, `code_machine`, `addres`, `information`) VALUES
('REP_001', '2020-08-03', 'SDL_001', 'PT Indonesia Chemical Alumina', 'COMPLETE', 'Iqbal Chandra Dewangga', 'Consumable', 'null', 'MX-112', 'Antam Building 4th Floor Jl TB Simatupang No 1 Lingkar Selatan, Tanjung Barat,', 'aman terkendali'),
('REP_002', '2020-08-04', 'SDL_002', 'PT. Istaka Karya', 'IN PROGRES', 'Fadil Aprilia', 'Installation', 'null', 'AR-113', 'Graha Iskandarsyah Lt. 9 Jln. Iskandarsyah Raya no. 66 Kebayoran baru', 'BELUM BERES'),
('REP_003', '2020-08-22', 'REP_004', 'PT. Anugrah Parmindo Lestari', 'COMPLETE', 'Ridho Satria', 'Service', 'C-51', 'MX-151', ' Industrial Estate Pulogadung,Jl. Pulo Lentut Kav. II-E/4, Pulogadung,Jakarta Timur', 'berhasil lancar');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `id_schedule` varchar(45) NOT NULL,
  `Date_act` varchar(45) NOT NULL,
  `company_name` varchar(45) NOT NULL,
  `activity` varchar(55) NOT NULL,
  `technician_name` varchar(45) NOT NULL,
  `code_machine` varchar(55) NOT NULL,
  `error_code` varchar(45) NOT NULL,
  `information` varchar(200) NOT NULL,
  `addres` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`id_schedule`, `Date_act`, `company_name`, `activity`, `technician_name`, `code_machine`, `error_code`, `information`, `addres`) VALUES
('REP_004', '2020-08-22', 'PT. Anugrah Parmindo Lestari', 'Service', 'Ridho Satria', 'MX-151', 'C-51', 'nggak bisa copy', ' Industrial Estate Pulogadung,Jl. Pulo Lentut Kav. II-E/4, Pulogadung,Jakarta Timur'),
('SDL_001', '2020-08-03', 'PT Indonesia Chemical Alumina', 'Consumable', 'Iqbal Chandra Dewangga', 'MX-112', 'null', 'replace Toner', 'Antam Building 4th Floor Jl TB Simatupang No 1 Lingkar Selatan, Tanjung Barat,'),
('SDL_002', '2020-08-04', 'PT. Istaka Karya', 'Installation', 'Fadil Aprilia', 'AR-113', 'null', 'Mesin Baru', 'Graha Iskandarsyah Lt. 9 Jln. Iskandarsyah Raya no. 66 Kebayoran baru'),
('SDL_003', '2020-08-05', 'PT. Nindya Karya', 'Maintanance', 'Ridho Satria', 'MX-N-223', 'null', 'perawatan bulanan', ' Jl. Letjend Haryono MT. Kav 22 Jakarta');

-- --------------------------------------------------------

--
-- Table structure for table `teknisi`
--

CREATE TABLE `teknisi` (
  `id_teknisi` varchar(35) NOT NULL,
  `nama_teknisi` varchar(45) NOT NULL,
  `religion` varchar(35) NOT NULL,
  `gender` varchar(35) NOT NULL,
  `place_of_bhirt` varchar(35) NOT NULL,
  `date_of_bhirt` varchar(35) NOT NULL,
  `date_of_join` varchar(35) NOT NULL,
  `addres` varchar(200) NOT NULL,
  `phone` varchar(35) NOT NULL,
  `email` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teknisi`
--

INSERT INTO `teknisi` (`id_teknisi`, `nama_teknisi`, `religion`, `gender`, `place_of_bhirt`, `date_of_bhirt`, `date_of_join`, `addres`, `phone`, `email`) VALUES
('TECH_001', 'Iqbal Chandra Dewangga', 'Islam', 'Laki-Laki', 'Jakarta', '1996-05-27', '2016-02-15', 'No.33 Rt.12 Rw.03 Lubang Buaya  Kec.cipayung Jakarta timur', '081210688545', 'iqbalchandra96@gmail.om'),
('TECH_005', 'Fadil Aprilia', 'Islam', 'Laki-Laki', 'Blitar', '2001-04-17', '2018-10-22', 'Cendrawasih Complex Block II-D No. 17-18,Jl. P. Jayakarta No. 141', '081210688545', 'fadil_aprilia17@gmail.com'),
('TECH_006', 'Ridho Satria', 'Islam', 'Laki-Laki', 'Bekasi', '1996-12-14', '2011-07-12', 'Jl. RA. Kartini VII Dalam No. 7', '08126329465', 'r_satria14@gmail.com'),
('TECH_007', 'Ahmad Alfan Huri', 'Islam', 'Laki-Laki', 'Bandung', '1995-06-13', '2018-03-06', 'Jl. P. Jayakarta 24 No. 1\nJakarta Pusat', '086336862', 'ahmadalfan@gmail.com'),
('TECH_008', 'Roberto Carillo', 'Islam', 'Laki-Laki', 'Sdney', '1996-03-10', '2015-01-06', 'Block B No. 14-16,Jl. Karang Anyar Raya No. 55', '061237893', 'r_carillo@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `report_teknisi`
--
ALTER TABLE `report_teknisi`
  ADD PRIMARY KEY (`id_report`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id_schedule`);

--
-- Indexes for table `teknisi`
--
ALTER TABLE `teknisi`
  ADD PRIMARY KEY (`id_teknisi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
