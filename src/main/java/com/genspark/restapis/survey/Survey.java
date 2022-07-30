package com.genspark.restapis.survey;

import java.util.List;

public class Survey {
    private String id;
    private String title;
    private String descriptions;
    private List<Question> questionsList;

    public Survey(String id, String title, String descriptions, List<Question> questionsList) {
        this.id = id;
        this.title = title;
        this.descriptions = descriptions;
        this.questionsList = questionsList;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public List<Question> getQuestionsList() {
        return questionsList;
    }

    @Override
    public String toString() {
        return "SurveyBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", questionsList=" + questionsList +
                '}';
    }
}
