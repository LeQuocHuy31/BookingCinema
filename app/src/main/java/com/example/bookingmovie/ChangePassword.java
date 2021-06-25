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

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.model.TaiKhoan;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class ChangePassword extends AppCompatActivity {
    private TextInputEditText txtmatkhau;
    private TextInputEditText txtmatkhaumoi;
    private TextInputEditText txtmatkhaumoi1;
    Database database;
    private TaiKhoan taiKhoan;
    private Button changepass;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");                    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đổi mật khẩu");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        database = new Database();

        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","username");

        taiKhoan = database.getChiTietTaiKhoan(username);

        txtmatkhau = findViewById(R.id.txt_Acc_pass);
        txtmatkhaumoi = findViewById(R.id.txt_Acc_pass_new);
        txtmatkhaumoi1 = findViewById(R.id.txt_Acc_pass_new_cf);

        changepass = findViewById(R.id.btn_Acc_change);

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpass  = txtmatkhaumoi.getText().toString();
                if (validatePassword()==true && validatePasswordNew()==true && validatePasswordNew1()==true){
                    taiKhoan.setPassword(newpass);
                    database.updateTaiKhoan(taiKhoan);
                    AlertDialog.Builder b = new AlertDialog.Builder(ChangePassword.this);
                    b.setTitle("Thông báo");
                    b.setMessage("Đổi mật khẩu thành công!!!");
                    b.setPositiveButton("Kết thúc!", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                }
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
    public boolean validatePassword() {
        String passwordInput = txtmatkhau.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            txtmatkhau.setError("Trường mật khẩu không được để trống");
            return false;
        }
        else if (taiKhoan.getPassword().equals(passwordInput)==false) {
            txtmatkhau.setError("Mật khẩu cũ không chính xác");
            return false;
        } else {
            txtmatkhau.setError(null);
            return true;
        }
    }
    public boolean validatePasswordNew() {
        String passwordInput = txtmatkhaumoi.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            txtmatkhaumoi.setError("Trường mật khẩu không được để trống");
            return false;
        }

        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            txtmatkhaumoi.setError("Mật khẩu không đủ mạnh. Mật khẩu phải tối thiểu 8 kí tự, không kí tự trắng, và chứa các kí tự đặc biệt");
            return false;
        } else {
            txtmatkhaumoi.setError(null);
            return true;
        }
    }
    public boolean validatePasswordNew1() {
        String password1 = txtmatkhaumoi.getText().toString();
        String password2 = txtmatkhaumoi1.getText().toString();
        if (password2.isEmpty()) {
            txtmatkhaumoi1.setError("Trường mật khẩu không được để trống");
            return false;
        }else
        if (password2.equals(password1)==false){
            txtmatkhaumoi1.setError("Không trùng với mật khẩu đã nhập");
            return false;
        }
        else
        {
            txtmatkhaumoi1.setError(null);
            return true;
        }
    }
}