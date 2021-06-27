package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.adapter.AdapterXuatChieu;
import com.example.bookingmovie.adapter.NgayChieuAdapter;
import com.example.bookingmovie.modelshowtime.NgayChieu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailShowTime extends AppCompatActivity {
    private RecyclerView rcvXuatChieu;
    private RecyclerView rcvXuatChieu3D;
    private TextView tvTenPhim;
    private TextView tvDinhdang2D;
    private  TextView tvDinhdang3D;
    private TextView tvKhongxuatchieu;
    private  Spinner spnRapPhim;
    private AdapterXuatChieu adapterXuatChieu;
    private AdapterXuatChieu adapterXuatChieu3D;
    private RecyclerView rcvNgayChieu;
    private NgayChieuAdapter ngayChieuAdapter;
    private Database bookingDatabase;
    String rapphim="";
    String ngaychieu="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show_time);

        rcvNgayChieu = findViewById(R.id.rcv_ngaychieu);
        ngayChieuAdapter = new NgayChieuAdapter(this);
        ngayChieuAdapter.setOnSelectedChangedListener(this::setOnSelectedChanged);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        rcvNgayChieu.setLayoutManager(gridLayoutManager);
        ngayChieuAdapter.setData(getListNgayChieu());
        rcvNgayChieu.setAdapter(ngayChieuAdapter);
        tvTenPhim = findViewById(R.id.tv_tphim);
        Intent intent = getIntent();
        String tenPhim = intent.getStringExtra("tenPhim");
        //  String ngaychieu=intent.getStringExtra("ngaychieu");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chọn suất chiếu");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        tvTenPhim.setText(tenPhim);
        //lưu tên phim đã chọn vào trong sharedPref
        SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("tenphim", tenPhim);
        editor.commit();
        //Lấy tên rạp phim
        rapphim= sharedPref.getString("rapphim","");
        spnRapPhim=findViewById(R.id.spn_rapphim);
        if(rapphim.equals("UIT cinema Sinh Viên")) {
            String[] RapPhim = {"UIT cinema Sinh Viên", "UIT cinema Thủ Đức"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, RapPhim);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnRapPhim.setAdapter(adapter);
        }
        else{
            String[] RapPhim = {"UIT cinema Thủ Đức", "UIT cinema Sinh Viên"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, RapPhim);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnRapPhim.setAdapter(adapter);
        }
        spnRapPhim.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rapphim =spnRapPhim.getSelectedItem().toString();
                setDsXuatChieu(String.valueOf(tvTenPhim.getText()),ngaychieu,rapphim);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("rapphim", rapphim);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // database.close();
    }

    private void setNgayChieuRecycler() {
        rcvNgayChieu = findViewById(R.id.rcv_ngaychieu);
        ngayChieuAdapter = new NgayChieuAdapter(this);
        ngayChieuAdapter.setOnSelectedChangedListener(this::setOnSelectedChanged);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        rcvNgayChieu.setLayoutManager(gridLayoutManager);
        ngayChieuAdapter.setData(getListNgayChieu());
        rcvNgayChieu.setAdapter(ngayChieuAdapter);
    }
    List<NgayChieu> ls = new ArrayList<>();
    // ArrayList<Date> mDateArray;
    private List<NgayChieu> getListNgayChieu() {
        Calendar calendar = Calendar.getInstance();
        // mDateArray = new ArrayList<>();
        //ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Date date = calendar.getTime();
            int day = date.getDay();
            String tenThu="";
            if(i==0){
                tenThu="H nay";
            }
            else{
                tenThu=getThu(day);
            }
            String str = DateFormat.format("dd/MM", date).toString();
            ls.add(new NgayChieu(tenThu, str));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return ls;

    }


    private String getThu(int day) {
        switch (day) {
            case 1: {
                return "Thứ 2";
            }

            case 2: {
                return "Thứ 3";
            }
            case 3: {
                return "Thứ 4";
            }
            case 4: {
                return "Thứ 5";
            }
            case 5: {
                return "Thứ 6";
            }
            case 6: {
                return "Thứ 7";
            }
            case 0: {
                return "CN";
            }
        }
        return "";
    }
    public void setDsXuatChieu(String tPhim,String ngay,String rap){
        rcvXuatChieu=findViewById(R.id.rcv_xuatChieu);
        tvDinhdang2D=findViewById(R.id.tv_dinhdang);
        rcvXuatChieu3D=findViewById(R.id.rcv_xuatChieu3D);
        tvDinhdang3D=findViewById(R.id.tv_dinhdang3D);
        tvKhongxuatchieu=findViewById(R.id.tv_khongxuatchieu);
        adapterXuatChieu =new AdapterXuatChieu(this);
        GridLayoutManager gridLayoutManagerxc= new GridLayoutManager(this,5);
        rcvXuatChieu.setLayoutManager(gridLayoutManagerxc);
        //xuat chieu 3d
        adapterXuatChieu3D= new AdapterXuatChieu(this);
        GridLayoutManager gridLayoutManagerxc3D= new GridLayoutManager(this,5);
        rcvXuatChieu3D.setLayoutManager(gridLayoutManagerxc3D);
        bookingDatabase= new Database();

        if (bookingDatabase.getXuatChieu2D(tPhim,ngay,rap).size()==0 && bookingDatabase.getXuatChieu3D(tPhim,ngay,rap).size()==0){
            tvDinhdang2D.setVisibility(View.INVISIBLE);
            tvDinhdang3D.setVisibility(View.INVISIBLE);
            tvKhongxuatchieu.setVisibility(View.VISIBLE);
        }
        else {
            tvDinhdang2D.setVisibility(View.VISIBLE);
            tvDinhdang3D.setVisibility(View.VISIBLE);
            tvKhongxuatchieu.setVisibility(View.INVISIBLE);
        }
        adapterXuatChieu.setData(bookingDatabase.getXuatChieu2D(tPhim,ngay,rap));
        rcvXuatChieu.setAdapter(adapterXuatChieu);
        adapterXuatChieu3D.setData(bookingDatabase.getXuatChieu3D(tPhim,ngay,rap));
        rcvXuatChieu3D.setAdapter(adapterXuatChieu3D);
    }

    public void setOnSelectedChanged(List<Integer> selects) {
        for(int select: selects) {
            setDsXuatChieu(String.valueOf(tvTenPhim.getText()),ls.get(select).getNgay(),rapphim);
            ngaychieu=ls.get(select).getNgay();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}