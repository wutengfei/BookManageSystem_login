package com.control;

import android.content.Context;

import com.model.Student;
import com.model.StudentSet;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/18.
 */
public class StudentControlSet implements StudentControlInterface {
    private static StudentSet set;
    Context context;

    public StudentControlSet(Context context) {
        this.context = context;
        set = StudentSet.getStudentList();
    }

    public void addStudent(Student s1) {
        set.ListInsert_S(s1);
    }

    public void saveAll() {
        set.readFile(context);
    }

    public void deleteAll() {
            set.clear();
    }

    public boolean deleteStudentByNo(String username) {
        ArrayList<Student> s1 = new ArrayList<Student>();
        s1 = set.stu_search_char(username);
        if (s1 != null) {
            set.ListDelete_S(username);
            return true;
        }
        return false;

    }

    public void updataByNo(Student student) {
        String no = student.getUsername();
        ArrayList<Student> s1 = new ArrayList<Student>();
        s1 = set.stu_search_char(no);
        if (s1 != null) {
            set.ListDelete_S(student.getUsername());
            set.ListInsert_S(student);
        }
    }

    public Student[] QueryOnByNo(String username) {
        if (set.stu_search_char(username) == null) return null;
        int size = set.stu_search_char(username).size();
        ArrayList<Student> s1 = new ArrayList<Student>();
        s1 = set.stu_search_char(username);
        Student s[] = new Student[size];
        for (int i = 0; i < size; i++) {
            s[i] = new Student();
            s[i] = s1.get(i);
        }
        return s;
    }

    public Student[] getAllStudent() {
        int Size = set.ListLength_S();
        Student s[] = new Student[Size];
        for (int i = 0; i < Size; i++) {
            s[i] = new Student();
            s[i] = set.get(i);
        }
        return s;
    }
}
