package com.decisionTree.wiki.controllers;

import com.decisionTree.wiki.dao.QuestionGroupRepository;
import com.decisionTree.wiki.dao.QuestionsDomainRepository;
import com.decisionTree.wiki.dao.TreeRepository;
import com.decisionTree.wiki.dao.UsersDomainRepository;
import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import com.decisionTree.wiki.domain.TreeDomain;
import com.decisionTree.wiki.domain.UsersDomain;
import com.decisionTree.wiki.dto.QuestionDto;
import com.decisionTree.wiki.exceptions.IdNotFound;
import com.decisionTree.wiki.services.TreeLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
public class QuestionsController {

    private QuestionsDomainRepository questionsDomainRepository;
    private TreeLogicService treeLogicService;
    private UsersDomainRepository usersDomainRepository;
    private QuestionGroupRepository questionGroupRepository;
    private TreeRepository treeRepository;


    @Autowired
    public QuestionsController(QuestionsDomainRepository questionsDomainRepository, TreeLogicService treeLogicService, UsersDomainRepository usersDomainRepository, QuestionGroupRepository questionGroupRepository, TreeRepository treeRepository) {
        this.questionsDomainRepository = questionsDomainRepository;
        this.treeLogicService = treeLogicService;
        this.usersDomainRepository = usersDomainRepository;
        this.questionGroupRepository = questionGroupRepository;
        this.treeRepository = treeRepository;
    }


    @GetMapping ("/questions")
    public QuestionDto questionHandler (@PathParam("id") Integer id){
        QuestionDto questionFromTree = treeLogicService.getQuestionFromTree(id);

        return questionFromTree;

    }

    @GetMapping ("/User/userId")
    public Optional<UsersDomain> findUserById (@PathParam("uID") Long uID) throws IdNotFound {
        Optional<UsersDomain> byId = usersDomainRepository.findById(uID);
        if(!byId.isPresent()) {throw new IdNotFound();}


     return byId;
    }

    @GetMapping("/questions/questionID")
    public Optional<QuestionsDomain> findQuestionById (@PathParam("qID") long qID) throws IdNotFound {
        Optional<QuestionsDomain> questionById = questionsDomainRepository.findById(qID);
        if(!questionById.isPresent()) {throw new IdNotFound();}

        return questionById;
    }

    @GetMapping("/questionQroup/questionQroupID")
    public Optional<QuestionGroupDomain> findQuestionGroupById (@PathParam("qqID")  long qqID) throws IdNotFound{

        Optional<QuestionGroupDomain> questionGroupById = questionGroupRepository.findById(qqID);
        if(!questionGroupById.isPresent()) {throw new IdNotFound();}

        return questionGroupById;

    }

    @GetMapping("/tree/treeID")
    public Optional<TreeDomain> findTreeById (@PathParam("tID") long tID) throws IdNotFound {

        Optional<TreeDomain> TreeByID = treeRepository.findById(tID);
        if(!TreeByID.isPresent()) {throw new IdNotFound();}

        return TreeByID;
    }

    @DeleteMapping ("/User/deleteUser")
    public void deleteUser (@PathParam ("id") long id) throws IdNotFound {
        Optional<UsersDomain> byId = usersDomainRepository.findById(id);
        if(!byId.isPresent()) {throw new IdNotFound();}
        byId.ifPresent(p->usersDomainRepository.delete(p));
    }


     @DeleteMapping("/questions/deleteQuestion")
    public void deleteQuestion (@PathParam("qID") Long qID) throws IdNotFound {
         Optional<QuestionsDomain> deleteQuestionByID = questionsDomainRepository.findById(qID);
         if(!deleteQuestionByID.isPresent()) {throw new IdNotFound();}
         deleteQuestionByID.ifPresent(p->questionsDomainRepository.delete(p) );

     }

     @DeleteMapping("/questionQroup/deleteQuestionGroup")
    public void deleteQuestionGroup (@PathParam("qqID") long qqID) throws IdNotFound{
         Optional<QuestionGroupDomain> questionGroupID = questionGroupRepository.findById(qqID);
         if(!questionGroupID.isPresent()) {throw new IdNotFound();}
         questionGroupID.ifPresent(p->questionGroupRepository.delete(p) );

     }

     @DeleteMapping("/tree/deleteTree")
    public void deleteTree (@PathParam("tID") long tID) throws IdNotFound{
         Optional<TreeDomain> treeID = treeRepository.findById(tID);
         if(!treeID.isPresent()) {throw new IdNotFound();}
         treeID.ifPresent(p->treeRepository.delete(p) );

     }

@PostMapping("/questions/addQuestion")
    public ResponseEntity<Integer> addNewQuestion (@RequestParam(value="question") String question,
                                                   @RequestParam(value="number") long number,
                                                   @RequestParam(value="questionGroup") int groupId){

        QuestionsDomain newQuestion = new QuestionsDomain();
        QuestionGroupDomain group =  new QuestionGroupDomain();
newQuestion.setQuestion(question);
newQuestion.setNumber(number);
newQuestion.setQuestionHandler(group);
group.setIdQuestionGroup(groupId);

    QuestionsDomain save = questionsDomainRepository.save(newQuestion);
    return ResponseEntity.status(HttpStatus.CREATED).body(Math.toIntExact(save.getIdQuestions()));



    }

    @PostMapping("/User/addUser")
    public ResponseEntity<Integer> addNewUser (@RequestParam(value = "name") String name){

        UsersDomain newUser = new UsersDomain();
        newUser.setName(name);

        UsersDomain save = usersDomainRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(Math.toIntExact(save.getIdUser()));


    }














}
