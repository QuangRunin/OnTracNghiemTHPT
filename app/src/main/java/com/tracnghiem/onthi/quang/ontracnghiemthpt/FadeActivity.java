package com.tracnghiem.onthi.quang.ontracnghiemthpt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import ir.alirezabdn.wp7progress.WP7ProgressBar;

public class FadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade);
        final WP7ProgressBar progressBar = findViewById(R.id.wp7progressBarTT);
        progressBar.showProgressBar();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        },3000);
    }
}