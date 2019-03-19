package com.decisionTree.wiki.dao;


import com.decisionTree.wiki.domain.QuestionGroupDomain;
import com.decisionTree.wiki.domain.QuestionsDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionGroupRepository extends JpaRepository<QuestionGroupDomain, Integer> {


    List<QuestionGroupDomain> findAllByActive (boolean active);
    List<QuestionGroupDomain> findAllBySingle (boolean single);
    Optional <QuestionGroupDomain> findByName (String name);
    List<QuestionGroupDomain> findAllBySingleAndActive (boolean active, boolean single);
    QuestionGroupDomain findByIdQuestionGroup (int id);
    QuestionGroupDomain findByTag (String tag);
    List<QuestionGroupDomain> findByTagIsContainingAndActive (String tag, boolean active);
    Optional <QuestionGroupDomain> findByIdQuestionGroupAndActive(int id, boolean active);



}


