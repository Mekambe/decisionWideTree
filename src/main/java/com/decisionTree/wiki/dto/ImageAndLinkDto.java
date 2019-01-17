package com.decisionTree.wiki.dto;

import javax.sql.rowset.serial.SerialBlob;

public class ImageAndLinkDto {
    String link;
    String image;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
