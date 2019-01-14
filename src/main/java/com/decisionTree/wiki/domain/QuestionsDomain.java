package com.decisionTree.wiki.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@ToString
public class QuestionsDomain {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuestions;

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
            @JoinColumn (name = "idQuestionGroup")
   QuestionGroupDomain questionHandler;

    private int number;

    private String question;

    public int getIdQuestions() {
        return idQuestions;
    }

    public void setIdQuestions(int idQuestions) {
        this.idQuestions = idQuestions;
    }

    public QuestionGroupDomain getQuestionHandler() {
        return questionHandler;
    }

    public void setQuestionHandler(QuestionGroupDomain questionHandler) {
        this.questionHandler = questionHandler;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
