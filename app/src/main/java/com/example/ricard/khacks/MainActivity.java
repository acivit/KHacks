package com.example.ricard.khacks;

import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;
    private ArrayList<Hackathon> hackathons = new ArrayList<>();
    private MyCustomAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        adapter = new MyCustomAdapter(getApplicationContext(), hackathons);

        fetchData();
        mListView.setAdapter(adapter);

        postData();
    }

    void fetchData() {
        adapter.add(new Hackathon("Kairos Hacks", "Barcelona, Spain", "28/02/2015", null));
        adapter.add(new Hackathon("FIBHACK", "Barcelona, Spain", "18/04/2015", null));
        SkyscannerBackGround skyTask = new SkyscannerBackGround();
        skyTask.execute();

    }

    public void postData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://partners.api.skyscanner.net/apiservices/pricing/v1.0");
        httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        httppost.setHeader("Accept", "application/json");

        try {
            // Add your data
            //List<NameValuePair>pairs = new ArrayList<NameValuePair>();
            JSONObject pairs = new JSONObject();
            pairs.put("apiKey", getString(R.string.apiKey));
            pairs.put("country", "UK");
            pairs.put("currency", "GBP");
            pairs.put("locale", "en-GB");
            pairs.put("originplace", "EDI");
            pairs.put("destinationplace", "LHR");
            pairs.put("outbounddate", new Date(2015,3,7));
            pairs.put("adults", 1);

            StringEntity se = new StringEntity(pairs.toString());
            httppost.setEntity(se);
            //httppost.setEntity(new StringEntity(pairs.toString(), "UTF-8"));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            /*String responseStr = EntityUtils.toString(response.getEntity());
            Log.w("test", responseStr);*/

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class SkyscannerBackGround extends AsyncTask<Void, Void, HttpResponse> {

        @Override
        protected HttpResponse doInBackground(Void... params) {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://partners.api.skyscanner.net/apiservices/pricing/v1.0");
            httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httppost.setHeader("Accept", "application/json");
            HttpResponse response = null;

            try {
                // Add your data
                //List<NameValuePair>pairs = new ArrayList<NameValuePair>();
                JSONObject pairs = new JSONObject();
                pairs.put("apiKey", getString(R.string.apiKey));
                pairs.put("country", "UK");
                pairs.put("currency", "GBP");
                pairs.put("locale", "en-GB");
                pairs.put("originplace", "EDI");
                pairs.put("destinationplace", "LHR");
                pairs.put("outbounddate", new Date(2015,3,7));
                pairs.put("adults", 1);

                StringEntity se = new StringEntity(pairs.toString());
                httppost.setEntity(se);
                //httppost.setEntity(new StringEntity(pairs.toString(), "UTF-8"));

                // Execute HTTP Post Request
                response = httpclient.execute(httppost);
            /*String responseStr = EntityUtils.toString(response.getEntity());
            Log.w("test", responseStr);*/

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return response;
        }
    }
}
