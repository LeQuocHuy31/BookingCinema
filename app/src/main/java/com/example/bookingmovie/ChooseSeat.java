package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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
import android.widget.Toast;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.adapter.ChooseSeatAdapter;
import com.example.bookingmovie.model.LichChieu;

import java.util.ArrayList;
import java.util.List;

public class ChooseSeat extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 10;
    private RecyclerView mRecyclerView;
    private TextView tvPhim;
    private TextView mSeatList;
    private TextView mPrice;
    private Button mPayNow;
    private int soghe = 64;
    ChooseSeatAdapter mAdapter;
    private int mPriceValue = 0;
    private Database bookingDatabase;
    List<LichChieu> mListShowTime= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seat);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chọn ghế");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        anhxa();
        //lấy thông tin giờ chiếu
        Intent intent = getIntent();
        String giochieu = intent.getStringExtra("xuatChieu");
        //lấy tên phim của giờ chiếu đó,phim đó chiếu ở rạp nào vào ngày nào
        SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String tenPhim = sharedPref.getString("tenphim", "");
        String rap =sharedPref.getString("rapphim","");
        String ngaychieu=sharedPref.getString("ngaychieu","");
        tvPhim.setText(tenPhim);
        mAdapter = new ChooseSeatAdapter(this);
        mAdapter.setOnSelectedChangedListener(this::setOnSelectedChanged);
        mRecyclerView = findViewById(R.id.recycle_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 8);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter.setData(getListSeat(tenPhim,giochieu,rap,ngaychieu));
        mRecyclerView.setAdapter(mAdapter);
        mPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPriceValue > 0) {
                    luutrangthaighe();
                    Intent i = new Intent(ChooseSeat.this, Service.class);
                    // i.putExtra("tongTien",mPriceValue);
                    i.putExtra("total", mPriceValue);
                    startActivity(i);
                } else {
                    Toast.makeText(ChooseSeat.this, "Vui lòng chọn ghế để thực hiện bước tiếp theo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

        }
    }

    private void anhxa() {
        mRecyclerView = findViewById(R.id.recycle_view);
        tvPhim=findViewById(R.id.tv_phim);
        mSeatList = findViewById(R.id.seat_list);
        mPrice = findViewById(R.id.price);
        mPayNow = findViewById(R.id.btn_TTDV);
        mRecyclerView = findViewById(R.id.recycle_view);
    }

    List<String> listtrangthai=new ArrayList<>();
    private List<String> getListSeat(String tenphim, String gio, String rap,String ngaychieu) {
        bookingDatabase = new Database();
        mListShowTime = bookingDatabase.getLichChieu(tenphim,gio,ngaychieu,rap);
        String chuoi=mListShowTime.get(0).getMtrangthaighe();
        String[] l=chuoi.split(",");
        listtrangthai.clear();
        for(int i=0;i<l.length;i++){
            if(l[i].equals("1")){
                listtrangthai.add("1");//ghế đã đặt
            }
            else {
                listtrangthai.add("0");//ghế chưa đặt
            }
        }
        return listtrangthai;
    }

    private void luutrangthaighe() {
        String strRePlace="";
        for (int i:mSelects) {
            listtrangthai.set(i,"1");
        }

        //chuyển list thành chuỗi để cập nhật lại vào csdl
        for(String i:listtrangthai){
            strRePlace=strRePlace+i+",";
        }
        SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("trangthaighe", strRePlace);
        editor.putString("idLichChieu",mListShowTime.get(0).getId());
        editor.putInt("phong",mListShowTime.get(0).getPhong());
        editor.putString("ghe",String.valueOf(mSeatList.getText()));
        editor.putString("ngay",mListShowTime.get(0).getNgay());
        editor.putString("gio",mListShowTime.get(0).getmGio());
        editor.commit();
        //cập nhật vào csdl
        //database.updateTrangThaiGhe(mListShowTime.get(0).getId(),strRePlace);
    }

    private String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String NUM = "123456789";
    private List<Integer> mSelects = new ArrayList<>();

    public void setOnSelectedChanged(List<Integer> selects) {
        mSelects.clear();
        mSelects.addAll(selects);

        String text = "";
        int column = 8;
        int row = 8;
        mPriceValue = mListShowTime.get(0).getGia() * selects.size();
        if (selects.size() <= 8) {
            for (int select : selects) {
                int myRow = select / row;
                int myColumn = select % column;
                text = String.format("%s%c%c ", text, ABC.charAt(myRow), NUM.charAt(myColumn));
            }

            mSeatList.setText(text);
            mPrice.setText(mPriceValue + " ₫");
        } else {
            Toast.makeText(getApplicationContext(), "Bạn chỉ chọn được tối đa 8 ghế", Toast.LENGTH_SHORT).show();
        }
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
