package com.genspark.restapis.survey;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Predicate;

@Component
public class SurveyService {
    private static List<Survey> surveys = new ArrayList<>();
    private static List<Question> questions;

    static {
        Question question1 = new Question("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        Question question2 = new Question("Question2",
                "Fastest Growing Cloud Platform", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
        Question question3 = new Question("Question3",
                "Most Popular DevOps Tool", Arrays.asList(
                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

        questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3));

        Survey survey = new Survey("Surveys1", "My Favorite Survey",
                "Description of the Survey", questions);

        surveys.add(survey);

    }

    public List<Survey> retrieveAllSurveys() {
        return surveys;
    }

    public Survey retrieveSurveyById(String surveyId) {
        Predicate<? super Survey> predicate = survey -> survey.getId().equalsIgnoreCase(surveyId);

        Optional<Survey> optionalSurvey = surveys.stream().filter(predicate).findFirst();

        if(optionalSurvey.isEmpty()){
            return null;
        }

        return optionalSurvey.get();
    }

    public List<Question> retrieveAllSurveyQuestions(String surveyId) {
        Survey survey = retrieveSurveyById(surveyId);
        if(survey == null) return null;


        return survey.getQuestionsList();

    }


    public Question retrieveSpecificQuestion(String surveyId, String questionsID) {
        Survey survey = retrieveSurveyById(surveyId);
        if (survey == null) return null;

        Predicate<? super Question> predicate = questionList -> questionList.getId().equalsIgnoreCase(questionsID);
        Optional<Question> optionalQuestion = questions.stream().filter(predicate).findFirst();

        if (optionalQuestion.isEmpty()) return null;

        return optionalQuestion.get();
    }

    public String addNewQuestion(String surveyId, Question question) {
        List<Question> questionList = retrieveAllSurveyQuestions(surveyId);
        String random = generateRandomId();
        question.setId(random);
        questionList.add(question);

        return random;
    }

    private String generateRandomId() {
        SecureRandom secureRandom = new SecureRandom();
        String random = new BigInteger(32, secureRandom).toString();
        return random;
    }


    public String deleteSpecificQuestion(String surveyId, String questionsId) {
        List<Question> surveyQuestions = retrieveAllSurveyQuestions(surveyId);
        if (surveyQuestions == null) return null;
        Predicate<? super Question> predicate = questionList -> questionList.getId().equalsIgnoreCase(questionsId);
        boolean removed = surveyQuestions.removeIf(predicate);
        if(!removed){
            return null;
        }
        return questionsId;
    }


    public void modifySpecificQuestion(String surveyId, String questionId, Question question) {
        List<Question> surveyQuestions = retrieveAllSurveyQuestions(surveyId);
        surveyQuestions.removeIf(questionList -> questionList.getId().equalsIgnoreCase(questionId));
        surveyQuestions.add(question);
    }
}
