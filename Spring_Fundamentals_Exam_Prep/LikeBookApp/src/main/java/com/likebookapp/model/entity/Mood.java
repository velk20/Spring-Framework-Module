package com.likebookapp.model.entity;

import com.likebookapp.model.enums.MoodEnum;

import javax.persistence.*;

@Table(name = "moods")
@Entity
public class Mood extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private MoodEnum name;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Mood() {
    }

    public Mood(MoodEnum name) {
        this.name = name;
    }

    public MoodEnum getName() {
        return name;
    }

    public Mood setName(MoodEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Mood setDescription(String description) {
        this.description = description;
        return this;
    }
}
