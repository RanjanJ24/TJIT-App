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
public class External5 extends Activity implements View.OnClickListener {
    Button fetch;
    TextView text;
    TextView subse,intavgse,extse,marksse;
    TextView subss,intavgss,extss,marksss;
    TextView subos,intavgos,extos,marksos;
    TextView subdbms,intavgdbms,extdbms,marksdbms;
    TextView subcn1,intavgcn1,extcn1,markscn1;
    TextView subflat,intavgflat,extflat,marksflat;
    TextView subdbmslab,intavgdbmslab,extdbmslab,marksdbmslab;
    TextView subssoslab,intavgssoslab,extssoslab,marksssoslab;
    TextView total,totalmarks;
    TextView results,resultvalue;
    TextView sub,intavg,external,marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external5);
        fetch= (Button) findViewById(R.id.bfetchext5);
        subse = (TextView) findViewById(R.id.tvse);
        intavgse = (TextView) findViewById(R.id.tvintavgse);
        extse = (TextView) findViewById(R.id.tvexternalse);
        marksse = (TextView) findViewById(R.id.tvmarksse);

        subss = (TextView) findViewById(R.id.tvss);
        intavgss = (TextView) findViewById(R.id.tvintavgss);
        extss = (TextView) findViewById(R.id.tvexternalss);
        marksss = (TextView) findViewById(R.id.tvmarksss);

        subos = (TextView) findViewById(R.id.tvos);
        intavgos = (TextView) findViewById(R.id.tvintavgos);
        extos = (TextView) findViewById(R.id.tvexternalos);
        marksos = (TextView) findViewById(R.id.tvmarksos);

        subdbms = (TextView) findViewById(R.id.tvdbms);
        intavgdbms = (TextView) findViewById(R.id.tvintavgdbms);
        extdbms = (TextView) findViewById(R.id.tvexternaldbms);
        marksdbms = (TextView) findViewById(R.id.tvmarksdbms);

        subcn1 = (TextView) findViewById(R.id.tvcn1);
        intavgcn1 = (TextView) findViewById(R.id.tvintavgcn1);
        extcn1 = (TextView) findViewById(R.id.tvexternalcn1);
        markscn1 = (TextView) findViewById(R.id.tvmarkscn1);

        subflat = (TextView) findViewById(R.id.tvflat);
        intavgflat = (TextView) findViewById(R.id.tvintavgflat);
        extflat = (TextView) findViewById(R.id.tvexternalflat);
        marksflat = (TextView) findViewById(R.id.tvmarksflat);

        subdbmslab = (TextView) findViewById(R.id.tvdbmslab);
        intavgdbmslab = (TextView) findViewById(R.id.tvintavgdbmslab);
        extdbmslab = (TextView) findViewById(R.id.tvexternaldbmslab);
        marksdbmslab = (TextView) findViewById(R.id.tvmarksdbmslab);

        subssoslab = (TextView) findViewById(R.id.tvssoslab);
        intavgssoslab = (TextView) findViewById(R.id.tvintavgssoslab);
        extssoslab = (TextView) findViewById(R.id.tvexternalssoslab);
        marksssoslab = (TextView) findViewById(R.id.tvmarksssoslab);

        total = (TextView) findViewById(R.id.tvtotalext5);
        totalmarks = (TextView) findViewById(R.id.tvtotalmarksext5);

        results = (TextView) findViewById(R.id.tvresultext5);
        resultvalue = (TextView) findViewById(R.id.tvresultvalueext5);

        sub = (TextView) findViewById(R.id.tvsubext5);
        intavg = (TextView) findViewById(R.id.tvintavgext5);
        external = (TextView) findViewById(R.id.tvexternalext5);
        marks = (TextView) findViewById(R.id.tvmarksext5);


        fetch.setOnClickListener(this);
    }

    class task extends AsyncTask<String, String, Void> {
        private ProgressDialog progressDialog = new ProgressDialog(External5.this);
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
            String url_select = "http://tjitattendance.esy.es/external5.php";

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

                        String se = Jasonobject.getString("softwareengg");
                        String[] seseparated;

                        if (se.equals("NA")) {
                            subse.setText("SE \t\t");
                            intavgse.setText("\t  NA");
                            extse.setText("\t  NA");
                            marksse.setText("\t  NA");
                        } else {
                            seseparated = se.split("-");
                            subse.setText("SE \t\t");
                            intavgse.setText("\t" + seseparated[0] + "\t\t");
                            extse.setText("\t" + seseparated[1] + "\t\t");
                            marksse.setText("\t" + seseparated[2]);
                        }

                        String ss = Jasonobject.getString("systemssoftware");
                        String[] ssseparated;

                        if (ss.equals("NA")) {
                            subss.setText("SS \t\t");
                            intavgss.setText("\t  NA");
                            extss.setText("\t  NA");
                            marksss.setText("\t  NA");
                        } else {
                            ssseparated = ss.split("-");
                            subss.setText("SS \t\t");
                            intavgss.setText("\t" + ssseparated[0] + "\t\t");
                            extss.setText("\t" + ssseparated[1] + "\t\t");
                            marksss.setText("\t" + ssseparated[2]);
                        }

                        String os = Jasonobject.getString("operatingsystem");
                        String[] osseparated;

                        if (os.equals("NA")) {
                            subos.setText("OS \t\t");
                            intavgos.setText("\t  NA");
                            extos.setText("\t  NA");
                            marksos.setText("\t  NA");
                        } else {
                            osseparated = os.split("-");
                            subos.setText("OS \t\t");
                            intavgos.setText("\t" + osseparated[0] + "\t\t");
                            extos.setText("\t" + osseparated[1] + "\t\t");
                            marksos.setText("\t" + osseparated[2]);
                        }

                        String dbms = Jasonobject.getString("dbms");
                        String[] dbmsseparated;

                        if (dbms.equals("NA")) {
                            subdbms.setText("DBMS \t\t");
                            intavgdbms.setText("\t  NA");
                            extdbms.setText("\t  NA");
                            marksdbms.setText("\t  NA");
                        } else {
                            dbmsseparated = dbms.split("-");
                            subdbms.setText("DBMS \t\t");
                            intavgdbms.setText("\t" + dbmsseparated[0] + "\t\t");
                            extdbms.setText("\t" + dbmsseparated[1] + "\t\t");
                            marksdbms.setText("\t" + dbmsseparated[2]);
                        }

                        String cn1 = Jasonobject.getString("cn1");
                        String[] cn1separated;

                        if (cn1.equals("NA")) {
                            subcn1.setText("CN1 \t\t");
                            intavgcn1.setText("\t  NA");
                            extcn1.setText("\t  NA");
                            markscn1.setText("\t  NA");
                        } else {
                            cn1separated = cn1.split("-");
                            subcn1.setText("CN1 \t\t");
                            intavgcn1.setText("\t" + cn1separated[0] + "\t\t");
                            extcn1.setText("\t" + cn1separated[1] + "\t\t");
                            markscn1.setText("\t" + cn1separated[2]);
                        }

                        String flat = Jasonobject.getString("flat");
                        String[] flatseparated;

                        if (flat.equals("NA")) {
                            subflat.setText("FLAT \t\t");
                            intavgflat.setText("\t  NA");
                            extflat.setText("\t  NA");
                            marksflat.setText("\t  NA");
                        } else {
                            flatseparated = flat.split("-");
                            subflat.setText("FLAT \t\t");
                            intavgflat.setText("\t" + flatseparated[0] + "\t\t");
                            extflat.setText("\t" + flatseparated[1] + "\t\t");
                            marksflat.setText("\t" + flatseparated[2]);
                        }


                        String dbmslab = Jasonobject.getString("dbmslab");
                        String[] dbmslabseparated;

                        if (dbmslab.equals("NA")) {
                            subdbmslab.setText("DBMSLAB \t\t");
                            intavgdbmslab.setText("\t  NA");
                            extdbmslab.setText("\t  NA");
                            marksdbmslab.setText("\t  NA");
                        } else {
                            dbmslabseparated = dbmslab.split("-");
                            subdbmslab.setText("DBMSLAB \t\t");
                            intavgdbmslab.setText("\t" + dbmslabseparated[0] + "\t\t");
                            extdbmslab.setText("\t" + dbmslabseparated[1] + "\t\t");
                            marksdbmslab.setText("\t" + dbmslabseparated[2]);
                        }

                        String ssoslab = Jasonobject.getString("ssoslab");
                        String[] ssoslabseparated;

                        if (ssoslab.equals("NA")) {
                            subssoslab.setText("SSOSLAB \t\t");
                            intavgssoslab.setText("\t  NA");
                            extssoslab.setText("\t  NA");
                            marksssoslab.setText("\t  NA");
                        } else {
                            ssoslabseparated = ssoslab.split("-");
                            subssoslab.setText("SSOSLAB \t\t");
                            intavgssoslab.setText("\t" + ssoslabseparated[0] + "\t\t");
                            extssoslab.setText("\t" + ssoslabseparated[1] + "\t\t");
                            marksssoslab.setText("\t" + ssoslabseparated[2]);
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
            case R.id.bfetchext5 : new task().execute();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(External5.this,Sem5.class);
        startActivity(intent);
        finish();
    }
}
