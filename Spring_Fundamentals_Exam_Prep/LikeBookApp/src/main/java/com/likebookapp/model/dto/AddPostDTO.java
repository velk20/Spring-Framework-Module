package com.likebookapp.model.dto;

import com.likebookapp.model.enums.MoodEnum;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPostDTO {
    @NotEmpty(message = "Content can not be empty.")
    @Size(min = 2,max = 150,message = "Content length must be between 2 and 150 characters.")
    private String content;
    @NotNull(message = "You need to choose a mood.")
    private MoodEnum mood;


    public String getContent() {
        return content;
    }

    public AddPostDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public MoodEnum getMood() {
        return mood;
    }

    public AddPostDTO setMood(MoodEnum mood) {
        this.mood = mood;
        return this;
    }
}
