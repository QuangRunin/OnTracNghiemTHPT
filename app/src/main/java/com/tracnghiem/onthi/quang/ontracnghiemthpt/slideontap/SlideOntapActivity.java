package com.tracnghiem.onthi.quang.ontracnghiemthpt.slideontap;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monontap.DuLieuOnTap;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monontap.GetDuLieu;

import java.util.ArrayList;


public class SlideOntapActivity extends AppCompatActivity {
    private static final int NUM_PAGES = 5;
    private ViewPager mPagerontap;
    private PagerAdapter mPagerAdapterOntap;
    private GetDuLieu getDuLieu;
    ArrayList<DuLieuOnTap> duLieuList;
    private int monontap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_ontap);
        mPagerontap = (ViewPager) findViewById(R.id.viewpageOntap);
        mPagerAdapterOntap = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPagerontap.setAdapter(mPagerAdapterOntap);
        mPagerontap.setPageTransformer(true, new DepthPageTransformer());
        getDuLieu = new GetDuLieu(this);
        duLieuList = new ArrayList<>();
        final Intent intent = getIntent();
        monontap = intent.getIntExtra("monontap",0);
        duLieuList = getDuLieu.getdata(monontap);
    }
    public  ArrayList<DuLieuOnTap> getDat(){
        return duLieuList;
    }

    @Override
    public void onBackPressed() {
        if (mPagerontap.getCurrentItem() == 0) {

            super.onBackPressed();
        } else {
            mPagerontap.setCurrentItem(mPagerontap.getCurrentItem() - 1);
        }
    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return  SlideOntapFragment.creat(position);
        }
        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);
                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }


}
