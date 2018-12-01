package com.tracnghiem.onthi.quang.ontracnghiemthpt.monontap;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.examadapter.OntapAdapter;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.modelontap.ExamOntap;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.slideontap.SlideOntapActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OntapFragment extends Fragment {
    private OntapAdapter examAdapterOntap;
    private GridView gridViewOntap;
    ArrayList<ExamOntap> arrayList = new ArrayList<>();
    public OntapFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ontap, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridViewOntap = getActivity().findViewById(R.id.grviewOntap);
        arrayList.add(new ExamOntap(" Môn Lịch Sử",""+(R.drawable.su)));
        arrayList.add(new ExamOntap(" Môn Sinh Học",""+(R.drawable.sinhhoc)));
        arrayList.add(new ExamOntap(" Môn Địa Lý",""+(R.drawable.diali)));
        arrayList.add(new ExamOntap(" Môn GDCD ",""+(R.drawable.dgcd)));
        examAdapterOntap = new OntapAdapter(getActivity(), arrayList);
        gridViewOntap.setAdapter(examAdapterOntap);
        gridViewOntap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), SlideOntapActivity.class);
                intent.putExtra("monontap",i+1);
                startActivity(intent);
            }
        });
    }
}
