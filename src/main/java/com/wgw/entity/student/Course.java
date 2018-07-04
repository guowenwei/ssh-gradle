package com.wgw.entity.student;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

public class Course {
    private Integer id;
    private String courseName;
    private Double creditHours;
    private List<Student> students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "courses")
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(Double creditHours) {
        this.creditHours = creditHours;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", creditHours=" + creditHours +
                '}';
    }

    public Course(String courseName, Double creditHours){
        this.courseName = courseName;
        this.creditHours = creditHours;
    }

    public Course(){};
}
