package com.example.thithu.database.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mark {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int type;
    private int sid;
    private int point;
    private int max;


    public Mark(int type, int sid, int point, int max) {
        this.type = type;
        this.sid = sid;
        this.point = point;
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }


    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
