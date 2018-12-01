package com.tracnghiem.onthi.quang.ontracnghiemthpt;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import ir.alirezabdn.wp7progress.WP7ProgressBar;

public class ManHinhChaoctivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chaoctivity);
        imageView = findViewById(R.id.imgmhc);
        textView = findViewById(R.id.tvmhc);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.animationmhc);
        a.reset();
        textView.clearAnimation();
        textView.startAnimation(a);
        Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide);
        imageView.startAnimation(animSlide);
        final WP7ProgressBar progressBar = findViewById(R.id.wp7progressBar);
        progressBar.showProgressBar();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(ManHinhChaoctivity.this,DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }

}
