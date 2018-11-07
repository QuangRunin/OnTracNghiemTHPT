package com.tracnghiem.onthi.quang.ontracnghiemthpt;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.cauhoi.CauHoi;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.slide.SlideActivity;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.thongke.TkDiem;

import java.util.ArrayList;

public class KetQuaActivity extends AppCompatActivity {
            ArrayList<CauHoi> cauHois_List = new ArrayList<CauHoi>();
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
//        style text
//        TextView textView1 = (TextView) findViewById(R.id.t);
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fontt/fff.ttf");
//        textView1.setTypeface(typeface);
        tkDiem = new TkDiem(KetQuaActivity.this);
        tvChuatraloi = findViewById(R.id.tvchualam);
        tvSocausai = findViewById(R.id.tvCauSai);
        tvsoCaudung = findViewById(R.id.tvCauDung);
        tvDiem = findViewById(R.id.tvTongdiem);
        Intent intent = getIntent();
        cauHois_List = (ArrayList<CauHoi>) intent.getExtras().getSerializable("cauhoiList");
        checkKetQua();
        diem = soCauDung * 0.25;
        tvChuatraloi.setText(""+chuaTraloi);
        tvSocausai.setText(""+soCausai);
        tvsoCaudung.setText(""+soCauDung);
        tvDiem.setText(""+diem);
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
    }

    public void LuuDiem(View view) {
        insertHoaodon();
    }
    public void insertHoaodon() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Lưu kết quả !");
        LayoutInflater inflater = (LayoutInflater) builder.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewDialog = inflater.inflate(R.layout.dialog_luuketqua, null);
        builder.setView(viewDialog);
        final EditText edthoten = viewDialog.findViewById(R.id.tvhoten);
        final EditText tvmonhocdialog = viewDialog.findViewById(R.id.edmonhoc_dialog);
        final TextView tvtongquan = viewDialog.findViewById(R.id.tvtongquan);
        final TextView tvdiemso = viewDialog.findViewById(R.id.tvdiem_dialog);
        final double diem = soCauDung * 0.25;
        tvtongquan.setText(+soCauDung+"/40");
        tvdiemso.setText(""+diem);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoten = edthoten.getText().toString();
                String monhoc =  tvmonhocdialog.getText().toString();
                String tq = tvtongquan.getText().toString();
                tkDiem.insertDiem(hoten,monhoc,tq,diem);
                Toast.makeText(KetQuaActivity.this, "Luu Thanh Cong !", Toast.LENGTH_SHORT).show();
                finish();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
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
}
