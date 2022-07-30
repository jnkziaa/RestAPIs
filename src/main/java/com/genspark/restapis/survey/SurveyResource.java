package com.genspark.restapis.survey;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyResource {

    SurveyService surveyService;


    public SurveyResource(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping("/surveys")
    public List<Survey> retrieveAllSurveys(){
        return surveyService.retrieveAllSurveys();
    }

    @RequestMapping("/surveys/{surveyId}")
    public Survey retrieveSurvey(@PathVariable String surveyId){
        Survey survey = surveyService.retrieveSurveyById(surveyId);
        if(survey == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return survey;
    }

    @RequestMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveQuestions(@PathVariable String surveyId){
        List<Question> questions = surveyService.retrieveAllSurveyQuestions(surveyId);
        if(questions == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return questions;
    }

    @RequestMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveSpecQuestion(@PathVariable String surveyId, @PathVariable String questionId){
        Question questions = surveyService.retrieveSpecificQuestion(surveyId, questionId);
        if(questions == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return questions;
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewQuestion(@PathVariable String surveyId, @RequestBody Question question){
        String questionId = surveyService.addNewQuestion(surveyId, question) ;
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{questionId}").buildAndExpand(questionId).toUri();
        return ResponseEntity.created(location).build();


    }


    @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSpecQuestion(@PathVariable String surveyId, @PathVariable String questionId){
        String response = surveyService.deleteSpecificQuestion(surveyId, questionId);
        if(response == null){
            return ResponseEntity.badRequest().build();
        }


        return ResponseEntity.status(200).build();
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.PUT)
    public void modifySpecQuestion(@PathVariable String surveyId, @PathVariable String questionId, @RequestBody Question question){
        surveyService.modifySpecificQuestion(surveyId, questionId, question);

    }


}
