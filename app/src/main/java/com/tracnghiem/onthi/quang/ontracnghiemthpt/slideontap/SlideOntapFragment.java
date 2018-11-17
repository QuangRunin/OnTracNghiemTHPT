package com.tracnghiem.onthi.quang.ontracnghiemthpt.slideontap;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monontap.DuLieuOnTap;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SlideOntapFragment extends Fragment {
    ArrayList<DuLieuOnTap> duLieuList;
    public static final String Page ="page";
     private int vitripage;
    private TextView tvtieude,tvNoidung;

    public SlideOntapFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_slide_ontap,container,false);
        tvtieude = (TextView)viewGroup.findViewById(R.id.tvTieude);
        tvNoidung = (TextView)viewGroup.findViewById(R.id.tvdata);
        return viewGroup;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        duLieuList = new ArrayList<DuLieuOnTap>();
        SlideOntapActivity slideOntapActivity= (SlideOntapActivity) getActivity();
        duLieuList =  slideOntapActivity.getDat();
        vitripage = getArguments().getInt(Page);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvtieude.setText(duLieuList.get(vitripage).getTieude());
        tvNoidung.setText(duLieuList.get(vitripage).getNoidung());
    }
    public static SlideOntapFragment creat(int sotrang){
        SlideOntapFragment slideOntapFragment = new SlideOntapFragment();
        Bundle bd = new Bundle();
        bd.putInt(Page,sotrang);
        slideOntapFragment.setArguments(bd);
        return slideOntapFragment;
    }
}
