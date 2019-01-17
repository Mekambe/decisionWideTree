package com.decisionTree.wiki.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
