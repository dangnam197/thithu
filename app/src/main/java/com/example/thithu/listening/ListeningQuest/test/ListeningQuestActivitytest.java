package com.example.thithu.listening.ListeningQuest.test;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thithu.IType;
import com.example.thithu.R;
import com.example.thithu.Service.MediaService;
import com.example.thithu.UIApp;
import com.example.thithu.adapter.QuestPagerAdapter;
import com.example.thithu.adapter.SureAdapter;
import com.example.thithu.listening.ListeningQuest.fragment.Secsion1.ListeningS1;
import com.example.thithu.listening.ListeningQuest.fragment.Secsion2.ListeningS2;
import com.example.thithu.listening.ListeningQuest.fragment.Secsion3.ListeningS3;
import com.example.thithu.model.AnswerCheck;
import com.example.thithu.model.EventCheckAnswer;
import com.example.thithu.model.ListListening;
import com.example.thithu.model.SetPagerEvent;
import com.example.thithu.model.StartAudio;
import com.example.thithu.model.Test;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class ListeningQuestActivitytest extends AppCompatActivity
        implements UIApp.IListeningQuestView, View.OnClickListener{
    private static final String TAG ="dangnam" ;
    private TextView timeCount,tvDialogResult;
    private Toolbar toolbar;
    private com.example.thithu.listening.ListeningQuest.test.ListeningQuestPresenter lQPresenter;
    private int type;
    private ListListening listListening;
    private LinearLayout btnSure,btnSubmit;
    private EventCheckAnswer eventCheckAnswer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_quest);
        initView();
        init();
        getDataInten();

    }


    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MediaService.class);
        intent.setAction(MediaService.STOPMEDIASERVER_ACTION);
        startService(intent);
        EventBus.getDefault().unregister(this);
    }

    private void getDataInten(){
        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE",0);
        listListening = (ListListening)intent.getSerializableExtra("ITEM");

        Bundle bundle = new Bundle();
        bundle.putInt("ID",listListening.getId());
        Fragment fragment = null;
        if(type == IType.LISTENINGS_SECTION1){
            fragment= new ListeningS1();
        } else if(type==IType.LISTENINGS_SECTION2){
            fragment = new ListeningS2();
        }else if(type==IType.LISTENINGS_SECTION3){
            fragment = new ListeningS3();
        }
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
        lQPresenter.setData(listListening.getId(),type);
    }
    private void initView(){
        toolbar = findViewById(R.id.toolbar_listening_quest);
        timeCount = findViewById(R.id.listening_quest_time_cout);
//        btnSure = findViewById(R.id.btn_sure);
//        btnSubmit = findViewById(R.id.btn_submit);
//        btnSure.setOnClickListener(this);
//        btnSubmit.setOnClickListener(this);

}
    private void init(){
        setSupportActionBar(toolbar);
        lQPresenter = new ListeningQuestPresenter(this,this);
        eventCheckAnswer = new EventCheckAnswer(false);
        //new ApiData().getData();
    }
    private void startAudio(String link){
            String url = getApplicationContext().getString(R.string.rootLink)+"file/audio/"+link;
            Log.d(TAG, "startAudio: "+url);
            runService(MediaService.STARTMEDIASERVER_ACTION,url);

    }
    public void runService(String action,String link){
        Intent intent = new Intent(this, MediaService.class);
        intent.putExtra("link",link);
        intent.setAction(action);
        startService(intent);
        Log.d(TAG, "setData: "+link);
    }
    @Override
    public void updateTime(String time) {
        timeCount.setText(time);
    }

    @Override
    public void setLinkAudio(String url) {
        startAudio(url);
    }
    @Override
    public void setPager(int poisition) {
        EventBus.getDefault().post(new SetPagerEvent(poisition));
    }

    @Override
    public void playPause() {
        Intent intent = new Intent(this, MediaService.class);
        intent.setAction(MediaService.PLAY_ACTION);
        startService(intent);
    }
    @Subscribe
    public void onStartAudioEvent(StartAudio startAudio) {
        //lQPresenter.updateAnswerCheck(answerCheck);
        Log.d(TAG, "onStartAudioEvent: " + startAudio.getTime());
        lQPresenter.countTime(600000);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
//        switch (id){
//            case R.id.btn_sure:
//                lQPresenter.clickSure();
//                break;
//            case R.id.btn_submit:
//                lQPresenter.clickSubmit();
//                break;
//            case R.id.btn_dialog_result_check:
//                eventCheckAnswer = new EventCheckAnswer(true);
//                EventBus.getDefault().post(eventCheckAnswer);
//                break;
//        }
    }

}
