package com.tracnghiem.onthi.quang.ontracnghiemthpt;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.examadapter.ThongKeAdapter;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.thongke.TkDiem;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeFragment extends Fragment {
        private ListView listView;
        private ThongKeAdapter thongKeAdapter;
        private TkDiem tkDiem;

    public ThongKeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tkDiem = new TkDiem(getActivity());
        listView = getActivity().findViewById(R.id.lv);
        Cursor cursor = tkDiem.getDiem();
        thongKeAdapter = new ThongKeAdapter(getActivity(),cursor,true);
        listView.setAdapter(thongKeAdapter);
    }
}
