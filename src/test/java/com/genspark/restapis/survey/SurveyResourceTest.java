package com.genspark.restapis.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SurveyResourceTest {
    private static String SPECIFIC_QUESTION = "/surveys/surveys1/questions/question1";
    private String str = """
             {
             "id": "Question1",
             "descriptions": "Most Popular Cloud Platform Today",
             "options": [
              "Azure",
             "AWS",
             "Google Cloud",
             "Oracle Cloud"
             ],
             "correctAnswer": "AWS"
             }
             """;

    @Autowired
    private TestRestTemplate template;

    @Test
    void retrieveSpecQuestion() throws JSONException {
        ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION, String.class);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
        JSONAssert.assertEquals(str.trim(), responseEntity.getBody(), false);


    }
}