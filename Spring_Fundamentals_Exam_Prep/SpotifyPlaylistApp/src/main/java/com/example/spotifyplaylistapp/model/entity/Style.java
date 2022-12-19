package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.enums.SongStyle;

import javax.persistence.*;

@Table(name = "styles")
@Entity
public class Style extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private SongStyle name;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Style() {
    }

    public Style(SongStyle name) {
        this.name = name;
    }

    public Style(SongStyle name, String description) {
        this.name = name;
        this.description = description;
    }

    public SongStyle getName() {
        return name;
    }

    public Style setName(SongStyle name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }
}
