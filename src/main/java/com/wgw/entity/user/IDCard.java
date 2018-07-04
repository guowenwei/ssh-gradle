package com.wgw.entity.user;

public class IDCard {

    private Integer id;
    private Integer no;
    private Citizen citizen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public String toString() {
        return "IDCard{" +
                "id=" + id +
                ", no=" + no +
                ", citizen=" + citizen +
                '}';
    }
}
