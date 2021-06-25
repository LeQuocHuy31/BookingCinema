package com.example.bookingmovie;

import android.widget.ImageView;

import com.example.bookingmovie.model.Phim;

public interface MovieItemClickListener {
    void onMovieClick(Phim movie, ImageView movieImageView);
}
