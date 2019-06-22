package com.example.thithu.listening.ListeningQuest.test;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.widget.Toast;

import com.example.thithu.IType;
import com.example.thithu.R;
import com.example.thithu.TimeModel;
import com.example.thithu.UIApp;
import com.example.thithu.api.ApiData;
import com.example.thithu.api.OnStringListener;
import com.example.thithu.listening.ListeningQuest.fragment.Secsion1.QuesterPager;
import com.example.thithu.model.AnswerCheck;
import com.example.thithu.model.ListeningsSection1;
import com.example.thithu.model.ListeningsSection1Question;
import com.example.thithu.model.ListeningsSection2;
import com.example.thithu.model.ListeningsSection3;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ListeningQuestPresenter implements OnStringListener {
    private UIApp.IListeningQuestView listeningQuestActivity;
    private CountDownTimer countDownTimer;
    private TimeModel timeModel;
    private Gson gson;
    private ArrayList<ListeningsSection1Question> list;
    private ApiData apiData;
    private int type;
    private Context context;
    private ArrayList<AnswerCheck> listAnswerCheck;
    private static String rootlinks2 = "data.listeningssection2/";
    private static String rootlinks3 = "data.listeningssection3/";
    private static String rootlinks1 = "data.listeningssection1question/sid=";
    private static String rootlinks4 = "data.listeningssection4/";
    private static final String TAG = "ReadingQuestPresenter";
    private ArrayList<Fragment> listFragments;

    private int poisitionQuest = 0;
    private int timePause = 10;
    private int curentPause = 0;
    private boolean checkPause = false;
    private boolean PAUSE = true;

    public ListeningQuestPresenter(final UIApp.IListeningQuestView listeningQuestActivity, Context context) {
        this.context = context;
        this.listeningQuestActivity = listeningQuestActivity;
        timeModel = new TimeModel();
        gson = new Gson();
        apiData = new ApiData();
        listAnswerCheck = new ArrayList<>();
        list = new ArrayList<>();

    }

    public void setData(int id, int type) {
        this.type = type;
        String link = "";
        if (type == IType.LISTENINGS_SECTION1) {
            link = context.getString(R.string.rootLink) + rootlinks1 + id;
            apiData.getData(context.getString(R.string.rootLink) + "data.listeningssection1/" + id, this);

        }
        if (type == IType.LISTENINGS_SECTION2) {
            link = context.getString(R.string.rootLink) + rootlinks2 + id;
            apiData.getData(link,this);
            Log.d(TAG, "setData: "+link);
        }
        if (type == IType.LISTENINGS_SECTION3) {
            link = context.getString(R.string.rootLink) + rootlinks3 + id;
            apiData.getData(link,this);
        }
        if (type == IType.LISTENINGS_SECTION_4) {
            link = context.getString(R.string.rootLink) + rootlinks4 + id;
        }
        apiData.getListData(link, this);

    }

    public void setListAnswerCheck(int size) {
        for (int i = 0; i < size; i++) {
            listAnswerCheck.add(new AnswerCheck(i));
        }
    }

    public void updateAnswerCheck(AnswerCheck answerCheck) {
        listAnswerCheck.set(answerCheck.getPoisition(), answerCheck);
    }

    public void countTime(final int time) {
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                listeningQuestActivity.updateTime(timeModel.timeToString(millisUntilFinished));
                if (type == IType.LISTENINGS_SECTION1) {
                    if (list.size() > poisitionQuest) {
                        if (checkPause != PAUSE) {
                            if (((time - millisUntilFinished) / 1000) - (poisitionQuest * timePause) >= list.get(poisitionQuest).getTimeFinish()) {
                                checkPause = PAUSE;
                                poisitionQuest++;
                                listeningQuestActivity.playPause();
                                //Log.d(TAG, "onTick: "+"đã tạm dừng"+(millisUntilFinished/1000));
                            }
                        } else {
                            if (curentPause >= timePause) {
                                curentPause = 0;
                                checkPause = false;
                                listeningQuestActivity.setPager(poisitionQuest);
                                listeningQuestActivity.playPause();
                            }
                            curentPause++;

                        }
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();

    }

    public void clickSure() {
        ArrayList<AnswerCheck> listAnswerCheckSureDialog = new ArrayList<>();
        for (AnswerCheck answerCheck : listAnswerCheck) {
            if (answerCheck.isCheckSure()) {
                listAnswerCheckSureDialog.add(answerCheck);
            }
        }
    }

    public void clickSubmit() {
        int i = 0;
        for (AnswerCheck answerCheck : listAnswerCheck) {
            if (answerCheck.isCheckAnswer()) {
                i++;
            }
        }
    }

    @Override
    public void onSuccess(String result) {
        if (type == IType.LISTENINGS_SECTION1) {
            ListeningsSection1 listeningsSection1 = gson.fromJson(result, ListeningsSection1.class);
            listeningQuestActivity.setLinkAudio(listeningsSection1.getAudio());
        }
        if (type == IType.LISTENINGS_SECTION2) {
            ListeningsSection2 listeningsSection2 = gson.fromJson(result, ListeningsSection2.class);
            listeningQuestActivity.setLinkAudio(listeningsSection2.getAudio());
            //Log.d(TAG, "onSuccess: "+listeningsSection2.getAudio());
        }
        if(type == IType.LISTENINGS_SECTION3){
            ListeningsSection3 listeningsSection2 = gson.fromJson(result, ListeningsSection3.class);
            listeningQuestActivity.setLinkAudio(listeningsSection2.getAudio());
        }

    }

    @Override
    public void onListSuccess(String result) {
        if (type == IType.LISTENINGS_SECTION1) {
            TypeToken<List<ListeningsSection1Question>> token = new TypeToken<List<ListeningsSection1Question>>() {
            };
            list = gson.fromJson(result, token.getType());
        }

    }

    @Override
    public void onJSonArray(JSONArray jsonArray) {

    }

}
