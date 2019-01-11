package com.decisionTree.wiki.dao;

import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsDomainRepository extends JpaRepository<QuestionsDomain, Integer> {

    QuestionsDomain findByNumber (int number);
    QuestionsDomain findByNumberAndQuestionHandler(int number, QuestionGroupDomain questionHandler);

}
