package com.attendancefortjit.tjitattendance;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
 * Created by jbran on 02-11-2015.
 */


public class Internal1 extends Activity implements View.OnClickListener {

    Button fetch;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal1);

        fetch= (Button) findViewById(R.id.bfetchint1);
        text = (TextView) findViewById(R.id.tvinternal);
        fetch.setOnClickListener(this);
    }



    class task extends AsyncTask<String, String, Void>
    {
        private ProgressDialog progressDialog = new ProgressDialog(Internal1.this);
        InputStream is = null ;
        String result = "";
        protected void onPreExecute() {
            progressDialog.setMessage("Fetching data...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    task.this.cancel(true);
                }
            });
        }
        @Override
        protected Void doInBackground(String... params) {
            String url_select = "http://tjitattendance.esy.es/internal.php";

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

                Log.e("log_tag", "Error in http connection " + e.toString());
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
                    String studentid = Jasonobject.getString("studentid");
                    String db_detail="";


                    Intent intent = getIntent();
                    String struser = intent.getStringExtra("message");

                    if(struser.equals(studentid)) {

                        String internal1 = Jasonobject.getString("internal1");
                        String internal2 = Jasonobject.getString("internal2");
                        String internal3 = Jasonobject.getString("internal3");
                        String avg = Jasonobject.getString("averageint");
                        String total = Jasonobject.getString("total");
                        text.setText("Total is " + total + ", 1st Internal is " + internal1 + ", 2nd Internal is " + internal2 + ", " +
                                "3rd Internal is" +
                                internal3 + ", " +
                                "Average is " + avg);
                    }
                }
                this.progressDialog.dismiss();
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error parsing data "+e.toString());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bfetchint1 : new task().execute();
                break;
        }
    }























    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Internal1.this,Sem1.class);
        startActivity(intent);
        finish();
    }
}
