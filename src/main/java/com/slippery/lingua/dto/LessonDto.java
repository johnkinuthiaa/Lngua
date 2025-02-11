package com.slippery.lingua.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.lingua.models.Lesson;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonDto {
    private String message;
    private int statusCode;
    private Lesson lesson;
    private List<Lesson> lessons;
}
