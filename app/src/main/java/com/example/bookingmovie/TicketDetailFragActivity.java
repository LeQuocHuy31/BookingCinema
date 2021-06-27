package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.model.VeUser;

public class TicketDetailFragActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_ticket_detail_frag);
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
        tvRap=findViewById(R.id.txtCinemaf);
        tvDiaChi=findViewById(R.id.txtAddressf);
        tvtenphim=findViewById(R.id.txtTitlef);
        tvthoigian=findViewById(R.id.txtTimef);
        tvngay=findViewById(R.id.txtDatef);
        tvphong=findViewById(R.id.txtRoomf);
        tvGhe=findViewById(R.id.txtSeatPlacesf);
        tvdichVu=findViewById(R.id.txtCombosf);
        txtCode =findViewById(R.id.txtCodef);
        btnHome=findViewById(R.id.btn_goHomef);


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
