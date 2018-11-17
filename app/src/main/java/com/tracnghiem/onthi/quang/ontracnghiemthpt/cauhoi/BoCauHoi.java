package com.tracnghiem.onthi.quang.ontracnghiemthpt.cauhoi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.database.DatabseHelper;

import java.util.ArrayList;

public class BoCauHoi {
    private DatabseHelper helper;

    public BoCauHoi(Context context) {
        helper = new DatabseHelper(context);
    }
    public ArrayList<CauHoi> getcauhoi(int masodekiemtra,String monhoc){
        ArrayList<CauHoi> listdata = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM BoDeTT WHERE masodekiemtra = '"+masodekiemtra+"'  AND monhoc = '"+monhoc+"'",null);
        cursor.moveToFirst();
        do {
            CauHoi item;
            item = new CauHoi(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getInt(8),cursor.getString(9));

            listdata.add(item);

        }while (cursor.moveToNext());
        return listdata;
    }
}
