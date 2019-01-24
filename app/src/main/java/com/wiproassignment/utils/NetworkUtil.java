package com.wiproassignment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkUtil {

    private final String TAG = NetworkUtil.class.getName();
    private Context context;

    public NetworkUtil(Context context) {
        this.context = context;
    }

    //Checking is connected to Internet
    public boolean isNetworkAvailable() {
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();

        } catch (Exception e) {
            Log.e(TAG, "Unable to check is internet Available", e);
        }
        return false;
    }

}
