package com.driver.service;

import com.driver.Student;
import com.driver.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student) {
        try{
            return studentRepository.addStudent(student);
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
