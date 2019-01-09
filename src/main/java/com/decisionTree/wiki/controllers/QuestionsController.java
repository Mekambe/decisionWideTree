package com.decisionTree.wiki.controllers;

import com.decisionTree.wiki.dao.QuestionsDomainRepository;
import com.decisionTree.wiki.dto.QuestionDto;
import com.decisionTree.wiki.services.TreeLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class QuestionsController {

    private QuestionsDomainRepository questionsDomainRepository;
    private TreeLogicService treeLogicService;

    @Autowired
    public QuestionsController(QuestionsDomainRepository questionsDomainRepository, TreeLogicService treeLogicService) {
        this.questionsDomainRepository = questionsDomainRepository;
        this.treeLogicService = treeLogicService;
    }



    @GetMapping ("/questions")
    public QuestionDto questionHandler (@PathParam("id") int firstQuestion){
        QuestionDto questionFromTree = treeLogicService.getQuestionFromTree(firstQuestion);

        return questionFromTree;

    }

}
