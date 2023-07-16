package com.driver.service;

import com.driver.Teacher;
import com.driver.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    public String addTeacher(Teacher teacher) {
        try{
            String addedTeacher = teacherRepository.addTeacher(teacher);
            return addedTeacher;
        }catch(Exception e){
            return e.getMessage();
        }

    }

    public Teacher getTeacherByName(String name) {
        if(name == null)
            return null;
        return teacherRepository.getTeacherByName(name);
    }

    public String deleteTeacherByName(String teacher) {
        if(teacher == null)
            return null;
        return teacherRepository.deleteTeacherByName(teacher);
    }

    public void deleteAllTeachers() {
        teacherRepository.deletedAllTeachers();
    }
}
