package com.driver.repository;

import com.driver.Student;
import com.driver.Teacher;
import com.driver.customExceptions.TeacherAlreadyExists;
import com.driver.customExceptions.teacherNameNotSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TeacherRepository {
    @Autowired
    StudentRepository studentRepository;
    Map<String, Teacher> teacherDb = new HashMap<>();

    public String addTeacher(Teacher teacher) {
        if(teacher == null)
            throw new teacherNameNotSet("Invalid request");
        if(teacherDb.containsKey(teacher.getName()))
            throw new TeacherAlreadyExists("Teacher Already exists");
        teacherDb.put(teacher.getName(), teacher);
        return "New teacher added successfully";
    }

    public Teacher getTeacherByName(String name) {
        if (teacherDb.containsKey(name))
            return teacherDb.get(name);
        return null;
    }

    public void deleteTeacherByName(String teacher) {
        if(teacherDb.containsKey(teacher)) {
            teacherDb.remove(teacher);
            List<String> students = studentRepository.studentTeacherPairDb.remove(teacher);
            if(studentRepository.studentTeacherPairDb.containsKey(teacher)) {
                for (int i = 0; i < students.size(); i++) {
                    String studentName = students.get(i);
                    studentRepository.studentDb.remove(studentName);

                }
            }
        }
    }

    public void deletedAllTeachers() {
        for(String k: studentRepository.studentTeacherPairDb.keySet()) {
            teacherDb.remove(k);
            List<String> al = studentRepository.studentTeacherPairDb.remove(k);
            for(String p: al) {
                if(studentRepository.studentDb.containsKey(p)) {
                    studentRepository.studentDb.remove(p);
                }
            }
        }
        for(String teacherName : teacherDb.keySet()){
            teacherDb.remove(teacherDb.get(teacherName));
        }
    }
}
