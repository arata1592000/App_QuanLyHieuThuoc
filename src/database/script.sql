USE [QLBT]
GO
/****** Object:  Table [dbo].[BangKetCa]    Script Date: 5/26/2024 11:58:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BangKetCa](
	[maCa] [nvarchar](10) NOT NULL,
	[maNV] [nvarchar](8) NULL,
	[ngayLap] [date] NULL,
	[tienCoTrongCa] [float] NULL,
	[tienMatThuTrongCa] [float] NULL,
	[tienATMThuTrongCa] [float] NULL,
	[tienLayRa] [float] NULL,
 CONSTRAINT [PK_BangKetCa] PRIMARY KEY CLUSTERED 
(
	[maCa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietBangKetCa]    Script Date: 5/26/2024 11:58:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietBangKetCa](
	[maCa] [nvarchar](10) NOT NULL,
	[menhGia] [float] NOT NULL,
	[soLuong] [int] NULL,
 CONSTRAINT [PK_ChiTietBangKetCa] PRIMARY KEY CLUSTERED 
(
	[maCa] ASC,
	[menhGia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 5/26/2024 11:58:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[maHD] [nvarchar](12) NOT NULL,
	[maThuoc] [nvarchar](7) NOT NULL,
	[tenThuoc] [nvarchar](50) NULL,
	[soLuong] [int] NULL,
	[donViTinh] [nvarchar](25) NULL,
	[gia] [float] NULL,
	[thanhPhan] [nvarchar](50) NULL,
	[ngayHetHan] [datetime] NULL,
	[khuyenMai] [float] NULL,
	[tongTien] [float] NULL,
 CONSTRAINT [PK_ChiTietHoaDon] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC,
	[maThuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 5/26/2024 11:58:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHD] [nvarchar](12) NOT NULL,
	[ngayLap] [datetime] NULL,
	[maNV] [nvarchar](8) NOT NULL,
	[maKH] [nvarchar](12) NOT NULL,
	[tongTien] [float] NULL,
	[loaiHD] [nvarchar](30) NULL,
	[maKMHD] [nvarchar](25) NULL,
	[thue] [float] NULL,
	[thanhTien] [float] NULL,
	[phuongThucTT] [nvarchar](15) NULL,
	[tienKhachDua] [float] NULL,
	[tienThua] [float] NULL,
	[ghiChu] [nvarchar](100) NULL,
 CONSTRAINT [PK_HoaDon] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 5/26/2024 11:58:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKH] [nvarchar](12) NOT NULL,
	[hoTen] [nvarchar](50) NULL,
	[soDienThoai] [nvarchar](11) NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[maKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhuyenMai]    Script Date: 5/26/2024 11:58:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuyenMai](
	[maKM] [nvarchar](25) NOT NULL,
	[ngayBatDau] [datetime] NULL,
	[ngayKetThuc] [datetime] NULL,
	[tyleKM] [float] NULL,
	[loaiKM] [nvarchar](30) NOT NULL,
	[giaTriKhuyenMai] [float] NULL,
 CONSTRAINT [PK_KhuyenMai] PRIMARY KEY CLUSTERED 
(
	[maKM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 5/26/2024 11:58:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [nvarchar](8) NOT NULL,
	[hoTen] [nvarchar](50) NULL,
	[gioiTinh] [nvarchar](3) NULL,
	[soDienThoai] [nvarchar](11) NULL,
	[ngaySinh] [datetime] NULL,
	[ngayVaoLam] [datetime] NULL,
	[chucVu] [nvarchar](25) NULL,
	[soCCCD] [nvarchar](12) NULL,
	[diaChi] [nvarchar](50) NULL,
	[trangThai] [nvarchar](50) NULL,
	[anh] [nvarchar](50) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 5/26/2024 11:58:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[tenTaiKhoan] [nvarchar](8) NOT NULL,
	[matKhau] [nvarchar](50) NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[tenTaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Thuoc]    Script Date: 5/26/2024 11:58:40 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Thuoc](
	[maThuoc] [nvarchar](7) NOT NULL,
	[tenThuoc] [nvarchar](50) NULL,
	[ngayNhapVe] [datetime] NULL,
	[ngayHetHan] [datetime] NULL,
	[ngaySanXuat] [datetime] NULL,
	[noiSanXuat] [nvarchar](50) NULL,
	[gia] [float] NULL,
	[donViTinh] [nvarchar](25) NULL,
	[thanhPhan] [nvarchar](100) NULL,
	[soLuong] [int] NULL,
	[maKMSP] [nvarchar](25) NULL,
PRIMARY KEY CLUSTERED 
(
	[maThuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[BangKetCa] ([maCa], [maNV], [ngayLap], [tienCoTrongCa], [tienMatThuTrongCa], [tienATMThuTrongCa], [tienLayRa]) VALUES (N'KC20052401', N'NV241001', CAST(N'2024-05-20' AS Date), 5000000, 124000, 124000, 76000)
INSERT [dbo].[BangKetCa] ([maCa], [maNV], [ngayLap], [tienCoTrongCa], [tienMatThuTrongCa], [tienATMThuTrongCa], [tienLayRa]) VALUES (N'KC20052402', N'NV241001', CAST(N'2024-05-20' AS Date), 2000000, 124000, 124000, 76000)
INSERT [dbo].[BangKetCa] ([maCa], [maNV], [ngayLap], [tienCoTrongCa], [tienMatThuTrongCa], [tienATMThuTrongCa], [tienLayRa]) VALUES (N'KC20052403', N'NV241002', CAST(N'2024-05-20' AS Date), 5000000, 0, 0, 0)
INSERT [dbo].[BangKetCa] ([maCa], [maNV], [ngayLap], [tienCoTrongCa], [tienMatThuTrongCa], [tienATMThuTrongCa], [tienLayRa]) VALUES (N'KC24052401', N'NV241001', CAST(N'2024-05-24' AS Date), 1000000, 699000, 0, 21000)
GO
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052401', 1000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052401', 2000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052401', 5000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052401', 10000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052401', 20000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052401', 50000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052401', 100000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052401', 200000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052401', 500000, 10)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052402', 1000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052402', 2000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052402', 5000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052402', 10000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052402', 20000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052402', 50000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052402', 100000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052402', 200000, 10)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052402', 500000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052403', 1000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052403', 2000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052403', 5000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052403', 10000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052403', 20000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052403', 50000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052403', 100000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052403', 200000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC20052403', 500000, 10)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC24052401', 1000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC24052401', 2000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC24052401', 5000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC24052401', 10000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC24052401', 20000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC24052401', 50000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC24052401', 100000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC24052401', 200000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC24052401', 500000, 2)
GO
INSERT [dbo].[ChiTietHoaDon] ([maHD], [maThuoc], [tenThuoc], [soLuong], [donViTinh], [gia], [thanhPhan], [ngayHetHan], [khuyenMai], [tongTien]) VALUES (N'HD2005240001', N'PRDH002', N'Paradon', 2, N'Hộp', 30000, N'.', CAST(N'2024-05-06T00:00:00.000' AS DateTime), 0, 60000)
INSERT [dbo].[ChiTietHoaDon] ([maHD], [maThuoc], [tenThuoc], [soLuong], [donViTinh], [gia], [thanhPhan], [ngayHetHan], [khuyenMai], [tongTien]) VALUES (N'HD2005240002', N'PRDH002', N'Paradon', 2, N'Hộp', 30000, N'.', CAST(N'2024-05-06T00:00:00.000' AS DateTime), 0, 60000)
INSERT [dbo].[ChiTietHoaDon] ([maHD], [maThuoc], [tenThuoc], [soLuong], [donViTinh], [gia], [thanhPhan], [ngayHetHan], [khuyenMai], [tongTien]) VALUES (N'HD2005240003', N'PRDH003', N'Paradon', 2, N'Hộp', 30000, N'.', CAST(N'2024-05-06T00:00:00.000' AS DateTime), 0, 0)
INSERT [dbo].[ChiTietHoaDon] ([maHD], [maThuoc], [tenThuoc], [soLuong], [donViTinh], [gia], [thanhPhan], [ngayHetHan], [khuyenMai], [tongTien]) VALUES (N'HD2405240001', N'PRDH001', N'Paradon', 2, N'Hộp', 10000, N'.', CAST(N'2024-04-13T00:00:00.000' AS DateTime), 0, 20000)
INSERT [dbo].[ChiTietHoaDon] ([maHD], [maThuoc], [tenThuoc], [soLuong], [donViTinh], [gia], [thanhPhan], [ngayHetHan], [khuyenMai], [tongTien]) VALUES (N'HD2405240001', N'PRDH002', N'Paradon', 10, N'Hộp', 30000, N'.', CAST(N'2024-05-06T00:00:00.000' AS DateTime), 0, 300000)
INSERT [dbo].[ChiTietHoaDon] ([maHD], [maThuoc], [tenThuoc], [soLuong], [donViTinh], [gia], [thanhPhan], [ngayHetHan], [khuyenMai], [tongTien]) VALUES (N'HD2405240001', N'PRDH003', N'Paradon', 10, N'Hộp', 30000, N'.', CAST(N'2024-05-06T00:00:00.000' AS DateTime), 0, 300000)
INSERT [dbo].[ChiTietHoaDon] ([maHD], [maThuoc], [tenThuoc], [soLuong], [donViTinh], [gia], [thanhPhan], [ngayHetHan], [khuyenMai], [tongTien]) VALUES (N'HD2405240002', N'PRDH002', N'Paradon', 2, N'Hộp', 30000, N'.', CAST(N'2024-05-06T00:00:00.000' AS DateTime), 0, 60000)
INSERT [dbo].[ChiTietHoaDon] ([maHD], [maThuoc], [tenThuoc], [soLuong], [donViTinh], [gia], [thanhPhan], [ngayHetHan], [khuyenMai], [tongTien]) VALUES (N'HD2405240003', N'PRDH003', N'Paradon', 2, N'Hộp', 30000, N'.', CAST(N'2024-05-06T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[HoaDon] ([maHD], [ngayLap], [maNV], [maKH], [tongTien], [loaiHD], [maKMHD], [thue], [thanhTien], [phuongThucTT], [tienKhachDua], [tienThua], [ghiChu]) VALUES (N'HD1905240001', CAST(N'2024-05-19T00:00:00.000' AS DateTime), N'NV241001', N'KH2005240001', 60000, N'Bán hàng', N'KM12', 0.30000001192092896, 61200, N'Tiền mặt', 100000, 38000, N'')
INSERT [dbo].[HoaDon] ([maHD], [ngayLap], [maNV], [maKH], [tongTien], [loaiHD], [maKMHD], [thue], [thanhTien], [phuongThucTT], [tienKhachDua], [tienThua], [ghiChu]) VALUES (N'HD2005240001', CAST(N'2024-05-20T00:00:00.000' AS DateTime), N'NV241001', N'KH2005240001', 60000, N'Bán hàng', N'KM12', 0.30000001192092896, 61200, N'Tiền mặt', 100000, 38000, N'')
INSERT [dbo].[HoaDon] ([maHD], [ngayLap], [maNV], [maKH], [tongTien], [loaiHD], [maKMHD], [thue], [thanhTien], [phuongThucTT], [tienKhachDua], [tienThua], [ghiChu]) VALUES (N'HD2005240002', CAST(N'2024-05-20T00:00:00.000' AS DateTime), N'NV241001', N'KH2005240001', 60000, N'Trả thuốc', N'KM12', 0.30000001192092896, 60000, N'Tiền mặt', 0, 0, N'Trả từ HD2005240001, Lý do: Thuốc không cần thiết nữa')
INSERT [dbo].[HoaDon] ([maHD], [ngayLap], [maNV], [maKH], [tongTien], [loaiHD], [maKMHD], [thue], [thanhTien], [phuongThucTT], [tienKhachDua], [tienThua], [ghiChu]) VALUES (N'HD2005240003', CAST(N'2024-05-20T00:00:00.000' AS DateTime), N'NV241001', N'KH2005240001', 0, N'Đổi thuốc', N'KM12', 0, 0, NULL, 0, 0, N'Đổi từ HD2005240001, Lý do: Thuốc bị lỗi do nhà sản xuất')
INSERT [dbo].[HoaDon] ([maHD], [ngayLap], [maNV], [maKH], [tongTien], [loaiHD], [maKMHD], [thue], [thanhTien], [phuongThucTT], [tienKhachDua], [tienThua], [ghiChu]) VALUES (N'HD2005240004', CAST(N'2024-05-20T00:00:00.000' AS DateTime), N'NV241001', N'KH2005240001', 60000, N'Bán hàng', N'KM12', 0.30000001192092896, 61200, N'Tiền mặt', 100000, 38000, N'')
INSERT [dbo].[HoaDon] ([maHD], [ngayLap], [maNV], [maKH], [tongTien], [loaiHD], [maKMHD], [thue], [thanhTien], [phuongThucTT], [tienKhachDua], [tienThua], [ghiChu]) VALUES (N'HD2005240005', CAST(N'2024-05-20T00:00:00.000' AS DateTime), N'NV241001', N'KH2005240001', 60000, N'Bán hàng', N'KM12', 0.30000001192092896, 61200, N'ATM', 62000, 0, N'')
INSERT [dbo].[HoaDon] ([maHD], [ngayLap], [maNV], [maKH], [tongTien], [loaiHD], [maKMHD], [thue], [thanhTien], [phuongThucTT], [tienKhachDua], [tienThua], [ghiChu]) VALUES (N'HD2005240006', CAST(N'2024-05-20T00:00:00.000' AS DateTime), N'NV241001', N'KH2005240001', 60000, N'Bán hàng', N'KM12', 0.30000001192092896, 61200, N'ATM', 62000, 0, N'')
INSERT [dbo].[HoaDon] ([maHD], [ngayLap], [maNV], [maKH], [tongTien], [loaiHD], [maKMHD], [thue], [thanhTien], [phuongThucTT], [tienKhachDua], [tienThua], [ghiChu]) VALUES (N'HD2405240001', CAST(N'2024-05-24T00:00:00.000' AS DateTime), N'NV241001', N'KH1504240001', 620000, N'Bán hàng', N'KM11', 0.30000001192092896, 636740, N'Tiền mặt', 650000, 13000, N'')
INSERT [dbo].[HoaDon] ([maHD], [ngayLap], [maNV], [maKH], [tongTien], [loaiHD], [maKMHD], [thue], [thanhTien], [phuongThucTT], [tienKhachDua], [tienThua], [ghiChu]) VALUES (N'HD2405240002', CAST(N'2024-05-24T00:00:00.000' AS DateTime), N'NV241001', N'KH1504240001', 60000, N'Bán hàng', N'KM12', 0.30000001192092896, 61200, N'Tiền mặt', 70000, 8000, N'')
INSERT [dbo].[HoaDon] ([maHD], [ngayLap], [maNV], [maKH], [tongTien], [loaiHD], [maKMHD], [thue], [thanhTien], [phuongThucTT], [tienKhachDua], [tienThua], [ghiChu]) VALUES (N'HD2405240003', CAST(N'2024-05-24T00:00:00.000' AS DateTime), N'NV241001', N'KH1504240001', 0, N'Đổi thuốc', N'KM11', 0, 0, NULL, 0, 0, N'Đổi từ HD2405240001, Lý do: Thuốc bị lỗi do nhà sản xuất')
GO
INSERT [dbo].[KhachHang] ([maKH], [hoTen], [soDienThoai]) VALUES (N'KH1504240001', N'Thắng', N'0354830957')
INSERT [dbo].[KhachHang] ([maKH], [hoTen], [soDienThoai]) VALUES (N'KH2004240001', N'123', N'123')
INSERT [dbo].[KhachHang] ([maKH], [hoTen], [soDienThoai]) VALUES (N'KH2004240002', N'1', N'1')
INSERT [dbo].[KhachHang] ([maKH], [hoTen], [soDienThoai]) VALUES (N'KH2004240003', N'2', N'2')
INSERT [dbo].[KhachHang] ([maKH], [hoTen], [soDienThoai]) VALUES (N'KH2004240004', N'1', N'22')
INSERT [dbo].[KhachHang] ([maKH], [hoTen], [soDienThoai]) VALUES (N'KH2005240001', N'Ẩn danh', N'Ẩn danh')
GO
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM01', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), 0.5, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM02', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), 1, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM03', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), 2, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM04', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), 3, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM05', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), 1, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM06', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), 1, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM07', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), 1, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM08', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), 1, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM09', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), 1, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM10', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), 1, N'Khuyến mãi trên hóa đơn', 100000)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM11', CAST(N'2024-02-13T00:00:00.000' AS DateTime), CAST(N'2024-07-13T00:00:00.000' AS DateTime), 0.30000001192092896, N'Khuyến mãi trên hóa đơn', 100000)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM12', CAST(N'2021-08-13T00:00:00.000' AS DateTime), CAST(N'2024-12-08T00:00:00.000' AS DateTime), 1, N'Khuyến mãi trên hóa đơn', 50000)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM13', CAST(N'2024-08-13T00:00:00.000' AS DateTime), CAST(N'2024-10-10T00:00:00.000' AS DateTime), 1, N'Khuyến mãi trên hóa đơn', 10000)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM15', CAST(N'2024-05-24T00:00:00.000' AS DateTime), CAST(N'2024-06-24T00:00:00.000' AS DateTime), 1, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM16', CAST(N'2024-05-24T00:00:00.000' AS DateTime), CAST(N'2024-06-24T00:00:00.000' AS DateTime), 1, N'Khuyến mãi trên hóa đơn', 150000)
GO
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [gioiTinh], [soDienThoai], [ngaySinh], [ngayVaoLam], [chucVu], [soCCCD], [diaChi], [trangThai], [anh]) VALUES (N'NV000000', N'ADMIN', N'', N'', CAST(N'2024-04-19T00:00:00.000' AS DateTime), CAST(N'2024-04-19T00:00:00.000' AS DateTime), N'ADMIN', N'', N'', N'Làm Việc', N'images/avatar-default.png')
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [gioiTinh], [soDienThoai], [ngaySinh], [ngayVaoLam], [chucVu], [soCCCD], [diaChi], [trangThai], [anh]) VALUES (N'NV241001', N'123', N'Nam', N'123', CAST(N'2024-04-19T00:00:00.000' AS DateTime), CAST(N'2024-04-19T00:00:00.000' AS DateTime), N'Quản Lý', N'123', N'123', N'Làm Việc', N'images\imagesAvatarNV\NV241001.jpg')
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [gioiTinh], [soDienThoai], [ngaySinh], [ngayVaoLam], [chucVu], [soCCCD], [diaChi], [trangThai], [anh]) VALUES (N'NV241002', N'123', N'Nam', N'123', CAST(N'2024-04-19T00:00:00.000' AS DateTime), CAST(N'2024-04-19T00:00:00.000' AS DateTime), N'Quản Lý', N'123', N'123', N'Làm Việc', N'images\imagesAvatarNV\NV241003.jpg')
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [gioiTinh], [soDienThoai], [ngaySinh], [ngayVaoLam], [chucVu], [soCCCD], [diaChi], [trangThai], [anh]) VALUES (N'NV242001', N'Trương Hải Anh Thắng', N'Nam', N'0354830957', CAST(N'2003-09-15T00:00:00.000' AS DateTime), CAST(N'2024-05-25T00:00:00.000' AS DateTime), N'Nhân Viên', N'054203002218', N'Gò Vấp', N'Làm Việc', N'images\imagesAvatarNV\NV242001.jpg')
GO
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau]) VALUES (N'NV000000', N'+jEAhpy4W5LulqmnmT1xXQ==')
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau]) VALUES (N'NV241001', N'pnRmmeKGpDsGDFPJsudQ1g==')
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau]) VALUES (N'NV241002', N'RYPyCiLdTCGDDwrRh4GWMg==')
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau]) VALUES (N'NV242001', N'eF6H8fhaoBr3qoWwyBpdvQ==')
GO
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'1111', N'1111', CAST(N'2024-05-25T00:00:00.000' AS DateTime), CAST(N'2024-05-25T00:00:00.000' AS DateTime), CAST(N'2024-05-25T00:00:00.000' AS DateTime), N'Việt Nam', 11, N'11', N'111', 1111, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PRD001', N'Paradon', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), N'Việt Nam', 10000, N'Hộp', N'.', 0, N'KM15')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PRDH001', N'Paradon', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), N'Việt Nam', 10000, N'Hộp', N'.', 0, N'KM15')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PRDH002', N'Paradon', CAST(N'2024-05-06T00:00:00.000' AS DateTime), CAST(N'2024-05-06T00:00:00.000' AS DateTime), CAST(N'2024-05-06T00:00:00.000' AS DateTime), N'Việt Nam', 30000, N'Hộp', N'.', 976, N'KM16')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PRDH003', N'Paradon', CAST(N'2024-05-06T00:00:00.000' AS DateTime), CAST(N'2024-10-06T00:00:00.000' AS DateTime), CAST(N'2024-05-06T00:00:00.000' AS DateTime), N'Việt Nam', 30000, N'Hộp', N'.', 974, N'KM16')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PRDV001', N'Paradon', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), N'Việt Nam', 10000, N'Vỉ', N'.', 0, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PRDV002', N'Paradon', CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), CAST(N'2024-04-13T00:00:00.000' AS DateTime), N'Việt Nam', 10000, N'Vỉ', N'.', 5, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PRDV003', N'Paradon', CAST(N'2024-04-14T00:00:00.000' AS DateTime), CAST(N'2024-04-14T00:00:00.000' AS DateTime), CAST(N'2024-04-14T00:00:00.000' AS DateTime), N'Việt Nam', 10000, N'Vỉ', N'.', 10, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PRDV004', N'Paradon', CAST(N'2024-04-14T00:00:00.000' AS DateTime), CAST(N'2024-04-14T00:00:00.000' AS DateTime), CAST(N'2024-04-14T00:00:00.000' AS DateTime), N'Việt Nam', 10000, N'Hộp', N'.', 10, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PRDV005', N'Paradon', CAST(N'2024-04-14T00:00:00.000' AS DateTime), CAST(N'2024-04-14T00:00:00.000' AS DateTime), CAST(N'2024-04-15T00:00:00.000' AS DateTime), N'Việt Nam', 10000, N'Hộp', N'.', 6, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PRDV006', N'Paradon', CAST(N'2024-04-14T00:00:00.000' AS DateTime), CAST(N'2024-04-15T00:00:00.000' AS DateTime), CAST(N'2024-04-14T00:00:00.000' AS DateTime), N'Việt Nam', 10000, N'Vỉ', N'.', 7, NULL)
GO
ALTER TABLE [dbo].[BangKetCa]  WITH CHECK ADD  CONSTRAINT [FK_BangKetCa_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[BangKetCa] CHECK CONSTRAINT [FK_BangKetCa_NhanVien]
GO
ALTER TABLE [dbo].[ChiTietBangKetCa]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietBangKetCa_BangKetCa] FOREIGN KEY([maCa])
REFERENCES [dbo].[BangKetCa] ([maCa])
GO
ALTER TABLE [dbo].[ChiTietBangKetCa] CHECK CONSTRAINT [FK_ChiTietBangKetCa_BangKetCa]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_HoaDon] FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDon] ([maHD])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_HoaDon]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_KhachHang] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_KhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_KhuyenMai] FOREIGN KEY([maKMHD])
REFERENCES [dbo].[KhuyenMai] ([maKM])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_KhuyenMai]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_NhanVien]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_NhanVien] FOREIGN KEY([tenTaiKhoan])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_NhanVien]
GO
ALTER TABLE [dbo].[Thuoc]  WITH CHECK ADD  CONSTRAINT [FK_Thuoc_KhuyenMai] FOREIGN KEY([maKMSP])
REFERENCES [dbo].[KhuyenMai] ([maKM])
GO
ALTER TABLE [dbo].[Thuoc] CHECK CONSTRAINT [FK_Thuoc_KhuyenMai]
GO
