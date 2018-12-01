package com.tracnghiem.onthi.quang.ontracnghiemthpt.thongke;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.modeltracnghiem.CauHoi;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.database.DatabseHelper;

import java.util.ArrayList;
import java.util.List;

public class TkDiem {
    private DatabseHelper helper;

    public TkDiem(Context context) {
        helper = new DatabseHelper(context);
    }
    public void insertDiem(String hoten,String monhoc,String tongquan,double diem){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("hoten",hoten);
        values.put("monhoc",monhoc);
        values.put("tongquan",tongquan);
        values.put("diem",diem);
        db.insert("ThongKe",null,values);
        db.close();
    }
    public Cursor getDiem(){
        List<CauHoi> cauHoiss = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.query("ThongKe",null,
                null,
                null,
                null,
                null, null);
        if (cursor !=null){
            cursor.moveToFirst();
                CauHoi cauHoi = new CauHoi();
                cauHoiss.add(cauHoi);
        }
        return cursor;
    }
}
