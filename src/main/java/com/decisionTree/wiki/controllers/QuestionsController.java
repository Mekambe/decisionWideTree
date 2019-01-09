package com.decisionTree.wiki.controllers;

import com.decisionTree.wiki.dao.QuestionsDomainRepository;
import com.decisionTree.wiki.dao.UsersDomainRepository;
import com.decisionTree.wiki.domain.QuestionsDomain;
import com.decisionTree.wiki.domain.UsersDomain;
import com.decisionTree.wiki.dto.QuestionDto;
import com.decisionTree.wiki.exceptions.IdNotFound;
import com.decisionTree.wiki.services.TreeLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
public class QuestionsController {

    private QuestionsDomainRepository questionsDomainRepository;
    private TreeLogicService treeLogicService;
    private UsersDomainRepository usersDomainRepository;

    @Autowired
    public QuestionsController(QuestionsDomainRepository questionsDomainRepository, TreeLogicService treeLogicService, UsersDomainRepository usersDomainRepository) {
        this.questionsDomainRepository = questionsDomainRepository;
        this.treeLogicService = treeLogicService;
        this.usersDomainRepository = usersDomainRepository;
    }






    @GetMapping ("/questions")
    public QuestionDto questionHandler (@PathParam("id") Integer id){
        QuestionDto questionFromTree = treeLogicService.getQuestionFromTree(id);

        return questionFromTree;

    }

    @GetMapping ("/User/userId")
    public Optional<UsersDomain> findUserById (@PathParam("userID") Long userId){
        Optional<UsersDomain> byId = usersDomainRepository.findById(userId);

     return byId;
    }

    @GetMapping("/Question")
    public Optional<QuestionsDomain> findQuestionByID (@PathParam("questionID") Long questionId){
        Optional<QuestionsDomain> questionById = questionsDomainRepository.findById(questionId);
        if(!questionById.isPresent()) try {
            throw new IdNotFound();
        } catch (IdNotFound idNotFound) {
            idNotFound.printStackTrace();
        }

        return questionById;
    }




}
