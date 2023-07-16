package com.driver.repository;

import com.driver.Teacher;
import com.driver.customExceptions.TeacherAlreadyExists;
import com.driver.customExceptions.teacherNameNotSet;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TeacherRepository {

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

    public String deleteTeacherByName(String teacher) {
        if(teacherDb.containsKey(teacher)) {
            Teacher removedTeacher = teacherDb.remove(teacher);
            return removedTeacher.getName();
        }
        return null;
    }
}
