package com.decisionTree.wiki.controllers;

import com.decisionTree.wiki.dao.QuestionsDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionsController {

    private QuestionsDomainRepository questionsDomainRepository;


    @Autowired
    public QuestionsController(QuestionsDomainRepository questionsDomainRepository) {
        this.questionsDomainRepository = questionsDomainRepository;
    }
}
