package project.dao;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.model.DBControl;
import project.model.Group;
import project.repository.GroupRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class DaoGroup implements GroupDAO{

    StudentDAO studentDAO;
    DBControl db;

    @Autowired
    private final GroupRepository groupRepository;


    public Group getOne(Long id) {
        return groupRepository.getOne(id);
    }


    public List<Group> findAll() {
        return groupRepository.findAll();
    }


    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }


    public void save(Group group) {
        groupRepository.save(group);
    }


    public void deleteGroup(Long id) {
        String query1 = "select stud_id from students where group_id="+id;
        ArrayList<Long> list = new ArrayList<>();
        try{
            ResultSet resultSet = db.getConnection().createStatement().executeQuery(query1);
            while(resultSet.next()){
                list.add(resultSet.getLong(1));
            }
            for (Long l:list){
                studentDAO.deleteById(l);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            groupRepository.deleteById(id);
        }
    }
}
