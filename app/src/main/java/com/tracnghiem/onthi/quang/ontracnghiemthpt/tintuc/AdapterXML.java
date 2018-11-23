package com.tracnghiem.onthi.quang.ontracnghiemthpt.tintuc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;

import java.util.ArrayList;

public class AdapterXML extends RecyclerView.Adapter<AdapterXML.HolderXML> {
    private  Context contextt;
    private ArrayList<News> newss;

    public AdapterXML(Context contextt, ArrayList<News> newss) {
        this.contextt = contextt;
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
        Uri uri = Uri.parse(news.summary);
        Picasso.with(contextt).load(uri).into(holderXML.imageView);
        holderXML.title.setText(news.title);
        holderXML.date.setText(news.date);
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
        private TextView date;
        private ImageView imageView;
        public HolderXML(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlee);
            imageView = itemView.findViewById(R.id.imgtt);
            date = itemView.findViewById(R.id.date);

        }
    }
}
