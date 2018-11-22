package com.tracnghiem.onthi.quang.ontracnghiemthpt.tintuc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.DrawerActivity;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.ManHinhChaoctivity;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.

 * create an instance of this fragment.
 */
public class TinTucFragment extends Fragment {
    private ArrayList<News> news;
    private AdapterXML adapterLab;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    TinTucChiTietActivity activity;
    ProgressDialog progressDialog;

    public TinTucFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tin_tuc, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setTitle("Chờ chút");
        progress.setMessage("Đang tải dữ liệu...");
        progress.show();
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.cancel();
                news = new ArrayList<>();
                recyclerView.addItemDecoration(new VerticalSpace(20));
                linearLayoutManager = new LinearLayoutManager(getActivity(),linearLayoutManager.VERTICAL,false);
                final String lik ="https://thptquocgia.edu.vn/rss/";
                new Readdata().execute(lik);
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 2000);

        return view;
    }

    class Readdata extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser xmldomParser = new XMLDOMParser();
            Document document = xmldomParser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String title = "";
            String summary = "";
            String link = "";
            for(int i=0; i < nodeList.getLength(); i++){
//                    String data = nodeList1.item(i+1).getTextContent();
//                Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]['\"]([^'\"]+)['\"][^>]*>");
//                Matcher matcher = pattern.matcher(data);
//                if(matcher.find()){
//                    summary=matcher.group(1);
//                    Log.e("Hinh anh",summary+"................."+i);
//                }
                Element element = (Element) nodeList.item(i);
                title = xmldomParser.getValue(element, "title");
                summary = xmldomParser.getValue(element, "description");
                link = xmldomParser.getValue(element, "link");
                news.add(new News(title, summary,link));
            }
            adapterLab = new AdapterXML(this,activity,news);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapterLab);
            super.onPostExecute(s);
        }
    }
    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
    private class YourAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;

        public YourAsyncTask(TinTucFragment fragment) {
            dialog = new ProgressDialog(getActivity());
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Doing something, please wait.");
            dialog.show();
        }

        protected Void doInBackground(Void... args) {
            // do background work here
            return null;
        }

        protected void onPostExecute(Void result) {
            // do UI work here
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }


}
