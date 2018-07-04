package com.wgw.entity.student;

public class StudentCourse {

    private Integer sId;
    private Integer cId;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "sId=" + sId +
                ", cId=" + cId +
                '}';
    }
}
