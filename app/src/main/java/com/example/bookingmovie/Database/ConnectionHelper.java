package com.example.bookingmovie.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import com.example.bookingmovie.model.DichVu;
import com.example.bookingmovie.modelshowtime.ThongTinLichChieu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectionHelper {
    private  static String ip="192.168.1.124";
    private static String port="1433";
    private static  String Classes="net.sourceforge.jtds.jdbc.Driver";
    private static String database="cinemaapp";
    private static String username="cinema";
    private static  String password="12345678";
    private static String url="jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    public Connection connectionclass(){
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        try {
            Class.forName(Classes);
            connection= DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

}