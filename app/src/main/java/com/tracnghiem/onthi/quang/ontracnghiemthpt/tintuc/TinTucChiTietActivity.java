package com.tracnghiem.onthi.quang.ontracnghiemthpt.tintuc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toolbar;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;

public class TinTucChiTietActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc_chi_tiet);
        webView = findViewById(R.id.webview);
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Vui lòng chờ !");
        progress.setMessage("Dữ liệu đang được tải...");
        progress.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.cancel();
                Intent intent =  getIntent();
                String link =  intent.getStringExtra("link");
                webView.loadUrl(link);
                webView.setWebViewClient(new WebViewClient());
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 1000);
    }
}
