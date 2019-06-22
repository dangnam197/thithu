package com.example.thithu.model;

import java.io.Serializable;

public class Writings implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String title;

    private String question;

    private String sample;

    private Integer image;

    public Writings() {
    }

    public Writings(Integer id) {
        this.id = id;
    }

    public Writings(Integer id, String title, String question, String sample) {
        this.id = id;
        this.title = title;
        this.question = question;
        this.sample = sample;
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

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
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
        if (!(object instanceof Writings)) {
            return false;
        }
        Writings other = (Writings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Writings[ id=" + id + " ]";
    }

}
