USE [QLBT]
GO
/****** Object:  Table [dbo].[BangKetCa]    Script Date: 5/27/2024 12:16:46 PM ******/
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
/****** Object:  Table [dbo].[ChiTietBangKetCa]    Script Date: 5/27/2024 12:16:46 PM ******/
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
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 5/27/2024 12:16:46 PM ******/
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
/****** Object:  Table [dbo].[HoaDon]    Script Date: 5/27/2024 12:16:46 PM ******/
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
/****** Object:  Table [dbo].[KhachHang]    Script Date: 5/27/2024 12:16:46 PM ******/
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
/****** Object:  Table [dbo].[KhuyenMai]    Script Date: 5/27/2024 12:16:46 PM ******/
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
/****** Object:  Table [dbo].[NhanVien]    Script Date: 5/27/2024 12:16:46 PM ******/
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
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 5/27/2024 12:16:46 PM ******/
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
/****** Object:  Table [dbo].[Thuoc]    Script Date: 5/27/2024 12:16:46 PM ******/
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
INSERT [dbo].[BangKetCa] ([maCa], [maNV], [ngayLap], [tienCoTrongCa], [tienMatThuTrongCa], [tienATMThuTrongCa], [tienLayRa]) VALUES (N'KC27052401', N'NV242001', CAST(N'2024-05-27' AS Date), 1000000, 0, 392000, 0)
INSERT [dbo].[BangKetCa] ([maCa], [maNV], [ngayLap], [tienCoTrongCa], [tienMatThuTrongCa], [tienATMThuTrongCa], [tienLayRa]) VALUES (N'KC27052402', N'NV242001', CAST(N'2024-05-27' AS Date), 1000000, 0, 392000, 0)
GO
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052401', 1000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052401', 2000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052401', 5000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052401', 10000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052401', 20000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052401', 50000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052401', 100000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052401', 200000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052401', 500000, 2)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052402', 1000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052402', 2000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052402', 5000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052402', 10000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052402', 20000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052402', 50000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052402', 100000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052402', 200000, 0)
INSERT [dbo].[ChiTietBangKetCa] ([maCa], [menhGia], [soLuong]) VALUES (N'KC27052402', 500000, 2)
GO
INSERT [dbo].[ChiTietHoaDon] ([maHD], [maThuoc], [tenThuoc], [soLuong], [donViTinh], [gia], [thanhPhan], [ngayHetHan], [khuyenMai], [tongTien]) VALUES (N'HD2705240001', N'AMLV001', N'Amlodipine', 10, N'Viên', 17000, N'Amlodipine 5mg', CAST(N'2025-01-20T00:00:00.000' AS DateTime), 8500, 161500)
INSERT [dbo].[ChiTietHoaDon] ([maHD], [maThuoc], [tenThuoc], [soLuong], [donViTinh], [gia], [thanhPhan], [ngayHetHan], [khuyenMai], [tongTien]) VALUES (N'HD2705240001', N'AMOV001', N'Amoxicillin', 10, N'Viên', 15000, N'Amoxicillin 500mg', CAST(N'2025-01-04T00:00:00.000' AS DateTime), 0, 150000)
INSERT [dbo].[ChiTietHoaDon] ([maHD], [maThuoc], [tenThuoc], [soLuong], [donViTinh], [gia], [thanhPhan], [ngayHetHan], [khuyenMai], [tongTien]) VALUES (N'HD2705240001', N'DOXV001', N'Doxycycline', 10, N'Viên', 14000, N'Doxycycline 100mg', CAST(N'2025-01-12T00:00:00.000' AS DateTime), 7000, 133000)
GO
INSERT [dbo].[HoaDon] ([maHD], [ngayLap], [maNV], [maKH], [tongTien], [loaiHD], [maKMHD], [thue], [thanhTien], [phuongThucTT], [tienKhachDua], [tienThua], [ghiChu]) VALUES (N'HD2705240001', CAST(N'2024-05-27T00:00:00.000' AS DateTime), N'NV242001', N'KH2705240001', 444500, N'Bán hàng', N'KM010', 0.30000001192092896, 391160, N'ATM', 392000, 0, N'')
GO
INSERT [dbo].[KhachHang] ([maKH], [hoTen], [soDienThoai]) VALUES (N'KH2005240001', N'Ẩn danh', N'Ẩn danh')
INSERT [dbo].[KhachHang] ([maKH], [hoTen], [soDienThoai]) VALUES (N'KH2705240001', N'Thắng', N'0354830957')
GO
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM001', CAST(N'2024-06-01T00:00:00.000' AS DateTime), CAST(N'2024-06-30T00:00:00.000' AS DateTime), 5, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM002', CAST(N'2024-07-01T00:00:00.000' AS DateTime), CAST(N'2024-07-31T23:59:59.000' AS DateTime), 5, N'Khuyến mãi trên hóa đơn', 200000)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM003', CAST(N'2024-08-01T00:00:00.000' AS DateTime), CAST(N'2024-08-31T00:00:00.000' AS DateTime), 10, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM004', CAST(N'2024-09-01T00:00:00.000' AS DateTime), CAST(N'2024-09-30T23:59:59.000' AS DateTime), 5, N'Khuyến mãi trên hóa đơn', 50000)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM005', CAST(N'2024-10-01T00:00:00.000' AS DateTime), CAST(N'2024-10-31T23:59:59.000' AS DateTime), 10, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM006', CAST(N'2024-11-01T00:00:00.000' AS DateTime), CAST(N'2024-11-30T23:59:59.000' AS DateTime), 5, N'Khuyến mãi trên hóa đơn', 120000)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM007', CAST(N'2024-12-01T00:00:00.000' AS DateTime), CAST(N'2024-12-31T23:59:59.000' AS DateTime), 5, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM008', CAST(N'2025-01-01T00:00:00.000' AS DateTime), CAST(N'2025-01-31T23:59:59.000' AS DateTime), 5, N'Khuyến mãi trên hóa đơn', 3000)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM009', CAST(N'2025-02-01T00:00:00.000' AS DateTime), CAST(N'2025-02-28T23:59:59.000' AS DateTime), 10, N'Khuyến mãi trên sản phẩm', 0)
INSERT [dbo].[KhuyenMai] ([maKM], [ngayBatDau], [ngayKetThuc], [tyleKM], [loaiKM], [giaTriKhuyenMai]) VALUES (N'KM010', CAST(N'2025-03-01T00:00:00.000' AS DateTime), CAST(N'2025-03-31T23:59:59.000' AS DateTime), 15, N'Khuyến mãi trên hóa đơn', 200000)
GO
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [gioiTinh], [soDienThoai], [ngaySinh], [ngayVaoLam], [chucVu], [soCCCD], [diaChi], [trangThai], [anh]) VALUES (N'NV000000', N'ADMIN', N'', N'', CAST(N'2024-04-19T00:00:00.000' AS DateTime), CAST(N'2024-04-19T00:00:00.000' AS DateTime), N'ADMIN', N'', N'', N'Làm Việc', N'images/avatar-default.png')
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [gioiTinh], [soDienThoai], [ngaySinh], [ngayVaoLam], [chucVu], [soCCCD], [diaChi], [trangThai], [anh]) VALUES (N'NV241001', N'Trương Hải Anh Thắng', N'Nam', N'0354830957', CAST(N'2003-09-15T00:00:00.000' AS DateTime), CAST(N'2024-01-01T00:00:00.000' AS DateTime), N'Quản Lý', N'0', N'Gò Vấp', N'Làm Việc', N'images/imagesAvatarNV/NV241001.jpg')
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [gioiTinh], [soDienThoai], [ngaySinh], [ngayVaoLam], [chucVu], [soCCCD], [diaChi], [trangThai], [anh]) VALUES (N'NV241002', N'Nguyễn Hạnh Bảo Ân', N'Nam', N'0379683151', CAST(N'2003-04-05T00:00:00.000' AS DateTime), CAST(N'2024-01-01T00:00:00.000' AS DateTime), N'Quản Lý', N'0', N'Gò Vấp', N'Làm Việc', N'images/imagesAvatarNV/NV241002.jpg')
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [gioiTinh], [soDienThoai], [ngaySinh], [ngayVaoLam], [chucVu], [soCCCD], [diaChi], [trangThai], [anh]) VALUES (N'NV242001', N'Quách Trung Tín', N'Nam', N'0866083740', CAST(N'2003-12-02T00:00:00.000' AS DateTime), CAST(N'2024-01-01T00:00:00.000' AS DateTime), N'Nhân Viên', N'0', N'Sóc Trăng', N'Làm Việc', N'images/imagesAvatarNV/NV242001.jpg')
INSERT [dbo].[NhanVien] ([maNV], [hoTen], [gioiTinh], [soDienThoai], [ngaySinh], [ngayVaoLam], [chucVu], [soCCCD], [diaChi], [trangThai], [anh]) VALUES (N'NV242002', N'Lê Phú Hào', N'Nam', N'0886549799', CAST(N'2003-03-12T00:00:00.000' AS DateTime), CAST(N'2024-01-01T00:00:00.000' AS DateTime), N'Nhân Viên', N'0', N'Củ Chi', N'Làm Việc', N'images/imagesAvatarNV/NV242002.jpg')
GO
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau]) VALUES (N'NV000000', N'+jEAhpy4W5LulqmnmT1xXQ==')
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau]) VALUES (N'NV241001', N'pnRmmeKGpDsGDFPJsudQ1g==')
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau]) VALUES (N'NV241002', N'RYPyCiLdTCGDDwrRh4GWMg==')
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau]) VALUES (N'NV242001', N'eF6H8fhaoBr3qoWwyBpdvQ==')
INSERT [dbo].[TaiKhoan] ([tenTaiKhoan], [matKhau]) VALUES (N'NV242002', N'qqyd45zFx0XEc2ZWtMDTWw==')
GO
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'AMLV001', N'Amlodipine', CAST(N'2024-01-20T00:00:00.000' AS DateTime), CAST(N'2025-01-20T00:00:00.000' AS DateTime), CAST(N'2023-12-20T00:00:00.000' AS DateTime), N'Turkey', 17000, N'Viên', N'Amlodipine 5mg', 280, N'KM001')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'AMLV002', N'Amlodipine', CAST(N'2024-01-20T00:00:00.000' AS DateTime), CAST(N'2025-01-20T00:00:00.000' AS DateTime), CAST(N'2023-12-20T00:00:00.000' AS DateTime), N'Turkey', 17000, N'Viên', N'Amlodipine 5mg', 280, N'KM003')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'AMOV001', N'Amoxicillin', CAST(N'2024-01-04T00:00:00.000' AS DateTime), CAST(N'2025-01-04T00:00:00.000' AS DateTime), CAST(N'2023-12-04T00:00:00.000' AS DateTime), N'India', 15000, N'Viên', N'Amoxicillin 500mg', 240, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'AMOV002', N'Amoxicillin', CAST(N'2024-01-04T00:00:00.000' AS DateTime), CAST(N'2025-01-04T00:00:00.000' AS DateTime), CAST(N'2023-12-04T00:00:00.000' AS DateTime), N'India', 15000, N'Viên', N'Amoxicillin 500mg', 220, N'KM001')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'ASPV001', N'Aspirin', CAST(N'2024-01-02T00:00:00.000' AS DateTime), CAST(N'2025-01-02T00:00:00.000' AS DateTime), CAST(N'2023-12-02T00:00:00.000' AS DateTime), N'USA', 8000, N'Viên', N'Aspirin 100mg', 200, N'KM003')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'ASPV002', N'Aspirin', CAST(N'2024-01-02T00:00:00.000' AS DateTime), CAST(N'2025-01-02T00:00:00.000' AS DateTime), CAST(N'2023-12-02T00:00:00.000' AS DateTime), N'USA', 8000, N'Viên', N'Aspirin 100mg', 150, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'ATOR001', N'Atorvastatin', CAST(N'2024-01-07T00:00:00.000' AS DateTime), CAST(N'2025-01-07T00:00:00.000' AS DateTime), CAST(N'2023-12-07T00:00:00.000' AS DateTime), N'UK', 25000, N'Viên', N'Atorvastatin 10mg', 300, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'ATOR002', N'Atorvastatin', CAST(N'2024-01-07T00:00:00.000' AS DateTime), CAST(N'2025-01-07T00:00:00.000' AS DateTime), CAST(N'2023-12-07T00:00:00.000' AS DateTime), N'UK', 25000, N'Viên', N'Atorvastatin 10mg', 270, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'AZIV001', N'Azithromycin', CAST(N'2024-01-13T00:00:00.000' AS DateTime), CAST(N'2025-01-13T00:00:00.000' AS DateTime), CAST(N'2023-12-13T00:00:00.000' AS DateTime), N'Australia', 28000, N'Viên', N'Azithromycin 250mg', 260, N'KM001')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'AZIV002', N'Azithromycin', CAST(N'2024-01-13T00:00:00.000' AS DateTime), CAST(N'2025-01-13T00:00:00.000' AS DateTime), CAST(N'2023-12-13T00:00:00.000' AS DateTime), N'Australia', 28000, N'Viên', N'Azithromycin 250mg', 240, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'CIPV001', N'Ciprofloxacin', CAST(N'2024-01-05T00:00:00.000' AS DateTime), CAST(N'2025-01-05T00:00:00.000' AS DateTime), CAST(N'2023-12-05T00:00:00.000' AS DateTime), N'France', 20000, N'Viên', N'Ciprofloxacin 250mg', 180, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'CIPV002', N'Ciprofloxacin', CAST(N'2024-01-05T00:00:00.000' AS DateTime), CAST(N'2025-01-05T00:00:00.000' AS DateTime), CAST(N'2023-12-05T00:00:00.000' AS DateTime), N'France', 20000, N'Viên', N'Ciprofloxacin 250mg', 150, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'CLAV001', N'Clarithromycin', CAST(N'2024-01-11T00:00:00.000' AS DateTime), CAST(N'2025-01-11T00:00:00.000' AS DateTime), CAST(N'2023-12-11T00:00:00.000' AS DateTime), N'Mexico', 30000, N'Viên', N'Clarithromycin 500mg', 160, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'CLAV002', N'Clarithromycin', CAST(N'2024-01-11T00:00:00.000' AS DateTime), CAST(N'2025-01-11T00:00:00.000' AS DateTime), CAST(N'2023-12-11T00:00:00.000' AS DateTime), N'Mexico', 30000, N'Viên', N'Clarithromycin 500mg', 140, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'DOXV001', N'Doxycycline', CAST(N'2024-01-12T00:00:00.000' AS DateTime), CAST(N'2025-01-12T00:00:00.000' AS DateTime), CAST(N'2023-12-12T00:00:00.000' AS DateTime), N'Canada', 14000, N'Viên', N'Doxycycline 100mg', 230, N'KM001')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'DOXV002', N'Doxycycline', CAST(N'2024-01-12T00:00:00.000' AS DateTime), CAST(N'2025-01-12T00:00:00.000' AS DateTime), CAST(N'2023-12-12T00:00:00.000' AS DateTime), N'Canada', 14000, N'Viên', N'Doxycycline 100mg', 220, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'FURV001', N'Furosemide', CAST(N'2024-01-14T00:00:00.000' AS DateTime), CAST(N'2025-01-14T00:00:00.000' AS DateTime), CAST(N'2023-12-14T00:00:00.000' AS DateTime), N'Japan', 12000, N'Viên', N'Furosemide 40mg', 230, N'KM003')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'FURV002', N'Furosemide', CAST(N'2024-01-14T00:00:00.000' AS DateTime), CAST(N'2025-01-14T00:00:00.000' AS DateTime), CAST(N'2023-12-14T00:00:00.000' AS DateTime), N'Japan', 12000, N'Viên', N'Furosemide 40mg', 210, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'HYCV001', N'Hydrochlorothiazide', CAST(N'2024-01-17T00:00:00.000' AS DateTime), CAST(N'2025-01-17T00:00:00.000' AS DateTime), CAST(N'2023-12-17T00:00:00.000' AS DateTime), N'South Africa', 11000, N'Viên', N'Hydrochlorothiazide 25mg', 220, N'KM003')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'HYCV002', N'Hydrochlorothiazide', CAST(N'2024-01-17T00:00:00.000' AS DateTime), CAST(N'2025-01-17T00:00:00.000' AS DateTime), CAST(N'2023-12-17T00:00:00.000' AS DateTime), N'South Africa', 11000, N'Viên', N'Hydrochlorothiazide 25mg', 200, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'IBUV001', N'Ibuprofen', CAST(N'2024-01-03T00:00:00.000' AS DateTime), CAST(N'2025-01-03T00:00:00.000' AS DateTime), CAST(N'2023-12-03T00:00:00.000' AS DateTime), N'Germany', 12000, N'Viên', N'Ibuprofen 200mg', 150, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'IBUV002', N'Ibuprofen', CAST(N'2024-01-03T00:00:00.000' AS DateTime), CAST(N'2025-01-03T00:00:00.000' AS DateTime), CAST(N'2023-12-03T00:00:00.000' AS DateTime), N'Germany', 12000, N'Viên', N'Ibuprofen 200mg', 140, N'KM003')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'LEVV001', N'Levothyroxine', CAST(N'2024-01-19T00:00:00.000' AS DateTime), CAST(N'2025-01-19T00:00:00.000' AS DateTime), CAST(N'2023-12-19T00:00:00.000' AS DateTime), N'Egypt', 15000, N'Viên', N'Levothyroxine 50mcg', 250, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'LEVV002', N'Levothyroxine', CAST(N'2024-01-19T00:00:00.000' AS DateTime), CAST(N'2025-01-19T00:00:00.000' AS DateTime), CAST(N'2023-12-19T00:00:00.000' AS DateTime), N'Egypt', 15000, N'Viên', N'Levothyroxine 50mcg', 230, N'KM003')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'LISV001', N'Lisinopril', CAST(N'2024-01-16T00:00:00.000' AS DateTime), CAST(N'2025-01-16T00:00:00.000' AS DateTime), CAST(N'2023-12-16T00:00:00.000' AS DateTime), N'Russia', 13000, N'Viên', N'Lisinopril 10mg', 180, N'KM003')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'LISV002', N'Lisinopril', CAST(N'2024-01-16T00:00:00.000' AS DateTime), CAST(N'2025-01-16T00:00:00.000' AS DateTime), CAST(N'2023-12-16T00:00:00.000' AS DateTime), N'Russia', 13000, N'Viên', N'Lisinopril 10mg', 160, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'LOSV001', N'Losartan', CAST(N'2024-01-18T00:00:00.000' AS DateTime), CAST(N'2025-01-18T00:00:00.000' AS DateTime), CAST(N'2023-12-18T00:00:00.000' AS DateTime), N'Argentina', 21000, N'Viên', N'Losartan 50mg', 200, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'LOSV002', N'Losartan', CAST(N'2024-01-18T00:00:00.000' AS DateTime), CAST(N'2025-01-18T00:00:00.000' AS DateTime), CAST(N'2023-12-18T00:00:00.000' AS DateTime), N'Argentina', 21000, N'Viên', N'Losartan 50mg', 190, N'KM001')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'METV001', N'Metformin', CAST(N'2024-01-06T00:00:00.000' AS DateTime), CAST(N'2025-01-06T00:00:00.000' AS DateTime), CAST(N'2023-12-06T00:00:00.000' AS DateTime), N'China', 10000, N'Viên', N'Metformin 500mg', 220, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'METV002', N'Metformin', CAST(N'2024-01-06T00:00:00.000' AS DateTime), CAST(N'2025-01-06T00:00:00.000' AS DateTime), CAST(N'2023-12-06T00:00:00.000' AS DateTime), N'China', 10000, N'Viên', N'Metformin 500mg', 200, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'OMEV001', N'Omeprazole', CAST(N'2024-01-08T00:00:00.000' AS DateTime), CAST(N'2025-01-08T00:00:00.000' AS DateTime), CAST(N'2023-12-08T00:00:00.000' AS DateTime), N'Italy', 18000, N'Viên', N'Omeprazole 20mg', 170, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'OMEV002', N'Omeprazole', CAST(N'2024-01-08T00:00:00.000' AS DateTime), CAST(N'2025-01-08T00:00:00.000' AS DateTime), CAST(N'2023-12-08T00:00:00.000' AS DateTime), N'Italy', 18000, N'Viên', N'Omeprazole 20mg', 150, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PANV001', N'Pantoprazole', CAST(N'2024-01-15T00:00:00.000' AS DateTime), CAST(N'2025-01-15T00:00:00.000' AS DateTime), CAST(N'2023-12-15T00:00:00.000' AS DateTime), N'Korea', 19000, N'Viên', N'Pantoprazole 40mg', 200, N'KM001')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PANV002', N'Pantoprazole', CAST(N'2024-01-15T00:00:00.000' AS DateTime), CAST(N'2025-01-15T00:00:00.000' AS DateTime), CAST(N'2023-12-15T00:00:00.000' AS DateTime), N'Korea', 19000, N'Viên', N'Pantoprazole 40mg', 180, N'KM001')
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PARV001', N'Paracetamol', CAST(N'2024-01-01T00:00:00.000' AS DateTime), CAST(N'2025-01-01T00:00:00.000' AS DateTime), CAST(N'2023-12-01T00:00:00.000' AS DateTime), N'Vietnam', 5000, N'Viên', N'Paracetamol 500mg', 100, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'PARV002', N'Paracetamol', CAST(N'2024-01-01T00:00:00.000' AS DateTime), CAST(N'2025-01-01T00:00:00.000' AS DateTime), CAST(N'2023-12-01T00:00:00.000' AS DateTime), N'Vietnam', 5000, N'Viên', N'Paracetamol 500mg', 80, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'SIMV001', N'Simvastatin', CAST(N'2024-01-09T00:00:00.000' AS DateTime), CAST(N'2025-01-09T00:00:00.000' AS DateTime), CAST(N'2023-12-09T00:00:00.000' AS DateTime), N'Spain', 22000, N'Viên', N'Simvastatin 20mg', 190, NULL)
INSERT [dbo].[Thuoc] ([maThuoc], [tenThuoc], [ngayNhapVe], [ngayHetHan], [ngaySanXuat], [noiSanXuat], [gia], [donViTinh], [thanhPhan], [soLuong], [maKMSP]) VALUES (N'SIMV002', N'Simvastatin', CAST(N'2024-01-09T00:00:00.000' AS DateTime), CAST(N'2025-01-09T00:00:00.000' AS DateTime), CAST(N'2023-12-09T00:00:00.000' AS DateTime), N'Spain', 22000, N'Viên', N'Simvastatin 20mg', 170, NULL)
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
