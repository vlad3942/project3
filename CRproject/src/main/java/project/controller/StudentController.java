package project.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.dao.DaoGroup;
import project.dao.DaoStudent;
import project.dao.GroupDAO;
import project.dao.StudentDAO;
import project.model.Group;
import project.model.Student;
import project.model.StudentDTO;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {


    //private final ObjectSrzService objectSrzService;
    private final StudentDAO studentDAO;
    private final GroupDAO groupDAO;


    @GetMapping("/add-student")
    public String adding(Model model){
        List<Group> groups = groupDAO.findAll();
        model.addAttribute("groups", groups);
        return "add-student";
    }

    @PostMapping("/add-student")
    public String addStudent(@RequestParam(value="group_id") Group group_id,
                             @RequestParam(value="stud_name") String stud_name,
                             @RequestParam(value="enroll_date") String enroll_date
    ){
        Student student = new Student();
        student.setStud_name(stud_name);
        student.setEnroll_date(enroll_date);
        student.setGroup_id(group_id);
        studentDAO.save(student);
        return "redirect:/students";
    }

    @GetMapping("/student-delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        studentDAO.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String showStudents(Model model){
        List<StudentDTO> students = studentDAO.getStudentsDTO();
        if(!students.isEmpty()){
            model.addAttribute("students", students);
        }
        return "students";
    }

    /*@GetMapping("student-srz/{id}")
    public String serializeStudent(@PathVariable("id") Long id) {
        objectSrzService.addObj(studentDAO.getOne(id));
        return "redirect:/students/srz";
    }*/



    @GetMapping("/student-edit/{id}")
    public String editStudentForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("ID",id);
        Student student = studentDAO.getOne(id);
        model.addAttribute("student", student);
        List<Group> gr = groupDAO.findAll();
        model.addAttribute("groups",gr);
        return "student-edit";
    }

    @PostMapping("/student-edit")
    public String updateStudent(@RequestParam(value = "group_id") Group group_id,
                                @RequestParam(value = "stud_name") String stud_name,
                                @RequestParam(value = "enroll_date") String enroll_date,
                                @RequestParam(value = "id") Long id){
        Student student = studentDAO.getOne(id);
        student.setStud_name(stud_name);
        student.setGroup_id(group_id);
        student.setEnroll_date(enroll_date);
        studentDAO.save(student);
        return "redirect:/students";
    }

    /*@GetMapping("/students/srz")
    public String getSrz(Model model) {
        List<StudentDTO> students = new LinkedList<>();
        objectSrzService.getStudents().forEach(e -> students.add(StudentDTO.createNewStudentByStudent(e)));
        if(!students.isEmpty()){
            model.addAttribute("students", students);
        }
        return "students";
    }*/
}
