package com.control;

import android.content.Context;

import com.model.DBAdapter;
import com.model.Student;
import com.model.StudentSet;
import com.model.User;

/**
 * Created by Administrator on 2016/7/16.
 */
public class StudentControl {
    private static DBAdapter dbAdapter;
    private static StudentSet studentSet;
    Context context;

    public StudentControl(Context context) {
        this.context = context;
        dbAdapter = new DBAdapter(context);
        dbAdapter.open();

    }

    public boolean addStudent(Student s1) {
        return dbAdapter.insert(s1) > 0;
    }

    public void saveAll() {
        studentSet = StudentSet.getStudentList();
        studentSet.readFile(context);

        //把从文件中读入的学生信息存放到User中
        User[] user=new User[studentSet.size()];
        for (int i = 0; i <studentSet.size() ; i++) {
                 user[i]=new User();
                user[i].setUsername(studentSet.get(i).getUsername());
                user[i].setPassword(studentSet.get(i).getPassword());
                user[i].setAuthorization(studentSet.get(i).getAuthorization());
                user[i].setName(studentSet.get(i).getName());
                user[i].setAge(studentSet.get(i).getAge());
                user[i].setPhone(studentSet.get(i).getPhone());
        }

        //使用事务可以极大地加快插入速度，不再是一条一条插入，而是暂存在缓存区最后一起插入数据库
        dbAdapter.db.beginTransaction();//开启事务
        for (int i = 0; i < studentSet.size(); i++) {
            dbAdapter.insert(studentSet.get(i)); //插入学生
            dbAdapter.insert(user[i]);  //插入学生对应的用户
        }
        dbAdapter.db.setTransactionSuccessful();// 设置事务标志为成功，当结束事务时就会提交事务
        dbAdapter.db.endTransaction();// 结束事务

    }

    public void deleteAll() {
        dbAdapter.deleteAllData();
    }

    public boolean deleteStudentByNo(String no) {
        Student s[] = dbAdapter.getOneByNo(no);
        if (s != null) {
            dbAdapter.deleteOneDataByNo(no);

            return true;
        }
        return false;
    }

    public void updateStudent(Student student) {
        String no = student.getUsername();
        Student s[] = dbAdapter.getOneByNo(no);
        if (s != null) {
            dbAdapter.updateOneDataByNo(no, student);
            dbAdapter.close();
        }
    }

    public Student[] QueryOnByNo(String username) {
        return dbAdapter.getOneByNo(username);
    }

    public Student[] getAllStudent() {
        return dbAdapter.getAllStu();
    }
}
