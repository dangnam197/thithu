package com.example.thithu.model;

public class StartAudio {
    private boolean start;
    private int time;
    public StartAudio(boolean start) {
        this.start = start;
    }

    public StartAudio(boolean start, int time) {
        this.start = start;
        this.time = time;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
