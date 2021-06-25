package com.example.bookingmovie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.adapter.PromotionAdapter;
import com.example.bookingmovie.model.KhuyenMai;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class Promotionfragment extends Fragment {
    Database database;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_promotion, container, false);
        database = new Database();
        List<KhuyenMai> lstPromotion = new ArrayList<>();
        lstPromotion = database.getKhuyenMai();
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_id);
        PromotionAdapter promotionAdapter = new PromotionAdapter(getContext(),lstPromotion);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(promotionAdapter);
        return v;
    }
}
