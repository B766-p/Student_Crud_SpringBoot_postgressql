package com.SpringLayer.CrudOperationonStudent.ReposiretoryLayer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringLayer.CrudOperationonStudent.Entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

}
