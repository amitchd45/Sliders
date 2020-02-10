package com.example.sliders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.sliders.Adapters.SliderAdapters;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<SliderItem> sliderItems =new ArrayList<>();
    private ViewPager2 viewPager2;
    private Handler sliderHandler =new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 =findViewById(R.id.viewPagerImageSlider);

        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background));

        viewPager2.setAdapter(new SliderAdapters(sliderItems, viewPager2, new SliderAdapters.Select() {
            @Override
            public void ClickSlider(int position) {
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        }));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r =1-Math.abs(position);
                page.setScaleY(0.85f +r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000);

            }
        });
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {

            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };
}
