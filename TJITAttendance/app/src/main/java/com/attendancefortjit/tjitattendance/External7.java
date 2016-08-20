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
 * Created by RJ on 26/03/16.
 */
public class External7 extends Activity implements View.OnClickListener {
    Button fetch;
    TextView text;
    TextView suboomd,intavgoomd,extoomd,marksoomd;
    TextView subecs,intavgecs,extecs,marksecs;
    TextView subweb,intavgweb,extweb,marksweb;
    TextView subaca,intavgaca,extaca,marksaca;
    TextView subjava,intavgjava,extjava,marksjava;
    TextView subaisan,intavgaisan,extaisan,marksaisan;
    TextView subnetlab,intavgnetlab,extnetlab,marksnetlab;
    TextView subweblab,intavgweblab,extweblab,marksweblab;
    TextView total,totalmarks;
    TextView results,resultvalue;
    TextView sub,intavg,external,marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external7);
        fetch= (Button) findViewById(R.id.bfetchext7);
        suboomd = (TextView) findViewById(R.id.tvoomd);
        intavgoomd = (TextView) findViewById(R.id.tvintavgoomd);
        extoomd = (TextView) findViewById(R.id.tvexternaloomd);
        marksoomd = (TextView) findViewById(R.id.tvmarksoomd);

        subecs = (TextView) findViewById(R.id.tvecs);
        intavgecs = (TextView) findViewById(R.id.tvintavgecs);
        extecs = (TextView) findViewById(R.id.tvexternalecs);
        marksecs = (TextView) findViewById(R.id.tvmarksecs);

        subweb = (TextView) findViewById(R.id.tvweb);
        intavgweb = (TextView) findViewById(R.id.tvintavgweb);
        extweb = (TextView) findViewById(R.id.tvexternalweb);
        marksweb = (TextView) findViewById(R.id.tvmarksweb);

        subaca = (TextView) findViewById(R.id.tvaca);
        intavgaca = (TextView) findViewById(R.id.tvintavgaca);
        extaca = (TextView) findViewById(R.id.tvexternalaca);
        marksaca = (TextView) findViewById(R.id.tvmarksaca);

        subjava = (TextView) findViewById(R.id.tvjava);
        intavgjava = (TextView) findViewById(R.id.tvintavgjava);
        extjava = (TextView) findViewById(R.id.tvexternaljava);
        marksjava = (TextView) findViewById(R.id.tvmarksjava);

        subaisan = (TextView) findViewById(R.id.tvaisan);
        intavgaisan = (TextView) findViewById(R.id.tvintavgaisan);
        extaisan = (TextView) findViewById(R.id.tvexternalaisan);
        marksaisan = (TextView) findViewById(R.id.tvmarksaisan);

        subnetlab = (TextView) findViewById(R.id.tvnetlab);
        intavgnetlab = (TextView) findViewById(R.id.tvintavgnetlab);
        extnetlab = (TextView) findViewById(R.id.tvexternalnetlab);
        marksnetlab = (TextView) findViewById(R.id.tvmarksnetlab);

        subweblab = (TextView) findViewById(R.id.tvweblab);
        intavgweblab = (TextView) findViewById(R.id.tvintavgweblab);
        extweblab = (TextView) findViewById(R.id.tvexternalweblab);
        marksweblab = (TextView) findViewById(R.id.tvmarksweblab);

        total = (TextView) findViewById(R.id.tvtotalext7);
        totalmarks = (TextView) findViewById(R.id.tvtotalmarksext7);

        results = (TextView) findViewById(R.id.tvresultext7);
        resultvalue = (TextView) findViewById(R.id.tvresultvalueext7);

        sub = (TextView) findViewById(R.id.tvsubext7);
        intavg = (TextView) findViewById(R.id.tvintavgext7);
        external = (TextView) findViewById(R.id.tvexternalext7);
        marks = (TextView) findViewById(R.id.tvmarksext7);


        fetch.setOnClickListener(this);
    }

    class task extends AsyncTask<String, String, Void> {
        private ProgressDialog progressDialog = new ProgressDialog(External7.this);
        InputStream is = null;
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
            String url_select = "http://tjitattendance.esy.es/external7.php";

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_select);

            ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(param));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();

                //read content
                is = httpEntity.getContent();

            } catch (Exception e) {

                Log.e("log_tag", "Error in http connection " + e.toString());
                //Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_LONG).show();
            }
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error converting result " + e.toString());
            }

            return null;

        }

        public void onPostExecute(Void v) {

            // ambil data dari Json database
            try {
                JSONArray Jarray = new JSONArray(result);
                for (int i = 0; i < Jarray.length(); i++) {
                    JSONObject Jasonobject = null;
                    //text_1 = (TextView)findViewById(R.id.txt1);
                    Jasonobject = Jarray.getJSONObject(i);

                    //get an output on the screen
                    //String id = Jasonobject.getString("id");
                    String studentusn = Jasonobject.getString("studentusn");
                    String db_detail = "";


                    Intent intent = getIntent();
                    String struser = intent.getStringExtra("message");

                    if (struser.equals(studentusn)) {

                        String oomd = Jasonobject.getString("oomd");
                        String[] oomdseparated;

                        if (oomd.equals("NA")) {
                            suboomd.setText("OOMD \t\t");
                            intavgoomd.setText("\t  NA");
                            extoomd.setText("\t  NA");
                            marksoomd.setText("\t  NA");
                        } else {
                            oomdseparated = oomd.split("-");
                            suboomd.setText("OOMD \t\t");
                            intavgoomd.setText("\t" + oomdseparated[0] + "\t\t");
                            extoomd.setText("\t" + oomdseparated[1] + "\t\t");
                            marksoomd.setText("\t" + oomdseparated[2]);
                        }

                        String ecs = Jasonobject.getString("ecs");
                        String[] ecsseparated;

                        if (ecs.equals("NA")) {
                            subecs.setText("ECS \t\t");
                            intavgecs.setText("\t  NA");
                            extecs.setText("\t  NA");
                            marksecs.setText("\t  NA");
                        } else {
                            ecsseparated = ecs.split("-");
                            subecs.setText("ECS \t\t");
                            intavgecs.setText("\t" + ecsseparated[0] + "\t\t");
                            extecs.setText("\t" + ecsseparated[1] + "\t\t");
                            marksecs.setText("\t" + ecsseparated[2]);
                        }

                        String web = Jasonobject.getString("web");
                        String[] webseparated;

                        if (web.equals("NA")) {
                            subweb.setText("WEB \t\t");
                            intavgweb.setText("\t  NA");
                            extweb.setText("\t  NA");
                            marksweb.setText("\t  NA");
                        } else {
                            webseparated = web.split("-");
                            subweb.setText("WEB \t\t");
                            intavgweb.setText("\t" + webseparated[0] + "\t\t");
                            extweb.setText("\t" + webseparated[1] + "\t\t");
                            marksweb.setText("\t" + webseparated[2]);
                        }

                        String aca = Jasonobject.getString("aca");
                        String[] acaseparated;

                        if (aca.equals("NA")) {
                            subaca.setText("ACA \t\t");
                            intavgaca.setText("\t  NA");
                            extaca.setText("\t  NA");
                            marksaca.setText("\t  NA");
                        } else {
                            acaseparated = aca.split("-");
                            subaca.setText("ACA \t\t");
                            intavgaca.setText("\t" + acaseparated[0] + "\t\t");
                            extaca.setText("\t" + acaseparated[1] + "\t\t");
                            marksaca.setText("\t" + acaseparated[2]);
                        }

                        String java = Jasonobject.getString("java");
                        String[] javaseparated;

                        if (java.equals("NA")) {
                            subjava.setText("JAVA \t\t");
                            intavgjava.setText("\t  NA");
                            extjava.setText("\t  NA");
                            marksjava.setText("\t  NA");
                        } else {
                            javaseparated = java.split("-");
                            subjava.setText("JAVA \t\t");
                            intavgjava.setText("\t" + javaseparated[0] + "\t\t");
                            extjava.setText("\t" + javaseparated[1] + "\t\t");
                            marksjava.setText("\t" + javaseparated[2]);
                        }

                        String aisan = Jasonobject.getString("ai/san");
                        String[] aisanseparated;

                        if (aisan.equals("NA")) {
                            subaisan.setText("AI/SAN \t\t");
                            intavgaisan.setText("\t  NA");
                            extaisan.setText("\t  NA");
                            marksaisan.setText("\t  NA");
                        } else {
                            aisanseparated = aisan.split("-");
                            subaisan.setText("AI/SAN \t\t");
                            intavgaisan.setText("\t" + aisanseparated[0] + "\t\t");
                            extaisan.setText("\t" + aisanseparated[1] + "\t\t");
                            marksaisan.setText("\t" + aisanseparated[2]);
                        }


                        String netlab = Jasonobject.getString("netlab");
                        String[] netlabseparated;

                        if (netlab.equals("NA")) {
                            subnetlab.setText("NETLAB \t\t");
                            intavgnetlab.setText("\t  NA");
                            extnetlab.setText("\t  NA");
                            marksnetlab.setText("\t  NA");
                        } else {
                            netlabseparated = netlab.split("-");
                            subnetlab.setText("NETLAB \t\t");
                            intavgnetlab.setText("\t" + netlabseparated[0] + "\t\t");
                            extnetlab.setText("\t" + netlabseparated[1] + "\t\t");
                            marksnetlab.setText("\t" + netlabseparated[2]);
                        }

                        String weblab = Jasonobject.getString("weblab");
                        String[] weblabseparated;

                        if (weblab.equals("NA")) {
                            subweblab.setText("WEBLAB \t\t");
                            intavgweblab.setText("\t  NA");
                            extweblab.setText("\t  NA");
                            marksweblab.setText("\t  NA");
                        } else {
                            weblabseparated = weblab.split("-");
                            subweblab.setText("WEBLAB \t\t");
                            intavgweblab.setText("\t" + weblabseparated[0] + "\t\t");
                            extweblab.setText("\t" + weblabseparated[1] + "\t\t");
                            marksweblab.setText("\t" + weblabseparated[2]);
                        }

                        String total1 = Jasonobject.getString("total");
                        String[] totalseparated;

                        if (total1.equals("NA")) {
                            total.setText("TOTAL \t\t");
                            totalmarks.setText("\t  NA");
                        } else {
                            total.setText("TOTAL \t\t");
                            totalmarks.setText("\t" + total1);
                        }

                        String result1 = Jasonobject.getString("result");
                        String[] resultseparated;

                        if (result1.equals("NA")) {
                            results.setText("RESULT \t\t");
                            resultvalue.setText("\t  NA");
                        } else {
                            results.setText("RESULT \t\t");
                            resultvalue.setText("\t" + result1);
                        }

                        sub.setText("SUB");
                        intavg.setText("IntAvg");
                        external.setText("External");
                        marks.setText("Marks");

                        break;

                    }
                }
                this.progressDialog.dismiss();
            }catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error parsing data "+e.toString());
            }
        }
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bfetchext7 : new task().execute();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(External7.this,Sem7.class);
        startActivity(intent);
        finish();
    }
}
