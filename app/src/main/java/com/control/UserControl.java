package com.control;

import android.content.Context;

import com.model.DBAdapter;
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

    //删除所有用户
    public void deleteAllUser() {
        dbAdapter.deleteAllUser();
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
