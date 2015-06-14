package com.example.franc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coinbase.android.sdk.OAuth;
import com.coinbase.api.Coinbase;
import com.coinbase.api.entity.OAuthTokensResponse;
import com.coinbase.api.exception.CoinbaseException;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.w3c.dom.Text;

import java.io.IOException;

import roboguice.util.RoboAsyncTask;


public class Bitcoin extends ActionBarActivity {
private Button usd;
    private Button gbp;
    private Button eur;
    private Button buy;
    private TextView price;
    private CoinbaseAccess coinbase;
    private EditText amount;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin);
        Intent intent = this.getIntent();
        coinbase = new CoinbaseAccess();
     coinbase.setOauth(MainActivity.Token.
             getT());
//        WebView webview = new WebView(this);
//        setContentView(webview);
//        webview.loadUrl("https://www.coinbase.com/");
        usd = (Button) findViewById(R.id.usd);
        price = (TextView) findViewById(R.id.price);
        gbp = (Button) findViewById(R.id.gbp);
        eur = (Button) findViewById(R.id.eur);
        amount = (EditText) findViewById(R.id.amount);
        buy = (Button) findViewById(R.id.buy);
        usd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                try {
                    price.setText(coinbase.getCb().getSpotPrice(CurrencyUnit.USD).toString());
                    Log.w("Coinbase Success", coinbase.getCb().getSpotPrice(CurrencyUnit.USD).toString());
                } catch (IOException | CoinbaseException e) {
                    e.printStackTrace();
                }
                return;

            }
        });


        gbp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                try {
                    price.setText(coinbase.getCb().getSpotPrice(CurrencyUnit.GBP).toString());
                } catch (IOException | CoinbaseException e) {
                    e.printStackTrace();
                }

                return;

            }
        });

        eur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                try {
                    price.setText(coinbase.getCb().getSpotPrice(CurrencyUnit.EUR).toString());
                } catch (IOException | CoinbaseException e) {
                    e.printStackTrace();
                }

                return;

            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Bitcoin.this, MainActivity.class);
                // Perform action on click
                try {
                    coinbase.getCb().buy(Money.parse("BTC" + " " + amount.getText().toString()));
                    toast = Toast.makeText(Bitcoin.this, "You just bought" + " " + amount.getText().toString() + " " + "BTC", Toast.LENGTH_LONG);
                    toast.show();
                } catch (CoinbaseException | IOException e) {
                    e.printStackTrace();
                    toast = Toast.makeText(Bitcoin.this, "Make sure you add a payment method to buy" + " " + amount.getText().toString() + " " + "BTC", Toast.LENGTH_LONG);
                    toast.show();
                }
                startActivity(i);
                return;
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bitcoin, menu);
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
