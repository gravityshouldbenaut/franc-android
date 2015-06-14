package com.example.franc;

import com.coinbase.api.Coinbase;
import com.coinbase.api.CoinbaseBuilder;

/**
 * Created by gravityshouldbenaut on 6/13/15.
 */
public class CoinbaseAccess {
    String token;
    Coinbase cb;
    public CoinbaseAccess(){
        token =  "d544a0b09eea69212caf5a3f86f575f2164361b67b9e4db1586cdfe45dfa8776";
         cb = new CoinbaseBuilder()
                .withAccessToken(token)
                .build();
    }

    public Coinbase getCb(){
        return cb;
    }
}
