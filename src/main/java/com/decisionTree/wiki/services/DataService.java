/*
 * Developed by Jan Misiorny
 * February A.D. 2019
 */

package com.decisionTree.wiki.services;

import com.decisionTree.wiki.domain.QuestionGroupDomain;

import java.util.List;

public interface DataService {


    List<QuestionGroupDomain> findAllQuestionGroupsBasedOnTags (String tag);

}
