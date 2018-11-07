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
import com.tracnghiem.onthi.quang.ontracnghiemthpt.modelexam.Exam;

import java.util.ArrayList;

public class ExamAdapter extends ArrayAdapter<Exam> {
    public ExamAdapter(@NonNull Context context, ArrayList<Exam> exams) {
        super(context,0, exams);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView ==null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.item_girdview,parent,false);

        }
        TextView tvname = (TextView) convertView.findViewById(R.id.tvde1);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
        Exam x = getItem(position);
        if(x !=null){
            tvname.setText(""+x.getName());
            image.setImageResource(R.drawable.sach);
        }
        return convertView;
    }
}
