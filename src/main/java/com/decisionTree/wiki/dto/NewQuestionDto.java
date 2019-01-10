package com.decisionTree.wiki.dto;

import com.decisionTree.wiki.domain.QuestionGroupDomain;

import java.util.List;

public class NewQuestionDto {



    String question;
    int number;
    int  questionHandler;


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getQuestionHandler() {
        return questionHandler;
    }

    public void setQuestionHandler(int questionHandler) {
        this.questionHandler = questionHandler;
    }


}
