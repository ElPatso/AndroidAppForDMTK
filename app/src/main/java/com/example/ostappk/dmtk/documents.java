package com.example.ostappk.dmtk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class documents extends ActionBarActivity {
    private static final String TEXT = "text";
    private static final String IMAGE = "image";
    private ActionBarDrawerToggle toggle;
    private ArrayList<HashMap<String, Object>> myBooks;
    private ListView listView;
    private static final String TEXT1 = "text";
    private static final String TEXT2= "Text";
    android.support.v7.widget.Toolbar toolbar;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(toggle);
        listView = (ListView) findViewById(R.id.lv_navigation_drawer);
        myBooks = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object>hm;
        hm = new HashMap<String, Object>();
        hm.put(TEXT, getResources().getString(R.string.name3));
        hm.put(IMAGE, R.drawable.news);
        myBooks.add(hm);
        hm = new HashMap<String, Object>();

        hm.put(TEXT, getResources().getString(R.string.name2));
        hm.put(IMAGE, R.drawable.vk);

        myBooks.add(hm);
        hm = new HashMap<String, Object>();
        hm.put(TEXT, getResources().getString(R.string.name4));
        hm.put(IMAGE, R.drawable.rozklad);

        myBooks.add(hm);
        SimpleAdapter adapter = new SimpleAdapter(this, myBooks, R.layout.bokovemenu, new String[]{
                TEXT, IMAGE
        }, new int[]{R.id.bokove, R.id.image} );
        listView.setAdapter(adapter);
        //listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                LinearLayout ll = (LinearLayout) itemClicked;
                TextView textView = (TextView) ll.findViewById(R.id.bokove);
                String strText = textView.getText().toString();

                if (strText.equalsIgnoreCase(getResources().getString(R.string.name3))) {

                    startActivity(new Intent(documents.this, news.class));
                }
                if (strText.equalsIgnoreCase(getResources().getString(R.string.name2))) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://vk.com")));
                }
                if (strText.equalsIgnoreCase(getResources().getString(R.string.name4))) {

                    new RequestTask().execute("http://dmtk.esy.es/login.php");
                }


            }
        });

        listView = (ListView) findViewById(R.id.list1);
        myBooks = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object>hm1;
        hm1 = new HashMap<String, Object>();
        hm1.put(TEXT1, "Методчні вказівки для курсової роботи для груп ПК");
        hm1.put(TEXT2, "Курсова Java");
        myBooks.add(hm1);

        hm1 = new HashMap<String, Object>();
        hm1.put(TEXT1, "Методичні вказівки для груп ПК");
        hm1.put(TEXT2, "МВ для підготовки до Державного екзамену");
        myBooks.add(hm1);

        hm1 = new HashMap<String, Object>();
        hm1.put(TEXT1, "Завдання для контрольних робіт");
        hm1.put(TEXT2, "Конрольні ООП");
        myBooks.add(hm1);

        hm1 = new HashMap<String, Object>();
        hm1.put(TEXT1, "Людино-машинний інтерфейс");
        hm1.put(TEXT2, "Методичні вказівки Людино-машинний інтерфейс");
        myBooks.add(hm1);

        SimpleAdapter adapter1 = new SimpleAdapter(this, myBooks, R.layout.url, new String[]{TEXT1, TEXT2}, new int[]{R.id.tx, R.id.tx2});
        listView.setAdapter(adapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout lay = (LinearLayout) view;
                TextView txt = (TextView) lay.findViewById(R.id.tx);
                String str = txt.getText().toString();

                if (str.equalsIgnoreCase(getResources().getString(R.string.do1))) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://dmtc.org.ua/wp-content/uploads/2014/11/Metod_vkazivku_kyrsova_Java.doc")));
                }
                if (str.equalsIgnoreCase(getResources().getString(R.string.do2))) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://dmtc.org.ua/wp-content/uploads/2014/03/Metoduchka_derg_.zip")));
                }
                if (str.equalsIgnoreCase(getResources().getString(R.string.do3))) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://dmtc.org.ua/wp-content/uploads/2012/02/%D0%BA%D0%BE%D0%BD%D1%80%D0%BE%D0%BB%D1%8C%D0%BD%D1%96-%D0%9E%D0%9E%D0%9F1.doc")));
                }
                if (str.equalsIgnoreCase(getResources().getString(R.string.do4))) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://dmtc.org.ua/?p=408")));
                }

            }
        });





    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_documents, menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.action_settings){
            System.exit(0);}
        else  {
            if (toggle.onOptionsItemSelected(item))
                return true; }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }
    class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            try {

                String a="PK-41";
                DefaultHttpClient hc = new DefaultHttpClient();
                ResponseHandler<String> res = new BasicResponseHandler();
                HttpPost postMethod = new HttpPost(params[0]);


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("login", a));

                postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                String response = hc.execute(postMethod, res);



                Intent intent = new Intent(documents.this, Rozklad.class);
                intent.putExtra(monday.JsonURL,  response.toString());
                intent.putExtra(Thursday.JsonURL, response.toString());
                intent.putExtra(Tuesday.JsonURL, response.toString());
                intent.putExtra(wednesday.JsonURL, response.toString());
                intent.putExtra(Friday.JsonURL, response.toString());

                startActivity(intent);

            } catch (Exception e) {
                System.out.println("Exp=" + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            dialog.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {

            dialog = new ProgressDialog(documents.this);
            dialog.setMessage("");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setIndeterminate(true);
            dialog.setCancelable(true);

            super.onPreExecute();
        }
    }
}
