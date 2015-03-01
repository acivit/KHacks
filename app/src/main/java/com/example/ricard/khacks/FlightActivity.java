package com.example.ricard.khacks;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
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
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String KEY_INBOUNDDATE = "inbounddate";
    private String KEY_ADULTS = "adults";
    private String KEY_LOCATIONSCHEMA = "LOCATIONSCHEMA";
    private String KEY_APIKEY = "apiKey";
    private String KEY_GROUPPRICING = "groupPricing";

    private int MAX_FLIGHTS = 5;


    private String currency = "EUR";
    private String country = "ES";
    private String locale = "en-GB";
    private String originplace = "EDI";
    private String destinationplace = "LHR";
    private String outbounddate = "2015-03-14";
    private String inbounddate = "2015-03-16";
    private String adults = "1";
    private String groupPricing = "true";
    private String LOCATIONSCHEMA = "iata";
    private String apiKey;
    private String httpRequest;

    String preu;
    String companyia_id ;
    String companyia_final;
    String departure;
    String arrival;
    String DeeplinkUrl;
    Map<String, String> keyToCompany;


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

        Bundle extras = getIntent().getExtras();
        mTextView.setText(extras.getString("name"));
        Flight flight = new Flight();
        flight.setDepLoc("BCN");
        flight.setArrLoc(extras.getString("location"));
        Log.i("hi et psso aquesta data", extras.getString("date"));
        flight.setDates(extras.getString("date"));
        outbounddate = flight.getDepDate();
        inbounddate = flight.getArrDate();
        destinationplace = flight.getArrLoc();


        iniSession();



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FlightActivity.class);
                Flight clickedFlight = flights.get(position);
                String url = clickedFlight.getUrl();
                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                catch(ActivityNotFoundException e) {
                    // Chrome is not installed
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
            }
        });


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

    private class FlightTask extends AsyncTask<Void, Void, List<Flight>> {

        @Override
        protected List<Flight> doInBackground(Void... params) {
            adapter.add(new Flight("Loading next Flights....", "", null, null, null, null, null));
            getPlace();
            try {
                Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            String location = new String();
            location = getSession();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Flight> list = getResults(location);




            return list;
        }

        private void getPlace() {
            String url = "http://partners.api.skyscanner.net/apiservices/autosuggest/v1.0/GB/EUR/en-GB?query="+destinationplace+"&apiKey=ilw18275648197427228911861507832";
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = null;

            try {
                response  = client.execute(httpget);
                String json_string = EntityUtils.toString(response.getEntity());
                //JSONArray temp1 = new JSONArray(json_string);
                JSONObject temp1 = new JSONObject(json_string);
                destinationplace = temp1.getJSONArray("Places").getJSONObject(0).get("PlaceId").toString();
                Log.w("destino", destinationplace);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
            pairs.add(new BasicNameValuePair(KEY_ORIGINPLACE, "BCN-sky"));
            pairs.add(new BasicNameValuePair(KEY_DESTINATIONPLACE, destinationplace));
            pairs.add(new BasicNameValuePair(KEY_OUTBOUNDDATE, outbounddate));
            pairs.add(new BasicNameValuePair(KEY_INBOUNDDATE, inbounddate));
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

        private List<Flight> getResults (String location) {
            location += "?apikey=" + apiKey;
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(location);
            HttpResponse response = null;
            List<Flight> result = new ArrayList<>();

            try {
                response  = client.execute(httpget);
                String json_string = EntityUtils.toString(response.getEntity());
                //JSONArray temp1 = new JSONArray(json_string);
                JSONObject temp1 = new JSONObject(json_string);
                keyToCompany = new HashMap<>();
                int f = temp1.getJSONArray("Carriers").length();
                //Mapejant les companyies amb les IDs
                for (int i = 0; i < f; ++i) {
                    String key = temp1.getJSONArray("Carriers").getJSONObject(i).get("Id").toString();
                    String value = temp1.getJSONArray("Carriers").getJSONObject(i).get("Name").toString();
                    keyToCompany.put(key, value);
                }
                //Parsejant el JSON a la ListView
                for (int i = 0; i < MAX_FLIGHTS; ++i) {
                    Flight flight = new Flight();
                    String desti = destinationplace.substring(0,3);
                    flight.setDepLoc("BCN - "+desti);
                    //flight.setArrLoc("NYC");
                    preu = temp1.getJSONArray("Itineraries").getJSONObject(i).getJSONArray("PricingOptions").getJSONObject(0).get("Price").toString();
                    companyia_id = temp1.getJSONArray("Legs").getJSONObject(i).getJSONArray("Carriers").get(0).toString();
                    companyia_final = keyToCompany.get(companyia_id);
                    departure = temp1.getJSONArray("Legs").getJSONObject(i).get("Departure").toString();
                    departure = departure.substring(0,10)+' '+departure.substring(11);
                    arrival = temp1.getJSONArray("Legs").getJSONObject(i).get("Arrival").toString();
                    arrival = arrival.substring(0,10)+' '+arrival.substring(11);
                    DeeplinkUrl = temp1.getJSONArray("Itineraries").getJSONObject(i).getJSONArray("PricingOptions").getJSONObject(0).get("DeeplinkUrl").toString();
                    flight.setCompany(companyia_final);
                    flight.setPrice(preu);
                    flight.setDepDate(departure);
                    flight.setArrDate(arrival);
                    flight.setUrl(DeeplinkUrl);
                    result.add(flight);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPostExecute(List<Flight> flightList) {
            super.onPostExecute(flightList);
            flights.remove(0);
            adapter.addAll(flightList);
        }
    }
}
