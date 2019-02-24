package com.marcecuevas.mltestapp.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Objects;

public class GenericController {

    private Context context;

    public GenericController(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public Boolean isNetworkingOnline(Context context){
        if (context == null){
            return true;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeConnection = Objects.requireNonNull(cm).
                getActiveNetworkInfo();
        return (activeConnection != null) && activeConnection.isConnected();
    }
}