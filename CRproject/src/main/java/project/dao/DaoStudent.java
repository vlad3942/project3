package project.dao;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.model.DBControl;
import project.model.Group;
import project.model.Student;
import project.model.StudentDTO;
import project.repository.StudentRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class DaoStudent implements StudentDAO{

    private final DBControl db;


    @Autowired
    private StudentRepository studentRepository;

    public void save(Student student) {
        /*try{
            Connection connection = db.getConnection();
            Long id = student.getStud_id();
            String enroll_date=student.getEnroll_date();
            String name=student.getStud_name();
            Long group_id= student.getGroup_id().getGroup_id();
            String query="insert into students(stud_id, enroll_date, stud_name, group_id)" +
                    " values("+id+","+"'"+enroll_date+"'"+","+"'"+name+"'"+","+group_id+")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch(SQLException e){}*/
        studentRepository.save(student);
    }

    public void deleteById(Long id){
        try{
            Connection connection = db.getConnection();
            String query="delete from students where stud_id="+id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e){}
    }

    public Student getOne(Long id){
        return studentRepository.getOne(id);
    }

    public List<StudentDTO> getStudentsDTO(){
        String query="select s.stud_id, s.stud_name,s.enroll_date, sg.group_id from students s inner join stud_groups sg on s.group_id = sg.group_id";
        List<StudentDTO> list = new ArrayList<>();
        try{
            Statement statement = db.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                list.add(new StudentDTO(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getLong(4)
                ));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
