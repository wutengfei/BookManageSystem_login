package com.control;

import com.model.DBAdapter;
import com.model.Student;
import com.model.StudentSet;

/**
 * Created by Administrator on 2016/7/17.
 */
public interface StudentControlInterface {
    public void addStudent(Student s1);
    public void saveAll();
    public void deleteAll();
    public boolean deleteStudentByNo(String no);
    public void updataByNo(Student e);
    public Student [] QueryOnByNo(String no);
    public Student[] getAllStudent();
    }

