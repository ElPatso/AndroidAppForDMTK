package com.example.ostappk.dmtk;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Main extends ActionBarActivity {
    public EditText login;

    private ProgressDialog dialog;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(EditText)findViewById(R.id.editText);
        Button button = (Button)findViewById(R.id.button);
         edit = (EditText ) findViewById(R.id.editText);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] spusok = {"PK-41", "PK-42"};
                for (int i = 0; i < spusok.length; i++) {
                    if (edit.getText().toString().equals(spusok[i])) {
                        new RequestTask().execute("http://dmtk.esy.es/login.php"); break;
                    }


                 else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Групи з таким кодом не існує, або виникла помилка на сервері.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.FILL_HORIZONTAL, 0, -9000);
                    LinearLayout toastContainer = (LinearLayout) toast.getView();

                    toast.show();
                } }


            }
        });
             
    }


    class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            try {


                DefaultHttpClient hc = new DefaultHttpClient();
                ResponseHandler<String> res = new BasicResponseHandler();
                HttpPost postMethod = new HttpPost(params[0]);


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("login", login.getText().toString()));

                postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                String response = hc.execute(postMethod, res);



                Intent intent = new Intent(Main.this, Rozklad.class);
              intent.putExtra(monday.JsonURL,  response.toString());
         intent.putExtra(Thursday.JsonURL, response.toString());
         intent.putExtra(Tuesday.JsonURL, response.toString());
                intent.putExtra(wednesday.JsonURL, response.toString());
               intent.putExtra(Friday.JsonURL, response.toString());

                intent.putExtra("grupa", edit.getText().toString());

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

            dialog = new ProgressDialog(Main.this);
            dialog.setMessage("");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setIndeterminate(true);
            dialog.setCancelable(true);
            dialog.show();
            super.onPreExecute();
        }
    }

   }
