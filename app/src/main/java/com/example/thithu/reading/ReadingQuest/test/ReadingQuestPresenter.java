package com.example.thithu.reading.ReadingQuest.test;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import com.example.thithu.IType;
import com.example.thithu.R;
import com.example.thithu.TimeModel;
import com.example.thithu.UIApp;
import com.example.thithu.api.ApiData;
import com.example.thithu.api.OnStringListener;
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

import androidx.fragment.app.Fragment;

public class ReadingQuestPresenter implements OnStringListener {
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
    private int timePause = 15;
    private int curentPause = 0;
    private boolean checkPause = false;
    private boolean PAUSE = true;

    public ReadingQuestPresenter(final UIApp.IListeningQuestView listeningQuestActivity, Context context) {
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
    }

    public void setListAnswerCheck(int size) {

    }

    public void updateAnswerCheck(AnswerCheck answerCheck) {

    }

    public void countTime(final int time) {
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                listeningQuestActivity.updateTime(timeModel.timeToString(millisUntilFinished));
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();

    }

    public void clickSure() {

    }

    public void clickSubmit() {

    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onListSuccess(String result) {


    }

    @Override
    public void onJSonArray(JSONArray jsonArray) {

    }

}
