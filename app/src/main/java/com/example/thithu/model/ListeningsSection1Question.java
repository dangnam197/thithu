package com.example.thithu.model;

import java.io.Serializable;

public class ListeningsSection1Question implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private int listeningSection1Id;

    private String question;

    private int timeFinish;

    public ListeningsSection1Question() {
    }

    public ListeningsSection1Question(Integer id) {
        this.id = id;
    }

    public ListeningsSection1Question(Integer id, int listeningSection1Id, String question, int timeFinish) {
        this.id = id;
        this.listeningSection1Id = listeningSection1Id;
        this.question = question;
        this.timeFinish = timeFinish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getListeningSection1Id() {
        return listeningSection1Id;
    }

    public void setListeningSection1Id(int listeningSection1Id) {
        this.listeningSection1Id = listeningSection1Id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(int timeFinish) {
        this.timeFinish = timeFinish;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListeningsSection1Question)) {
            return false;
        }
        ListeningsSection1Question other = (ListeningsSection1Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.ListeningsSection1Question[ id=" + id + " ]";
    }

}

