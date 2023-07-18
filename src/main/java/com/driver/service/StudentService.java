package com.driver.service;

import com.driver.Student;
import com.driver.Teacher;
import com.driver.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public void addStudent(Student student) {
         studentRepository.addStudent(student);

    }

    public void addStudentTeacherPair(String student, String teacher) {
       studentRepository.addStudentTeacherPair(student, teacher);
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

    public List<String> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void addTeacher(Teacher teacher) {
            studentRepository.addTeacher(teacher);
    }

    public Teacher getTeacherByName(String name) {
        return studentRepository.getTeacherByName(name);
    }

    public void deleteTeacherByName(String teacher) {
        studentRepository.deleteTeacherByName(teacher);
    }

    public void deleteAllTeachers() {
        studentRepository.deletedAllTeachers();
    }
}
