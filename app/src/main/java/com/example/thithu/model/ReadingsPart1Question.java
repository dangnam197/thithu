package com.example.thithu.model;

import java.io.Serializable;

public class ReadingsPart1Question implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private int readingsPart1Id;

    private String question;

    private short answer;

    public ReadingsPart1Question() {
    }

    public ReadingsPart1Question(Integer id) {
        this.id = id;
    }

    public ReadingsPart1Question(Integer id, int readingsPart1Id, String question, short answer) {
        this.id = id;
        this.readingsPart1Id = readingsPart1Id;
        this.question = question;
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getReadingsPart1Id() {
        return readingsPart1Id;
    }

    public void setReadingsPart1Id(int readingsPart1Id) {
        this.readingsPart1Id = readingsPart1Id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public short getAnswer() {
        return answer;
    }

    public void setAnswer(short answer) {
        this.answer = answer;
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
        if (!(object instanceof ReadingsPart1Question)) {
            return false;
        }
        ReadingsPart1Question other = (ReadingsPart1Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.ReadingsPart1Question[ id=" + id + " ]";
    }

}
