package com.tracnghiem.onthi.quang.ontracnghiemthpt.examadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.modelontap.ExamOntap;

import java.util.ArrayList;

public class OntapAdapter extends ArrayAdapter<ExamOntap> {

    public OntapAdapter(Context context, ArrayList<ExamOntap> examOntaps) {
        super(context,0, examOntaps);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.item_grilviewontap,parent,false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvMonOntap);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgOntap);
        ExamOntap ontap = getItem(position);
        if(ontap !=null){
            tv.setText(""+ontap.getNameontap());
            img.setImageResource(Integer.parseInt(""+ontap.getImg()));
        }

        return convertView;
    }
}
