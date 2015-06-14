package com.example.franc;

import android.os.Parcel;
import android.os.Parcelable;

import com.coinbase.api.Coinbase;
import com.coinbase.api.CoinbaseBuilder;
import com.coinbase.api.entity.OAuthTokensResponse;

/**
 * Created by gravityshouldbenaut on 6/13/15.
 */
public class CoinbaseAccess implements Parcelable {
    String token;
    Coinbase cb;

    public CoinbaseAccess(){


    }

    public Coinbase getCb(){
        return cb;
    }
    public void setOauth(String something){
        token = something;
        createCB(token);

    }
    public void createCB(String t){
        cb = new CoinbaseBuilder()
                .withAccessToken(t)
                .build();
    }


    protected CoinbaseAccess(Parcel in) {
        this();
       readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        dest.writeValue(cb);
    }

    public static final Parcelable.Creator<CoinbaseAccess> CREATOR = new Parcelable.Creator<CoinbaseAccess>() {
        @Override
        public CoinbaseAccess createFromParcel(Parcel in) {
            return new CoinbaseAccess(in);
        }

        @Override
        public CoinbaseAccess[] newArray(int size) {
            return new CoinbaseAccess[size];
        }
    };
    private void readFromParcel(Parcel source) {
        token = source.readString();
        cb  = (Coinbase) source.readValue(CoinbaseAccess.class.getClassLoader());
    }

}