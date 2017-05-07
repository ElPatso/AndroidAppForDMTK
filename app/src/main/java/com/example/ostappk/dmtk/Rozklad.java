package com.example.ostappk.dmtk;



import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class Rozklad extends ActionBarActivity {
    android.support.v7.widget.Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"MON", "TUE", "WED", "THU", "FRI"};
    int Numboftabs = 5;
    private ActionBarDrawerToggle toggle;
    private ArrayList <HashMap<String, Object>> myBooks;
    private ListView listView;
    private static final String TEXT = "text";
    private static final String IMAGE = "image";
DialogFragment dlg1;
    Button btn;
    public String grupa ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dlg1 = new dialogcolor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rozklad);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
         btn = (Button) findViewById(R.id.button2);


        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);


        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
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
        hm.put(TEXT, getResources().getString(R.string.name1));
        hm.put(IMAGE, R.drawable.docum);

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

                    startActivity(new Intent(Rozklad.this, news.class));
                }
                if (strText.equalsIgnoreCase(getResources().getString(R.string.name2))) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://vk.com")));
                }
                if (strText.equalsIgnoreCase(getResources().getString(R.string.name1))) {

                    startActivity(new Intent(Rozklad.this, documents.class));
                }


            }
        });


        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });


        tabs.setViewPager(pager);


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item) {

            if (item.getItemId()==R.id.exit){
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






    public void someMethod() {
        Toast toast = Toast.makeText(getApplicationContext(), "LLAslalSLAS", Toast.LENGTH_LONG);
        toast.show();
        dlg1.dismiss();
    }

    public void onClick(View view) {
        grupa = getIntent().getExtras().getString("grupa");
        Toast toast = Toast.makeText(getApplicationContext(), grupa, Toast.LENGTH_LONG);
        toast.show();;
    }
}