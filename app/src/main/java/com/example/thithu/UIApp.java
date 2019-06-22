package com.example.thithu;

import androidx.fragment.app.Fragment;

import com.example.thithu.model.AnswerCheck;
import com.example.thithu.model.ListListening;
import com.example.thithu.model.ListeningsSection2;
import com.example.thithu.model.ListeningsSection3Question;
import com.example.thithu.model.ReadingsPart1;
import com.example.thithu.model.ReadingsPart1Question;
import com.example.thithu.model.Test;

import java.util.ArrayList;

public interface UIApp {
    interface IMainView {
        void setData(String text);
        void startListeningActivity();
        void starReadingActivity();
        void starWritingActivity();
        void starSpeakingActivity();
        void startTimePicker();
        void startServiceAudio();
    }
    interface IListeningQuestView {
        void updateTime(String time);
        void setLinkAudio(String url);
        void setPager(int poisition);
        void playPause();

    }
    interface FragmentListeningS1 {
        void setPager(int poisition);
        void setPageAdapter(ArrayList<Fragment> listFragment);
        void showDialogSure(ArrayList<AnswerCheck> listAnswerCheck);
        void showDialogResult(int correct,int total);
    }
    interface FragmentListeningS2{
        void setDataRecyclerView(ListeningsSection2 listeningsSection2);
        void showDialogResult(int correct,int total);
        void setImageView(String link);
    }
    interface FragmentListeningS3{
        void setDataRecyclerView(ArrayList<ListeningsSection3Question> list);
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
    interface FragmentReadingP1{
        void setDataRecyclerView(ArrayList<ReadingsPart1Question> listReadingP1);
        void showDialogResult(int correct,int total);
        void setTextReading(String text);
    }
}
