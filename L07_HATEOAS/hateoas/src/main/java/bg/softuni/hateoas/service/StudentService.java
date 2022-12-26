package bg.softuni.hateoas.service;

import bg.softuni.hateoas.model.dto.OrderDTO;
import bg.softuni.hateoas.model.dto.StudentDTO;
import bg.softuni.hateoas.model.entity.OrderEntity;
import bg.softuni.hateoas.model.entity.StudentEntity;
import bg.softuni.hateoas.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<StudentDTO> getStudentById(Long studentId) {
        return studentRepository.findById(studentId).map(this::map);
    }

    public List<OrderDTO> getStudentOrders(Long studentId) {
        return getStudentById(studentId)
                .map(StudentDTO::getOrders)
                .orElseGet(ArrayList::new);
    }
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::map).collect(Collectors.toList());
    }

    private StudentDTO map(StudentEntity studentEntity) {
        List<OrderDTO> orderDTOS = studentEntity.getOrders()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());

        return new StudentDTO()
                .setAge(studentEntity.getAge())
                .setDeleted(studentEntity.isDeleted())
                .setId(studentEntity.getId())
                .setName(studentEntity.getName())
                .setOrders(orderDTOS);
    }

    private OrderDTO map(OrderEntity entity) {
        return new OrderDTO()
                .setStudentId(entity.getStudent().getId())
                .setCourseId(entity.getCourse().getId());
    }
}
