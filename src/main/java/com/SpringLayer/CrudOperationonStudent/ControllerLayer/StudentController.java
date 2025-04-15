package com.SpringLayer.CrudOperationonStudent.ControllerLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringLayer.CrudOperationonStudent.DtoLayer.ResponseStructure;
import com.SpringLayer.CrudOperationonStudent.Entity.Student;
import com.SpringLayer.CrudOperationonStudent.ServiceLayer.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
    private StudentService studentService;
    // Add a new student
    @PostMapping
    public ResponseEntity<ResponseStructure<Student>> addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // Get all students
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Student>>> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Student>> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    // Update student by ID
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Student>> updateStudentById(@PathVariable int id, @RequestBody Student student) {
        return studentService.updateStudentById(id, student);
    }

    // Delete student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Void>> deleteStudentById(@PathVariable int id) {
        return studentService.deleteStudentById(id);
    }

    
}
