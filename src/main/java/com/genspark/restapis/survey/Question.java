package com.genspark.restapis.survey;

import java.util.List;

public class Question {
    private String id;
    private String descriptions;
    private List<String> options;
    private String correctAnswer;

    public Question(String id, String descriptions, List<String> options, String correctAnswer) {
        this.id = id;
        this.descriptions = descriptions;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public Question(){

    }

    @Override
    public String toString() {
        return "Questions{" +
                "id='" + id + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", options=" + options +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }

}
