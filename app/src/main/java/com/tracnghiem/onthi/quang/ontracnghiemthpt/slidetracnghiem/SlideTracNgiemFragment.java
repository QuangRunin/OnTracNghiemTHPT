package com.tracnghiem.onthi.quang.ontracnghiemthpt.slidetracnghiem;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.modeltracnghiem.CauHoi;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.

 * create an instance of this fragment.
 */
public class SlideTracNgiemFragment extends Fragment {
    ArrayList<CauHoi> cauhoiList;
    public static  final String PAGE = "page";
    public static  final String CHECK = "check";
    private int vitriTrang;
    private TextView tvNum,tvCauHoi;
    private RadioGroup radioGroup;
    private RadioButton rdA,rdB,rdC,rdD;
    private int checkKqua;

    public SlideTracNgiemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_slide_toan,container,false);
       tvNum = viewGroup.findViewById(R.id.tvNum);
       tvCauHoi = viewGroup.findViewById(R.id.tvQuestion);
       rdA = viewGroup.findViewById(R.id.radA);
       rdB = viewGroup.findViewById(R.id.radB);
       rdC = viewGroup.findViewById(R.id.radC);
       rdD = viewGroup.findViewById(R.id.radD);
       radioGroup =  viewGroup.findViewById(R.id.radGroup);
       return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNum.setText("Câu : "+(vitriTrang+1));
        tvCauHoi.setText(cauhoiList.get(vitriTrang).getCauhoi());
        rdA.setText("A : "+""+getItem(vitriTrang).getDapan_a());
        rdB.setText("B : "+""+getItem(vitriTrang).getDapan_b());
        rdC.setText("C : "+""+getItem(vitriTrang).getDapan_c());
        rdD.setText("D : "+""+getItem(vitriTrang).getDapan_d());

        if(checkKqua !=0){
            rdA.setClickable(false);
            rdB.setClickable(false);
            rdC.setClickable(false);
            rdD.setClickable(false);
            getCheckKq(getItem(vitriTrang).getKetqua().toString());
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int check) {
                cauhoiList.get(vitriTrang).choiceId = check;
                cauhoiList.get(vitriTrang).setTraloi(getChoiceId(check));
//                Toast.makeText(getActivity(), "Đây là đáp án"+i, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public CauHoi getItem(int posion){
        return cauhoiList.get(posion);
    }
    private String getChoiceId(int ID){
        if(ID==R.id.radA){
            return "A";
        }
        else if(ID==R.id.radB){
            return "B";
        }
        else if(ID==R.id.radC){
            return "C";
        }
        else if(ID==R.id.radD){
            return "D";
        }
        else return "";
    }
    private void getCheckKq(String kq){
        if(kq.equals("A")==true){
            rdA.setBackgroundColor(Color.GRAY);
        }
        else if(kq.equals("B")==true){
            rdB.setBackgroundColor(Color.GRAY);
        }
        else if(kq.equals("C")==true){
            rdC.setBackgroundColor(Color.GRAY);
        }
        else if(kq.equals("D")==true){
            rdD.setBackgroundColor(Color.GRAY);
        }else;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cauhoiList = new ArrayList<CauHoi>();
        SlideActivity slideActivity = (SlideActivity) getActivity();
        cauhoiList =  slideActivity.getData();
        vitriTrang =  getArguments().getInt(PAGE);
        checkKqua = getArguments().getInt(CHECK);



    }
    public  static SlideTracNgiemFragment create(int pageNumber, int checkKq){
        SlideTracNgiemFragment fragment = new SlideTracNgiemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE,pageNumber);
        bundle.putInt(CHECK,checkKq);
        fragment.setArguments(bundle);
        return fragment;
    }
}
