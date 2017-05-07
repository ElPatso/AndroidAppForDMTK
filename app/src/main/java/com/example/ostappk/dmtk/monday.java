package com.example.ostappk.dmtk;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


/**
 * Created by OstapPK on 14.10.2015.
 */
class monday extends Fragment  {
    public static String JsonURL;
    private static ArrayList<HashMap<String, Object>> myBooks;
    private static final String FIRST = "subject";
    private static final String LAST = "teacher";
    private static final String INDEX = "INDEX";
    public ListView listView;


    public void JSONURL (String result)   {
        String data="", data2="";
        try {

            JSONObject json = new JSONObject(result);

            JSONArray urls = json.getJSONArray("monday");

            for (int i = 0; i < urls.length(); i++) {
                HashMap<String, Object> hm;
                hm = new HashMap<String, Object>();

                hm.put(FIRST, urls.getJSONObject(i).getString("subject").toString());

                hm.put(LAST, urls.getJSONObject(i).getString("teacher").toString());
                hm.put(INDEX,i+1);
                myBooks.add(hm);

                SimpleAdapter adapter = new SimpleAdapter(getActivity(), myBooks, R.layout.list,
                      new String[] { FIRST, LAST, INDEX  }, new int[] { R.id.text12, R.id.text22, R.id.button2});

                listView.setAdapter(adapter);
                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);





            }






        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
    }



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.mon, container, false);
       
        listView = (ListView) v.findViewById(R.id.list);

        myBooks = new ArrayList<HashMap<String, Object>>();
        Bundle extras = getActivity().getIntent().getExtras();

            String json = extras.getString(JsonURL);

           JSONURL(json);




         return v;
    }







}
