package com.decisionTree.wiki.dao;


import com.decisionTree.wiki.domain.QuestionGroupDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionGroupRepository extends JpaRepository<QuestionGroupDomain, Long> {
}
