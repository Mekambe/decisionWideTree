package com.decisionTree.wiki.dao;

import com.decisionTree.wiki.domain.UsersDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDomainRepository extends JpaRepository<UsersDomain, Integer> {
}
