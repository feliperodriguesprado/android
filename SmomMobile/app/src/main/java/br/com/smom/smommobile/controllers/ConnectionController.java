package br.com.smom.smommobile.controllers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionController {

    public static boolean checkConnection(Context context) {

        ConnectivityManager connectivityManager;
        NetworkInfo network;

        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        network = connectivityManager.getActiveNetworkInfo();

        if (network != null) {
            return true;
        } else {
            return false;
        }
    }
}
