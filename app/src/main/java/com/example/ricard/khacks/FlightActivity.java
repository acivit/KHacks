package com.example.ricard.khacks;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTextView = (TextView) findViewById(R.id.title);


        mListView = (ListView) findViewById(R.id.listFlights);
        adapter = new FlightsCustomAdapter(getApplicationContext(), flights);
        mListView.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        mTextView.setText(extras.getString("name"));
        Flight test = new Flight("2000",extras.getString("date"),extras.getString("location") + " epicAVIO");
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
