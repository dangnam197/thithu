package com.example.thithu;

public interface UIApp {
    interface IMainActivity{
        void setData(String text);
        void startListeningActivity();
        void starReadingActivity();
        void starWritingActivity();
        void starSpeakingActivity();
    }
}
