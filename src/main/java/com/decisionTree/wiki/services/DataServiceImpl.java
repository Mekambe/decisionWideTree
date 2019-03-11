/*
 * Developed by Jan Misiorny
 * February A.D. 2019
 */

package com.decisionTree.wiki.services;

import com.decisionTree.wiki.controllers.DataController;
import com.decisionTree.wiki.controllers.QuestionsController;
import com.decisionTree.wiki.dao.QuestionGroupRepository;
import com.decisionTree.wiki.dao.QuestionsDomainRepository;
import com.decisionTree.wiki.dao.TreeRepository;
import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    private QuestionsDomainRepository questionsDomainRepository;
    private TreeRepository treeRepository;
    private QuestionGroupRepository questionGroupRepository;
    private DataController dataController;

    @Autowired
    public DataServiceImpl(QuestionsDomainRepository questionsDomainRepository, TreeRepository treeRepository, QuestionGroupRepository questionGroupRepository, DataController dataController) {
        this.questionsDomainRepository = questionsDomainRepository;
        this.treeRepository = treeRepository;
        this.questionGroupRepository = questionGroupRepository;
        this.dataController = dataController;
    }



public List<QuestionGroupDomain> findAllQuestionGroupsBasedOnTags (String tag) {


    String[] split = tag.split(",");
    List<QuestionGroupDomain> objects = new ArrayList<>();
    List<QuestionsDomain> object2 = new ArrayList<>();
    for (String tags : split) {
        String upperCaseTags = tags.toUpperCase();
        List<QuestionGroupDomain> byTagIsContaining = questionGroupRepository.findByTagIsContaining(upperCaseTags);



        for (int i = 0; i < byTagIsContaining.size(); i++) {

            objects.add(byTagIsContaining.get(i));

        }

    }
    for (QuestionGroupDomain list2: objects){
        List<QuestionsDomain> groupId = list2.getGroupId();
    }


    return objects;

}



}
