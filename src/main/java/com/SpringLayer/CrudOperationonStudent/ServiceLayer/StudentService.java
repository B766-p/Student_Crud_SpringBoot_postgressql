package com.SpringLayer.CrudOperationonStudent.ServiceLayer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.SpringLayer.CrudOperationonStudent.DtoLayer.ResponseStructure;
import com.SpringLayer.CrudOperationonStudent.Entity.Student;
import com.SpringLayer.CrudOperationonStudent.ReposiretoryLayer.StudentRepo;
import com.SpringLayer.CrudOperationonStudent.daoLayer.StudentDao;

@Service
public class StudentService {

	@Autowired
    private StudentDao dao;  // Injecting the DAO layer
	@Autowired
    private StudentRepo rep;
    // Add new student
    public ResponseEntity<ResponseStructure<Student>> addStudent(Student student) {
        // Save student using DAO
        Student savedStudent = dao.saveStudent(student);

        // Preparing the response
        ResponseStructure<Student> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Student added successfully");
        response.setData(savedStudent);
        
        // Return response with HTTP status
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get all students
    public ResponseEntity<ResponseStructure<List<Student>>> getAllStudents() {
   
        List<Student> students = rep.findAll();  
        ResponseStructure<List<Student>> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("All students fetched successfully"); 
       
        response.setData(students);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get student by ID
    public ResponseEntity<ResponseStructure<Student>> getStudentById(int id) {
        Optional<Student> student = dao.getStudentById(id);  // Fetch student by ID from DAO
        ResponseStructure<Student> response = new ResponseStructure<>();

        if (student != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Student found");
            response.setData(student.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Student not found with ID: " + id);
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Update student by ID
    public ResponseEntity<ResponseStructure<Student>> updateStudentById(int id, Student student) {
        Optional<Student> existingStudent = dao.getStudentById(id);  // Get student from DAO

        if (existingStudent != null) {
            // Updating student details
        	Student s=existingStudent.get();
            s.setName(s.getName());
            s.setEmail(s.getEmail());
            s.setAge(s.getAge());

            // Save updated student using DAO
            Student updatedStudent = dao.updateStudent(s);

            ResponseStructure<Student> response = new ResponseStructure<>();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Student updated successfully");
            response.setData(updatedStudent);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseStructure<Student> response = new ResponseStructure<>();
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Student not found with ID: " + id);
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Delete student by ID
    public ResponseEntity<ResponseStructure<Void>> deleteStudentById(int id) {
        boolean deleted = dao.deleteStudent(id); 
        // Delete student using DAO
        ResponseStructure<Void> response = new ResponseStructure<>();

        if (deleted) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Student deleted successfully");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Student not found with ID: " + id);
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
