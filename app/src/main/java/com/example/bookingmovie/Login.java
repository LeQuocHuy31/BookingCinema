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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.ConnectionHelper;
import com.example.bookingmovie.Database.Database;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    private Button buttonSign;
    private  Button  buttonReg;
    private TextInputEditText textViewUser;
    private  TextInputEditText textViewPass;
    private TextView txForget;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng nhập");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        buttonReg = findViewById(R.id.btn_Reg);
        buttonSign = findViewById(R.id.btn_Sign);
        textViewUser = findViewById(R.id.txt_Email_SDT);
        textViewPass = findViewById(R.id.txt_pass);
        database = new Database();
        txForget = findViewById(R.id.txt_Forget);
        txForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),forgetpass1.class);
                startActivity(intent);
            }
        });
        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = textViewUser.getText().toString();
                String pass = textViewPass.getText().toString();
                if (user.equals("")||pass.equals(""))
                    Toast.makeText(getApplicationContext(),"Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else {
                    int checkuserpass = database.checkLogin(user,pass);
                    if (checkuserpass==1){
                        Toast.makeText(getApplicationContext(),"Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Login","true");
                        editor.putString("username",user);
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Vui lòng kiểm tra lại thông tin đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration = new Intent(getApplicationContext(), Registration.class);
                startActivity(registration);
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