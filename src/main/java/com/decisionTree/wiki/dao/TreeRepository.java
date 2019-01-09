package com.decisionTree.wiki.dao;

import com.decisionTree.wiki.domain.TreeDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<TreeDomain, Long> {

    TreeDomain findByRoot (int root );



}
