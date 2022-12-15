package com.likebookapp.init;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.enums.MoodEnum;
import com.likebookapp.repository.MoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitialSeeder implements CommandLineRunner {
    private final MoodRepository moodRepository;

    public InitialSeeder(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedMoods();
    }

    public void seedMoods() {
        if (moodRepository.count() > 0) {
            return;
        }
        Arrays.stream(MoodEnum.values())
                .forEach(m->this.moodRepository.save(new Mood(m)));
    }
}
