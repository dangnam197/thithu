package com.example.thithu;

import android.support.v4.app.Fragment;

import com.example.thithu.listening.ListeningQuest.fragment.QuesterPager;
import com.example.thithu.model.AnswerCheck;
import com.example.thithu.model.ListListening;
import com.example.thithu.model.ListeningsSection2;
import com.example.thithu.model.Test;

import java.util.ArrayList;

public interface UIApp {
    interface IMainView {
        void setData(String text);
        void startListeningActivity();
        void starReadingActivity();
        void starWritingActivity();
        void starSpeakingActivity();
    }
    interface IListeningQuestView {
        void updateTime(String time);
        void setAdapterRecycler(ArrayList<Test> listTest);
        void setPageAdapter(ArrayList<Fragment> listFragment);
        void setLinkAudio(String url);
        void showDialogSure(ArrayList<AnswerCheck> listAnswerCheck);
        void showDialogResult(int correct,int total);

    }
    interface IListeningView {
        void startActivityS1();
        void startActivityS2();
        void startActivityS3();
        void startActivityS4();
    }
    interface IListeningListView {
        void setAdapterRecycler(ArrayList<ListListening> list);
        void startActivity(int type,ListListening listListening);
    }
}
