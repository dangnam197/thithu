package com.example.thithu.reading.ReadingQuest.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thithu.IType;
import com.example.thithu.R;
import com.example.thithu.Service.MediaService;
import com.example.thithu.UIApp;
import com.example.thithu.model.EventCheckAnswer;
import com.example.thithu.model.ListListening;
import com.example.thithu.reading.ReadingQuest.fragment.Secsion1.ReadingP1;
import com.example.thithu.reading.ReadingQuest.fragment.Secsion2.ReadingP2;
import com.example.thithu.reading.ReadingQuest.fragment.Secsion3.ReadingP3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class ReadingQuestActivitytest extends AppCompatActivity
        implements UIApp.IListeningQuestView, View.OnClickListener{
    private static final String TAG ="dangnam" ;
    private TextView timeCount,tvDialogResult;
    private Toolbar toolbar;
    private ReadingQuestPresenter lQPresenter;
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
//        if(!EventBus.getDefault().isRegistered(this)){
//            EventBus.getDefault().register(this);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MediaService.class);
        intent.setAction(MediaService.STOPMEDIASERVER_ACTION);
//        startService(intent);
//        EventBus.getDefault().unregister(this);
    }

    private void getDataInten(){
        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE",0);
        listListening = (ListListening)intent.getSerializableExtra("ITEM");

        Bundle bundle = new Bundle();
        bundle.putInt("ID",listListening.getId());
        Fragment fragment = null;
        if(type == IType.READINGS_PART1){
            fragment= new ReadingP1();
        } else if(type==IType.LISTENINGS_SECTION2){
            fragment = new ReadingP2();
        }else if(type==IType.LISTENINGS_SECTION3){
            fragment = new ReadingP3();
        }
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
        lQPresenter.setData(listListening.getId(),type);
    }
    private void initView(){
        toolbar = findViewById(R.id.toolbar_listening_quest);
        timeCount = findViewById(R.id.listening_quest_time_cout);


}
    private void init(){
        setSupportActionBar(toolbar);
        lQPresenter = new ReadingQuestPresenter(this,this);
//        eventCheckAnswer = new EventCheckAnswer(false);//new ApiData().getData();
    }

    @Override
    public void updateTime(String time) {
        timeCount.setText(time);
    }

    @Override
    public void setLinkAudio(String url) {

    }
    @Override
    public void setPager(int poisition) {

    }

    @Override
    public void playPause() {
//        Intent intent = new Intent(this, MediaService.class);
//        intent.setAction(MediaService.PLAY_ACTION);
//        startService(intent);
    }

    @Override
    public void onClick(View v) {

    }

}
