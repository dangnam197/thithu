package com.example.thithu.model;

import java.io.Serializable;

public class ListeningsSection1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String title;

    private String audio;

    private String suggestion;

    public ListeningsSection1() {
    }

    public ListeningsSection1(Integer id) {
        this.id = id;
    }

    public ListeningsSection1(Integer id, String title, String audio, String suggestion) {
        this.id = id;
        this.title = title;
        this.audio = audio;
        this.suggestion = suggestion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListeningsSection1)) {
            return false;
        }
        ListeningsSection1 other = (ListeningsSection1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.ListeningsSection1[ id=" + id + " ]";
    }

}
