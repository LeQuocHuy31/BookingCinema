package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.adapter.ChonDoUongAdapter;
import com.example.bookingmovie.model.DichVu;

import java.util.ArrayList;
import java.util.List;

public class Service extends AppCompatActivity {
    private RecyclerView rcvKhuyenMai;
    private TextView tvPrice;
    private TextView tvPhim;
    ChonDoUongAdapter chonDoUongAdapter;
    Database connectdata;
    private Button btnDatVe;
    int tong;
    private List<DichVu> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        rcvKhuyenMai = findViewById(R.id.rcv_khuyenMai);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dịch vụ");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        tvPrice = findViewById(R.id.price);
        tvPhim = findViewById(R.id.tv_phim);
        SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String tenPhim = sharedPref.getString("tenphim", "");
        tvPhim.setText(tenPhim);
        chonDoUongAdapter = new ChonDoUongAdapter(this);
        chonDoUongAdapter.setOnSelectedChangedListener(this::setOnSelectedChanged);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvKhuyenMai.setLayoutManager(linearLayoutManager);
        connectdata = new Database();
        //chonDoUongAdapter.setData(getListThucUong());
        list = connectdata.getListDichVu();
        chonDoUongAdapter.setData(list);
        rcvKhuyenMai.setAdapter(chonDoUongAdapter);
        Intent intent = getIntent();
        tong = intent.getIntExtra("total", 0);
        tvPrice.setText(String.valueOf(tong) + " ₫");
        //datVe();
        btnDatVe = findViewById(R.id.btn_TTDV);
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                //editor.putString("trangthaighe", strRePlace);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("tongtien", String.valueOf(tvPrice.getText()));
                editor.putString("dichvu", tenDichVu);
                editor.commit();
                Intent iVe = new Intent(Service.this, Payment.class);
                startActivity(iVe);
            }
        });
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

    public void datVe() {
        btnDatVe = findViewById(R.id.button);
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iVe = new Intent(Service.this, Payment.class);
                startActivity(iVe);
            }
        });
    }

    String tenDichVu = "";
    private List<Integer> mSelects = new ArrayList<>();

    public void setOnSelectedChanged(List<Integer> selects) {
        int total = 0;
        String dichVu = "";
        mSelects.clear();
        mSelects.addAll(selects);
        int tongthucuong = 0;
        int kthu = 0;
        for (int select : selects) {
            tongthucuong = list.get(select).getSoluong() * list.get(select).getGia();
            total = total + tongthucuong;
            dichVu = dichVu + list.get(select).getSoluong() + " " + list.get(select).getTenDichVu();
        }
        total = total + tong;
        tenDichVu = dichVu;
        tvPrice.setText(String.valueOf(total) + " ₫");
    }
}


