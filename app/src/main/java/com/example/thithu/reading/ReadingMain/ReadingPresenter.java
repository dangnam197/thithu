package com.example.thithu.reading.ReadingMain;

import android.content.Context;

import com.example.thithu.UIApp;

public class ReadingPresenter {
    private UIApp.IListeningView listeningView;
    private Context context;

    public ReadingPresenter(UIApp.IListeningView listeningView, Context context) {
        this.listeningView = listeningView;
        this.context = context;
    }
    public void clickS1(){
        listeningView.startActivityS1();
    }
    public void clickS2(){
        listeningView.startActivityS2();
    }
    public void clickS3(){
        listeningView.startActivityS3();
    }
    public void clickS4(){
        listeningView.startActivityS4();
    }
}
