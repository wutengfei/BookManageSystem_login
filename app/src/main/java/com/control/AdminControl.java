package com.control;

import android.content.Context;

import com.model.Admin;
import com.model.DBAdapter;

/**
 * Created by dell on 2017/6/15.
 */

public class AdminControl {
    private static DBAdapter dbAdapter;
    Context context;

    public AdminControl(Context context) {
        this.context = context;
        dbAdapter = new DBAdapter(context);
        dbAdapter.open();
    }

    //添加用户
    public boolean addAdmin(Admin admin) {
        dbAdapter.insert(admin);
        return true;
    }

    //查询用户名
    public Admin[] queryByUsername(String username) {
        Admin[] admins = dbAdapter.queryAdmin(username);
        return admins;

    }

    //查询所有管理员
    public Admin[] getAllAdmin() {
        return dbAdapter.getAllAdmin();
    }
     //删除一名管理员
    public boolean deleteByUsername(String username) {
        Admin admins[]= dbAdapter.queryAdmin(username);
        if(admins!=null) {
            dbAdapter.deleteByUsername(username);
            return true;
        }
        return false;
    }
   //修改
    public void update(Admin admin)
    {
        String username=admin.getUsername();
        Admin admins[]= dbAdapter.queryAdmin(username);
        if(admins!=null) {
            dbAdapter.updateByUsername(username,admin);
            dbAdapter.close();
        }
    }
}
