package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.model.TaiKhoan;
import com.google.android.material.textfield.TextInputEditText;

public class AccountInfo extends AppCompatActivity {
    private TextInputEditText hoten;
    private TextInputEditText email;
    private TextInputEditText sdt;
    private TextInputEditText username;
    Button update;
    Database database;
    TaiKhoan taiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin tài khoản");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        database = new Database();

        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("username","Tên đăng nhập");

        taiKhoan = database.getChiTietTaiKhoan(user);

        hoten = findViewById(R.id.txt_Acc_name);
        email = findViewById(R.id.txt_Acc_email);
        sdt = findViewById(R.id.txt_Acc_sdt);
        username = findViewById(R.id.txt_Acc_username);

        hoten.setText(taiKhoan.getHoTen());
        email.setText(taiKhoan.getEmail());
        sdt.setText(taiKhoan.getSdt());
        username.setText(taiKhoan.getUsername());

        update =findViewById(R.id.btn_capnhap);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = hoten.getText().toString();
                String soDT = sdt.getText().toString();
                String mail = email.getText().toString();
                taiKhoan.setHoTen(name);
                taiKhoan.setSdt(soDT);
                taiKhoan.setEmail(mail);
                database.updateTaiKhoan(taiKhoan);
                AlertDialog.Builder b = new AlertDialog.Builder(AccountInfo.this);
                b.setTitle("Thông báo");
                b.setMessage("Cập nhập thành công!!!");
                b.setPositiveButton("Kết thúc!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(AccountInfo.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                AlertDialog al = b.create();
                al.show();
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
}