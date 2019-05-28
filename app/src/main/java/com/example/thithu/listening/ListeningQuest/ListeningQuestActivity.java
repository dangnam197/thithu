package com.example.thithu.listening.ListeningQuest;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.thithu.R;
import com.example.thithu.Service.MediaService;
import com.example.thithu.UIApp;
import com.example.thithu.adapter.QuestPagerAdapter;
import com.example.thithu.adapter.SureAdapter;
import com.example.thithu.model.AnswerCheck;
import com.example.thithu.model.EventCheckAnswer;
import com.example.thithu.model.ListListening;
import com.example.thithu.model.Test;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class ListeningQuestActivity extends AppCompatActivity implements UIApp.IListeningQuestView, View.OnClickListener,SureAdapter.OnClickListener {
    private static final String TAG ="test" ;
    private TextView timeCount,tvDialogResult;
    private Toolbar toolbar;
    private ListeningQuestPresenter lQPresenter;
    private ViewPager viewPager;
    private int type;
    private ListListening listListening;
    private Dialog dialogSure,dialogResult;
    private RecyclerView recyclerViewSure;
    private LinearLayout btnSure,btnSubmit;
    private Button btnCheck,btnFinish;
    private EventCheckAnswer eventCheckAnswer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_quest);
        initView();
        init();
        getDataInten();
        initDilogSure();
        initDilogResult();

    }

    private void initDilogSure() {
        dialogSure = new Dialog(this);
        dialogSure.setContentView(R.layout.dialog_sure);
        dialogSure.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        recyclerViewSure = dialogSure.findViewById(R.id.recycler_sure);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSure.setLayoutManager(layoutManager);
        recyclerViewSure.setAdapter(new SureAdapter(this,null,this));
    }
    private void initDilogResult() {
        dialogResult = new Dialog(this);
        dialogResult.setContentView(R.layout.dialog_result);
        dialogResult.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tvDialogResult = dialogResult.findViewById(R.id.tv_dialog_result);
        btnCheck = dialogResult.findViewById(R.id.btn_dialog_result_check);
        btnFinish = dialogResult.findViewById(R.id.btn_dialog_result_finish);

        btnFinish.setOnClickListener(this);
        btnCheck.setOnClickListener(this);

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
        EventBus.getDefault().unregister(this);
    }

    private void getDataInten(){
        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE",0);
        listListening = (ListListening)intent.getSerializableExtra("ITEM");
        lQPresenter.setData(listListening.getId(),type);
        Toast.makeText(this, type+ "  " +listListening.getTitle(), Toast.LENGTH_SHORT).show();
    }
    private void initView(){
        toolbar = findViewById(R.id.toolbar_listening_quest);
        timeCount = findViewById(R.id.listening_quest_time_cout);
        viewPager = findViewById(R.id.listening_quest_pager);
        btnSure = findViewById(R.id.btn_sure);
        btnSubmit = findViewById(R.id.btn_submit);
        btnSure.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                    if(eventCheckAnswer.isCheck()){
                        EventBus.getDefault().post(eventCheckAnswer);
                    }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    private void init(){
        setSupportActionBar(toolbar);
        lQPresenter = new ListeningQuestPresenter(this,this);
        lQPresenter.countTime(600000);
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
    }
    @Override
    public void updateTime(String time) {
        timeCount.setText(time);
    }

    @Override
    public void setAdapterRecycler(ArrayList<Test> listTest) {

    }

    @Override
    public void setPageAdapter(ArrayList<Fragment> listFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        QuestPagerAdapter questPagerAdapter = new QuestPagerAdapter(fragmentManager,listFragment);
        viewPager.setAdapter(questPagerAdapter);
    }

    @Override
    public void setLinkAudio(String url) {
        startAudio(url);
    }

    @Override
    public void showDialogSure(ArrayList<AnswerCheck> listAnswerCheck) {
        recyclerViewSure.setAdapter(new SureAdapter(this,listAnswerCheck,this));
        dialogSure.show();
    }

    @Override
    public void showDialogResult(int correct, int total) {
        tvDialogResult.setText(correct+"/"+total);
        dialogResult.show();
    }

    @Subscribe
    public void onAnswerCheckEvent(AnswerCheck answerCheck) {
        lQPresenter.updateAnswerCheck(answerCheck);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_sure:
                lQPresenter.clickSure();
                break;
            case R.id.btn_submit:
                lQPresenter.clickSubmit();
                break;
            case R.id.btn_dialog_result_check:
                eventCheckAnswer = new EventCheckAnswer(true);
                EventBus.getDefault().post(eventCheckAnswer);
                dialogResult.dismiss();
                break;
        }
    }

    @Override
    public void clickItem(int poisition) {
        viewPager.setCurrentItem(poisition);
        dialogSure.dismiss();
    }
}
