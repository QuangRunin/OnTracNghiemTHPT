package com.tracnghiem.onthi.quang.ontracnghiemthpt;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.examadapter.ExamAdapter;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.modelexam.Exam;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.slide.SlideActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OnTapFragment extends Fragment {
    private ExamAdapter examAdapter;
    private GridView gridView;
    private ImageView t ;
    ArrayList<Exam> arrayList = new ArrayList<>();
    public OnTapFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_tap, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        t = getActivity().findViewById(R.id.imgtest);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
//        gridView = getActivity().findViewById(R.id.grviewsinh);
//        arrayList.add(new Exam(" Môn Lịch Sử"));
//        arrayList.add(new Exam(" Môn Sinh Học"));
//        arrayList.add(new Exam(" Môn Địa Lý"));
//        arrayList.add(new Exam(" Môn GDCD"));
//
//        examAdapter = new ExamAdapter(getActivity(), arrayList);
//        gridView.setAdapter(examAdapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getContext(), SlideActivity.class);
//                intent.putExtra("masodekiemtra", i + 1);
//                intent.putExtra("monhoc", "DiaLy");
//                startActivity(intent);
//            }
//        });
    }

}
