package com.tracnghiem.onthi.quang.ontracnghiemthpt.slide;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.KetQuaActivity;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.cauhoi.BoCauHoi;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.cauhoi.CauHoi;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.examadapter.CheckDapanAdapter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SlideActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 40;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private BoCauHoi boCauHoi;
    ArrayList<CauHoi> cauhoiList;
    private TextView tvKiemTra,tvThoigian,tvXemdiem;
    private CounterClass time;
    private String monhoc;
    private int maso;
    private int Thoigian;
    private int checkKqua = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        mPager = (ViewPager) findViewById(R.id.pager);
        tvKiemTra = findViewById(R.id.tvKiemTra);
        tvThoigian = findViewById(R.id.tvTimer);
        tvXemdiem = findViewById(R.id.tvScore);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());

        final Intent intent = getIntent();
        monhoc = intent.getStringExtra("monhoc");
        maso = intent.getIntExtra("masodekiemtra",0);
        Thoigian = 10;
        time = new CounterClass(Thoigian*60*1000,1000);
        boCauHoi = new BoCauHoi(this);
        cauhoiList = new ArrayList<>();
        cauhoiList = boCauHoi.getcauhoi(maso,monhoc);

        tvKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCauTraLoi();
            }
        });
        tvThoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvXemdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent1 = new Intent(SlideActivity.this,KetQuaActivity.class);
                intent1.putExtra("cauhoiList",cauhoiList);
                startActivity(intent1);
            }
        });
        time.start();

    }
    public  ArrayList<CauHoi> getData(){
        return cauhoiList;
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {

            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return SlideToanFragment.create(position,checkKqua);

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
    public void checkCauTraLoi(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_check_cautraloi);
        dialog.setTitle("Danh sách câu trả lời !");
        CheckDapanAdapter check = new CheckDapanAdapter(cauhoiList,this);
        GridView grDanhsach = dialog.findViewById(R.id.grDanhSach);
        grDanhsach.setAdapter(check);
        grDanhsach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPager.setCurrentItem(i);
                dialog.dismiss();
            }
        });
        Button btnDong,btKetThuc;
        btnDong = dialog.findViewById(R.id.btnDong);
        btKetThuc = dialog.findViewById(R.id.btnKetthuc);
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.cancel();
                result();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void result(){
        checkKqua =  1;
        if (mPager.getCurrentItem()>=5){
            mPager.setCurrentItem(mPager.getCurrentItem()-4);
        }
        else if(mPager.getCurrentItem()<=5){
            mPager.setCurrentItem(mPager.getCurrentItem()+4);

        }
        tvXemdiem.setVisibility(View.VISIBLE);
        tvKiemTra.setVisibility(View.GONE);
    }

    public class CounterClass extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvThoigian.setText(countTime);
        }

        @Override
        public void onFinish() {
            tvThoigian.setText("00:00");
        }

    }


    // Phương thức xoa database viết vào hàm MainActivity
    //        try {
//            db.deleteDataBase();
//            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "bi loi rui", Toast.LENGTH_SHORT).show();
//        }
}
