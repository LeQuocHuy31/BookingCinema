package com.example.bookingmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingmovie.DetailShowTime;
import com.example.bookingmovie.Login;
import com.example.bookingmovie.MovieItemClickListener;
import com.example.bookingmovie.R;
import com.example.bookingmovie.model.LichChieu;
import com.example.bookingmovie.model.Phim;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    Context context;
    List<Phim> Data;
    MovieItemClickListener movieItemClickListener;

    public MovieAdapter(Context context, List<Phim> data, MovieItemClickListener listener) {
        this.context = context;
        this.Data = data;
        movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent, false);

        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(Data.get(position).getTenPhim());
        Glide.with(context).load(Data.get(position).getImage()).into(holder.imageView);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailShowTime.class);
                intent.putExtra("tenPhim",holder.textView.getText());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return Data.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;
        private Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_movie_title);
            imageView = itemView.findViewById(R.id.item_movie_image);
            button = itemView.findViewById(R.id.buyinHome);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClick(Data.get(getAdapterPosition()),imageView);
                }
            });
        }
    }
}
