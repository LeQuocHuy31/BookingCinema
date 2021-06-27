package com.example.bookingmovie;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.adapter.AdapterCatagory;
import com.example.bookingmovie.adapter.LichChieuAdapter;
import com.example.bookingmovie.model.LichChieu;
import com.example.bookingmovie.modelshowtime.Catagory;
import com.example.bookingmovie.modelshowtime.NgayChieu;
import com.example.bookingmovie.modelshowtime.ThongTinLichChieu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Showtimefragment extends Fragment {
    private RecyclerView rcvLichChieu;
    private LichChieuAdapter lichChieuAdapter;
    private Spinner spnCatagory;
    private TextView tv_date;
    private List<ThongTinLichChieu> list= new ArrayList<>();
    private int mDate, mMonth, mYear;
    private Database connectdata;
    private String rapphim;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_showtime,container,false);

        spnCatagory=v.findViewById(R.id.spn_catagory);
        String[] RapPhim={"UIT cinema Thủ Đức","UIT cinema Sinh Viên"};//android.R.layout.simple_spinner_item
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,RapPhim);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCatagory.setAdapter(adapter);
        spnCatagory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rapphim =spnCatagory.getSelectedItem().toString();
                SetThongTinLichChieu(rapphim,tv_date.getText().toString());
                SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("rapphim", rapphim);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tv_date= v.findViewById(R.id.tv_date);
        final Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
        tv_date.setText(simpleDateFormat.format(calendar.getTime()));
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDate = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH);
                mYear= calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog= new DatePickerDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        tv_date.setText(simpleDateFormat.format(calendar.getTime()));
                        SetThongTinLichChieu(rapphim,tv_date.getText().toString());
                    }
                },mYear,mMonth,mDate);
                datePickerDialog.show();
            }
        });
        rapphim =spnCatagory.getSelectedItem().toString();
        rcvLichChieu= v.findViewById(R.id.rcv_lichchieu);
        SetThongTinLichChieu(rapphim,tv_date.getText().toString());
        return v;
    }

    private void SetThongTinLichChieu(String rapphim,String ngaychieu){
        lichChieuAdapter =new LichChieuAdapter(getContext());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcvLichChieu.setLayoutManager(linearLayoutManager);
        connectdata =new Database();
        list=connectdata.getThongTinLichChieu(rapphim,ngaychieu);
        lichChieuAdapter.setData(list);
        rcvLichChieu.setAdapter(lichChieuAdapter);
    }

}
