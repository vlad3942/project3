package project.dao;

import project.model.Group;

import java.util.List;

public interface GroupDAO {

    List<Group> findAll();
    void deleteById(Long id);
    Group getOne(Long id);
    void save(Group group);
    void deleteGroup(Long id);
}
