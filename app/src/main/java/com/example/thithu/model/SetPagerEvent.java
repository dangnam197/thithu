package com.example.thithu.model;

public class SetPagerEvent {
    private int poisition;

    public SetPagerEvent(int poisition) {
        this.poisition = poisition;
    }

    public int getPoisition() {
        return poisition;
    }

    public void setPoisition(int poisition) {
        this.poisition = poisition;
    }
}
