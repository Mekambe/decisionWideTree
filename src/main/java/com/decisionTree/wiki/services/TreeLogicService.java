package com.decisionTree.wiki.services;

import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import com.decisionTree.wiki.domain.TreeDomain;
import com.decisionTree.wiki.dto.QuestionDto;

import java.util.List;

public interface TreeLogicService {

//    public QuestionDto getQuestionFromTree(int firstQuestion);
    public List<QuestionsDomain> randomTreeQuestion (boolean singleOrMulti);


}
