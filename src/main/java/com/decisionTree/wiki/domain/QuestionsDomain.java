package com.decisionTree.wiki.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@ToString
public class QuestionsDomain {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idQuestions;

    @ManyToOne
            @JoinColumn (name = "idQuestionGroup")
   QuestionGroupDomain questionHandler;

    private long number;

    private String question;

    public long getIdQuestions() {
        return idQuestions;
    }

    public void setIdQuestions(long idQuestions) {
        this.idQuestions = idQuestions;
    }

    public QuestionGroupDomain getQuestionHandler() {
        return questionHandler;
    }

    public void setQuestionHandler(QuestionGroupDomain questionHandler) {
        this.questionHandler = questionHandler;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
