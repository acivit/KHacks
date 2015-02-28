package com.example.ricard.khacks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FlightActivity.class);
                Hackathon clickedHackathon = hackathons.get(position);
                intent.putExtra("name", clickedHackathon.getName());
                intent.putExtra("location", clickedHackathon.getLocation());
                intent.putExtra("date", "2015-03-28");
                startActivity(intent);
            }
        });
    }

    void fetchData() {
        adapter.add(new Hackathon("Loading next Hackathons....", null, null, null));
        HackTask task = new HackTask();
        task.execute();
    }

    public void postData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://partners.api.skyscanner.net/apiservices/pricing/v1.0");
        httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        httppost.setHeader("Accept", "application/json");

        /*try {
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

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
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

    private class HackTask extends AsyncTask<Void, Void, List<Hackathon>> {

        @Override
        protected List<Hackathon> doInBackground(Void... params) {
            List<Hackathon> result = new ArrayList<>();
            try {
                Document doc = Jsoup.connect("https://www.mlh.io/seasons/2015-eu/events").get();
                Elements hackz = doc.select("div.event");
                Log.i("Hackz size", "SIZE: " + hackz.size());

                for (Element thing : hackz) {
                    Hackathon event = new Hackathon();
                    Elements nom = thing.select("h3");
                    if (nom.size() == 1) {
                        // title
                        event.setName(nom.first().text());
                        Log.i("Event name", event.getName());

                        // img
                        Elements img = thing.select("img");
                        Log.i("Img size: ", img.size()+"");
                        if (img.size() == 2) {
                            Log.i("Debugging", img.last().attr("src"));
                            URL url = new URL(img.last().attr("src"));
                            Log.i("Debugging", "2estic aqui");
                            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            event.setImage(bitmap);
                            Log.i("Event img", "http:" + img.first().attr("src"));
                        }
                        Log.i("Debugging", "3estic aqui");
                        Elements p = thing.select("p");
                        event.setDate(p.first().text());
                        event.setLocation(p.last().text());
                        Log.i("Event p", p.first().text() + "huehue " + p.last().text());
                        result.add(event);
                    }

                }
            } catch (IOException e) {
                Log.wtf("HackTask", "Something went wrong");
                cancel(true);
            }
            return result;
        }
        @Override
        protected void onPostExecute(List<Hackathon> hackzList) {
            super.onPostExecute(hackzList);
            hackathons.remove(0);
            adapter.addAll(hackzList);
        }
    }
}
