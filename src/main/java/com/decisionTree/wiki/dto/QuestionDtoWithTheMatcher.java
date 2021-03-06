/*
 * Developed by Jan Misiorny
 * February A.D. 2019
 */

package com.decisionTree.wiki.dto;

public class QuestionDtoWithTheMatcher {
    int root;
    int left;
    int right;
    String question;
    int groupId;
    String link;
    int idQuestions;
    int matcher;

    public int getIdQuestions() {
        return idQuestions;
    }

    public void setIdQuestions(int idQuestions) {
        this.idQuestions = idQuestions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getMatcher() {
        return matcher;
    }

    public void setMatcher(int matcher) {
        this.matcher = matcher;
    }
}
