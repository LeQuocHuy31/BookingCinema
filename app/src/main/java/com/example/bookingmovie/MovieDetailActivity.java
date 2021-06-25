package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.ConnectionHelper;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.model.Phim;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView MovieThumnailImg;
    private YouTubePlayerView youTubePlayerView;
    private TextView tv_title,tv_description,tv_thoiluong,tv_ngaykhoichieu,tv_theloai,tv_kiemduyet;
    private Button booking;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        booking = (Button) findViewById(R.id.btn_booking);
        iniView();
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DetailShowTime.class);
                intent.putExtra("tenPhim",tv_title.getText());
                startActivity(intent);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
    void iniView(){
        String movieTitle = getIntent().getExtras().getString("title");
        database = new Database();
        Phim phim = database.getChiTietPhim(movieTitle);
        MovieThumnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(phim.getImage()).into(MovieThumnailImg);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        tv_description= findViewById(R.id.detail_movie_desc);
        tv_description.setText(phim.getNoiDung());
        tv_description.setMovementMethod(new ScrollingMovementMethod());
        tv_kiemduyet = findViewById(R.id.txtKiemDuyet);
        tv_kiemduyet.setText(phim.getKiemDuyet());
        tv_thoiluong = findViewById(R.id.txtThoiLuong);
        tv_thoiluong.setText(phim.getThoiLuong());
        tv_theloai = findViewById(R.id.txtTheLoai);
        tv_theloai.setText(phim.getTheLoai());
        tv_ngaykhoichieu = findViewById(R.id.txtKhoiChieu);
        tv_ngaykhoichieu.setText(phim.getNgayKhoiChieu());
        int trangthai = Integer.parseInt(phim.getTinhTrang());
        if (trangthai==0){
            booking.setVisibility(View.INVISIBLE);
        }
        YouTubePlayerView youTubePlayerView = findViewById(R.id.detail_movie_trailer);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = phim.getIdtrailer();
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }
}