/*
 * Developed by Jan Misiorny
 * February A.D. 2019
 */

package com.decisionTree.wiki.controllers;

import com.decisionTree.wiki.dao.*;
import com.decisionTree.wiki.domain.CustomerDataDomain;
import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import com.decisionTree.wiki.dto.CustomerDataDto;
import com.decisionTree.wiki.dto.QuestionDtoWithTheMatcher;
import com.decisionTree.wiki.exceptions.IdNotFound;
import com.decisionTree.wiki.services.DataService;
import com.decisionTree.wiki.services.TreeLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class DataController {

    private DataService dataService;
    private QuestionsDomainRepository questionsDomainRepository;
    private TreeLogicService treeLogicService;
    private UsersDomainRepository usersDomainRepository;
    private QuestionGroupRepository questionGroupRepository;
    private TreeRepository treeRepository;
    private AnwsersImageAndLinksRepository anwsersImageAndLinksRepository;
    private CustomerDataRepository customerDataRepository;

    @Autowired
    public DataController(@Lazy DataService dataService, QuestionsDomainRepository questionsDomainRepository, TreeLogicService treeLogicService, UsersDomainRepository usersDomainRepository, QuestionGroupRepository questionGroupRepository, TreeRepository treeRepository, AnwsersImageAndLinksRepository anwsersImageAndLinksRepository, CustomerDataRepository customerDataRepository) {
        this.dataService = dataService;
        this.questionsDomainRepository = questionsDomainRepository;
        this.treeLogicService = treeLogicService;
        this.usersDomainRepository = usersDomainRepository;
        this.questionGroupRepository = questionGroupRepository;
        this.treeRepository = treeRepository;
        this.anwsersImageAndLinksRepository = anwsersImageAndLinksRepository;
        this.customerDataRepository = customerDataRepository;
    }










    @GetMapping("tag/findOneTreeFromTag")
    public QuestionGroupDomain findByTag (@PathParam("tag") String tag){
        return questionGroupRepository.findByTag(tag);

    }


    @GetMapping("firstQuestion")
    public QuestionDtoWithTheMatcher returnaListOfGroupDomainsContainingGivenTags (@RequestParam("tag") String tag) throws IdNotFound {

        List tree=new ArrayList();


        String[] split1 = tag.split(",");

        List<QuestionGroupDomain> allQuestionGroupsBasedOnTags = dataService.findAllQuestionGroupsBasedOnTags(tag);

        int matchGlobal = 0;
        int matchCounting = 0;

        List<Integer> matchGroupId = new ArrayList<>();

        for (int i = 0; i <allQuestionGroupsBasedOnTags.size() ; i++) {
            QuestionGroupDomain questionGroupDomain = allQuestionGroupsBasedOnTags.get(i);

            String tagsFromOneTree = questionGroupDomain.getTag();

            int match = 0;
            for (int j = 0; j < split1.length; j++) {
                if ( tagsFromOneTree.contains(split1[j])){
                    match ++;

                }
            }   int idQuestionGroup = questionGroupDomain.getIdQuestionGroup(); if (match==matchGlobal){



                matchGroupId.add(idQuestionGroup);


            } if (match>matchGlobal){
                matchGlobal=match;

                matchGroupId.clear();

                matchGroupId.add(idQuestionGroup);
            }
        }

        Integer theRightTree = matchGroupId.get(new Random().nextInt(matchGroupId.size()));


        QuestionGroupDomain byIdQuestionGroup = questionGroupRepository.findByIdQuestionGroup(theRightTree);
        List<QuestionsDomain> groupId1 = byIdQuestionGroup.getGroupId();

        Optional<QuestionsDomain> first = groupId1.stream().findFirst();
        int idQuestions = first.get().getNumber();


//        tree.add(first.get());
//            tree.add(theRightTree);

        QuestionDtoWithTheMatcher questionDto = treeLogicService.mappingTheQuestionsForTheTreeAlgorythmWithTheMatcher(idQuestions, theRightTree,matchGlobal);
        return questionDto;


//        QuestionGroupDomain randomQuestionGroup = allQuestionGroupsBasedOnTags.get(new Random().nextInt(allQuestionGroupsBasedOnTags.size()));
//
//
//        int idQuestionGroup = randomQuestionGroup.getIdQuestionGroup();
//        if (idQuestionGroup == Integer.parseInt(ip)){
//
//        }
//
//
//        List<QuestionsDomain> groupId = randomQuestionGroup.getGroupId();
//
//        Optional<QuestionsDomain> first = groupId.stream().findFirst();
//
//
//        return first;


    }








    @PostMapping ("data/saveAllCustomerDataInBase")
    public ResponseEntity<Integer> saveAllUserDataInBase (@RequestBody CustomerDataDto customerDataDto) throws IdNotFound {
        Optional<CustomerDataDomain> customerDataDomain = Optional.of(new CustomerDataDomain());

        customerDataDomain.get().setDate(customerDataDto.getDate());
        customerDataDomain.get().setIp(customerDataDto.getIp());
        customerDataDomain.get().setDid(customerDataDto.getDid());
        customerDataDomain.get().setGroupId(customerDataDto.getGroupId());
        customerDataDomain.get().setChoices(customerDataDto.getChoices());
        customerDataDomain.get().setTags(customerDataDto.getTags());


        CustomerDataDomain save = customerDataRepository.save(customerDataDomain.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(Math.toIntExact(save.getiDCustomerData()));


    }


    @PostMapping ("data/checkIfTheIpIsPresent")
    public String  checkIpAndIfItIsPresentLoadNewTree (@RequestParam(value="ip") String ip1) {

       Optional <CustomerDataDomain>  byIp = Optional.ofNullable(customerDataRepository.findByIp(ip1));

        if (byIp.isPresent()){
            return byIp.get().getGroupId();
        }else {return "Unique id";}





    }

}
















