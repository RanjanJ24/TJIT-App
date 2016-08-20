package com.attendancefortjit.tjitattendance;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Data extends Activity implements OnClickListener{
    Button fetch;
    TextView text;
    TextView subsa,outsa,attendedsa,persa;
    TextView subsms,outsms,attendedsms,persms;
    TextView subweb,outweb,attendedweb,perweb;
    TextView subins,outins,attendedins,perins;
    TextView subst,outst,attendedst,perst;
    TextView totalpercentage;
    TextView sub,outof,attended,percentage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        fetch= (Button) findViewById(R.id.fetch);
        text = (TextView) findViewById(R.id.text);
        subsa = (TextView) findViewById(R.id.tvsa);
        outsa = (TextView) findViewById(R.id.tvoutsa);
        attendedsa = (TextView) findViewById(R.id.tvattsa);
        persa = (TextView) findViewById(R.id.tvpersa);

        subsms = (TextView) findViewById(R.id.tvsms);
        outsms = (TextView) findViewById(R.id.tvoutsms);
        attendedsms = (TextView) findViewById(R.id.tvattsms);
        persms = (TextView) findViewById(R.id.tvpersms);

        subweb = (TextView) findViewById(R.id.tvweb);
        outweb = (TextView) findViewById(R.id.tvoutweb);
        attendedweb = (TextView) findViewById(R.id.tvattweb);
        perweb = (TextView) findViewById(R.id.tvperweb);

        subins = (TextView) findViewById(R.id.tvins);
        outins = (TextView) findViewById(R.id.tvoutins);
        attendedins = (TextView) findViewById(R.id.tvattins);
        perins = (TextView) findViewById(R.id.tvperins);

        subst = (TextView) findViewById(R.id.tvst);
        outst = (TextView) findViewById(R.id.tvoutst);
        attendedst = (TextView) findViewById(R.id.tvattst);
        perst = (TextView) findViewById(R.id.tvperst);

        totalpercentage = (TextView) findViewById(R.id.tvtotalpercent);

        sub = (TextView) findViewById(R.id.tvsub);
        outof = (TextView) findViewById(R.id.tvoutof);
        attended = (TextView) findViewById(R.id.tvattended);
        percentage = (TextView) findViewById(R.id.tvpercentage);

        fetch.setOnClickListener(this);
    }

    class task extends AsyncTask<String, String, Void>
    {
        private ProgressDialog progressDialog = new ProgressDialog(Data.this);
        InputStream is = null ;
        String result = "";
        protected void onPreExecute() {
            progressDialog.setMessage("Fetching data...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    task.this.cancel(true);
                }
            });
        }
        @Override
        protected Void doInBackground(String... params) {
            String url_select = "http://tjitattendance.esy.es/attendance.php";

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
                    String studentid = Jasonobject.getString("usn");
                    String db_detail="";


                    Intent intent = getIntent();
                    String struser = intent.getStringExtra("message");

                    if(struser.equals(studentid)) {

                        String attsa = Jasonobject.getString("sa");
                        String[] saseparated = attsa.split("-");

                        String attweb = Jasonobject.getString("web");
                        String[] webseparated;

                        if(attweb.equals("N/A")){
                            webseparated = attweb.split("/");
                            subweb.setText("WEB 2.0 \t\t");
                            outweb.setText("\t  0");
                            attendedweb.setText("\t  0");
                            perweb.setText("\t  0%");
                        }
                        else{
                            webseparated = attweb.split("-");
                            subweb.setText("WEB 2.0 \t\t");
                            outweb.setText("\t"+ webseparated[0] + "\t\t");
                            attendedweb.setText("\t"+ webseparated[1]+ "\t\t");
                            perweb.setText("\t" + webseparated[2]);
                        }

                        String attsms = Jasonobject.getString("sms");
                        String[] smsseparated = attsms.split("-");

                        String attins = Jasonobject.getString("ins");
                        String[] insseparated = attins.split("-");

                        String attst = Jasonobject.getString("softwaretesting");
                        String[] stseparated = attst.split("-");

                        String total = Jasonobject.getString("totalpercent");
                        totalpercentage.setText("Total Percentage = "+total);


                        sub.setText("SUB");
                        outof.setText("CLASSES");
                        attended.setText("ATTENDED");
                        percentage.setText("%");


                        subsa.setText("SA \t\t");
                        outsa.setText("\t"+ saseparated[0] + "\t\t");
                        attendedsa.setText("\t"+ saseparated[1]+ "\t\t");
                        persa.setText("\t" + saseparated[2]);

                        subsms.setText("SMS\t\t");
                        outsms.setText("\t"+ smsseparated[0] + "\t\t");
                        attendedsms.setText("\t"+ smsseparated[1]+ "\t\t");
                        persms.setText("\t" + smsseparated[2]);

                        subweb.setText("WEB 2.0 \t\t");
                        outweb.setText("\t"+ webseparated[0] + "\t\t");
                        attendedweb.setText("\t"+ webseparated[1]+ "\t\t");
                        perweb.setText("\t" + webseparated[2]);

                        subins.setText("INS \t\t");
                        outins.setText("\t"+ insseparated[0] + "\t\t");
                        attendedins.setText("\t"+ insseparated[1]+ "\t\t");
                        perins.setText("\t" + insseparated[2]);

                        subst.setText("ST \t\t");
                        outst.setText("\t"+ stseparated[0] + "\t\t");
                        attendedst.setText("\t"+ stseparated[1]+ "\t\t");
                        perst.setText("\t" + stseparated[2]);

                        break;
                    }
                    //text_1.append(id+"\t\t"+name+"\t\t"+password+"\t\t"+"\n");

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
        // TODO Auto-generated method stub
        switch(v.getId()) {
            case R.id.fetch : new task().execute();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Data.this,Welcome.class);
        startActivity(intent);
        finish();
    }
}