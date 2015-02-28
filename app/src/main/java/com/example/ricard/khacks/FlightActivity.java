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

import org.apache.http.HttpResponse;
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

    private String currency;
    private String country;
    private String locale;
    private String originplace;
    private String destinationplace;
    private String outbounddate;
    private String adults;
    private String groupPricing;
    private String LOCATIONSCHEMA;
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
        /*"name", clickedHackathon.getName());
                intent.putExtra("location", clickedHackathon.getLocation());
                intent.putExtra("date", "2015-03-28");
                */
        Bundle extras = getIntent().getExtras();
        mTextView.setText(extras.getString("name"));
        Flight flight = new Flight();
        flight.setDepLoc("BCN");
        flight.setArrLoc(extras.getString("location"));
/*
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
            pairs.put("country", "value2");
            pairs.put("currency", "value3");
            pairs.put("locale", "value4");
            pairs.put("originplace", "value5");
            pairs.put("destinationplace", "value6");
            pairs.put("outbounddate", "value7");
            pairs.put("adults", "value8");

            httppost.setEntity(new StringEntity(pairs.toString(), "UTF-8"));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        }*/

        // Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost("http://partners.api.skyscanner.net/apiservices/pricing/v1.0");

        // Building post parameters
        // key and value pair
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded"));
        nameValuePair.add(new BasicNameValuePair("Accept", "application/json"));
/*
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }

        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);

            // writing response to log
            Log.d("Http Response:", response.toString());
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();

        }
*/
        Flight test = new Flight("BCN", "EDI", null, extras.getString("date"), extras.getString("date"), null);
        flights.add(test);
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

    private class FlightTask extends AsyncTask<Void, Void, HttpResponse> {

        @Override
        protected HttpResponse doInBackground(Void... params) {
            HttpResponse result;

            HttpPost httppost = new HttpPost("http://partners.api.skyscanner.net/apiservices/pricing/v1.0");
            httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httppost.setHeader("Accept", "application/json");


            return result;
        }
    }
}
