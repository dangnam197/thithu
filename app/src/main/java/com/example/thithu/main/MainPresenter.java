package com.example.thithu.main;

import com.example.thithu.UIApp;

public class MainPresenter {
    private UIApp.IMainView iMainView;

    public MainPresenter(UIApp.IMainView iMainView) {
        this.iMainView = iMainView;
    }

    public void setData() {
        iMainView.setData("đặng nam");
    }
    public void ListeningClick(){
        iMainView.startListeningActivity();
    }
    public void ReadingClick(){
        iMainView.starReadingActivity();
    }
    public void WritingClick(){
        iMainView.starWritingActivity();
    }
    public void SpeakingClick(){
        iMainView.starSpeakingActivity();
    }
}
