package com.decisionTree.wiki.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.*;

@Entity
@ToString
@NoArgsConstructor
public class AnwsersImageAndLinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idImageLinks;

    String links;

    @OneToOne (cascade=CascadeType.ALL)
    QuestionsDomain questionsDomain;

    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdImageLinks() {
        return idImageLinks;
    }

    public void setIdImageLinks(int idImageLinks) {
        this.idImageLinks = idImageLinks;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public QuestionsDomain getQuestionsDomain() {
        return questionsDomain;
    }

    public void setQuestionsDomain(QuestionsDomain questionsDomain) {
        this.questionsDomain = questionsDomain;
    }
}
