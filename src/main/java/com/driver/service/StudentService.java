package com.driver.service;

import com.driver.Student;
import com.driver.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public String addStudentTeacherPair(String student, String teacher) {
        try{
            String studentTeacherPair = studentRepository.addStudentTeacherPair(student, teacher);
            return studentTeacherPair;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    public Student getStudentByName(String name) {
        try{
            Student student = studentRepository.getStudentByName(name);
            return student;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<String> getStudentByTeacherName(String teacher) {
        if(teacher == null)
            return new ArrayList<>();
        return studentRepository.getStudentByTeacherName(teacher);
    }
}
