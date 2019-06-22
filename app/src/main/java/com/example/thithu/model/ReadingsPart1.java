package com.example.thithu.model;

import java.io.Serializable;

public class ReadingsPart1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String title;

    private String suggestion;

    private String paragraph;

    public ReadingsPart1() {
    }

    public ReadingsPart1(Integer id) {
        this.id = id;
    }

    public ReadingsPart1(Integer id, String title, String suggestion, String paragraph) {
        this.id = id;
        this.title = title;
        this.suggestion = suggestion;
        this.paragraph = paragraph;
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

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
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
        if (!(object instanceof ReadingsPart1)) {
            return false;
        }
        ReadingsPart1 other = (ReadingsPart1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.ReadingsPart1[ id=" + id + " ]";
    }

}