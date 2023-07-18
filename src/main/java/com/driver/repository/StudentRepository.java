package com.driver.repository;

import com.driver.Student;
import com.driver.Teacher;
import com.driver.customExceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    Map<String, Student> studentDb = new HashMap<>();
    Map<String, Teacher> teacherDb = new HashMap<>();
    Map<String, List<String>> studentTeacherPairDb = new HashMap<>();
    public void addStudent(Student student) {
        studentDb.put(student.getName(), student);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if (studentDb.containsKey(student) && teacherDb.containsKey((teacher))) {

            if (studentTeacherPairDb.containsKey(teacher)) {
                List<String> list = studentTeacherPairDb.get(teacher);
                list.add(student);
                studentTeacherPairDb.put(teacher, list);
            } else {
                List<String> newlist = new ArrayList<>();
                newlist.add(student);
                studentTeacherPairDb.put(teacher, newlist);
            }
        }
//        if(student == null)
//            return "The student name is missing";
//        if(teacher == null)
//            return "The teacher name is missing";
//        if(!studentDb.containsKey(student))
//            throw new StudentDoesNotExist("Student does not exist");
////        if(teacherRepository.teacherDb.containsKey(teacher))
////            throw new TeacherDoesNotExist("Teacher Does Not Exist");
////        check if the teacher has zero students paired
//        if(!studentTeacherPairDb.containsKey(teacher))
////            create an entry and an empty arraylist to store the students assigned
//            studentTeacherPairDb.put(teacher, new ArrayList<>());
////        if the teacher already has a student add more students to him
//        studentTeacherPairDb.get(teacher).add(student);
//        teacherRepository.teacherDb.get(teacher).setNumberOfStudents(studentTeacherPairDb.get(teacher).size());

    }

    public Student getStudentByName(String name) {
        if(studentDb.containsKey(name))
            return studentDb.get(name);
        else return null;
    }

    public List<String> getStudentByTeacherName(String teacher) {
        if(!studentTeacherPairDb.containsKey(teacher))
            return new ArrayList<>();
        else
            return studentTeacherPairDb.get(teacher);

    }

    public List<String> getAllStudents() {
        List<String> students = new ArrayList<>();
        for(String student : studentDb.keySet()){
            students.add(studentDb.get(student).getName());
        }
        return students;
    }



    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(), teacher);
    }

    public Teacher getTeacherByName(String name) {
        if (teacherDb.containsKey(name))
            return teacherDb.get(name);
        else
            return null;
    }

    public void deleteTeacherByName(String teacher) {
        if(teacherDb.containsKey(teacher)) {
            teacherDb.remove(teacher);
            List<String> students = studentTeacherPairDb.remove(teacher);
            if(studentTeacherPairDb.containsKey(teacher)) {
                for (int i = 0; i < students.size(); i++) {
                    String studentName = students.get(i);
                    studentDb.remove(studentName);

                }
            }
        }
    }

    public void deletedAllTeachers() {
        for(String k: studentTeacherPairDb.keySet()) {
            teacherDb.remove(k);
            List<String> al = studentTeacherPairDb.remove(k);
            for(String p: al) {
                if(studentDb.containsKey(p)) {
                    studentDb.remove(p);
                }
            }
        }
    }
}
