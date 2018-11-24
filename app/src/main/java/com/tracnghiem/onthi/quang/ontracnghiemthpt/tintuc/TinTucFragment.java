package com.tracnghiem.onthi.quang.ontracnghiemthpt.tintuc;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tracnghiem.onthi.quang.ontracnghiemthpt.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
    private ImageView imageView;
    private LinearLayoutManager linearLayoutManager;
    public TinTucFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tin_tuc, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        imageView =  view.findViewById(R.id.imgtintuc);
        Animation animSlide = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide);
        imageView.startAnimation(animSlide);
        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("Đang tải dữ liệu.....");
        progress.setCanceledOnTouchOutside(true);
        progress.show();
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                progress.cancel();
                checkInternet();
                news = new ArrayList<>();
                recyclerView.addItemDecoration(new VerticalSpace(20));
                linearLayoutManager = new LinearLayoutManager(getActivity(),linearLayoutManager.VERTICAL,false);
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000);

        return view;
    }
    private boolean checkInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo ==null){
            final ProgressDialog progress = new ProgressDialog(getActivity());
            progress.setTitle("Không có kết nối Internet !");
            progress.setMessage("Đang thử lại kết nối lại...");
            progress.setCancelable(true);
            progress.show();
            return false;
        }
        if(!networkInfo.isConnected()){
            final ProgressDialog progress = new ProgressDialog(getActivity());
            progress.setTitle("Không có kết nối Internet !");
            progress.setMessage("Đang thử lại kết nối lại...");
            progress.setCancelable(true);
            progress.show();
            return false;

        }
        if(!networkInfo.isAvailable()){
            final ProgressDialog progress = new ProgressDialog(getActivity());
            progress.setTitle("Không có kết nối Internet !");
            progress.setMessage("Đang thử lại kết nối lại...");
            progress.setCancelable(true);
            progress.show();
            return false;
        }
        else{
        String lik ="https://thptquocgia.edu.vn/rss/";
        new Readdata().execute(lik);
        return true;}
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
            NodeList nodeListdescription = document.getElementsByTagName("description");
            String title = "";
            String summary = "";
            String link = "";
            String pubDate = "";
            for(int i=0; i < nodeList.getLength(); i++){
                String cdata = nodeListdescription.item(i+1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher  matcher= p.matcher(cdata);
                if (matcher.find()){
                    summary = matcher.group(1);
                    Log.e("Hinh Anh",""+summary+""+i);
                }
                Element element = (Element) nodeList.item(i);
                title = xmldomParser.getValue(element, "title");
                pubDate = xmldomParser.getValue(element, "pubDate");
                link = xmldomParser.getValue(element, "link");
                news.add(new News(title, summary,link,pubDate));
            }
            adapterLab = new AdapterXML(getActivity(),news);
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
