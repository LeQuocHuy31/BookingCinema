package com.example.bookingmovie;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.adapter.TicketAdapter;
import com.example.bookingmovie.model.TaiKhoan;
import com.example.bookingmovie.model.VeUser;

import java.util.ArrayList;
import java.util.List;

public class Ticketfragment extends Fragment {
    Database database;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ticket, container, false);
        database = new Database();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","Tên đăng nhập");
        TaiKhoan taiKhoan = database.getChiTietTaiKhoan(username);
        List<VeUser> list = database.getVeUser(taiKhoan.getId());
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.rcv_ticket);
        TicketAdapter ticketAdapter = new TicketAdapter(getContext(),list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(ticketAdapter);
        return v;
    }
}

