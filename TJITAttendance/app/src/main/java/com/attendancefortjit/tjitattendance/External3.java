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
public class External3 extends Activity implements View.OnClickListener {
    Button fetch;
    TextView text;
    TextView submat3,intavgmat3,extmat3,marksmat3;
    TextView subec,intavgec,extec,marksec;
    TextView subld,intavgld,extld,marksld;
    TextView subdms,intavgdms,extdms,marksdms;
    TextView subds,intavgds,extds,marksds;
    TextView suboops,intavgoops,extoops,marksoops;
    TextView subdslab,intavgdslab,extdslab,marksdslab;
    TextView subecldlab,intavgecldlab,extecldlab,marksecldlab;
    TextView total,totalmarks;
    TextView results,resultvalue;
    TextView sub,intavg,external,marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external3);
        fetch= (Button) findViewById(R.id.bfetchext3);
        submat3 = (TextView) findViewById(R.id.tvmat3);
        intavgmat3 = (TextView) findViewById(R.id.tvintavgmat3);
        extmat3 = (TextView) findViewById(R.id.tvexternalmat3);
        marksmat3 = (TextView) findViewById(R.id.tvmarksmat3);

        subec = (TextView) findViewById(R.id.tvec);
        intavgec = (TextView) findViewById(R.id.tvintavgec);
        extec = (TextView) findViewById(R.id.tvexternalec);
        marksec = (TextView) findViewById(R.id.tvmarksec);

        subld = (TextView) findViewById(R.id.tvld);
        intavgld = (TextView) findViewById(R.id.tvintavgld);
        extld = (TextView) findViewById(R.id.tvexternalld);
        marksld = (TextView) findViewById(R.id.tvmarksld);

        subdms = (TextView) findViewById(R.id.tvdms);
        intavgdms = (TextView) findViewById(R.id.tvintavgdms);
        extdms = (TextView) findViewById(R.id.tvexternaldms);
        marksdms = (TextView) findViewById(R.id.tvmarksdms);

        subds = (TextView) findViewById(R.id.tvds);
        intavgds = (TextView) findViewById(R.id.tvintavgds);
        extds = (TextView) findViewById(R.id.tvexternalds);
        marksds = (TextView) findViewById(R.id.tvmarksds);

        suboops = (TextView) findViewById(R.id.tvoops);
        intavgoops = (TextView) findViewById(R.id.tvintavgoops);
        extoops = (TextView) findViewById(R.id.tvexternaloops);
        marksoops = (TextView) findViewById(R.id.tvmarksoops);

        subdslab = (TextView) findViewById(R.id.tvdslab);
        intavgdslab = (TextView) findViewById(R.id.tvintavgdslab);
        extdslab = (TextView) findViewById(R.id.tvexternaldslab);
        marksdslab = (TextView) findViewById(R.id.tvmarksdslab);

        subecldlab = (TextView) findViewById(R.id.tvecldlab);
        intavgecldlab = (TextView) findViewById(R.id.tvintavgecldlab);
        extecldlab = (TextView) findViewById(R.id.tvexternalecldlab);
        marksecldlab = (TextView) findViewById(R.id.tvmarksecldlab);

        total = (TextView) findViewById(R.id.tvtotalext3);
        totalmarks = (TextView) findViewById(R.id.tvtotalmarksext3);

        results = (TextView) findViewById(R.id.tvresultext3);
        resultvalue = (TextView) findViewById(R.id.tvresultvalueext3);

        sub = (TextView) findViewById(R.id.tvsubext3);
        intavg = (TextView) findViewById(R.id.tvintavgext3);
        external = (TextView) findViewById(R.id.tvexternalext3);
        marks = (TextView) findViewById(R.id.tvmarksext3);


        fetch.setOnClickListener(this);
    }

    class task extends AsyncTask<String, String, Void> {
        private ProgressDialog progressDialog = new ProgressDialog(External3.this);
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
            String url_select = "http://tjitattendance.esy.es/external3.php";

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

                        String math3 = Jasonobject.getString("mathematics3");
                        String[] math3separated;

                        if (math3.equals("NA")) {
                            submat3.setText("Math3 \t\t");
                            intavgmat3.setText("\t  NA");
                            extmat3.setText("\t  NA");
                            marksmat3.setText("\t  NA");
                        } else {
                            math3separated = math3.split("-");
                            submat3.setText("Math3 \t\t");
                            intavgmat3.setText("\t" + math3separated[0] + "\t\t");
                            extmat3.setText("\t" + math3separated[1] + "\t\t");
                            marksmat3.setText("\t" + math3separated[2]);
                        }

                        String ec = Jasonobject.getString("electroniccircuits");
                        String[] ecseparated;

                        if (ec.equals("NA")) {
                            subec.setText("EC \t\t");
                            intavgec.setText("\t  NA");
                            extec.setText("\t  NA");
                            marksec.setText("\t  NA");
                        } else {
                            ecseparated = ec.split("-");
                            subec.setText("EC \t\t");
                            intavgec.setText("\t" + ecseparated[0] + "\t\t");
                            extec.setText("\t" + ecseparated[1] + "\t\t");
                            marksec.setText("\t" + ecseparated[2]);
                        }

                        String ld = Jasonobject.getString("logicdesign");
                        String[] ldseparated;

                        if (ld.equals("NA")) {
                            subld.setText("LD \t\t");
                            intavgld.setText("\t  NA");
                            extld.setText("\t  NA");
                            marksld.setText("\t  NA");
                        } else {
                            ldseparated = ld.split("-");
                            subld.setText("LD \t\t");
                            intavgld.setText("\t" + ldseparated[0] + "\t\t");
                            extld.setText("\t" + ldseparated[1] + "\t\t");
                            marksld.setText("\t" + ldseparated[2]);
                        }

                        String dms = Jasonobject.getString("dms");
                        String[] dmsseparated;

                        if (dms.equals("NA")) {
                            subdms.setText("DMS \t\t");
                            intavgdms.setText("\t  NA");
                            extdms.setText("\t  NA");
                            marksdms.setText("\t  NA");
                        } else {
                            dmsseparated = dms.split("-");
                            subdms.setText("DMS \t\t");
                            intavgdms.setText("\t" + dmsseparated[0] + "\t\t");
                            extdms.setText("\t" + dmsseparated[1] + "\t\t");
                            marksdms.setText("\t" + dmsseparated[2]);
                        }

                        String ds = Jasonobject.getString("datastructures");
                        String[] dsseparated;

                        if (ds.equals("NA")) {
                            subds.setText("DS \t\t");
                            intavgds.setText("\t  NA");
                            extds.setText("\t  NA");
                            marksds.setText("\t  NA");
                        } else {
                            dsseparated = ds.split("-");
                            subds.setText("DS \t\t");
                            intavgds.setText("\t" + dsseparated[0] + "\t\t");
                            extds.setText("\t" + dsseparated[1] + "\t\t");
                            marksds.setText("\t" + dsseparated[2]);
                        }

                        String oops = Jasonobject.getString("oops");
                        String[] oopsseparated;

                        if (oops.equals("NA")) {
                            suboops.setText("OOPS \t\t");
                            intavgoops.setText("\t  NA");
                            extoops.setText("\t  NA");
                            marksoops.setText("\t  NA");
                        } else {
                            oopsseparated = oops.split("-");
                            suboops.setText("OOPS \t\t");
                            intavgoops.setText("\t" + oopsseparated[0] + "\t\t");
                            extoops.setText("\t" + oopsseparated[1] + "\t\t");
                            marksoops.setText("\t" + oopsseparated[2]);
                        }


                        String dslab = Jasonobject.getString("dslab");
                        String[] dslabseparated;

                        if (dslab.equals("NA")) {
                            subdslab.setText("DSLAB \t\t");
                            intavgdslab.setText("\t  NA");
                            extdslab.setText("\t  NA");
                            marksdslab.setText("\t  NA");
                        } else {
                            dslabseparated = dslab.split("-");
                            subdslab.setText("DSLAB \t\t");
                            intavgdslab.setText("\t" + dslabseparated[0] + "\t\t");
                            extdslab.setText("\t" + dslabseparated[1] + "\t\t");
                            marksdslab.setText("\t" + dslabseparated[2]);
                        }

                        String ecldlab = Jasonobject.getString("ecldlab");
                        String[] ecldlabseparated;

                        if (ecldlab.equals("NA")) {
                            subecldlab.setText("ECLDLAB \t\t");
                            intavgecldlab.setText("\t  NA");
                            extecldlab.setText("\t  NA");
                            marksecldlab.setText("\t  NA");
                        } else {
                            ecldlabseparated = ecldlab.split("-");
                            subecldlab.setText("ECLDLAB \t\t");
                            intavgecldlab.setText("\t" + ecldlabseparated[0] + "\t\t");
                            extecldlab.setText("\t" + ecldlabseparated[1] + "\t\t");
                            marksecldlab.setText("\t" + ecldlabseparated[2]);
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
            case R.id.bfetchext3 : new task().execute();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(External3.this,Sem3.class);
        startActivity(intent);
        finish();
    }
}
