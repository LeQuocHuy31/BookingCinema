package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.ConnectionHelper;
import com.example.bookingmovie.Database.Database;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");                    //
    private TextInputEditText password;
    private TextInputEditText hoten;
    private TextInputEditText email;
    private TextInputEditText sodienthoai;
    private TextInputEditText username;
    private TextInputEditText passwordCF;
    private TextInputLayout hotenl;
    private TextInputLayout emaill;
    private TextInputLayout sodienthoail;
    private TextInputLayout passwordl;
    private TextInputLayout usernamel;
    private TextInputLayout passwordCFl;

    private  Button buttonReg;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        database = new Database();

        password = findViewById(R.id.txt_Reg_Pass);
        hoten = findViewById(R.id.txt_Reg_Name);
        email = findViewById(R.id.txt_Reg_Email);
        sodienthoai = findViewById(R.id.txt_Reg_SDT);
        username = findViewById(R.id.txt_Username);
        passwordCF = findViewById(R.id.txt_Reg_Pass_Cof);
        hotenl = findViewById(R.id.txt_Reg_Name_layout);
        emaill = findViewById(R.id.txt_Reg_Email_layout);
        sodienthoail = findViewById(R.id.txt_Reg_SDT_layout);
        passwordl = findViewById(R.id.txt_Reg_Pass_layout);
        usernamel = findViewById(R.id.txt_Username_layout);
        passwordCFl = findViewById(R.id.txt_Reg_Pass_Cof_layout);

        buttonReg = findViewById(R.id.btn_capnhap);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng kí tài khoản");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInput(v);
                String pass = password.getText().toString();
                String phone = sodienthoai.getText().toString();
                String mail = email.getText().toString();
                String name = hoten.getText().toString();
                String user = username.getText().toString();

                if (validateEmail() == true && validateName() == true && validatePhone() == true && validatePassword() == true && validateConfirm() == true && validateUsername() == true) {
                    int checkuser = database.checkUser(user);
                    int checkmail = database.checkEmail(mail);
                    if (checkuser == 0) {
                        if (checkmail == 0) {
                            database.InsertTaiKhoan(user,name,pass,mail,phone);
                            AlertDialog.Builder b = new AlertDialog.Builder(Registration.this);
                            b.setTitle("Thông báo");
                            b.setMessage("Đăng kí tài khoản mới thành công!!!");
                            b.setPositiveButton("Kết thúc!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(Registration.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                            AlertDialog al = b.create();
                            al.show();
                        } else
                            Toast.makeText(Registration.this, "Email đăng kí đã trùng. Vui lòng email mới", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(Registration.this, "Tên đăng nhập trùng. Vui lòng chọn tên mới", Toast.LENGTH_SHORT).show();
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
    public boolean validateEmail() {

        String emailInput = email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            emaill.setError("Trường email không được để trống");
            return false;
        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emaill.setError("Vui lòng điền đúng email");
            return false;
        } else {
            emaill.setError(null);
            return true;
        }
    }

    public boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            passwordl.setError("Trường mật khẩu không được để trống");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            passwordl.setError("Mật khẩu không đủ mạnh. Mật khẩu phải tối thiểu 8 kí tự, không kí tự trắng, và chứa các kí tự đặc biệt");
            return false;
        } else {
            passwordl.setError(null);
            return true;
        }
    }
    public boolean validateName() {
        String nameInput = hoten.getText().toString();
        if (nameInput.isEmpty()){
            hotenl.setError("Thiếu thông tin họ tên");
            return false;
        }
        return true;
    }
    public boolean validatePhone() {
        String nameInput = sodienthoai.getText().toString();
        if (nameInput.isEmpty()){
            sodienthoail.setError("Thiếu thông tin số điện thoại");
            return false;
        }
        return true;
    }
    public boolean validateUsername() {
        String nameInput = username.getText().toString();
        if (nameInput.isEmpty()){
            usernamel.setError("Thiếu thông tin tên đăng nhập");
            return false;
        }
        return true;
    }
    public boolean validateConfirm() {
        String password1 = password.getText().toString();
        String password2 = passwordCF.getText().toString();
        if (password2.isEmpty()) {
            passwordCFl.setError("Trường mật khẩu không được để trống");
            return false;
        }else
        if (password2.equals(password1)==false){
            passwordCFl.setError("Không trùng với mật khẩu đã nhập");
            return false;
        }
        else
        {
            passwordCFl.setError(null);
            return true;
        }
    }
    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword() | !validateName() | !validatePhone()| !validateUsername()|validateConfirm()) {
            return;
        }
    }
}