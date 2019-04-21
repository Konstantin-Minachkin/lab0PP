package com.example.lab2ppp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Peredach> techList = new ArrayList<Peredach>();
    //ArrayList<Objects> techList = new ArrayList<Objects>();
    //private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ParseClass().execute();
    }

    class ParseClass extends AsyncTask<Void, Void, Void> {
        String url = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /* Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();*/

        }
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    jsonStr += "}";
                    jsonStr = "{\"objects\":" + jsonStr;
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    //Getting JSON Array node
                    JSONArray techs = jsonObj.getJSONArray("objects");

                    // looping through All
                    for (int i = 1; i < techs.length(); i++) {
                        JSONObject c = techs.getJSONObject(i);
                        String name = c.getString("name");
                        String pict = c.getString("graphic");
                        String help;
                        try {
                            help = c.getString("helptext");
                        }catch (final JSONException e){
                            help = "";
                        }
                        //Context h = getApplicationContext();
                        techList.add(new Peredach(name, help, pict));
                    }
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Intent intent = new Intent(MainActivity.this, Splashscreen.class);
            intent.putExtra("mas", techList);
            startActivity(intent);
        }
    }

}

