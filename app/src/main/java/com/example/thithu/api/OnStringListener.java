package com.example.thithu.api;

import org.json.JSONArray;

public interface OnStringListener {
    void onSuccess(String result);
    void onListSuccess(String result);
    void onJSonArray(JSONArray jsonArray);
}
