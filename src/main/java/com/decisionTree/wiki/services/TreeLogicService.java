package com.decisionTree.wiki.services;

import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import com.decisionTree.wiki.domain.TreeDomain;
import com.decisionTree.wiki.dto.QuestionDto;

public interface TreeLogicService {

    public QuestionDto getQuestionFromTree(int firstQuestion);


}
