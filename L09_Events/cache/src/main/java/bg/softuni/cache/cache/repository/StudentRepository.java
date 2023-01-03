package bg.softuni.cache.cache.repository;

import bg.softuni.cache.cache.model.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
    private static List<StudentDTO> allStudents = List.of(
            new StudentDTO("Angel",10,15),
            new StudentDTO("Anna",101,16),
            new StudentDTO("Gosho",12,13)
    );
    public List<StudentDTO> getAllStudents(){
        dummyWait();

        return allStudents;
    }

    public Optional<StudentDTO> getStudentByName(String name) {
        dummyWait();

        return allStudents
                .stream().filter(s -> s.getName().equals(name))
                .findAny();
    }

    private static void dummyWait() {
        try {
            Thread.sleep(5000);
            // Dummy fetching
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
