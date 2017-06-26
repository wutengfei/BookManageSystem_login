package com.control;

import android.content.Context;

import com.model.DBAdapter;
import com.model.Student;
import com.model.User;


/**
 * Created by dell on 2016/9/18.
 */
public class UserControl {
    private static DBAdapter dbAdapter;
    Context context;

    public UserControl(Context context) {
        this.context = context;
        dbAdapter = new DBAdapter(context);
        dbAdapter.open();
    }

    //添加用户
    public boolean addUser(User user) {
        dbAdapter.insert(user);

        return true;
    }

    //删除单个用户
    public boolean deleteByUsername(String username) {
        User user[] = dbAdapter.queryUser(username);
        if (user != null) {
            dbAdapter.deleteOneUser(username);
            return true;
        }
        return false;
    }

    //删除所有学生对应的User
    public void deleteAllUser(Student[] students) {

        dbAdapter.db.beginTransaction();//开启事务

        for (int i = 0; i < students.length; i++) {
            dbAdapter.deleteOneUser(students[i].getUsername());
        }

        dbAdapter.db.setTransactionSuccessful();// 设置事务标志为成功，当结束事务时就会提交事务
        dbAdapter.db.endTransaction();// 结束事务
    }

    //修改
    public boolean updateUser(User user) {
        String username = user.getUsername();
        //先查找到该用户
        User users[] = dbAdapter.queryUser(username);
        if (users != null) {
            //更新密码
            dbAdapter.updateUser(username, user);
            dbAdapter.close();
        }
        return true;
    }

    //查询用户
    public User[] QueryUserByUsername(String username) {
        User[] user = dbAdapter.queryUser(username);
        return user;
    }

}
