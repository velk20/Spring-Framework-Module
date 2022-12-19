package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.enums.SongStyle;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class StyleService {
    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public boolean isDateSeeded() {
        return this.styleRepository.count() > 0;
    }

    public void seedStyles() {
        SongStyle[] values = SongStyle.values();
        this.styleRepository.saveAll(Arrays.stream(values)
                .map(v -> new Style(v))
                .collect(Collectors.toList()));

    }
}
