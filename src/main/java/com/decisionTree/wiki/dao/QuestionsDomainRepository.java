package com.decisionTree.wiki.dao;

import com.decisionTree.wiki.domain.QuestionsDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsDomainRepository extends JpaRepository<QuestionsDomain, Long> {

    QuestionsDomain findByNumber (int number);

}
