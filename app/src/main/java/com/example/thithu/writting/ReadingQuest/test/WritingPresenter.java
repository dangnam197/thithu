package com.example.thithu.writting.ReadingQuest.test;

import android.content.Context;

import com.example.thithu.R;
import com.example.thithu.UIApp;
import com.example.thithu.api.ApiData;
import com.example.thithu.api.OnStringListener;
import com.example.thithu.model.Writings;
import com.google.gson.Gson;

import org.json.JSONArray;

public class WritingPresenter implements OnStringListener {
    private UIApp.Writting uiWritting;
    private ApiData apiData;
    private Writings writings;
    private Gson gson;
    private Context context;
    public WritingPresenter(Context context,UIApp.Writting writting) {
        this.uiWritting = writting;
        this.context = context;
        apiData = new ApiData();
        gson = new Gson();
    }

    public void getData(int id) {
        String link = context.getString(R.string.rootLink) + "data.writings/"+id;
        apiData.getData(link,this);
    }

    @Override
    public void onSuccess(String result) {
        writings = gson.fromJson(result, Writings.class);
        uiWritting.setTvQuest(writings.getQuestion());
        uiWritting.setTvSample(writings.getSample());
    }

    @Override
    public void onListSuccess(String result) {

    }

    @Override
    public void onJSonArray(JSONArray jsonArray) {

    }
}
