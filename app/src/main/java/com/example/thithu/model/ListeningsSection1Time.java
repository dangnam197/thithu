package com.example.thithu.model;

public class ListeningsSection1Time {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private int begin;

    private int finish;


    private int listeningssection1questionId;

    private int listeningssection1Id;
    public ListeningsSection1Time() {
    }

    public ListeningsSection1Time(Integer id) {
        this.id = id;
    }

    public ListeningsSection1Time(Integer id, int begin, int finish, int listeningssection1questionId, int listeningssection1Id) {
        this.id = id;
        this.begin = begin;
        this.finish = finish;
        this.listeningssection1questionId = listeningssection1questionId;
        this.listeningssection1Id = listeningssection1Id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getListeningssection1questionId() {
        return listeningssection1questionId;
    }

    public void setListeningssection1questionId(int listeningssection1questionId) {
        this.listeningssection1questionId = listeningssection1questionId;
    }

    public int getListeningssection1Id() {
        return listeningssection1Id;
    }

    public void setListeningssection1Id(int listeningssection1Id) {
        this.listeningssection1Id = listeningssection1Id;
    }
}
