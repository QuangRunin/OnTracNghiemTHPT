package com.tracnghiem.onthi.quang.ontracnghiemthpt.examadapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.cauhoi.CauHoi;

import java.util.ArrayList;
import java.util.List;

public class ThongKeAdapter extends CursorAdapter {


    public ThongKeAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_thongke,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        List<CauHoi> cauHoiss = new ArrayList<>();
        TextView tvdiem = view.findViewById(R.id.tvdiem);
        TextView tvname = view.findViewById(R.id.tvname);
        TextView tvmonhoc = view.findViewById(R.id.tvmonhoc);
        TextView tvtongquan = view.findViewById(R.id.tvtongquan);
        tvname.setText("Họ tên :"+cursor.getString(1));
        tvmonhoc.setText("Môn học : "+cursor.getString(2));
        tvtongquan.setText("Tổng quan : "+cursor.getString(3));
        tvdiem.setText("Điểm :"+cursor.getDouble(4)+"");

    }
}
