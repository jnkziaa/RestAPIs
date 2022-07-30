package com.genspark.restapis.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonAssertTest {

    private String str = """
             {
             "id": "Question1",
             "descriptions": "Most Popular Cloud Platform Today",
             "options": [
             "AWS",
             "Azure",
             "Google Cloud",
             "Oracle Cloud"
             ],
             "correctAnswer": "AWS"
             }
             """;

    private static String SPECIFIC_QUESTION = "/surveys/surveys1/questions/question1";
    @Autowired
    private TestRestTemplate template;

    @Test
    void retrieveSpecQuestion() throws JSONException {
        ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION, String.class);
        String expectedResponse1 = "{\"id\":\"Question1\",\"descriptions\":\"Most Popular Cloud Platform Today\",\"options\":[\"AWS\",\"Azure\",\"Google Cloud\",\"Oracle Cloud\"],\"correctAnswer\":\"AWS\"}";
        String expectedResponse = responseEntity.getBody();
        JSONAssert.assertEquals(str, expectedResponse, false);


    }
}
