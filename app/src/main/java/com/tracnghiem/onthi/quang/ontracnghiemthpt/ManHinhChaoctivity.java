package com.tracnghiem.onthi.quang.ontracnghiemthpt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class ManHinhChaoctivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chaoctivity);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(ManHinhChaoctivity.this,DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}
