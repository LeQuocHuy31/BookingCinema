package com.example.bookingmovie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.adapter.MoviefAdapter;
import com.example.bookingmovie.model.Phim;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class Moviefragment extends Fragment {
    Database database;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        database = new Database();
        tabLayout = v.findViewById(R.id.tabMovie);
        List<Phim> movieList1 = database.getPhim(1);
        List<Phim> movieList2 = database.getPhim(0);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.listmovie);
        MoviefAdapter movieAdapter = new MoviefAdapter(getContext(),movieList1);
        MoviefAdapter movieAdapter0 = new MoviefAdapter(getContext(),movieList2);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(movieAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                    recyclerView.setAdapter(movieAdapter);
                }
                else{
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                    recyclerView.setAdapter(movieAdapter0);
                }

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }
}
