/*
 * Developed by Jan Misiorny
 * February A.D. 2019
 */

package com.decisionTree.wiki.controllers;

import com.decisionTree.wiki.dao.*;
import com.decisionTree.wiki.domain.*;
import com.decisionTree.wiki.dto.NewQuestionDto;
import com.decisionTree.wiki.dto.NewQuestionGroupBooleanDto;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class QuestionsController {





    private QuestionsDomainRepository questionsDomainRepository;
    private TreeLogicService treeLogicService;
    private UsersDomainRepository usersDomainRepository;
    private QuestionGroupRepository questionGroupRepository;
    private TreeRepository treeRepository;
    private AnwsersImageAndLinksRepository anwsersImageAndLinksRepository;

    @Autowired
    public QuestionsController(QuestionsDomainRepository questionsDomainRepository, TreeLogicService treeLogicService, UsersDomainRepository usersDomainRepository, QuestionGroupRepository questionGroupRepository, TreeRepository treeRepository, AnwsersImageAndLinksRepository anwsersImageAndLinksRepository) {
        this.questionsDomainRepository = questionsDomainRepository;
        this.treeLogicService = treeLogicService;
        this.usersDomainRepository = usersDomainRepository;
        this.questionGroupRepository = questionGroupRepository;
        this.treeRepository = treeRepository;
        this.anwsersImageAndLinksRepository = anwsersImageAndLinksRepository;
    }




    @GetMapping("/questions/findAllActive")
    public List<QuestionGroupDomain> returnAllActiveQuestions () {
        List <QuestionGroupDomain> allByActive = questionGroupRepository.findAllByActive(true);
        return allByActive;

    }

    @GetMapping("/questions/findAllInactive")
    public List<QuestionGroupDomain> returnAllInactiveQuestions () {
        List <QuestionGroupDomain> allByActive = questionGroupRepository.findAllByActive(false);
        return allByActive;

    }
    @GetMapping("/questions/findAllInSingleGroup")
    public List<QuestionGroupDomain> returnAllSingleGroup () {
        List <QuestionGroupDomain> allByActive = questionGroupRepository.findAllBySingle(true);
        return allByActive;

    }
    @GetMapping("/questions/findAllInMultiGroup")
    public List<QuestionGroupDomain> returnAllMultiGroup () {
        List <QuestionGroupDomain> allByActive = questionGroupRepository.findAllBySingle(false);
        return allByActive;

    }

    @GetMapping("/questions/findAll")
    public List<QuestionsDomain> returnAllQuestions() {
        return questionsDomainRepository.findAll();
    }

    @CrossOrigin(origins = "C:/Users/j.misiorny/Desktop/Kinguinator/Front/Wersja%203/Kinguinator/dashboard.html")
    @GetMapping("/questionGroup/findAllQQ")
    public List<QuestionGroupDomain> returnAllQuestionGroup(){
        return questionGroupRepository.findAll();
    }

    @GetMapping ("/questionGroup/returnOne")
    public QuestionGroupDomain returnOneQuestionGroup (@PathParam("id") int id){
        QuestionGroupDomain byIdQuestionGroup = questionGroupRepository.findByIdQuestionGroup(id);
        return byIdQuestionGroup;
    }

    @GetMapping("/questionGroup/returnAllGroupsID")
    public ArrayList<Object> returnAllIDForGroups (){
        ArrayList<Object> list = new ArrayList<Object>();

        List<QuestionGroupDomain> all = questionGroupRepository.findAll();

        for (QuestionGroupDomain object : all){
            int idQuestionGroup = object.getIdQuestionGroup();
            list.add(idQuestionGroup);
        }

        return list;
    }


    @GetMapping("/User/findAllUsers")
    public List<UsersDomain> findAllUsers(){
       return usersDomainRepository.findAll();
    }

    @GetMapping("findAllTags")
    public List<String> findAllTags(){
        List<QuestionGroupDomain> all = questionGroupRepository.findAll();

        List<String> listofAllTags = treeLogicService.returnListOfStringsContainingTags(all);
        return listofAllTags;

    }



    @GetMapping("findAllActiveTags")
    @CrossOrigin(origins = "http://localhost:8090")
    public List<String> findAllActiveTAgs(){

        List<QuestionGroupDomain> all = questionGroupRepository.findAllByActive(true);
        List<String> listofAllActiveTags = treeLogicService.returnListOfStringsContainingTags(all);
        return listofAllActiveTags;
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

//    @GetMapping("/questionQroup/questionQroupID")
//    public Optional<QuestionGroupDomain> findQuestionGroupById(@PathParam("qqID") int qqID) throws IdNotFound {
//
//        Optional <QuestionGroupDomain> questionGroupById = Optional.ofNullable((questionGroupRepository.findByIdQuestionGroup(qqID)));
//        if (!questionGroupById.isPresent()) {
//            throw new IdNotFound();
//        }
//
//        return questionGroupById;
//
//    }

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
//        if (!deleteQuestionByID.isPresent()) {
//            throw new IdNotFound();
//        }
        notDelete(deleteQuestionByID);

        if (deleteQuestionByID.isPresent()){

            questionsDomainRepository.delete(deleteQuestionByID.get());
        }



    }





    public void notDelete (Optional w) throws IdNotFound{



        if (!w.isPresent()){
            throw new IdNotFound();
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


    @PostMapping ("/User/userUpdate")
    public int updateUser (@RequestBody UsersDomain usersDomain){


        UsersDomain byName = usersDomainRepository.findByName(usersDomain.getName());
        if (!(byName == null)) {
            usersDomain.setName(usersDomain.getName());
            return 2;
        }else{usersDomainRepository.save(usersDomain);}

     return 1;

    }


    @PostMapping("/questions/addQuestion")
    public ResponseEntity<Integer> addNewQuestion(@RequestParam(value = "question") String question,
                                                  @RequestParam(value = "number") int number,
                                                  @RequestParam(value = "questionGroup") int groupId,
                                                  @RequestParam(value="link") String link) {



        QuestionsDomain newQuestion = new QuestionsDomain();
        QuestionGroupDomain group = new QuestionGroupDomain();
        newQuestion.setQuestion(question);
        newQuestion.setNumber(number);
        newQuestion.setQuestionHandler(group);
        newQuestion.setLink(link);
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



    @PostMapping("/questions/addQuestionOrUpdate")
    public QuestionsDomain addNewQuestionOrUpdate(@RequestBody NewQuestionDto newQuestionDto) throws GroupNotFound {


        Optional<QuestionGroupDomain> questionGroupNumber = questionGroupRepository.findById(newQuestionDto.getQuestionHandler());
        if (questionGroupNumber.isPresent()) {
//            Optional<QuestionsDomain> question = Optional.ofNullable(questionsDomainRepository.findByNumberAndQuestionHandler(newQuestionDto.getNumber(), questionGroupNumber.get()));
            Optional<QuestionsDomain> question = Optional.ofNullable(questionsDomainRepository.findByIdQuestionsAndQuestionHandler(newQuestionDto.getNumber(), questionGroupNumber.get()));
            if (question.isPresent() && question.get().getQuestionHandler().getIdQuestionGroup() == questionGroupNumber.get().getIdQuestionGroup()) {

                question.get().setQuestion(newQuestionDto.getQuestion());
                question.get().setLink(newQuestionDto.getLink());


                QuestionsDomain save = questionsDomainRepository.save(question.get());

                return save;

            } else {
                QuestionsDomain questionsDomain = new QuestionsDomain();
                questionsDomain.setQuestion(newQuestionDto.getQuestion());
                questionsDomain.setQuestionHandler(questionGroupNumber.get());
                questionsDomain.setLink(newQuestionDto.getLink());
                questionsDomain.setNumber(treeLogicService.returnTheNextQuestionNumberInsideTheTree(newQuestionDto.getQuestionHandler()));
                QuestionsDomain save = questionsDomainRepository.save(questionsDomain);
//                save.setNumber(save.getIdQuestions());
//                questionsDomainRepository.save(save);

                return  questionsDomain;

            }
        } else {
            throw new GroupNotFound();


        }

    }


    @PostMapping("questionGroup/addOneQuestionGroupAndOneQuestion")
    public int addGroupAndQuestion (){

        QuestionGroupDomain questionGroupDomain = new QuestionGroupDomain();
        questionGroupDomain.setTag("");
        QuestionGroupDomain save = questionGroupRepository.save(questionGroupDomain);

        Optional<QuestionGroupDomain> byId = questionGroupRepository.findById(save.getIdQuestionGroup());

        if (byId.isPresent()) {
            QuestionsDomain questionsDomain = new QuestionsDomain();
            questionsDomain.setQuestionHandler(byId.get());

            QuestionsDomain save2 = questionsDomainRepository.save(questionsDomain);
//             save2.setNumber(save2.getIdQuestions());
            save2.setNumber(1);
             questionsDomainRepository.save(save2);

        }


        return save.getIdQuestionGroup();
    }

    @PostMapping("questionGroup/updateQuestionGroup")
    public ResponseEntity<Integer> updateSingleAndActiveInsideQuestionGroup (@RequestBody NewQuestionGroupBooleanDto newQuestionGroupBooleanDto) throws IdNotFound {

        Optional<QuestionGroupDomain> byIdQuestionGroup = Optional.ofNullable(questionGroupRepository.findByIdQuestionGroup(newQuestionGroupBooleanDto.getIdGroup()));
        if (!byIdQuestionGroup.isPresent()){
            throw new IdNotFound();
        }else {

            byIdQuestionGroup.get().setActive(newQuestionGroupBooleanDto.isActive());
            byIdQuestionGroup.get().setSingle(newQuestionGroupBooleanDto.isSingle());
            QuestionGroupDomain save = questionGroupRepository.save(byIdQuestionGroup.get());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(Math.toIntExact(save.getIdQuestionGroup()));



        }


    }




    @PostMapping("questionGroup/addQuestionGroup")
    public void addNewQuestionGroup (@RequestBody NewQuestionGroupDto newQuestionGroupDto){
        Optional<QuestionGroupDomain> groupNumber = questionGroupRepository.findByName(newQuestionGroupDto.getName());
        if (groupNumber.isPresent()){
            groupNumber.get().setActive(newQuestionGroupDto.isActive());
            groupNumber.get().setSingle(newQuestionGroupDto.isSingle());


            questionGroupRepository.save(groupNumber.get());

        }else {
            QuestionGroupDomain questionGroupDomain = new QuestionGroupDomain();
            questionGroupDomain.setName(newQuestionGroupDto.getName());
            questionGroupDomain.setSingle(newQuestionGroupDto.isSingle());
            questionGroupDomain.setActive(newQuestionGroupDto.isSingle());
            questionGroupDomain.setTag("");


            questionGroupRepository.save(questionGroupDomain);

        }


    }



//    @GetMapping ("firstQuestion")
//    public String returnFirstQuestion (){
//        return "Do we go Single?";
//    }

    @PostMapping("firstQuestion/firstQuestionResponce")
    public boolean anwserfortheFirstQuestion (@RequestParam(value="firstQuestion") String firstQuestion){
        if (firstQuestion.equals("Yes")){
            return true;
        } else if(firstQuestion.equals("No")) {
            return false;
        }
        return false;
    }

    @GetMapping("/firstQuestion/randomQuestion")
    public QuestionDto returnRandomQuestion (@RequestParam(value="single") boolean singleOrMulti){

        List<QuestionsDomain> questionsDomains = treeLogicService.randomTreeQuestion(singleOrMulti);
        QuestionDto questionDto = new QuestionDto();
      // treeRepository.findByRoot(questionsDom)

      QuestionsDomain firstQuestion = null;
        for (int i = 0; i < questionsDomains.size(); i++) {

            if(questionsDomains.get(i).getNumber()==1)
                firstQuestion=questionsDomains.get(i);

        }
      Optional <TreeDomain> byRoot = Optional.ofNullable(treeRepository.findByRoot(firstQuestion.getNumber()));
        questionDto.setLeft(byRoot.get().getLeft());
        questionDto.setRight(byRoot.get().getRight());
        questionDto.setRoot(byRoot.get().getRoot());
        questionDto.setGroupId(firstQuestion.getQuestionHandler().getIdQuestionGroup());
        questionDto.setQuestion(firstQuestion.getQuestion());


        return questionDto;
    }

    @GetMapping ("nextRandomQuestion")
    public QuestionDto returnNextRandomQuestion (@RequestParam(value="domainNumber") int questionDomainNumber, @RequestParam(value="questionGroupId") int questionGroupId) throws IdNotFound {
//        Optional <QuestionsDomain> byNumberAndQuestionHandler_idQuestionGroup = Optional.ofNullable(questionsDomainRepository.findByNumberAndQuestionHandler_IdQuestionGroup(questionDomainNumber, questionGroupId));
//
//        Optional <TreeDomain> treeRootNumber = Optional.ofNullable(treeRepository.findByRoot(questionDomainNumber));
//
//        if (!byNumberAndQuestionHandler_idQuestionGroup.isPresent()&&treeRootNumber.isPresent()){throw new IdNotFound();}
//        QuestionDto questionDto = new QuestionDto();
//
//        questionDto.setQuestion(byNumberAndQuestionHandler_idQuestionGroup.get().getQuestion());
//        questionDto.setGroupId(byNumberAndQuestionHandler_idQuestionGroup.get().getIdQuestions());
//        questionDto.setLink(byNumberAndQuestionHandler_idQuestionGroup.get().getLink());
//        questionDto.setRoot(treeRootNumber.get().getRoot());
//        questionDto.setRight(treeRootNumber.get().getRight());
//        questionDto.setLeft(treeRootNumber.get().getLeft());
//
//        return questionDto;

        QuestionDto questionDto = treeLogicService.mappingTheQuestionsForTheTreeAlgorythm(questionDomainNumber, questionGroupId);
        return questionDto;
    }

   @GetMapping ("/findAllTreeForQuestionGroup")
   public QuestionDto returnAllTreeQuestions (){


       QuestionDto questionDto = treeLogicService.returnAllTreeQuestions(1);

       return questionDto;
   }







    @GetMapping("imageLink/find")
    public List<AnwsersImageAndLinks> findById (@RequestParam (value = "id") int idImage){
        List<AnwsersImageAndLinks> byIdImageLinks = anwsersImageAndLinksRepository.findByIdImageLinks(idImage);


        return byIdImageLinks;
    }

    //TODO
     //metoda ktora czyta obrazki z dysku
    // zainsaluj microsoftr visual studio code


    @GetMapping ("firstQuestionProfile")
    public String returnFirstQuestionProfile (){
        return "Do you want to choose your game ?";
    }


    @GetMapping("changedLink")
    public String returnChangeLink (@RequestParam (value = "link") String link) {

        char d = 'd';
        char e = 'e';
        char slash = '/';
        for (int i = 0; i < link.length(); i++) {

            char linkChar = link.charAt(i);
//            25,26
            if (d == link.charAt(24) && e == link.charAt(25)) {
                return link + " to jest to";


            } else{
                StringBuilder newLInk = new StringBuilder(link);
                newLInk.insert(24,d);
                newLInk.insert(25,e);
                newLInk.insert(26,slash);

                return newLInk.toString();



        }
    }
        return null;
    }

    @GetMapping("customerLink")
    public String returnLinkThatCustomerIsIn (@RequestParam(value="idQuestion") int idQuestion){

        Optional<QuestionsDomain> byId = questionsDomainRepository.findById(idQuestion);

        return byId.get().getLink();


    }

    @PostMapping("updateTags")
    public void updateTagsInsideAGroupDomain (@RequestParam(value = "questionGroupId") int idQuestionGroup, @RequestParam(value = "updateTag") String tag) throws GroupNotFound {


        String[] split1 = tag.split(",");

        Optional<QuestionGroupDomain> byIdQuestionGroup = Optional.ofNullable(questionGroupRepository.findByIdQuestionGroup(idQuestionGroup));

        if (!byIdQuestionGroup.isPresent()){throw new GroupNotFound();} else {

//        Optional<String> tag1 = Optional.ofNullable(byIdQuestionGroup.getTag());
//
//        String[] split = tag1.get().split(",");


//        if (tag1.isPresent()){
//
//            String[] strings = Stream.concat(Arrays.stream(split1), Arrays.stream(split)).toArray(String[]::new);
//            String s = Arrays.toString(strings);
//
//            byIdQuestionGroup.setTag(s);
//            questionGroupRepository.save(byIdQuestionGroup);
//            return s;
//
//}
            String upperCaseTag = tag.toUpperCase();
            byIdQuestionGroup.get().setTag(upperCaseTag);
            questionGroupRepository.save(byIdQuestionGroup.get());

        }
    }

    @PostMapping("deleteTag")
    public void deleteAllTags (@RequestParam(value = "questionGroupId") int idQuestionGroup) {

        QuestionGroupDomain byIdQuestionGroup = questionGroupRepository.findByIdQuestionGroup(idQuestionGroup);

        String tag = byIdQuestionGroup.getTag();

        byIdQuestionGroup.setTag("");

        questionGroupRepository.save(byIdQuestionGroup);


    }

//    @PostMapping("deleteTag")
//    public void deleteOneTag (@RequestParam(value = "questionGroupId") int idQuestionGroup,@RequestParam(value = "tag") String tag){
//        QuestionGroupDomain byIdQuestionGroup = questionGroupRepository.findByIdQuestionGroup(idQuestionGroup);
//
//        String tag1 = byIdQuestionGroup.getTag();
//        String[] split = tag1.split(",");
//
//        if (tag1.contains(tag)){
//
//            for (int i = 0; i < split.length; i++) {
//                String s = split[i];
//
//
//            }
//
//
//            }
//
//
//    }
//
}
