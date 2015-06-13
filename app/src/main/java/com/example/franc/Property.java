package com.example.franc;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Property extends ActionBarActivity {
    private ListView property;
    private URL url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
//        WebView webview = new WebView(this);
//        setContentView(webview);
//        webview.loadUrl("http://www.realtor.com/realestateandhomes-search/Austin_TX");
        property = (ListView) findViewById(R.id.property);
        try {
            url = new URL(
                    "http://www.trulia.com/rss2/for_sale/Austin,TX/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new ReadRssTask().execute(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_property, menu);
        return true;
    }
    private class ReadRssTask extends AsyncTask<URL, Void, RssFeed> {

        @Override
        protected RssFeed doInBackground(URL... params) {
            RssFeed result = null;
            URL url = params[0];
            if (!TextUtils.isEmpty(url.toString())) {
                try {
                    result = RssReader.read(url);
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

        protected void onPostExecute(RssFeed result) {
            if (result != null) {
                ArrayList<RssItem> rssItems = (ArrayList<RssItem>) result
                        .getRssItems();
                List<String> titles = new ArrayList<String>();
                for (RssItem rssItem : rssItems) {
                    titles.add(rssItem.getTitle());
                }
                ArrayAdapter<String> list = new ArrayAdapter<String>(
                        getBaseContext(), android.R.layout.simple_list_item_1,
                        titles);
                property.setAdapter(list);
            }
        }

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
