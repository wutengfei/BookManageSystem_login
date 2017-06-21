package com.model;

/**
 * Created by dell on 2017/6/9.
 */

public class Students extends User {
    private String Major;        //专业
    private String Grade;        //年级
    private String ClassNo;        //班级编号
    private int Graduated;        //判断学生是否毕业



    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getClassNo() {
        return ClassNo;
    }

    public void setClassNo(String classNo) {
        ClassNo = classNo;
    }

    public int getGraduated() {
        return Graduated;
    }

    public void setGraduated(int graduated) {
        Graduated = graduated;
    }

    public Students() {
    }

    public Students(String username, String password, int authorization, String name,
                    int age, String phone, String major, String grade, String classNo, int graduated) {
        super(username, password, authorization, name, age, phone);
        Major = major;
        Grade = grade;
        ClassNo = classNo;
        Graduated = graduated;
    }

    public Students(int id, String username, String password, int authorization, String name, int age, String phone,
                    String major, String grade, String classNo, int graduated) {
        super(id, username, password, authorization, name, age, phone);
        Major = major;
        Grade = grade;
        ClassNo = classNo;
        Graduated = graduated;
    }
}
