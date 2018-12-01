package com.tracnghiem.onthi.quang.ontracnghiemthpt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.modeltracnghiem.CauHoi;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.thongke.TkDiem;

import java.util.ArrayList;

public class KetQuaActivity extends AppCompatActivity {
    private ShareDialog shareDialog;
    private Button buttonshare;
    String TAG = "LoginFaceActivity";
    private CallbackManager manager;
            ArrayList<CauHoi> cauHois_List = new ArrayList<>();
            int chuaTraloi = 0;
            int soCauDung = 0;
            int soCausai = 0;
            double diem = 0;
            private TextView tvChuatraloi,tvsoCaudung,tvSocausai,tvDiem;
            TkDiem tkDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        buttonshare = findViewById(R.id.btnShareKQ);
        shareDialog = new ShareDialog(KetQuaActivity.this);
        manager = CallbackManager.Factory.create();
        buttonshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInternet();
            }
        });

        manager = CallbackManager.Factory.create();
        shareDialog.registerCallback(manager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(KetQuaActivity.this.getApplicationContext(), "Share success!", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"Fb onSuccess");
            }

            @Override
            public void onCancel() {
                Toast.makeText(KetQuaActivity.this.getApplicationContext(), "Did not share", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"Fb onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(KetQuaActivity.this.getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"Fb onError");
            }
        }, 90);



        tkDiem = new TkDiem(KetQuaActivity.this);
        tvChuatraloi = findViewById(R.id.tvchualam);
        tvSocausai = findViewById(R.id.tvCauSai);
        tvsoCaudung = findViewById(R.id.tvCauDung);
        tvDiem = findViewById(R.id.tvTongdiem);
        Intent intent = getIntent();
        cauHois_List = (ArrayList<CauHoi>) intent.getExtras().getSerializable("cauhoiList");
        checkKetQua();
        diem = soCauDung * 0.5;
        tvChuatraloi.setText(""+chuaTraloi+" câu");
        tvSocausai.setText(""+soCausai +" câu");
        tvsoCaudung.setText(""+soCauDung+" câu");
        tvDiem.setText(""+diem +" điểm");
    }
    public void checkKetQua(){
        for (int i = 0 ; i < cauHois_List.size();i++){
                    if(cauHois_List.get(i).getTraloi().equals("")){
                        chuaTraloi++;
                    }
                    else if(cauHois_List.get(i).getKetqua().equals(cauHois_List.get(i).getTraloi())==true) {
                        soCauDung++;
                    }
                    else soCausai++;

        }
    }

    public void LamLai(View view) {
        finish();

    }

    public void LuuDiem(View view) {
        insertDiem();
    }
    public void insertDiem() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Lưu kết quả !");
        LayoutInflater inflater = (LayoutInflater) builder.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewDialog = inflater.inflate(R.layout.dialog_luuketqua, null);
        builder.setView(viewDialog);
        final EditText edthoten = viewDialog.findViewById(R.id.tvhoten);
        final EditText tvmonhocdialog = viewDialog.findViewById(R.id.edmonhoc_dialog);
        final TextView tvtongquan = viewDialog.findViewById(R.id.tvtongquan);
        final TextView tvdiemso = viewDialog.findViewById(R.id.tvdiem_dialog);
        final double diem = soCauDung * 0.5;
        tvtongquan.setText(+soCauDung+"/20");
        tvdiemso.setText(""+diem);

        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoten = edthoten.getText().toString();
                String monhoc =  tvmonhocdialog.getText().toString();
                String tq = tvtongquan.getText().toString();
                tkDiem.insertDiem(hoten,monhoc,tq,diem);
                Toast.makeText(KetQuaActivity.this, "Lưu thành công !", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setCancelable(false);
        builder.show();

    }

    public void Thoat(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(KetQuaActivity.this);
        builder.setIcon(R.drawable.ic_exit_to_app_black_24dp);
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có chắc chắn không ?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    private boolean checkInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager)KetQuaActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo ==null){
            final ProgressDialog progress = new ProgressDialog(KetQuaActivity.this);
            progress.setMessage("Không có kết nối mạng...");
            progress.setCancelable(true);
            progress.show();
            return false;
        }
        if(!networkInfo.isConnected()){
            final ProgressDialog progress = new ProgressDialog(KetQuaActivity.this);
            progress.setMessage("Không có kết nối mạng...");
            progress.setCancelable(true);
            progress.show();
            return false;
        }
        if(!networkInfo.isAvailable()){
            final ProgressDialog progress = new ProgressDialog(KetQuaActivity.this);
            progress.setMessage("Không có kết nối mạng...");
            progress.setCancelable(true);
            progress.show();
            return false;
        }
        else{
            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://drive.google.com/drive/u/0/folders/1dT4JgX0Zik4KF3PpoPKPCtcqnrYQLHa0"))
                    .setQuote("Ôn Tập THPT "+" - "+" "+
                            "Thành tích : "+diem +" điểm")
                    .build();
            shareDialog.show(content);
            return true;}
    }

}
