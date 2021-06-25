package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private  Toolbar toolbar;
    private long pressedTime;
    private BookingCinemaDatabase database;
    String islogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }
        getSupportActionBar().setTitle("Trang chủ");
        View headerView = navigationView.getHeaderView(0);

        SharedPreferences sharedPreferences = getSharedPreferences("Login",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","Tên đăng nhập");
        islogin = sharedPreferences.getString("Login","false");
        Menu nav_Menu = navigationView.getMenu();
        TextView navName = headerView.findViewById(R.id.nav_name);
        ImageView navImg = headerView.findViewById(R.id.nav_img);
        if (islogin.equals("true")){
            nav_Menu.findItem(R.id.login).setVisible(false);
            navName.setText(username);
        }else
        {
            navName.setText("Tên đăng nhập");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.ticket:
                if (islogin.equals("true")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Ticketfragment()).commit();
                    getSupportActionBar().setTitle("Vé");
                }
                else {
                        Intent intent = new Intent(this,Login.class);
                        startActivity(intent);
                }
        }
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                getSupportActionBar().setTitle("Trang chủ");
                break;
            case R.id.showtime:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Showtimefragment()).commit();
                getSupportActionBar().setTitle("Lịch chiếu");
                break;
            case R.id.movie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Moviefragment()).commit();
                getSupportActionBar().setTitle("Phim");
                break;
            case R.id.store:
                if (islogin.equals("true")){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Ticketfragment()).commit();
                    getSupportActionBar().setTitle("Vé");
                }
                else {
                    Intent intent = new Intent(this,Login.class);
                    startActivity(intent);
                }
                break;
            case R.id.person:
                if (islogin.equals("true")){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Accountfragment()).commit();
                        getSupportActionBar().setTitle("Tài khoản");
                }
                else {
                    Intent intent4 = new Intent(this,Login.class);
                    startActivity(intent4);
                }
                break;
            case R.id.information:
                Intent intent3 = new Intent(this,Information.class);
                startActivity(intent3);
                break;
            case R.id.introduction:
                Intent intent2 = new Intent(this,Information.class);
                startActivity(intent2);
                break;
            case R.id.promotion:
                getSupportActionBar().setTitle("Khuyến mãi");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Promotionfragment()).commit();
                break;
            case R.id.login:
                Intent intent1 = new Intent(this,Login.class);
                startActivity(intent1);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }
}