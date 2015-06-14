package com.example.franc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.coinbase.android.sdk.OAuth;
import com.coinbase.api.entity.OAuthTokensResponse;
import com.coinbase.api.exception.CoinbaseException;

import java.io.IOException;

import roboguice.util.RoboAsyncTask;


public class MainActivity extends ActionBarActivity {

    Button oInv;
    Button oSen;
    Button oBit;
    private static final String CLIENT_ID = "90b3d6115dbe834121a24c5f20c41d2aa4d84b1489bbf58578794df359713791";
    private static final String CLIENT_SECRET = "4476c4a33b862e1e33c122c6c7cd12d8c1829a27ee4daf33f3d3fdb29c7ef562";
    private static final String REDIRECT_URI = "franc://coinbase-oauth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       oInv  = (Button) findViewById(R.id.invest);
        oInv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent openInvest = new Intent(MainActivity.this, Invest.class);
               startActivity(openInvest);
                return;

            }
        });
        oSen = (Button) findViewById(R.id.send);
        oSen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent openSend  = new Intent(MainActivity.this, Send.class);
                startActivity(openSend);
                return;
            }
        });

        oBit = (Button) findViewById(R.id.bitcoin);
        oBit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                try {
                    OAuth.beginAuthorization(MainActivity.this, CLIENT_ID, "user", REDIRECT_URI, null);
                } catch (CoinbaseException ex) {
                    Toast.makeText(MainActivity.this, "There was an error redirecting to Coinbase: " + ex.getMessage(), Toast.LENGTH_SHORT);
                }
                return;

            }
        });
        

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
    public class CompleteAuthorizationTask extends RoboAsyncTask<OAuthTokensResponse> {
        private Intent mIntent;

        public CompleteAuthorizationTask(Intent intent) {
            super(MainActivity.this);
            mIntent = intent;
        }

        @Override
        public OAuthTokensResponse call() throws Exception {
            return OAuth.completeAuthorization(MainActivity.this, CLIENT_ID, CLIENT_SECRET, mIntent.getData());
        }

        @Override
        public void onSuccess(OAuthTokensResponse tokens) {
            // Do something with the tokens
        }

        @Override
        public void onException(Exception ex) {
            // Authorization failed for whatever reason
        }
    }
    @Override
    protected void onNewIntent(final Intent intent) {
        if (intent != null && intent.getAction() != null && intent.getAction().equals("android.intent.action.VIEW")) {
            new CompleteAuthorizationTask(intent).execute();




        }
    }
}
