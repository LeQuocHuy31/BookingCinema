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
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.adapter.PhuongThucThanhToanAdapter;
import com.example.bookingmovie.model.TaiKhoan;
import com.example.bookingmovie.model.ThanhToan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Payment extends AppCompatActivity {
    private Button btnThanhToan;
    private TextView tvPhim;
    private TextView tvRap;
    private TextView tvPhong;
    private TextView tvGhe;
    private TextView tvNgayChieu;
    private TextView tvGio;
    private TextView tvDichVu;
    private TextView tvtongTien;
    private RecyclerView rcvPhuongThuc;
    Database bookingDatabase;
    private int idlichChieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thanh toán");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        bookingDatabase = new Database();
        AnhXa();
        SetThongTinVe();
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ma = MaXacNhan();
                SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "username");
                TaiKhoan taiKhoan = bookingDatabase.getChiTietTaiKhoan(username);
                int diemtichluy = taiKhoan.getDiemTichLuy();

                bookingDatabase.insertVe(taiKhoan.getId(), idlichChieu, tvGhe.getText().toString(), tvDichVu.getText().toString(), ma, tvtongTien.getText().toString());

                String[] tien = tvtongTien.getText().toString().split(" ₫");
                int bonus = Integer.parseInt(tien[0]);
                taiKhoan.setDiemTichLuy(diemtichluy + bonus / 1000);

                bookingDatabase.updateTaiKhoan(taiKhoan);

                updateTrangThaiGhe();

                Intent i = new Intent(Payment.this, TicketDetailActivity.class);
                i.putExtra("mave", ma);
                startActivity(i);
            }
        });
        setListPhuongThuc();
    }

    private void AnhXa() {
        tvPhim = findViewById(R.id.phim);
        tvRap = findViewById(R.id.rapPhim);
        tvPhong = findViewById(R.id.phong);
        tvGhe = findViewById(R.id.ghe);
        tvNgayChieu = findViewById(R.id.ngay);
        tvDichVu = findViewById(R.id.thucUong);
        tvGio = findViewById(R.id.gio);
        tvtongTien = findViewById(R.id.tongTien);
        btnThanhToan = findViewById(R.id.button);
    }

    private void SetThongTinVe() {
        SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String tenPhim = sharedPref.getString("tenphim", "");
        int phong = sharedPref.getInt("phong", 0);
        String rap = sharedPref.getString("rapphim", "");
        String ghe = sharedPref.getString("ghe", "");
        String ngay = sharedPref.getString("ngay", "");
        String gio = sharedPref.getString("gio", "");
        String thucuong = sharedPref.getString("dichvu", "");
        String tongtien = sharedPref.getString("tongtien", "");
        String lc = sharedPref.getString("idLichChieu", "");
        tvDichVu.setMovementMethod(new ScrollingMovementMethod());
        idlichChieu = Integer.parseInt(lc);
        tvPhim.setText(tenPhim);
        tvRap.setText(rap);
        tvPhong.setText(String.valueOf(phong));
        tvGhe.setText(ghe);
        tvGio.setText(gio);
        tvNgayChieu.setText(ngay);
        tvDichVu.setText(thucuong);
        tvtongTien.setText(tongtien);
    }

    private void updateTrangThaiGhe() {
        SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String id = sharedPref.getString("idLichChieu", "");
        String trangthai = sharedPref.getString("trangthaighe", "");
        bookingDatabase.updateGhe(id, trangthai);

    }

    private void setListPhuongThuc() {
        rcvPhuongThuc = findViewById(R.id.rcv_phuongThuc);
        PhuongThucThanhToanAdapter phuongThucThanhToanAdapter = new PhuongThucThanhToanAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvPhuongThuc.setLayoutManager(linearLayoutManager);
        phuongThucThanhToanAdapter.setData(getListThanhToan());
        rcvPhuongThuc.setAdapter(phuongThucThanhToanAdapter);
    }

    private List<ThanhToan> getListThanhToan() {
        List<ThanhToan> list = new ArrayList<>();
        list.add(new ThanhToan(R.drawable.logo_momo, "Ví MoMo"));
        list.add(new ThanhToan(R.drawable.visa, "Thẻ quốc tế (Visa, Master)"));
        list.add(new ThanhToan(R.drawable.atm, "Thẻ ngân hàng ATM"));
        return list;
    }

    private String chuoi = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

    private String MaXacNhan() {
        String ma = "";
        for (int i = 0; i < 6; i++) {
            Random random = new Random();
            int x = random.nextInt(35);
            ma = ma + chuoi.charAt(x);
        }
        return ma;
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
