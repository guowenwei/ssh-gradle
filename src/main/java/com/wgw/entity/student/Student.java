package com.wgw.entity.student;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private String grade;
    private List<Course> courses;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany
    @JoinTable(name = "student_course", joinColumns = {@JoinColumn(name="s_id")}, inverseJoinColumns = {@JoinColumn(name = "c_id")} )
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public Student(String name, String grade){
        this.name = name;
        this.grade = grade;
    }

    public Student(){};
}
