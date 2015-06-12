package com.example.franc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    Button oInv;
    Button oSen;
    Button oBit;


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
                Intent openBitcoin = new Intent(MainActivity.this, Bitcoin.class);
                startActivity(openBitcoin);
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
}
