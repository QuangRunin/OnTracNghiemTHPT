package com.tracnghiem.onthi.quang.ontracnghiemthpt.examadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.cauhoi.CauHoi;

import java.util.ArrayList;

public class CheckDapanAdapter extends BaseAdapter {
    ArrayList listDataDapAn ;
    LayoutInflater inflater;

    public CheckDapanAdapter(ArrayList listDataDapAn, Context context) {
        this.listDataDapAn = listDataDapAn;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listDataDapAn.size();
    }

    @Override
    public Object getItem(int i) {
        return listDataDapAn.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CauHoi data = (CauHoi) getItem(i);
        ViewHolder holder;
        if(view ==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_gridview_dscautraloi,null);
            holder.tvCauHoi =  view.findViewById(R.id.tvNumcauHoi);
            holder.tvDapAn = view.findViewById(R.id.tvDapan);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        int  posion = i+1;
        holder.tvCauHoi.setText("Câu "+posion+ ":");
        holder.tvDapAn.setText(data.getTraloi());
        return view;
    }
    private static class ViewHolder{
        TextView tvCauHoi,tvDapAn;
    }
}
