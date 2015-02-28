package com.example.ricard.khacks;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


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
        //flight.set
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
}
