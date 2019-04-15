package com.example.thithu.main;

import com.example.thithu.UIApp;

public class MainPresenter {
    private UIApp.IMainActivity iMainActivity;

    public MainPresenter(UIApp.IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
    }

    public void setData() {
        iMainActivity.setData("đặng nam");
    }
    public void ListeningClick(){
        iMainActivity.startListeningActivity();
    }
    public void ReadingClick(){
        iMainActivity.starReadingActivity();
    }
    public void WritingClick(){
        iMainActivity.starWritingActivity();
    }
    public void SpeakingClick(){
        iMainActivity.starSpeakingActivity();
    }
}
