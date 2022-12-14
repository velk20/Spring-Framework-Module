package bg.softuni.cache.cache.service;

import bg.softuni.cache.cache.CacheApplication;
import bg.softuni.cache.cache.model.StudentDTO;
import bg.softuni.cache.cache.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable("students")
    public List<StudentDTO> getAllStudents() {
        LOGGER.info("Getting all students.");
        return studentRepository.getAllStudents();
    }
    @Cacheable("students")
    public Optional<StudentDTO> getStudentByName(String name) {
        LOGGER.info("Getting student by name {}.",name);

        return studentRepository.getStudentByName(name);

    }

    @CacheEvict(cacheNames = "students",allEntries = true)
    public void refresh() {

    }
}
