package project.model;

public class StudentDTO {
    private Long stud_id;
    private String stud_name;
    private Long group_id;
    private String enroll_date;

    public StudentDTO(Long stud_id, String stud_name, String enroll_date, Long group_id) {
        this.stud_id = stud_id;
        this.stud_name=stud_name;
        this.group_id = group_id;
        this.enroll_date = enroll_date;
    }

    public Long getStud_id() {
        return stud_id;
    }

    public void setStud_id(Long stud_id) {
        this.stud_id = stud_id;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getEnroll_date() {
        return enroll_date;
    }

    public void setEnroll_date(String enroll_date) {
        this.enroll_date = enroll_date;
    }

    public String getStud_name() {
        return stud_name;
    }

    public void setStud_name(String stud_name) {
        this.stud_name = stud_name;
    }

    public static StudentDTO createNewStudentByStudent(Student student) {
        return new StudentDTO(
                student.getStud_id(),
                student.getStud_name(),
                student.getEnroll_date(),
                student.getGroup_id().getGroup_id()
        );
    }
}
