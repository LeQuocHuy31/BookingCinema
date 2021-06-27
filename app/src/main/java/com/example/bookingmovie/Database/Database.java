package com.example.bookingmovie.Database;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import com.example.bookingmovie.model.DichVu;
import com.example.bookingmovie.model.KhuyenMai;
import com.example.bookingmovie.model.LichChieu;
import com.example.bookingmovie.model.Phim;
import com.example.bookingmovie.model.TaiKhoan;
import com.example.bookingmovie.model.Ve;
import com.example.bookingmovie.model.VeUser;
import com.example.bookingmovie.modelshowtime.ThongTinLichChieu;
import com.example.bookingmovie.modelshowtime.XuatChieu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public List<Phim> getPhim(int trangthai){
        List<Phim> phims = new ArrayList<>();
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select * from Phim where tinhtrang= "+trangthai);
                while (resultSet.next()){
                    Phim phim = new Phim (resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10));
                    phims.add(phim);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return  phims;
    }
    public List<KhuyenMai> getKhuyenMai(){
        List<KhuyenMai> khuyenMais = new ArrayList<>();
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select * from KhuyenMai");
                while (resultSet.next()){
                    KhuyenMai khuyenMai = new KhuyenMai(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                    khuyenMais.add(khuyenMai);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return  khuyenMais;
    }
    public Phim getChiTietPhim(String tenPhim){
        List<Phim> phims = new ArrayList<>();
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select * from Phim where tenphim = N'"+tenPhim+"'");
                while (resultSet.next()){
                    Phim phim = new Phim (resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10));
                    phims.add(phim);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        Phim phim = phims.get(0);
        return phim;
    }
    public TaiKhoan getChiTietTaiKhoan(String username){
        List<TaiKhoan> taiKhoans = new ArrayList<>();
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select * from TaiKhoan where username like '"+username+"' or email like '"+username+"'");
                while (resultSet.next()){
                    TaiKhoan taiKhoan = new TaiKhoan(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8));
                    taiKhoans.add(taiKhoan);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        TaiKhoan taiKhoan = taiKhoans.get(0);
        return taiKhoan;
    }
    //Kiểm tra usernme
    public int checkUser(String username2){
        int isUsername = 1;
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select count(*) AS SL from TaiKhoan where username like '%"+username2+"%'");//thế này chắc đc à
                resultSet.next();
                isUsername =  resultSet.getInt(1);
                return  isUsername;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return isUsername;
    }
    public int checkEmail(String email){
        int isUsername = 1;
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select count(*) AS SL from TaiKhoan where email like '%"+email+"%'");
                resultSet.next();
                isUsername =  resultSet.getInt("SL");
                return  isUsername;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return isUsername;
    }
    public int checkLogin(String username,String password){
        int isLogin = 0;
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select count (*) AS SL from TaiKhoan where password like '"+password+"' AND username like '"+username+"'");
                resultSet.next();
                isLogin=resultSet.getInt(1);
                return isLogin;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return isLogin;
    }
    public void InsertTaiKhoan(String username1,String hoten,String password1,String email,String sdt){
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            try {
                String insertString = "Insert Into TaiKhoan Values(?,?,?,?,?,?,?)";
                PreparedStatement insert = connection.prepareStatement(insertString);

                insert.setString(1,username1);
                insert.setString(2,password1);
                insert.setString(3,hoten);
                insert.setString(4,email);
                insert.setString(5,sdt);
                insert.setString(6,"");
                insert.setInt(7,0);

                insert.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public void updateTaiKhoan(TaiKhoan taiKhoan){
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            try {
                String updateString = "Update TaiKhoan set username = ?, password = ?, hoten = ?, email = ?, sdt = ?, avatar = ?, diemtichluy = ? where id = ?";
                PreparedStatement update = connection.prepareStatement(updateString);

                update.setString(1,taiKhoan.getUsername());
                update.setString(2,taiKhoan.getPassword());
                update.setString(3,taiKhoan.getHoTen());
                update.setString(4,taiKhoan.getEmail());
                update.setString(5,taiKhoan.getSdt());
                update.setString(6,taiKhoan.getAvatar());
                update.setInt(7,taiKhoan.getDiemTichLuy());
                update.setInt(8,taiKhoan.getId());
                update.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public List<DichVu> getListDichVu( ) {
        List<DichVu>  List = new ArrayList<>();
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("SELECT * from DichVu");
                while (resultSet.next()){
                    DichVu dichVu= new DichVu(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6));
                    List.add(dichVu);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return List;
    }
    public List<ThongTinLichChieu> getThongTinLichChieu(String tenrap, String ngaychieu){
        List<ThongTinLichChieu>  list = new ArrayList<>();
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("SELECT DISTINCT p.tenphim,p.kiemduyet,p.thoiluong,p.imgphim FROM Phim p, LichChieu lc WHERE lc.idphim=p.id and p.tinhtrang=1 and lc.rapphim like N'%" + tenrap + "%' and lc.ngaychieu like N'%"+ngaychieu+"%'");
                while (resultSet.next()){
                    ThongTinLichChieu thongTinLichChieu= new ThongTinLichChieu(resultSet.getString(4),resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                    list.add(thongTinLichChieu);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    public List<XuatChieu> getXuatChieu2D(String tenPhim, String ngay, String tenRap){
        List<XuatChieu>  listXuaChieu = new ArrayList<>();
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet rs=statement.executeQuery("SELECT lc.thoigian FROM LichChieu lc,Phim p where lc.idPhim = p.id and p.tenphim LIKE N'%" + tenPhim + "%' and lc.ngaychieu LIKE N'%" + ngay +"%' and lc.rapphim LIKE N'%" + tenRap + "%' and lc.dinhdang='2D'");
                while (rs.next()){
                    XuatChieu xuatChieu = new XuatChieu(rs.getString(1));
                    listXuaChieu.add(xuatChieu);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return listXuaChieu;
    }

    public List<XuatChieu> getXuatChieu3D(String tenPhim, String ngay, String tenRap){
        List<XuatChieu>  listXuaChieu = new ArrayList<>();
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet rs=statement.executeQuery("SELECT lc.thoigian FROM LichChieu lc,Phim p where lc.idPhim = p.id and p.tenphim LIKE N'%" + tenPhim + "%' and lc.ngaychieu LIKE N'%" + ngay +"%' and lc.rapphim LIKE N'%" + tenRap + "%' and lc.dinhdang='3D'");
                while (rs.next()){
                    XuatChieu xuatChieu = new XuatChieu(rs.getString(1));
                    listXuaChieu.add(xuatChieu);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return listXuaChieu;
    }

    public List<LichChieu> getLichChieu(String tenPhim, String gioChieu, String ngay, String tenRap){
        List<LichChieu>  listLc = new ArrayList<>();
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                //String sql="SELECT lc.id,p.id,p.tenphim,lc.phong,lc.dinhdang,lc.gia,lc.trangthai,lc.ngaychieu,lc.thoigian FROM LichChieu lc,Phim p where lc.idPhim = p.id and p.tenphim LIKE N'%" + tenPhim + "%' and lc.thoigian LIKE N'%" + gioChieu + "%' and lc.ngaychieu LIKE N'%" + ngay +"%' and lc.rapphim LIKE N'%" + tenRap + "%'";
                ResultSet rs=statement.executeQuery("SELECT lc.id,p.id,p.tenphim,lc.phong,lc.dinhdang,lc.gia,lc.trangthai,lc.ngaychieu,lc.thoigian FROM LichChieu lc,Phim p where lc.idPhim = p.id and p.tenphim LIKE N'%" + tenPhim + "%' and lc.thoigian LIKE N'%" + gioChieu + "%' and lc.ngaychieu LIKE N'%" + ngay +"%' and lc.rapphim LIKE N'%" + tenRap + "%'");
                while (rs.next()){
                    LichChieu lichChieu = new LichChieu(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9));
                    listLc.add(lichChieu);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return listLc;
    }

    public  void updateGhe(String idlichChieu, String listTrangThai){

        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            PreparedStatement statement = null;
            try {
                String sql = "UPDATE LichChieu SET trangthai = ?  WHERE id = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, listTrangThai);
                statement.setString(2, idlichChieu);
                statement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void insertVe(int idkhachhang,int idlichchieu, String vitrighe, String dichVu, String code,String tongtien) {
        ConnectionHelper con = new ConnectionHelper();
        Connection connection = con.connectionclass();
        if (connection != null) {
            PreparedStatement statement = null;
            try {
                String sql = "INSERT INTO Ve Values(?,?,?,?,?,?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, idkhachhang);
                statement.setInt(2, idlichchieu);
                statement.setString(3, vitrighe);
                statement.setString(4, dichVu);
                statement.setString(5, code);
                statement.setString(6, tongtien);
                statement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public VeUser getVeById(String id){
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        VeUser ve= new VeUser();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet rs=statement.executeQuery("Select p.imgphim,p.tenphim,lc.rapphim,lc.ngaychieu,lc.thoigian,lc.phong,v.vitrighe,v.dichvu,v.tongtien,v.mave FROM LichChieu lc,Phim p, Ve v Where v.idlichchieu=lc.id AND lc.idphim= p.id AND v.mave='"+ id+"'");
                rs.next();
                if(rs != null) {
                    ve = new VeUser(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return ve;
    }

    public Ve getVe(String mave){
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        Ve ve= new Ve();
        if(connection!=null){
            Statement statement=null;
            try {
                statement=connection.createStatement();
                ResultSet rs=statement.executeQuery("Select * FROM Ve where mave = "+ mave );
                //while (rs.next()){
                if(rs != null) {
                    ve = new Ve(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
                }
                // }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return ve;
    }


    public List<VeUser> getVeUser(int idUser){
        List<VeUser> veuser = new ArrayList<>();
        ConnectionHelper con= new ConnectionHelper();
        Connection connection=con.connectionclass();
        if(connection!=null){
            Statement statement=null;
            try {
                statement = connection.createStatement();
                ResultSet rs=statement.executeQuery("Select p.imgphim,p.tenphim,lc.rapphim,lc.ngaychieu,lc.thoigian,lc.phong,v.vitrighe,v.dichvu,v.tongtien,v.mave FROM LichChieu lc,Phim p, Ve v Where v.idlichchieu=lc.id AND lc.idphim= p.id AND v.idkhachhang = "+ idUser);
                while (rs.next()){
                    VeUser ve = new VeUser(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
                    veuser.add(ve);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return veuser;
    }
    /*public int tongDoanhThu(int idUser) {
        ConnectionHelper con = new ConnectionHelper();
        Connection connection = con.connectionclass();
        int chitieu = 0;
        if (connection != null) {
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("Select v.tongtien From Ve v, TaiKhoan tk Where v.idkhachhang = tk.id AND tk.id = ? " + idUser);
                while (rs.next()) {
                    String[] tien = rs.getString(1).split(" ?");
                    int bonus = Integer.parseInt(tien[0]);
                    chitieu += bonus;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return chitieu;
    }*/
}