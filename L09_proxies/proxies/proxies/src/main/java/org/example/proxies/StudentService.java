package org.example.proxies;

import java.util.List;

public class StudentService implements StudentServiceIfc {
    private static List<StudentDTO> allStudents = List.of(
            new StudentDTO("Angel",10,15),
            new StudentDTO("Anna",101,16),
            new StudentDTO("Gosho",12,13)
    );
    @Override
    @Cacheable("students")
    public List<StudentDTO> getAllStudents() {
        System.out.println("Complex calculation of all students ...");
        dummyWait();

        System.out.println("Students calculated");

        return allStudents;
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
