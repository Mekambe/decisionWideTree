package com.decisionTree.wiki.services;

import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import com.decisionTree.wiki.domain.TreeDomain;
import com.decisionTree.wiki.dto.QuestionDto;
import com.decisionTree.wiki.dto.QuestionDtoWithTheMatcher;
import com.decisionTree.wiki.exceptions.IdNotFound;

import java.util.List;

public interface TreeLogicService {

//    public QuestionDto getQuestionFromTree(int firstQuestion);
    public List<QuestionsDomain> randomTreeQuestion (boolean singleOrMulti);
    public QuestionDto returnAllTreeQuestions (int id);
    public  QuestionDto mappingTheQuestionsForTheTreeAlgorythm (int questionId,int questionGroupId ) throws IdNotFound;
    public QuestionDtoWithTheMatcher mappingTheQuestionsForTheTreeAlgorythmWithTheMatcher (int questionId, int questionGroupId, int matcher ) throws IdNotFound;

}
