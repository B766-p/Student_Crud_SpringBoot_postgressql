package com.SpringLayer.CrudOperationonStudent.daoLayer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SpringLayer.CrudOperationonStudent.Entity.Student;
import com.SpringLayer.CrudOperationonStudent.ReposiretoryLayer.StudentRepo;
@Repository
public class StudentDao {
    @Autowired(required=true)
    private StudentRepo customRepo;

    public List<Student> getAllStudents() {
        return customRepo.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return customRepo.findById(id);
    }

    public Student saveStudent(Student student) {
        return customRepo.save(student);
    }

    public Student updateStudent(Student student) {
        return customRepo.save(student);
    }

    public boolean deleteStudent(int id) {
        if(customRepo.existsById(id)) {
        	customRepo.deleteById(id);
        	return true;
        	}
        return false;
    }
}
