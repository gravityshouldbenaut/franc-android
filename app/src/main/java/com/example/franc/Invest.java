package com.example.franc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Invest extends ActionBarActivity {
    private URL nasdaqURL;
    private URL nyseURL;
    private ListView stocks;
    private Button openNasdaq;
    private Button openNYSE;
    private Button openProperty;
    @Override
    protected void   onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest);

//        try {
//            nasdaqURL = new URL("http://articlefeeds.nasdaq.com/nasdaq/categories?category=Stocks&format=xml");
//            nyseURL = new URL("http://feeds2.feedburner.com/hybridtalk?fmt=xml");
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        try {
//            nasdaqURL = new URL("http://feeds.pcworld.com/pcworld/latestnews");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        RssFeed feed = null;
//        try {
//            feed = RssReader.read(nasdaqURL);
//            ArrayList<RssItem> rssItems = feed.getRssItems();
//            for(RssItem rssItem : rssItems) {
//                Log.i("RSS Reader", rssItem.getTitle());
//            }
//        } catch (SAXException | IOException e) {
//            e.printStackTrace();
//        }



//        SimpleAdapter stockAdapter = new SimpleAdapter(this, this.getRssFromTwoURL(nasdaqURL, nyseURL),
//                android.R.layout.simple_list_item_2,
//                new String[] {"title", "description"},
//                new int[] {android.R.id.text1,
//                        android.R.id.text2});
//        stocks = (ListView) findViewById(R.id.stocks);
//        stocks.setAdapter(stockAdapter);
        openNasdaq = (Button) findViewById(R.id.nasdaq);
        openNasdaq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent openSend = new Intent(Invest.this, NASDAQ.class);
                startActivity(openSend);
                return;
            }
        });

        openNYSE = (Button) findViewById(R.id.nyse);
        openNYSE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent openBitcoin = new Intent(Invest.this, Property.class);
                startActivity(openBitcoin);
                return;

            }
        });
        openProperty = (Button) findViewById(R.id.property);
        openProperty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent openBitcoin = new Intent(Invest.this, Property.class);
                startActivity(openBitcoin);
                return;

            }
        });


    }


    public List<Map<String, String>> getRssFromTwoURL(URL url, URL url1){
        RssFeed feed;
        RssFeed feed1;
        List<Map<String, String>> data = new ArrayList<>();

        try {
            feed = RssReader.read(url);
            ArrayList<RssItem> rssItems = feed.getRssItems();

            feed1 = RssReader.read(url1);
            ArrayList<RssItem> rssItems1 = feed1.getRssItems();

            for(RssItem rssItem : rssItems) {
                Map<String, String> datum = new HashMap<>(2);
                datum.put("title", rssItem.getTitle());
                datum.put("description", rssItem.getDescription());
                data.add(datum);
                System.out.println(rssItem.getTitle());
            }
            for(RssItem rssItem : rssItems1) {
                Map<String, String> datum = new HashMap<>(2);
                datum.put("title", rssItem.getTitle());
                datum.put("description", rssItem.getDescription());
                data.add(datum);
            }

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return data;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_invest, menu);
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
}
