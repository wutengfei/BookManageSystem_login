package com.AndroidUI.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.control.AdminControl;
import com.control.UserControl;
import com.example.administrator.book.R;
import com.model.Admin;


public class AdminManage extends AppCompatActivity {
    private EditText edit;
    AdminControl adminControl;
    UserControl userControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage);
        adminControl = new AdminControl(this);
        userControl = new UserControl(this);
    }

    //添加管理员
    public void add(View v) {
        Intent intent = new Intent(this, AdminInsert.class);
        startActivity(intent);
    }

    //删除管理员
    public void delete(View v) {
        edit = new EditText(AdminManage.this);
        new AlertDialog.Builder(AdminManage.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setTitle("请输入")
                .setMessage("用户名")
                .setView(edit)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String username = edit.getText().toString().trim();
                        if (adminControl.deleteByUsername(username) && userControl.deleteByUsername(username))
                            Toast.makeText(AdminManage.this, "删除成功", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AdminManage.this, "无此人", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    //查询管理员
    public void search(View v) {
        edit = new EditText(AdminManage.this);
        new AlertDialog.Builder(AdminManage.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setTitle("请输入")
                .setMessage("用户名")
                .setView(edit)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String username = edit.getText().toString().trim();
                        Admin admins[] = adminControl.queryByUsername(username);
                        if (admins != null)
                            new AlertDialog.Builder(AdminManage.this)
                                    .setTitle("结果为")
                                    .setMessage("用户名 " + admins[0].getUsername() +
                                            "\n密码 " + admins[0].getPassword() +
                                            "\n权限 " + admins[0].getAuthorization() +
                                            "\n姓名 " + admins[0].getName()+
                                            "\n年龄 " + admins[0].getAge()+
                                            "\n电话 " + admins[0].getPhone()+
                                            "\n部门 " + admins[0].getDepartment()
                                    )
                                    .show();
                        else Toast.makeText(AdminManage.this, "没有符合的记录", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    //查看管理员
    public void show(View view) {
        startActivity(new Intent(this, AdminShow.class));
    }

}
