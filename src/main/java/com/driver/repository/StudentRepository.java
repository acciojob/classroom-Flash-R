package com.driver.repository;

import com.driver.Student;
import com.driver.customExceptions.StudentDoesNotExist;
import com.driver.customExceptions.TeacherDoesNotExist;
import com.driver.customExceptions.studentIsNull;
import com.driver.customExceptions.studentNameNotSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    @Autowired
    TeacherRepository teacherRepository;
    Map<String, Student> studentDb = new HashMap<>();
    Map<String, List<Student>> studentTeacherPairDb = new HashMap<>();
    public String addStudent(Student student) {
        if(student == null)
            throw new studentIsNull("Please add Student Attributes");
        if(student.getName() == null)
            throw new studentNameNotSet("Student Name is not defined");
        studentDb.put(student.getName(), student);

        return "Student Added successfully";
    }

    public String addStudentTeacherPair(String student, String teacher) {
        if(student == null)
            return "The student name is missing";
        if(teacher == null)
            return "The teacher name is missing";
        if(!studentDb.containsKey(student))
            throw new StudentDoesNotExist("Student does not exist");
//        if(teacherRepository.teacherDb.containsKey(teacher))
//            throw new TeacherDoesNotExist("Teacher Does Not Exist");
//        check if the teacher has zero students paired
        if(!studentTeacherPairDb.containsKey(teacher))
//            create an entry and an empty arraylist to store the students assigned
            studentTeacherPairDb.put(teacher, new ArrayList<>());
//        if the teacher already has a student add more students to him
        studentTeacherPairDb.get(teacher).add(studentDb.get(student));
        teacherRepository.teacherDb.get(teacher).setNumberOfStudents(studentTeacherPairDb.get(teacher).size());

        return "New student-teacher pair added successfully";

    }

    public Student getStudentByName(String name) {
        if(studentDb.containsKey(name))
            return studentDb.get(name);
        else throw new StudentDoesNotExist("The Student Does not Exist");
    }

    public List<String> getStudentByTeacherName(String teacher) {
         List<String> studentList = new ArrayList<>();
        if(!studentTeacherPairDb.containsKey(teacher))
            return new ArrayList<>();
        List<Student> list = studentTeacherPairDb.get(teacher);
        for (Student st: list ) {
            studentList.add(st.getName());
        }
        return studentList;
    }

    public List<String> getAllStudents() {
        List<String> students = new ArrayList<>();
        for(String student : studentDb.keySet()){
            students.add(studentDb.get(student).getName());
        }
        return students;
    }
}
