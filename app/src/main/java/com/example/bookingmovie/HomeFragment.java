package com.example.bookingmovie;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.Database.ConnectionHelper;
import com.example.bookingmovie.Database.Database;
import com.example.bookingmovie.adapter.MovieAdapter;
import com.example.bookingmovie.adapter.MovieAdapter0;
import com.example.bookingmovie.adapter.MoviefAdapter;
import com.example.bookingmovie.adapter.PromotionAdapter;
import com.example.bookingmovie.adapter.SliderAdapter;
import com.example.bookingmovie.model.KhuyenMai;
import com.example.bookingmovie.model.Phim;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements MovieItemClickListener,SliderItemClickListener{
    private ViewPager2 viewPager2;
    private Handler sliderHandlder =new Handler();
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private TextView suggestion;
    Database database;
    public HomeFragment(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2 = v.findViewById(R.id.viewPagerSlide);
        tabLayout = v.findViewById(R.id.tabLayout);
        recyclerView =  (RecyclerView) v.findViewById(R.id.rv_movie);

        database = new Database();

        List<Phim> movieList1 = database.getPhim(1);

        MovieAdapter movieAdapter1 = new MovieAdapter(getActivity(), movieList1, this::onMovieClick);


        List<Phim> movieList2 = database.getPhim(0);

        MovieAdapter0 movieAdapter2 = new MovieAdapter0(getActivity(), movieList2, this::onMovieClick);

        recyclerView.setAdapter(movieAdapter1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    recyclerView.setAdapter(movieAdapter1);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
                }
                else{
                    recyclerView.setAdapter(movieAdapter2);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        List<KhuyenMai> sliderItems = database.getKhuyenMai();
        SliderAdapter sliderAdapter = new SliderAdapter(getActivity(),sliderItems,viewPager2, this::onSliderClick);
        viewPager2.setAdapter(sliderAdapter);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=  1- Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandlder.removeCallbacks(sliderRunnable);
                sliderHandlder.postDelayed(sliderRunnable,1500);
            }
        });


        List<KhuyenMai> lstPromotion = new ArrayList<>();
        lstPromotion = database.getKhuyenMai();
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_id);
        PromotionAdapter promotionAdapter = new PromotionAdapter(getContext(),lstPromotion);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(promotionAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        return v;
    }
    @Override
    public void onSliderClick(KhuyenMai sliderItem, ImageView imageView) {
        Intent intent = new Intent(getActivity(),SliderDetailActivity.class);
        intent.putExtra("content",sliderItem.getTenKhuyenMai());
        intent.putExtra("detailcontent",sliderItem.getNoiDung());
        intent.putExtra("imgcontent",sliderItem.getImg());
        ActivityOptions options1 = ActivityOptions.makeSceneTransitionAnimation(getActivity(),imageView,"sharedName");
        startActivity(intent,options1.toBundle());
        Toast.makeText(getActivity(),"Mở : " + sliderItem.getTenKhuyenMai(),Toast.LENGTH_LONG).show();
    }

    private  Runnable sliderRunnable= new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    @Override
    public void onMovieClick(Phim movie, ImageView movieImageView) {
        Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
        intent.putExtra("title",movie.getTenPhim());
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),movieImageView,"sharedName");
        startActivity(intent,options.toBundle());
        Toast.makeText(getActivity(),"Mở : " + movie.getTenPhim(),Toast.LENGTH_LONG).show();
    }
}
