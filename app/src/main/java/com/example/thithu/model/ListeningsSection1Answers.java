package com.example.thithu.model;

import java.io.Serializable;

public class ListeningsSection1Answers implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String answer;

    private short value;

    private int listeningsSection1QuestionId;

    public ListeningsSection1Answers() {
    }

    public ListeningsSection1Answers(Integer id) {
        this.id = id;
    }

    public ListeningsSection1Answers(Integer id, String answer, short value, int listeningsSection1QuestionId) {
        this.id = id;
        this.answer = answer;
        this.value = value;
        this.listeningsSection1QuestionId = listeningsSection1QuestionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public int getListeningsSection1QuestionId() {
        return listeningsSection1QuestionId;
    }

    public void setListeningsSection1QuestionId(int listeningsSection1QuestionId) {
        this.listeningsSection1QuestionId = listeningsSection1QuestionId;
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
        if (!(object instanceof ListeningsSection1Answers)) {
            return false;
        }
        ListeningsSection1Answers other = (ListeningsSection1Answers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return answer +" "+value;
    }

}
