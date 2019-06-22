package com.example.thithu.listening.ListeningList;

import android.content.Context;
import android.util.Log;

import com.example.thithu.IType;
import com.example.thithu.R;
import com.example.thithu.RListener;
import com.example.thithu.UIApp;
import com.example.thithu.api.ApiData;
import com.example.thithu.api.OnStringListener;
import com.example.thithu.database.database.AppDataBase;
import com.example.thithu.database.entity.Mark;
import com.example.thithu.model.ListListening;
import com.example.thithu.model.ListeningsSection2;
import com.example.thithu.model.RandomArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListeningListPresenter implements OnStringListener, RListener.ListeningList {
    private static final String TAG = "ReadingListPresenter";
    private ArrayList<ListeningsSection2> list;
    private UIApp.IListeningListView listeningListView;
    private Gson gson;
    private ApiData apiData;
    private Context context;
    private static String rootlinks2 = "data.listeningssection2/";
    private static String rootlinks3 = "data.listeningssection3/";
    private static String rootlinks1 = "data.listeningssection1/";
    private static String rootlinks4 = "data.listeningssection4/";
    private int type;
    private AppDataBase dataBase;
    public ListeningListPresenter(Context context, UIApp.IListeningListView listeningListView) {
        this.context = context;
        this.listeningListView = listeningListView;
        gson = new Gson();
        apiData = new ApiData();
        dataBase = AppDataBase.getAppDatabase(context);

    }

    public void setData(int type) {
        this.type = type;
        String link = "";
        if (type == IType.LISTENINGS_SECTION1) {
            link = context.getString(R.string.rootLink) + rootlinks1;
        }
        if (type == IType.LISTENINGS_SECTION2) {
            link = context.getString(R.string.rootLink) + rootlinks2;
        }
        if (type == IType.LISTENINGS_SECTION3) {
            link = context.getString(R.string.rootLink) + rootlinks3;
        }
        if (type == IType.LISTENINGS_SECTION_4) {
            link = context.getString(R.string.rootLink) + rootlinks4;
        }
        apiData.getDataJSonArray(link, this);
    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onListSuccess(String result) {
        TypeToken<List<ListeningsSection2>> token = new TypeToken<List<ListeningsSection2>>() {
        };
        list = gson.fromJson(result, token.getType());
        ArrayList<ListListening> llist = new ArrayList<>();
        for (ListeningsSection2 section2 : list) {
            llist.add(new ListListening(section2.getId(), section2.getTitle()));

        }
        listeningListView.setAdapterRecycler(llist);

    }

    @Override
    public void onJSonArray(JSONArray jsonArray) {

        try {
            ArrayList<ListListening> llist = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String title = jsonObject.getString("title");

                Mark mark = dataBase.MarkDao().getMarkSection(this.type,id);
                if(mark!=null){
                    llist.add(new ListListening(id,title,mark.getMax(),mark.getPoint()));
                }else {
                    llist.add(new ListListening(id, title));
                }
            }
            llist = new RandomArrayList<ListListening>().random(llist);
            listeningListView.setAdapterRecycler(llist);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void itemClick(int poisition, ListListening listListening) {
        listeningListView.startActivity(type,listListening);
    }
}
