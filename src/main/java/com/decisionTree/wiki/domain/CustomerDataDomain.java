/*
 * Developed by Jan Misiorny
 * February A.D. 2019
 */

package com.decisionTree.wiki.domain;


import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@ToString
@NoArgsConstructor
public class CustomerDataDomain {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iDCustomerData;

    String date;
    String Ip;
    String did;
    String groupId;
    String choices;
    String tags;

    public int getiDCustomerData() {
        return iDCustomerData;
    }

    public void setiDCustomerData(int iDCustomerData) {
        this.iDCustomerData = iDCustomerData;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
