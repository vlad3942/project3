package project.dao;

import project.model.Student;
import project.model.StudentDTO;

import java.util.List;

public interface StudentDAO {

    void save(Student student);
    void deleteById(Long id);
    Student getOne(Long id);
    List<StudentDTO> getStudentsDTO();

}
