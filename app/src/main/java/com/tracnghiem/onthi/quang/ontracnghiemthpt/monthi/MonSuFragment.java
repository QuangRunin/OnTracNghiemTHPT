package com.tracnghiem.onthi.quang.ontracnghiemthpt.monthi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.examadapter.ExamAdapter;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.modelexam.Exam;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.slide.SlideActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class MonSuFragment extends Fragment {
    private ExamAdapter examAdapter;
    private GridView gridView;
    ArrayList<Exam> arrayList = new ArrayList<>();
    public MonSuFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mon_toan, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView = getActivity().findViewById(R.id.grview);
        arrayList.add(new Exam("Lịch sử  : đề số 1"));
        arrayList.add(new Exam("Lịch sử  : đề số 2"));
        arrayList.add(new Exam("Lịch sử  : đề số 3"));
        arrayList.add(new Exam("Lịch sử  : đề số 4"));

        examAdapter = new ExamAdapter(getActivity(),arrayList);
        gridView.setAdapter(examAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),SlideActivity.class);
                intent.putExtra("masodekiemtra",i+1);
                intent.putExtra("monhoc","LichSu") ;
                startActivity(intent);
            }
        });

    }
}
