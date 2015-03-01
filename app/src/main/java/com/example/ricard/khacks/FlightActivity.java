package com.example.ricard.khacks;

import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class FlightActivity extends ActionBarActivity {

    private ListView mListView;
    private FlightsCustomAdapter adapter;
    private ArrayList<Flight> flights = new ArrayList<>();
    private TextView mTextView;

    private String KEY_CURRENCY = "currency";
    private String KEY_COUNTRY = "country";
    private String KEY_LOCALE = "locale";
    private String KEY_ORIGINPLACE = "originplace";
    private String KEY_DESTINATIONPLACE = "destinationplace";
    private String KEY_OUTBOUNDDATE = "outbounddate";
    private String KEY_ADULTS = "adults";
    private String KEY_LOCATIONSCHEMA = "LOCATIONSCHEMA";
    private String KEY_APIKEY = "apiKey";
    private String KEY_GROUPPRICING = "groupPricing";


    private String currency = "EUR";
    private String country = "ES";
    private String locale = "en-GB";
    private String originplace = "EDI";
    private String destinationplace = "LHR";
    private String outbounddate = "2015-03-14";
    private String adults = "1";
    private String groupPricing = "true";
    private String LOCATIONSCHEMA = "iata";
    private String apiKey;
    private String httpRequest;

    //http://partners.api.skyscanner.net/apiservices/pricing/v1.0/3088a4dcc54c4e9aaa51e88c4f2264ad_ecilpojl_0DE6F86506FD53306370A7DB75D2C3F6?apikey=ilw18275648197427228911861507832

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTextView = (TextView) findViewById(R.id.title);
        mListView = (ListView) findViewById(R.id.listFlights);

        apiKey = getString(R.string.apiKey);
        httpRequest = "http://partners.api.skyscanner.net/apiservices/pricing/v1.0";

        adapter = new FlightsCustomAdapter(getApplicationContext(), flights);
        mListView.setAdapter(adapter);
        iniSession();
        /*Bundle extras = getIntent().getExtras();
        mTextView.setText(extras.getString("name"));
        Flight flight = new Flight();
        flight.setDepLoc("BCN");
        flight.setArrLoc(extras.getString("location"));*/

    }

    private void iniSession() {
        FlightTask task = new FlightTask();
        task.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flights, menu);
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
        else if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private class FlightTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String location = new String();
            location = getSession();
            getResults(location);




            return null;
        }

        private String getSession() {
            String res = new String();
            HttpClient client = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(httpRequest);
            httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httppost.setHeader("Accept", "application/json");

            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair(KEY_CURRENCY, currency));
            pairs.add(new BasicNameValuePair(KEY_COUNTRY, country));
            pairs.add(new BasicNameValuePair(KEY_LOCALE, locale));
            pairs.add(new BasicNameValuePair(KEY_ORIGINPLACE, originplace));
            pairs.add(new BasicNameValuePair(KEY_DESTINATIONPLACE, destinationplace));
            pairs.add(new BasicNameValuePair(KEY_OUTBOUNDDATE, outbounddate));
            pairs.add(new BasicNameValuePair(KEY_ADULTS, adults));
            pairs.add(new BasicNameValuePair(KEY_LOCATIONSCHEMA, LOCATIONSCHEMA));
            pairs.add(new BasicNameValuePair(KEY_GROUPPRICING, groupPricing));
            pairs.add(new BasicNameValuePair(KEY_APIKEY, apiKey));

            try {
                httppost.setEntity(new UrlEncodedFormEntity(pairs));
                HttpResponse response  = client.execute(httppost);
                Header[] h = response.getAllHeaders();
                res = h[4].getValue();

                Log.wtf("header", h[4].getValue());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;
        }

        private void getResults (String location) {
            location += "?apikey=" + apiKey;
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(location);
            try {
                HttpResponse response  = client.execute(httpget);

                Log.w("wut", response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
