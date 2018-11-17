package com.tracnghiem.onthi.quang.ontracnghiemthpt.monontap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.database.DatabseHelper;

import java.util.ArrayList;

public class GetDuLieu {
    private DatabseHelper helper;

    public GetDuLieu(Context context) {
        helper = new DatabseHelper(context);
    }
    public ArrayList<DuLieuOnTap> getdata(int monontap){
        ArrayList<DuLieuOnTap> listdata = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM OnTap WHERE monontap = '"+monontap+"'",null);
        cursor.moveToFirst();
        do {
            DuLieuOnTap item;
            item = new DuLieuOnTap(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getInt(3));

            listdata.add(item);

        }while (cursor.moveToNext());
        return listdata;
    }
}
