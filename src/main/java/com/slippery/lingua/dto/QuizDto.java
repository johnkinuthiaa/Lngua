package com.slippery.lingua.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.lingua.models.Quiz;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizDto {
    private String message;
    private Integer statusCode;
    private Quiz quiz;
    private List<Quiz> quizList;


}
