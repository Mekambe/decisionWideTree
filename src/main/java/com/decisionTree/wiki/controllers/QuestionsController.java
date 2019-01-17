package com.decisionTree.wiki.controllers;

import com.decisionTree.wiki.dao.QuestionGroupRepository;
import com.decisionTree.wiki.dao.QuestionsDomainRepository;
import com.decisionTree.wiki.dao.TreeRepository;
import com.decisionTree.wiki.dao.UsersDomainRepository;
import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import com.decisionTree.wiki.domain.TreeDomain;
import com.decisionTree.wiki.domain.UsersDomain;
import com.decisionTree.wiki.dto.NewQuestionDto;
import com.decisionTree.wiki.dto.NewQuestionGroupDto;
import com.decisionTree.wiki.dto.QuestionDto;
import com.decisionTree.wiki.exceptions.GroupNotFound;
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


    @GetMapping("/questions/findAllActive")
    public List<QuestionGroupDomain> returnAllActiveQuestions() {
        List<QuestionGroupDomain> allByActive = questionGroupRepository.findAllByActive(true);
        return allByActive;

    }

    @GetMapping("/questions/findAllInactive")
    public List<QuestionGroupDomain> returnAllInactiveQuestions() {
        List<QuestionGroupDomain> allByActive = questionGroupRepository.findAllByActive(false);
        return allByActive;

    }

    @GetMapping("/questions/findAllInSingleGroup")
    public List<QuestionGroupDomain> returnAllSingleGroup() {
        List<QuestionGroupDomain> allByActive = questionGroupRepository.findAllBySingle(true);
        return allByActive;

    }

    @GetMapping("/questions/findAllInMultiGroup")
    public List<QuestionGroupDomain> returnAllMultiGroup() {
        List<QuestionGroupDomain> allByActive = questionGroupRepository.findAllBySingle(false);
        return allByActive;

    }

    @GetMapping("/questions/findAll")
    public List<QuestionsDomain> returnAllQuestions() {
        return questionsDomainRepository.findAll();
    }

    @GetMapping("/questionQroup/findAllQQ")
    public List<QuestionGroupDomain> returnAllQuestionGroup() {
        return questionGroupRepository.findAll();

    }

    @GetMapping("/User/findAllUsers")
    public List<UsersDomain> findAllUsers() {
        return usersDomainRepository.findAll();
    }




    @GetMapping("/User/userId")
    public Optional<UsersDomain> findUserById(@PathParam("uID") int uID) throws IdNotFound {
        Optional<UsersDomain> byId = usersDomainRepository.findById(uID);
        if (!byId.isPresent()) {
            throw new IdNotFound();
        }


        return byId;
    }

    @GetMapping("/questions/questionID")
    public Optional<QuestionsDomain> findQuestionById(@PathParam("qID") int qID) throws IdNotFound {
        Optional<QuestionsDomain> questionById = questionsDomainRepository.findById(qID);
        if (!questionById.isPresent()) {
            throw new IdNotFound();
        }

        return questionById;
    }

    @GetMapping("/questionQroup/questionQroupID")
    public Optional<QuestionGroupDomain> findQuestionGroupById(@PathParam("qqID") int qqID) throws IdNotFound {

        Optional<QuestionGroupDomain> questionGroupById = questionGroupRepository.findById(qqID);
        if (!questionGroupById.isPresent()) {
            throw new IdNotFound();
        }

        return questionGroupById;

    }

    @GetMapping("/tree/treeID")
    public Optional<TreeDomain> findTreeById(@PathParam("tID") int tID) throws IdNotFound {

        Optional<TreeDomain> TreeByID = treeRepository.findById(tID);
        if (!TreeByID.isPresent()) {
            throw new IdNotFound();
        }

        return TreeByID;
    }

    @DeleteMapping("/User/deleteUser")
    public void deleteUser(@PathParam("id") int id) throws IdNotFound {
        Optional<UsersDomain> byId = usersDomainRepository.findById(id);
        if (!byId.isPresent()) {
            throw new IdNotFound();
        }
        byId.ifPresent(p -> usersDomainRepository.delete(p));
    }


    @DeleteMapping("/questions/deleteQuestion")
    public void deleteQuestion(@PathParam("qID") int qID) throws IdNotFound {
        Optional<QuestionsDomain> deleteQuestionByID = questionsDomainRepository.findById(qID);

        if (!deleteQuestionByID.isPresent()) {
            throw new IdNotFound();
        } else {
            questionsDomainRepository.delete(deleteQuestionByID.get());
        }

    }


    @DeleteMapping("/questionQroup/deleteQuestionGroup")
    public void deleteQuestionGroup(@PathParam("qqID") int qqID) throws IdNotFound {
        Optional<QuestionGroupDomain> questionGroupID = questionGroupRepository.findById(qqID);
        if (!questionGroupID.isPresent()) {
            throw new IdNotFound();
        }
        questionGroupID.ifPresent(p -> questionGroupRepository.delete(p));

    }

    @DeleteMapping("/tree/deleteTree")
    public void deleteTree(@PathParam("tID") int tID) throws IdNotFound {
        Optional<TreeDomain> treeID = treeRepository.findById(tID);
        if (!treeID.isPresent()) {
            throw new IdNotFound();
        }
        treeID.ifPresent(p -> treeRepository.delete(p));

    }


    @PostMapping("/User/userUpdate")
    public int updateUser(@RequestBody UsersDomain usersDomain) {


        UsersDomain byName = usersDomainRepository.findByName(usersDomain.getName());
        if (!(byName == null)) {
            usersDomain.setName(usersDomain.getName());
            return 2;
        } else {
            usersDomainRepository.save(usersDomain);
        }

        return 1;

    }


    @PostMapping("/questions/addQuestion")
    public ResponseEntity<Integer> addNewQuestion(@RequestParam(value = "question") String question,
                                                  @RequestParam(value = "number") int number,
                                                  @RequestParam(value = "questionGroup") int groupId) {


        QuestionsDomain newQuestion = new QuestionsDomain();
        QuestionGroupDomain group = new QuestionGroupDomain();
        newQuestion.setQuestion(question);
        newQuestion.setNumber(number);
        newQuestion.setQuestionHandler(group);
        group.setIdQuestionGroup(groupId);


        QuestionsDomain save = questionsDomainRepository.save(newQuestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(Math.toIntExact(save.getIdQuestions()));


    }

    @PostMapping("/User/addUser")
    public ResponseEntity<Integer> addNewUser(@RequestParam(value = "name") String name) {

        UsersDomain newUser = new UsersDomain();
        newUser.setName(name);

        UsersDomain save = usersDomainRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(Math.toIntExact(save.getIdUser()));


    }


//    @PostMapping("/questions/addQuestionOrUpdate")
//    public void addNewQuestionOrUpdate(@RequestBody NewQuestionDto newQuestionDto) {
//
//
//
//        Optional<QuestionGroupDomain> questionGroupNumber = questionGroupRepository.findById(newQuestionDto.getQuestionHandler());
//        if (questionGroupNumber.isPresent()){
//            Optional <QuestionsDomain> question = Optional.ofNullable(questionsDomainRepository.findByNumberAndQuestionHandler(newQuestionDto.getNumber(),questionGroupNumber.get()));
//            if (question.isPresent() && question.get().getQuestionHandler().getIdQuestionGroup() == questionGroupNumber.get().getIdQuestionGroup()){
//                question.get().setQuestion(newQuestionDto.getQuestion());
//
//
//
//                questionsDomainRepository.save(question.get());
//
//            }else {
//                QuestionsDomain questionsDomain = new QuestionsDomain();
//                questionsDomain.setNumber(newQuestionDto.getNumber());
//                questionsDomain.setQuestion(newQuestionDto.getQuestion());
//                questionsDomain.setQuestionHandler(questionGroupNumber.get());
//                questionsDomainRepository.save(questionsDomain);
//
//
//            }
//
//
//        }else{
//            QuestionGroupDomain questionGroupDomain = new QuestionGroupDomain();
//            //questionGroupDomain.setGroupId(newQuestionDto.getQuestionHandler());
//             questionGroupDomain.setActive(true);
//            QuestionGroupDomain questionGroupID = questionGroupRepository.save(questionGroupDomain);
//
//
//            QuestionsDomain questionsDomain = new QuestionsDomain();
//
//            questionsDomain.setQuestion(newQuestionDto.getQuestion());
//            questionsDomain.setNumber(newQuestionDto.getNumber());
//            questionsDomain.setQuestionHandler(questionGroupID);
//
//            questionsDomainRepository.save(questionsDomain);
//
//
//
//        }
//
//    }

    @PostMapping("/questions/addQuestionOrUpdate")
    public void addNewQuestionOrUpdate(@RequestBody NewQuestionDto newQuestionDto) throws GroupNotFound {


        Optional<QuestionGroupDomain> questionGroupNumber = questionGroupRepository.findById(newQuestionDto.getQuestionHandler());
        if (questionGroupNumber.isPresent()) {
            Optional<QuestionsDomain> question = Optional.ofNullable(questionsDomainRepository.findByNumberAndQuestionHandler(newQuestionDto.getNumber(), questionGroupNumber.get()));
            if (question.isPresent() && question.get().getQuestionHandler().getIdQuestionGroup() == questionGroupNumber.get().getIdQuestionGroup()) {
                question.get().setQuestion(newQuestionDto.getQuestion());


                questionsDomainRepository.save(question.get());

            } else {
                QuestionsDomain questionsDomain = new QuestionsDomain();
                questionsDomain.setNumber(newQuestionDto.getNumber());
                questionsDomain.setQuestion(newQuestionDto.getQuestion());
                questionsDomain.setQuestionHandler(questionGroupNumber.get());
                questionsDomainRepository.save(questionsDomain);


            }
        } else {
            throw new GroupNotFound();


        }
    }

    @PostMapping("questionGroup/addQuestionGroup")
    public void addNewQuestionGroup(@RequestBody NewQuestionGroupDto newQuestionGroupDto) {
        Optional<QuestionGroupDomain> groupNumber = questionGroupRepository.findByName(newQuestionGroupDto.getName());
        if (groupNumber.isPresent()) {
            groupNumber.get().setActive(newQuestionGroupDto.isActive());
            groupNumber.get().setSingle(newQuestionGroupDto.isSingle());


            questionGroupRepository.save(groupNumber.get());

        } else {
            QuestionGroupDomain questionGroupDomain = new QuestionGroupDomain();
            questionGroupDomain.setName(newQuestionGroupDto.getName());
            questionGroupDomain.setSingle(newQuestionGroupDto.isSingle());
            questionGroupDomain.setActive(newQuestionGroupDto.isSingle());

            questionGroupRepository.save(questionGroupDomain);

        }


    }


    @GetMapping("/question/randomQuestion")
    public List<QuestionsDomain> returnRandomQuestion(@RequestParam(value = "single") boolean singleOrMulti) {

        List<QuestionsDomain> questionsDomains = treeLogicService.randomTreeQuestion(singleOrMulti);

        return questionsDomains;
    }

    @GetMapping("/getQuestionDecisionTree")
    public QuestionDto questionHandler(@PathParam("id") int id) {
        QuestionDto questionFromTree = treeLogicService.getQuestionFromTree(id);

        return questionFromTree;

    }


}