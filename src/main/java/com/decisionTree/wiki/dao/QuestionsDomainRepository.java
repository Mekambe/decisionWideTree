package com.decisionTree.wiki.dao;

import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsDomainRepository extends JpaRepository<QuestionsDomain, Integer> {

    QuestionsDomain findByNumber (int number);
    QuestionsDomain findByNumberAndQuestionHandler(int number, QuestionGroupDomain questionHandler);
    QuestionsDomain findByNumberAndQuestionHandler_IdQuestionGroup (int number, int idNumber);
    QuestionsDomain findByQuestionHandler (int number);
    QuestionsDomain findByImage (String image);
    QuestionsDomain findByIdQuestions (int id);
    QuestionsDomain findByIdQuestionsAndQuestionHandler (int number,QuestionGroupDomain questionHandler );




}
