package project.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.dao.DaoGroup;
import project.dao.GroupDAO;
import project.model.Group;
import project.repository.GroupRepository;

import java.util.List;


@Controller
@AllArgsConstructor
public class GroupController {

    private final GroupDAO groupDAO;



    @GetMapping("/groups")
    public String showGroups(Model model){
        List<Group> list = groupDAO.findAll();
        if(!list.isEmpty()){
            model.addAttribute("groups",list);
        }
        return "groups";
    }

    @GetMapping("/group-delete/{id}")
    public String deleteGroup(@PathVariable("id") Long id){
        groupDAO.deleteGroup(id);
        return "redirect:/groups";
    }

    @GetMapping("/group-edit/{id}")
    public String editGroup(@PathVariable("id") Long id, Model model){
        model.addAttribute("ID",id);
        Group gr = groupDAO.getOne(id);
        model.addAttribute("group", gr);
        return "group-edit";
    }

    @PostMapping("/group-edit")
    public String updateStudent(@RequestParam(value = "faculty") String faculty,
                                @RequestParam(value = "id") Long id){
        Group group = groupDAO.getOne(id);
        group.setFaculty(faculty);
        groupDAO.save(group);
        return "redirect:/groups";
    }

    @GetMapping("/add-group")
    public String adding(){
        return "add-group";
    }

    @PostMapping("/add-group")
    public String addGroup(@RequestParam(value="faculty") String faculty){
        Group group = new Group();
        group.setFaculty(faculty);
        groupDAO.save(group);
        return "redirect:/groups";
    }
}
