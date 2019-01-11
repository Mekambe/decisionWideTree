package com.decisionTree.wiki.domain;


import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
public class QuestionGroupDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuestionGroup;


    @OneToMany (mappedBy = "questionHandler")
    private List<QuestionsDomain>groupId = new ArrayList();

    private boolean active;



    public int getIdQuestionGroup() {
        return idQuestionGroup;
    }

    public void setIdQuestionGroup(int idQuestionGroup) {
        this.idQuestionGroup = idQuestionGroup;
    }

    public List<QuestionsDomain> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<QuestionsDomain> groupId) {
        this.groupId = groupId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
