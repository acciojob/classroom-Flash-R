package com.driver.repository;

import com.driver.Student;
import com.driver.customExceptions.studentIsNull;
import com.driver.customExceptions.studentNameNotSet;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentRepository {
    Map<String, Student> studentDb = new HashMap<>();
    public String addStudent(Student student) {
        if(student == null)
            throw new studentIsNull("Please add Student Attributes");
        if(student.getName() == null)
            throw new studentNameNotSet("Student Name is not defined");
        studentDb.put(student.getName(), student);

        return "Student Added successfully";
    }
}
