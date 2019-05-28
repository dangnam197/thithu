package com.example.thithu.api;

import android.content.res.Resources;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.thithu.R;
import com.example.thithu.model.Test;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public  class ApiData {
    private static final String TAG = "ApiData";
    public ApiData() {

    }

    public void getListData(String link, final OnStringListener listener){
        AndroidNetworking.get(link)
                .setPriority(Priority.LOW).build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                listener.onListSuccess(response);
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }
    public void getData(String link, final OnStringListener listener){
        AndroidNetworking.get(link)
                .setPriority(Priority.LOW).build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }
    public void getDataJSonArray(String link, final OnStringListener listener){
        AndroidNetworking.get(link)
                .setPriority(Priority.LOW).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                listener.onJSonArray(response);
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }
}
