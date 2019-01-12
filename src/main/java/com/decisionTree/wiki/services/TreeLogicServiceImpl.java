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


    public QuestionDto getQuestionFromTree(int firstQuestion) {

        TreeDomain treeDomainParsed = treeRepository.findByRoot(firstQuestion);
        QuestionsDomain questionDomainParsed = questionsDomainRepository.findByNumber(firstQuestion);


        QuestionDto questionDtoParsed = new QuestionDto();

        questionDtoParsed.setQuestion(questionDomainParsed.getQuestion());
        questionDtoParsed.setLeft(treeDomainParsed.getLeft());
        questionDtoParsed.setRight(treeDomainParsed.getRight());
        questionDtoParsed.setRoot(treeDomainParsed.getRoot());

        return (questionDtoParsed);


    }
}


    public QuestionsDomain randomTreeQuestion (QuestionGroupDomain idQuestionGroup ){

        Random random = new Random();
    QuestionGroupDomain questionGroupDomain = new QuestionGroupDomain();
        int idQuestionGroup1 = questionGroupDomain.getIdQuestionGroup();


        Math.random(idQuestionGroup) * 100;

        Random random = new Random();

        idQuestionGroup.getIdQuestionGroup(random.nextInt());

        QuestionsDomain randomQuestion = randomQuestionList.get(random.nextInt(randomQuestionList.size()));

        return randomQuestion;
    }


}
