package com.example.thithu.model;

import java.io.Serializable;

public class ListListening implements Serializable {
    private int id;
    private String title;
    private int duration;
    private int current;

    public ListListening(int id, String title) {
        this.id = id;
        this.title = title;
        this.current = 0;
        this.duration = 5;
    }

    public ListListening(int id, String title, int duration, int current) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.current = current;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
