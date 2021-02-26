package project.service;

import project.model.Student;

import java.util.List;

public interface ObjectSrzService {
    void addObj(Student obj);
    Object getObjById(Long id);
    void deleteObj(Long id);
    List<Student> getStudents();
}
