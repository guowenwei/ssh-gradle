package com.wgw.entity.user;

public class Account {
    private int id;
    private String accountName;
    private String accountPwd;
   /* public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private Employee employee;*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountPwd='" + accountPwd + '\'' +
               /* ", employee=" + employee +*/
                '}';
    }

    /*
        构造函数
         */
    public Account(String accountName,String accountPwd){
        this.accountName = accountName;
        this.accountPwd = accountPwd;
        //this.employee = employee;
    }
    public  Account(){ }
}
