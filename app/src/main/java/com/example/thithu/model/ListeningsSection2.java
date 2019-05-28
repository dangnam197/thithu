package com.example.thithu.model;

import java.io.Serializable;

public class ListeningsSection2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String title;

    private String question;


    private String audio;

    private String suggestion;

    private String q1;

    private String q2;

    private String q3;

    private String q4;

    private String q5;

    public ListeningsSection2() {
    }

    public ListeningsSection2(Integer id) {
        this.id = id;
    }

    public ListeningsSection2(Integer id, String title, String question, String audio, String suggestion, String q1, String q2, String q3, String q4, String q5) {
        this.id = id;
        this.title = title;
        this.question = question;
        this.audio = audio;
        this.suggestion = suggestion;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getQ4() {
        return q4;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    public String getQ5() {
        return q5;
    }

    public void setQ5(String q5) {
        this.q5 = q5;
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
        if (!(object instanceof ListeningsSection2)) {
            return false;
        }
        ListeningsSection2 other = (ListeningsSection2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.ListeningsSection2[ id=" + id + " ]";
    }
}