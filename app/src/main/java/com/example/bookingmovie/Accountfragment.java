package com.example.bookingmovie;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.model.TaiKhoan;
import com.google.android.material.textfield.TextInputEditText;

public class Accountfragment extends Fragment {
    Database database;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnDangXuat;
    private Button btnDoiMatKhau;
    private Button btnThongTinTaiKhoan;
    private TextView changeImg;
    private TextView  txtuser;
    private TextView txtdiem;
    private TextView txtChitieu;
    private ImageView userImg;
    TaiKhoan taiKhoan;
    private static final int pic_id = 123;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v;
            database = new Database();
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login",Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username","Tên đăng nhập");
            v = inflater.inflate(R.layout.fragment_account,container,false);


            taiKhoan = database.getChiTietTaiKhoan(username);
            int tongChiTieu = taiKhoan.getDiemTichLuy()*1000;

            txtuser = v.findViewById(R.id.user);
            txtuser.setText(taiKhoan.getHoTen());
            String diemtichluy = String.valueOf(taiKhoan.getDiemTichLuy());
            txtdiem = v.findViewById(R.id.diemtichluy);
            txtdiem.setText(diemtichluy);
            txtChitieu = v.findViewById(R.id.tongchitieu);
            txtChitieu.setText(String.valueOf(tongChiTieu));
            changeImg = v.findViewById(R.id.change_img);
            userImg = v.findViewById(R.id.user_img);
            btnDangXuat = v.findViewById(R.id.btnDangXuat);
            btnThongTinTaiKhoan = v.findViewById(R.id.btnThongTinTaiKhoan);
            btnThongTinTaiKhoan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),AccountInfo.class);
                    startActivity(intent);
                }
            });
            btnDangXuat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Login","false");
                    editor.putString("username","Tên đăng nhập");
                    editor.commit();
                    Intent intent = new Intent(getContext(),MainActivity.class);
                    startActivity(intent);
                }
            });
            btnDoiMatKhau = v.findViewById(R.id.btnDoiMatKhau);
            btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),ChangePassword.class);
                    startActivity(intent);
                }
            });
            changeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dispatchTakePictureIntent();
                    Intent camera_intent
                            = new Intent(MediaStore
                            .ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera_intent, pic_id);
                }
            });
         return v;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == pic_id) {
            Bitmap photo = (Bitmap) data.getExtras()
                    .get("data");
            userImg.setImageBitmap(photo);
        }
    }
}
