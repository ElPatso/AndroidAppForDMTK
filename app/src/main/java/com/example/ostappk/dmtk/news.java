package com.example.ostappk.dmtk;

import android.app.Application;
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
import android.widget.ArrayAdapter;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class news extends ActionBarActivity {
    private static final String TEXT = "text";
    private static final String IMAGE = "image";
    private ActionBarDrawerToggle toggle;
    private ArrayList<HashMap<String, Object>> myBooks;
    private ListView listView;
    android.support.v7.widget.Toolbar toolbar;
    public Elements title;
    private ProgressDialog dialog;
     ArrayList<String> titleList = new ArrayList<String>();

    private ArrayAdapter<String> adapter1;

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
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
        HashMap<String, Object> hm;
        hm = new HashMap<String, Object>();
        hm.put(TEXT, getResources().getString(R.string.name4));
        hm.put(IMAGE, R.drawable.rozklad);
        myBooks.add(hm);
        hm = new HashMap<String, Object>();

        hm.put(TEXT, getResources().getString(R.string.name2));
        hm.put(IMAGE, R.drawable.vk);

        myBooks.add(hm);
        hm = new HashMap<String, Object>();
        hm.put(TEXT, getResources().getString(R.string.name1));
        hm.put(IMAGE, R.drawable.docum);

        myBooks.add(hm);
        SimpleAdapter adapter = new SimpleAdapter(this, myBooks, R.layout.bokovemenu, new String[]{
                TEXT, IMAGE
        }, new int[]{R.id.bokove, R.id.image});
        listView.setAdapter(adapter);
        //listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                LinearLayout ll = (LinearLayout) itemClicked;
                TextView textView = (TextView) ll.findViewById(R.id.bokove);
                String strText = textView.getText().toString();

                if (strText.equalsIgnoreCase(getResources().getString(R.string.name4))) {

                    new RequestTask().execute("http://dmtk.esy.es/login.php");
                }
                if (strText.equalsIgnoreCase(getResources().getString(R.string.name2))) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://vk.com")));
                }
                if (strText.equalsIgnoreCase(getResources().getString(R.string.name1))) {

                    startActivity(new Intent(news.this, documents.class));
                }


            }
        });
        lv = (ListView) findViewById(R.id.list1);

        new NewThread().execute();

        adapter1 = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, titleList);
        lv.post(new Runnable() {
            @Override
            public void run() {
                lv.setSelection(lv.getCount() - 1);
            }
        });

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



                Intent intent = new Intent(news.this, Rozklad.class);
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

            dialog = new ProgressDialog(news.this);
            dialog.setMessage("");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setIndeterminate(true);
            dialog.setCancelable(true);

            super.onPreExecute();
        }
    }

    public class NewThread extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... arg) {


            Document doc;
            try {

                doc = Jsoup.connect("http://dmtc.org.ua/?cat=8").get();

                title = doc.select("h2");

                titleList.clear();

                for (Element titles : title) {

                    titleList.add(titles.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {


            lv.setAdapter(adapter1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {

        } else {
            if (toggle.onOptionsItemSelected(item))
                return true;
        }
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
}
