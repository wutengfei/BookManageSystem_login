package com.model;


/**
 * Created by dell on 2017/6/8.
 */

public class Admin extends User {
    String Department;	//部门，区别管理员归属

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public Admin() {
    }

    public Admin(String username, String password, int authorization, String name, int age, String phone, String department) {
        super(username, password, authorization, name, age, phone);
        Department = department;
    }

    public Admin(String username, String password, int authorization, String name, int age, String phone) {
        super(username, password, authorization, name, age, phone);
    }
}
