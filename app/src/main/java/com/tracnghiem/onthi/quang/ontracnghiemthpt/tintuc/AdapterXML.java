package com.tracnghiem.onthi.quang.ontracnghiemthpt.tintuc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;

import java.util.ArrayList;

public class AdapterXML extends RecyclerView.Adapter<AdapterXML.HolderXML> {
    private TinTucFragment.Readdata context;
    private  TinTucChiTietActivity  tinTucChiTietActivity;
    private ArrayList<News> newss;

    public AdapterXML(TinTucFragment.Readdata context, TinTucChiTietActivity tinTucChiTietActivity, ArrayList<News> newss) {
        this.context = context;
        this.tinTucChiTietActivity = tinTucChiTietActivity;
        this.newss = newss;
    }
    @NonNull
    @Override
    public HolderXML onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custum_row_news_item,viewGroup,false);
        HolderXML xml = new HolderXML(view);
        return xml;
    }
    @Override
    public void onBindViewHolder(@NonNull HolderXML holderXML, final  int i) {
        final News news =  newss.get(i);
        holderXML.title.setText(news.title);
        holderXML.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(v.getContext(),TinTucChiTietActivity.class);
                intent.putExtra("link",newss.get(i).link);
                v.getContext().startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return newss.size();
    }

    public class HolderXML extends RecyclerView.ViewHolder {
        private TextView title;
        public HolderXML(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlee);

        }
    }
}
