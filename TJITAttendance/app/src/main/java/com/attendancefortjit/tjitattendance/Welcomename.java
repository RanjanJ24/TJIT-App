package com.attendancefortjit.tjitattendance;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseUser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by jbran on 28-10-2015.
 */
public class Welcomename extends Activity{

    TextView welcome,welname;
    Button fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomename);
        welcome = (TextView) findViewById(R.id.tvwelcome);
        welname = (TextView) findViewById(R.id.tvwelname);
        new task().execute();
    }
    class task extends AsyncTask<String, String, Void>
    {
        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        final String struser = currentUser.getUsername().toString();


        private ProgressDialog progressDialog = new ProgressDialog(Welcomename.this);
        InputStream is = null ;
        String result = "";

        @Override
        protected Void doInBackground(String... params) {
            String url_select = "http://tjitattendance.esy.es/welcomename.php";

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_select);

            ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(param));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();

                //read content
                is =  httpEntity.getContent();

            } catch (Exception e) {

                Log.e("log_tag", "Error in http connection "+e.toString());
                //Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_LONG).show();
            }
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while((line=br.readLine())!=null)
                {
                    sb.append(line+"\n");
                }
                is.close();
                result=sb.toString();

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error converting result "+e.toString());
            }

            return null;

        }
        public void onPostExecute(Void v) {

            // ambil data dari Json database
            try {
                JSONArray Jarray = new JSONArray(result);
                for(int i=0;i<Jarray.length();i++)
                {
                    JSONObject Jasonobject = null;
                    //text_1 = (TextView)findViewById(R.id.txt1);
                    Jasonobject = Jarray.getJSONObject(i);

                    //get an output on the screen
                    //String id = Jasonobject.getString("id");
                    String studentusn = Jasonobject.getString("studentusn");
                    String db_detail="";


                    Intent intent = getIntent();

                    if(struser.equals(studentusn)) {

                        String name = Jasonobject.getString("studentname");

                        welname.setText("WELCOME  "+name);
                        welname.setTextColor(Color.parseColor("#ffffff"));
                        break;
                    }
                    //text_1.append(id+"\t\t"+name+"\t\t"+password+"\t\t"+"\n");

                }


                Thread timerThread = new Thread(){
                    public void run(){
                        try{
                            sleep(3000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }finally{
                            Intent intent = new Intent(Welcomename.this,Welcome.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                };
                timerThread.start();




            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error parsing data "+e.toString());
            }
        }
    }
}
