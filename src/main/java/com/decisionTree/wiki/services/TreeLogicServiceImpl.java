package com.decisionTree.wiki.services;

import com.decisionTree.wiki.dao.QuestionGroupRepository;
import com.decisionTree.wiki.dao.QuestionsDomainRepository;
import com.decisionTree.wiki.dao.TreeRepository;
import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import com.decisionTree.wiki.domain.TreeDomain;
import com.decisionTree.wiki.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class TreeLogicServiceImpl implements TreeLogicService {

    private QuestionsDomainRepository questionsDomainRepository;
    private TreeRepository treeRepository;
    private QuestionGroupRepository questionGroupRepository;

    @Autowired
    public TreeLogicServiceImpl(QuestionsDomainRepository questionsDomainRepository, TreeRepository treeRepository, QuestionGroupRepository questionGroupRepository) {
        this.questionsDomainRepository = questionsDomainRepository;
        this.treeRepository = treeRepository;
        this.questionGroupRepository = questionGroupRepository;
    }


//    public QuestionDto getQuestionFromTree(List<QuestionsDomain> radnomQuestionList) {
//
//
//
//        QuestionsDomain questionDomainParsed = questionsDomainRepository.findByNumber(radnomQuestionList.size());
//        TreeDomain treeDomainParsed = treeRepository.findByRoot(questionDomainParsed.getNumber());
//
//        QuestionDto questionDtoParsed = new QuestionDto();
//
//
//        questionDtoParsed.setQuestion(questionDomainParsed.getQuestion());
//        questionDtoParsed.setLeft(treeDomainParsed.getLeft());
//        questionDtoParsed.setRight(treeDomainParsed.getRight());
//        questionDtoParsed.setRoot(treeDomainParsed.getRoot());
//
//        return (questionDtoParsed);
//
//
//    }


    public List<QuestionsDomain> randomTreeQuestion(boolean singleOrMulti) {



        List<QuestionGroupDomain> allByQuestionHandlerIsActoveAndSingle = questionGroupRepository.findAllBySingleAndActive(true,true);
        List<QuestionGroupDomain> allByQuestionHandlerIsInactiveandMulti = questionGroupRepository.findAllBySingleAndActive(false,true);

        //takes out all active groups from GroupDomain
        if (singleOrMulti == true) {

            QuestionGroupDomain randomQuestionGroup = allByQuestionHandlerIsActoveAndSingle.get(new Random().nextInt(allByQuestionHandlerIsActoveAndSingle.size()));
            //random object
            List<QuestionsDomain> groupId = randomQuestionGroup.getGroupId();

            return groupId;

        } else {
            QuestionGroupDomain randomQuestionGroup2 = allByQuestionHandlerIsInactiveandMulti.get(new Random().nextInt(allByQuestionHandlerIsInactiveandMulti.size()));
            //random object
            List<QuestionsDomain> groupId2 = randomQuestionGroup2.getGroupId();


            return groupId2;
        }


    }
}
