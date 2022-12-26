package bg.softuni.hateoas.web;

import bg.softuni.hateoas.model.dto.OrderDTO;
import bg.softuni.hateoas.model.dto.StudentDTO;
import bg.softuni.hateoas.service.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudentById() {
        List<EntityModel<StudentDTO>> entityModels = studentService.getAllStudents()
                .stream()
                .map(s -> EntityModel.of(s, getStudentLinks(s)))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(CollectionModel.of(entityModels));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentById(@PathVariable("id") Long id) {
        Optional<StudentDTO> studentById = studentService.getStudentById(id);
        if (studentById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .ok(EntityModel.of(studentById.get(),getStudentLinks(studentById.get())));

    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getStudentOrders(@PathVariable("id") Long id) {
        List<EntityModel<OrderDTO>> collect = studentService.getStudentOrders(id)
                .stream().map(o -> EntityModel.of(o, getOrderLinks(o)))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(CollectionModel.of(collect));

    }

    @PutMapping("/{id}/update")
    public ResponseEntity<EntityModel<StudentDTO>> updateStudent(@PathVariable("id") Long id,
                              StudentDTO studentDTO) {
        throw new UnsupportedOperationException("NOT importrat");
    }

    private Link[] getStudentLinks(StudentDTO studentDTO) {
        List<Link> studentLinks = new ArrayList<>();

        Link selfRel = linkTo(methodOn(StudentController.class).getStudentById(studentDTO.getId())).withSelfRel();

        studentLinks.add(selfRel);

        if (!studentDTO.isDeleted()) {
            Link orderLink = linkTo(methodOn(StudentController.class)
                    .getStudentOrders(studentDTO.getId()))
                    .withRel("orders");

            studentLinks.add(orderLink);

            Link updateLink = linkTo(methodOn(StudentController.class)
                    .updateStudent(studentDTO.getId(),studentDTO))
                    .withRel("update");

            studentLinks.add(updateLink);
        }

        return studentLinks.toArray(new Link[studentLinks.size()]);
    }

    private Link getOrderLinks(OrderDTO orderDTO) {
        return
                linkTo(methodOn(StudentController.class)
                        .getStudentById(orderDTO.getStudentId()))
                        .withRel("student");
    }
}
