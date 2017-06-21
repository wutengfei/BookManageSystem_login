package com.control;

import android.content.Context;

import com.model.DBAdapter;
import com.model.StudentSet;
import com.model.Students;

/**
 * Created by Administrator on 2016/7/16.
 */
public class StudentControl {
    private static DBAdapter dbAdapter;
    private static StudentSet set;
    Context context;

    public StudentControl(Context context) {
        this.context = context;
        dbAdapter = new DBAdapter(context);
        dbAdapter.open();

    }

    public boolean addStudent(Students s1) {
        return dbAdapter.insert(s1) > 0;
    }

//        public void saveAll() {
//        set= StudentSet.getStudentList();
//        set.readFile(context);
//        //使用事务可以极大地加快插入速度，不再是一条一条插入，而是暂存在缓存区最后一起插入数据库
//        dbAdapter.db.beginTransaction();//开启事务
//        for (int i = 0; i < set.size(); i++) {
//            dbAdapter.insert(set.get(i));
//        }
//        dbAdapter.db.setTransactionSuccessful();// 设置事务标志为成功，当结束事务时就会提交事务
//        dbAdapter.db.endTransaction();// 结束事务
//        dbAdapter.close();
//    }
    public void deleteAll() {
        dbAdapter.deleteAllData();
    }

    public boolean deleteStudentByNo(String no) {
        Students s[] = dbAdapter.getOneByNo(no);
        if (s != null) {
            dbAdapter.deleteOneDataByNo(no);

            return true;
        }
        return false;
    }

    public void updateStudent(Students students) {
        String no = students.getUsername();
        Students s[] = dbAdapter.getOneByNo(no);
        if (s != null) {
            dbAdapter.updateOneDataByNo(no, students);
            dbAdapter.close();
        }
    }

    public Students[] QueryOnByNo(String username) {
        return dbAdapter.getOneByNo(username);
    }

    public Students[] getAllStudent() {
        return dbAdapter.getAllStu();
    }
}
