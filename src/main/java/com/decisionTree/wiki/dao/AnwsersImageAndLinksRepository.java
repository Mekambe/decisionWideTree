package com.decisionTree.wiki.dao;

import com.decisionTree.wiki.domain.AnwsersImageAndLinks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnwsersImageAndLinksRepository extends JpaRepository <AnwsersImageAndLinks, Integer> {

    List <AnwsersImageAndLinks> findByIdImageLinks (int idImage);
    //List<AnwsersImageAndLinks> createImage (List<image>)




}
