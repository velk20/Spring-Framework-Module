package bg.softuni.cache.cache.web;

import bg.softuni.cache.cache.model.StudentDTO;
import bg.softuni.cache.cache.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/all")
    public ResponseEntity<StudentDTO> getStudentByName(
            @RequestParam("name") String name
    ) {
        Optional<StudentDTO> studentByName = studentService.getStudentByName(name);
        return studentByName.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/evict")
    public ResponseEntity<StudentDTO> evict() {
        studentService.refresh();
        return ResponseEntity.noContent().build();
    }

}
