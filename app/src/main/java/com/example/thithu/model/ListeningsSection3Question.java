package com.example.thithu.model;

import java.io.Serializable;

public class ListeningsSection3Question implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private int listeningSection3Id;

    private String question;

    private String answer;

    public ListeningsSection3Question() {
    }

    public ListeningsSection3Question(Integer id) {
        this.id = id;
    }

    public ListeningsSection3Question(Integer id, int listeningSection3Id, String question, String answer) {
        this.id = id;
        this.listeningSection3Id = listeningSection3Id;
        this.question = question;
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getListeningSection3Id() {
        return listeningSection3Id;
    }

    public void setListeningSection3Id(int listeningSection3Id) {
        this.listeningSection3Id = listeningSection3Id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
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
        if (!(object instanceof ListeningsSection3Question)) {
            return false;
        }
        ListeningsSection3Question other = (ListeningsSection3Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.ListeningsSection3Question[ id=" + id + " ]";
    }


}
