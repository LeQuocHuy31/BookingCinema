create database cinemaapp
use cinemaapp


create table DichVu (
id int IDENTITY(1,1) primary key,
imgDichVu text not null,
tenDichVu text not null,
noiDungDichVu nvarchar(100) not null,
soLuong integer not null,
gia integer not null);

create table TaiKhoan (
id integer IDENTITY(1,1) primary key,
username text not null,
password text not null, 
hoten text not null,
email text not null, 
sdt text not null, 
avatar integer, 
diemtichluy integer not null);
drop table KhuyenMai
create table KhuyenMai (
id integer IDENTITY(1,1) primary key,
tenkhuyenmai nvarchar(100) not null,
noidung nvarchar(2000) not null, 
imgkhuyenmai varchar(200) not null)

create table Phim (
id integer IDENTITY(1,1) primary key,
tenphim nvarchar(50) not null,
noidung nvarchar(1000) not null,
kiemduyet char(5) not null,
ngaykhoichieu char(10) not null, 
theloai nvarchar(20) not null, 
thoiluong char(10) not null,
imgphim varchar(500) not null,
tinhtrang integer not null,
idtrailer varchar(30) not null);

create table LichChieu (
id integer IDENTITY(1,1) primary key,
idphim integer not null,
rapphim nvarchar(50) not null, 
phong integer not null, 
dinhdang char(5) not null, 
thoigian char(5) not null, 
ngaychieu char(10) not null, 
gia integer not null,
trangthai varchar(500) not null,
constraint fk1 foreign key (idphim) references Phim(id));


create table Ve 
(id integer IDENTITY(1,1) primary key, 
idkhachhang integer not null,
idlichchieu integer not null,
vitrighe char(20) not null,
dichvu nvarchar(100) not null,
mave char(8) not null,
tongtien char(10) not null,
constraint fk3 foreign key (idkhachhang) references TaiKhoan(id), 
constraint fk4 foreign key (idlichchieu) references LichChieu(id));

--Thêm dữ liệu Khuyến Mãi
Insert into KhuyenMai Values(N'Thành viên U22',N'Để khuyến khích các bạn học sinh, sinh viên cũng như tạo nhiều cơ hội hơn cho các bạn xem những siêu phẩm điện ảnh đình đám, UIT Cinema chính thức áp dụng chính sách giá vé 45k. Ưu đãi chỉ dành riêng cho các bạn thành viên dưới 22 tuổi thôi đó.  
Cách thức hưởng ưu đãi cực đơn giản:  

Bước 1: Đăng ký tài khoản thành viên UIT Cinema tại www.uit.edu.vn (Bỏ qua bước này nếu bạn đã có tài khoản rồi nhé)  

Bước 2: Đến rạp UIT Cinema gần nhất và xuất trình giấy tờ tuỳ thân chứng minh “Em chưa 22” (CMND hoặc giấy tờ tuỳ thân có ảnh và năm sinh) và đọc thông tin tài khoản đã đăng ký (email hoặc số điện thoại) cho nhân viên để kích hoạt tài khoản Thành Viên U22 nhé ♥️','https://firebasestorage.googleapis.com/v0/b/booking-6561c.appspot.com/o/panel_khuyen_mai.jpg?alt=media&token=30e92324-ac84-4c6e-b4e3-db1ab068cb9a')
Insert into KhuyenMai Values(N'Thanh toán giảm 10% trên Momo',N'Từ giờ đặt vé đã quá dễ dàng cho các tín đồ mê phim khi vừa có thể chọn chỗ ngồi đẹp, lại vừa được hoàn tiền 10% siêu tiết kiệm khi mua vé xem phim UIT Cinema và chọn thanh toán bằng Ví MoMo. Đặc biệt là bạn có thể mua KHÔNG GIỚI HẠN số lượng vé mà vẫn được hoàn tiền 10%.
 Điều kiện chương trình:

 Thời gian chương trình: Từ 04/03/2021 – đến khi có thông báo mới

 Chương trình áp dụng cho tất cả các khách hàng của Ví MoMo và cho tất cả các loại vé của UIT Cinema (không áp dụng cho combo và dịch vụ đi kèm)

 *Ưu đãi áp dụng cho các khách hàng đang liên kết ngân hàng với Ví MoMo.

 *Ưu đãi không được áp dụng đồng thời với ưu đãi hoặc thẻ quà khác.
','https://firebasestorage.googleapis.com/v0/b/booking-6561c.appspot.com/o/panel_khuyen_mai1.jpg?alt=media&token=9713c1f9-8358-417a-8867-a5da6e51b5e1')
Insert into KhuyenMai Values(N'Xem phim rinh quà',N'Lễ này đến UIT Cinema để rinh ngay phần quà hấp dẫn thôi nè. Cụ thể: Tặng 01 sữa rửa mặt Nivea khi mua combo special bất kỳ tại UIT Cinema nhà văn hóa Sinh Viên từ ngày 29.04.2021.
 
 Lưu ý:
 – Số lượng quà tặng có hạn, chương trình sẽ kết thúc khi hết quà tặng.

 – Quà tặng ngẫu nhiên trong các dòng sản phẩm sữa rửa mặt của Nivea.

 – Trong mọi trường hợp, quyết định thuộc về UIT Cinema Cineplex.','https://firebasestorage.googleapis.com/v0/b/booking-6561c.appspot.com/o/panel_khuyen_mai2.png?alt=media&token=b1a614d0-f4dd-4aae-a29e-f0ceba5cd12b')
Insert into KhuyenMai Values(N'Happy Monday',N'Khởi đầu 01 tuần mới thật vui vẻ vì đã có “HAPPY MONDAY” của UIT Cinema

 Với “HAPPY MONDAY”, bạn được gì?

 Xem phim 2D chỉ từ 50,000đ/vé

 Xem phim 3D chỉ từ 80,000đ/vé

 *Giá vé có thể thay đổi theo từng rạp. Xin vui lòng truy cập website mục “Hệ Thống Rạp” để biết giá vé cụ thể.','https://firebasestorage.googleapis.com/v0/b/booking-6561c.appspot.com/o/panel_khuyen_mai3.png?alt=media&token=bbcd1936-78b6-4f6a-b434-256d1f585999')
Insert into KhuyenMai Values(N'Xem phim, nhận 20.000đ từ Grab Moca',N'ƯU ĐÃI SIÊU ĐẶC BIỆT: HOÀN 20.000Đ KHI THANH TOÁN BẰNG VÍ GRAB MOCA TẠI UIT Cinema

Quá đã đúng không cả nhà mình ơi, “quẩy nhẹ” cùng các phim siêu hot trong tháng còn “bỏng ví” khi được hoàn 20.000đ từ Grab Moca.

Quẹo lựa UIT Cinema ngay với chương trình HOÀN TIỀN hấp dẫn nào.

Nội dung chi tiết:

Từ ngày 01.04.2021 khi khách hàng mua vé trực tiếp tại các cụm rạp UIT Cinema trên toàn quốc thanh toán bằng Grab Moca thì sẽ được hoàn 20.000đ (cho hóa đơn bất kỳ từ 70.000đ) vào tài khoản ví của khách.

*Mỗi khách hàng được hưởng ưu đãi hoàn tiền tối đa 2 lần trong suốt thời gian diễn ra chương trình.

Sau khi thanh toán, vào mục Ưu đãi của tôi để lấy mã hoàn tiền về (trong vòng 3 ngày). Xem chi tiết hướng dẫn lấy mã hoàn tiền tại', 'https://firebasestorage.googleapis.com/v0/b/booking-6561c.appspot.com/o/panel_khuyenmai4%20(1).png?alt=media&token=0b1feb80-07f3-48c6-b3b0-c526b709a04b')

--Thêm dữ liệu dịch vụ
insert into DichVu values('https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/coca_bap.jpg?alt=media&token=344dadcc-9097-4b2e-910a-b71b4809aa70','Line 3 single combo - 199.000 đ',N'1 ly Line 3 + 1 bắp ngọt lớn',0,199000);
insert into DichVu values('https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/combo_family.jpg?alt=media&token=a9a8cd9a-c742-429b-912c-2dfca8aa2b17','COMBO FAMILY - 299.000 đ',N'2 Coca lớn + 1 bắp ngọt lớn',0,299000);
insert into DichVu values('https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/single.jpg?alt=media&token=6f4e27cf-bfd4-4241-a002-a75a043a5f71','COMBO SINGLE- 99.000 đ',N'1 Coca lớn + 1 bắp ngọt nhỏ',0,99000);
insert into DichVu values('https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/couple.jpg?alt=media&token=4a7066ef-5a86-4b3c-8c2d-9038db2588b4','COMBO COUPLE - 199.000 đ',N'2 CoCa + 1 bắp ngọt lớn',0,299000);



/*thêm lịch chiếu*/

 insert into Phim values(N'Thiên thần hộ mệnh',N'Cái chết của một cô ca sĩ nổi tiếng dẫn đến sự thành công của một cô ca sĩ trẻ khác. Câu chuyện này có liên quan như thế nào đến sự giúp đỡ của một "thiên thần hộ mệnh"?
','C13','30/4/2021',N'Tâm lý','124 phút', 'https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/tthm.jpg?alt=media&token=dab81be7-b4fa-4ce4-9ec8-cb752796b067',1,'1H-2FeFOM08');
 insert into Phim values(N'Thám tử lừng danh Conan',N'Thế vận hội thể thao lớn nhất thế giới được tổ chức tại Tokyo, Nhật Bản thu hút rất nhiều sự chú ý. Khi sự kiện ra mắt con tàu siêu tốc với tốc độ 1000km/h diễn ra cũng là lúc hàng loạt các vụ bắt cóc xảy ra! Gia tộc hiểm ác tụ tập tại đây sẽ gây ra một loạt sự kiện chấn động thế giới!
','P','23/4/2021',N'Hành động','101 phút','https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/conan.jpg?alt=media&token=e7beef1e-6c77-492e-8ed1-cb4d1a01805a',1,'kOh1OTokLCk');
 insert into Phim values(N'Bàn tay của  quỉ', N'Sau khi bản thân bỗng nhiên sở hữu “Bàn tay diệt quỷ”, võ sĩ MMA Yong Hoo (Park Seo Joon thủ vai) đã dấn thân vào hành trình trừ tà, trục quỷ đối đầu với Giám Mục Bóng Tối (Woo Do Hwan) – tên quỷ Satan đột lốt người. Từ đó sự thật về cái chết của cha Yong Hoo cũng dần được hé lộ cũng như nguyên nhân anh trở thành “người được chọn”.
','C18','23/4/2021',N'Kinh dị','96 phút','https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/bantay.jpg?alt=media&token=51d23d95-173f-459c-8f8f-47bf98b7b810',1,'uqJ9u7GSaYM');
 insert into Phim values(N'Fast and furious 9', N'“Fast and Furious 9: The Fast Saga” hứa hẹn sẽ đem lại nhiều ngạc nhiên cho người hâm mộ về thân thế của nhân vật do John Cena thủ vai, đồng thời cho tay lái lụa Han Lue tái xuất.
','C13','25/4/2021',N'Hành động','113 phút','https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/ff.jpg?alt=media&token=65a66b93-b5f6-4070-bed7-29a919cc2e0e',1,'f02Haxdg7EQ');
 insert into Phim values(N'Lặt mặt 48H',N'Lý Hải trở lại với dòng phim hành động sở trường của mình. Bối cảnh hoành tráng với sự đầu tư nghiêm túc, siêu phẩm hành động Việt Lật Mặt 48h sẽ kể về một hành trình trốn chạy đầy kịch tính, nghẹt thở đến phút cuối cùng.
','C18','16/04/2021',N'Hành động','110 phút','https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/lat_mat.jpg?alt=media&token=43b456c6-d2e2-4db5-a385-8ff6a6bcc09b',1,'ykBfss-8H4Y');
 insert into Phim values(N'Đêm trói buộc',N'‘Đêm Trói Buộc’ kể về câu chuyện của một cặp vợ chồng người Iran (do Shahab Hosseini and Niousha Jafarian thủ vai) và đứa con gái một tuổi phát hiện họ bị nhốt bên trong khách sạn cũ và bị lực lượng ngoại bang cưỡng bức. Song tại đây, họ phải đối mặt với những bí mật mà cả hai đã âm thầm chôn giấu người bạn đời của mình.'
 ,'C18','14/05/2021',N'Kinh dị','120 phút','https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/dem_troi_buoc.jpg?alt=media&token=8d9cdb64-8a40-4376-9562-618d03725344',0,'KzOKHBmHb44');
 insert into Phim values(N'Vùng đất im lặng 2',N'Phần hai tiếp nối các sự kiện xảy ra trong phần một, khi gia đình Abbot gồm người mẹ Evelyn (do Emily Blunt thủ vai) cùng ba con chạy trốn đến một thành phố tưởng như an toàn. Tuy nhiên, cả gia đình không ngờ rằng ở thế giới bên ngoài cũng đã bị những sinh vật ngoài hành tinh thâu tóm. Những sinh vật này khiếm khuyết về thị giác nhưng có thính giác siêu nhạy để săn mồi bằng cách lần theo âm thanh. “Vùng đất câm lặng” lúc này đã trở thành “thế giới câm lặng” khi những người sống sót tiếp tục phải lẩn trốn, không được tạo ra tiếng động mỗi khi di chuyển hay giao tiếp với nhau. Nhưng càng bước ra ngoài thế giới, gia đình Abbot sớm nhận ra rằng hiểm họa duy nhất không chỉ đến từ những sinh vật ngoài hành tinh.'
 ,'C18','28/05/2021',N'Kinh dị','120 phút','https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/vung_dat_cam_lang.jpg?alt=media&token=81fe9890-4a60-49a1-8aa8-0fa4666771a7',0,'eytKQW5kA3Y');
 insert into Phim values(N'Phi công siêu đẳng',N'Bộ phim xoay quanh những cuộc chiến đấu gay cấn trên bầu trời cao của chàng phi công tài ba Maverick (Tom Cruise). Tuy nhiên, người hùng nay còn phải đối mặt với những chiếc máy bay không người lái được điều khiển từ xa.
','C13','02/07/2021',N'Hành đông, phiêu lưu','105 phút','https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/phi_cong_sieu_dang.jpg?alt=media&token=8dc3731d-9375-4360-974a-3e5c0bde8209',0,'jLRyrn2DT38');
  

 insert into Phim values(N'Hố địa ngục',N'Hố khoan Kola Superdeep của Nga được khoan sâu 12.000m vào lòng đất – là cơ sở nghiên cứu bí mật sâu nhất thế giới được ghi lại cho đến nay. Vào năm 1984, các nhà khoa học bắt đầu nghe được những âm thanh lạ vọng từ hố sâu Kola và quyết định đóng cửa cơ sở nghiên cứu này. Một nhóm nghiên cứu đã quyết định thám hiểm hố khoan để tìm ra bí mật mà nơi đây đang ẩn giấu. Tuy nhiên, từng thành viên trong nhóm nghiên cứu đều lần lượt bỏ mạng. Những gì họ tìm thấy kinh khủng hơn bất cứ điều từng được ghi chép lại về chiếc hố tử thần này.
','C18','02/07/2021',N'Hồi hộp, Kinh Dị','105 phút','https://firebasestorage.googleapis.com/v0/b/booking-6561c.appspot.com/o/phim_hodianguc.JPG?alt=media&token=d05fc46c-6b65-48a0-9602-24217a9efb6e',0,'H7yk5qtyHDI');


/*chèn lịch chiếu*/

insert into LichChieu values(1,N'UIT cinema sinh viên',1,'3D','17:00','21/06/2021',55000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(1,N'UIT cinema sinh viên',2,'2D','19:00','21/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(1,N'UIT cinema sinh viên',3,'3D','20:00','22/06/2021',55000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(1,N'UIT cinema sinh viên',2,'2D','21:00','22/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(2,N'UIT cinema sinh viên',1,'2D','17:00','21/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(2,N'UIT cinema sinh viên',2,'2D','20:00','22/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(3,N'UIT cinema sinh viên',3,'2D','13:00','21/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(3,N'UIT cinema sinh viên',3,'2D','17:00','22/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(4,N'UIT cinema sinh viên',2,'2D','18:00','21/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');

insert into LichChieu values(2,N'UIT cinema thủ đức',1,'3D','18:00','22/06/2021',55000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(2,N'UIT cinema thủ đức',2,'3D','19:00','21/06/2021',55000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(2,N'UIT cinema thủ đức',3,'2D','20:00','22/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(2,N'UIT cinema thủ đức',2,'2D','21:00','21/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(4,N'UIT cinema thủ đức',3,'3D','13:00','22/06/2021',55000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(4,N'UIT cinema thủ đức',3,'2D','17:00','21/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(5,N'UIT cinema thủ đức',2,'2D','18:00','22/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');

insert into LichChieu values(1,N'UIT cinema sinh viên',1,'3D','17:00','24/06/2021',55000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(1,N'UIT cinema sinh viên',2,'2D','19:00','23/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(1,N'UIT cinema sinh viên',3,'3D','20:00','24/06/2021',55000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(1,N'UIT cinema sinh viên',2,'2D','21:00','22/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(2,N'UIT cinema thủ đức',1,'2D','17:00','24/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(2,N'UIT cinema sinh viên',2,'2D','20:00','24/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(3,N'UIT cinema thủ đức',3,'2D','13:00','23/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(3,N'UIT cinema sinh viên',3,'2D','17:00','25/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');
insert into LichChieu values(4,N'UIT cinema thủ đức',2,'2D','18:00','26/06/2021',45000,'0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0');


SELECT DISTINCT p.tenphim,p.kiemduyet,p.thoiluong,p.imgphim FROM Phim p, LichChieu lc WHERE lc.idphim=p.id and p.tinhtrang=1 and lc.rapphim ='UIT cinema thủ đức'
SELECT DISTINCT p.tenphim,p.kiemduyet,p.thoiluong,p.imgphim FROM Phim p, LichChieu lc WHERE lc.idphim=p.id and p.tinhtrang=1 and lc.rapphim like N'%uit%'




SELECT lc.id,p.id,p.tenphim,lc.phong,lc.dinhdang,lc.gia,lc.trangthai,lc.ngaychieu,lc.thoigian FROM LichChieu lc,Phim p where lc.idPhim = p.id and p.tenphim LIKE N'%Thiên thần hộ mệnh%' and lc.thoigian LIKE N'%17:00%' and lc.ngaychieu LIKE N'%21/06%' and lc.rapphim LIKE N'%UIT cinema sinh viên%'


select * from phim
update Phim set noidung=N'' where id=1;
select *from DichVu
select *from LichChieu
drop table DichVu
drop table Phim
drop table LichChieu
drop table Ve