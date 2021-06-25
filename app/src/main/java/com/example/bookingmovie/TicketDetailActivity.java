package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.model.TaiKhoan;
import com.example.bookingmovie.model.Ve;
import com.example.bookingmovie.model.VeUser;

import java.util.Random;

public class TicketDetailActivity extends AppCompatActivity {
    private TextView tvtenphim;
    private TextView tvthoigian;
    private TextView tvRap;
    private TextView tvngay;
    private TextView tvphong;
    private TextView tvGhe;
    private TextView tvdichVu;
    private TextView tvDiaChi;
    Database bookingDatabase;
    private TextView txtCode;
    private Button btnHome;
    //private BookingCinemaDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vé của bạn");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        bookingDatabase= new Database();
        AnhXa();
        Intent intent = getIntent();
        String mave = intent.getStringExtra("mave");
        VeUser detailve= bookingDatabase.getVeById(mave);
        tvtenphim.setText(detailve.getTenphim());
        tvRap.setText(detailve.getRap());
        //lấy đại chỉ của rạp
        /*if(detailve.getRap().equals("UIT cinema sinh viên")){
            tvDiaChi.setText(R.string.diachi1);
        }
        else {
            tvDiaChi.setText(R.string.diachi2);
        }*/
        tvphong.setText(detailve.getPhong());
        tvGhe.setText(detailve.getVitrighe());
        tvthoigian.setText(detailve.getThoigian());
        tvngay.setText(detailve.getNgaychieu());
        tvdichVu.setText(detailve.getDichvu());
        txtCode.setText(mave);
        // database.close();
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private  void AnhXa(){
        tvRap=findViewById(R.id.txtCinema);
        tvDiaChi=findViewById(R.id.txtAddress);
        tvtenphim=findViewById(R.id.txtTitle);
        tvthoigian=findViewById(R.id.txtTime);
        tvngay=findViewById(R.id.txtDate);
        tvphong=findViewById(R.id.txtRoom);
        tvGhe=findViewById(R.id.txtSeatPlaces);
        tvdichVu=findViewById(R.id.txtCombos);
        txtCode =findViewById(R.id.txtCode);
        btnHome=findViewById(R.id.btn_goHome);


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