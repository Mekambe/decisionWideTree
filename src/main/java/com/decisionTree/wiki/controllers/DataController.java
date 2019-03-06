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
import com.decisionTree.wiki.exceptions.IdNotFound;
import com.decisionTree.wiki.services.DataService;
import com.decisionTree.wiki.services.TreeLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
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


    @GetMapping("tag/returnaListOfGroupDomainsContainingGivenTags")
    public Optional<QuestionsDomain> returnaListOfGroupDomainsContainingGivenTags (@PathParam("tag") String tag) {






        List<QuestionGroupDomain> allQuestionGroupsBasedOnTags = dataService.findAllQuestionGroupsBasedOnTags(tag);
        QuestionGroupDomain randomQuestionGroup = allQuestionGroupsBasedOnTags.get(new Random().nextInt(allQuestionGroupsBasedOnTags.size()));
        List<QuestionsDomain> groupId = randomQuestionGroup.getGroupId();

        Optional<QuestionsDomain> first = groupId.stream().findFirst();


        return first;

    }








    @PostMapping ("data/saveAllCustomerDataInBase")
    public ResponseEntity<Integer> saveAllUserDataInBase (@RequestBody CustomerDataDto customerDataDto) throws IdNotFound {
        Optional<CustomerDataDomain> customerDataDomain = Optional.of(new CustomerDataDomain());

        customerDataDomain.get().setDate(customerDataDto.getData());
        customerDataDomain.get().setIp(customerDataDto.getIp());
        customerDataDomain.get().setDid(customerDataDto.getDid());
        customerDataDomain.get().setGroupId(customerDataDto.getGroupId());
        customerDataDomain.get().setChoices(customerDataDto.getChoices());
        customerDataDomain.get().setTags(customerDataDto.getTags());


        CustomerDataDomain save = customerDataRepository.save(customerDataDomain.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(Math.toIntExact(save.getiDCustomerData()));


    }

}
















