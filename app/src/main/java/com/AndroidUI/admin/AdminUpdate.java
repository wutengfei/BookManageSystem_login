package com.AndroidUI.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.control.AdminControl;
import com.control.UserControl;
import com.example.administrator.book.R;
import com.model.Admin;
import com.model.User;

public class AdminUpdate extends AppCompatActivity {
    private TextView tv_username;
    private EditText et_pass;
    private EditText et_dept;
    private EditText et_name;
    private EditText et_age;
    private EditText et_phone;

    String getUsername;
    String getPassword;
    String getName;
    int getAge;
    String getPhone;
    String getDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update);
        tv_username = (TextView) findViewById(R.id.tv_username);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_dept = (EditText) findViewById(R.id.et_dept);
        et_name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        et_phone = (EditText) findViewById(R.id.et_phone);

        Intent intent = getIntent(); //从AdminShow获取数据
        getUsername = intent.getStringExtra("getUsername");
        getPassword = intent.getStringExtra("getPassword");
        getDepartment = intent.getStringExtra("getDepartment");
        getName = intent.getStringExtra("getName");
        getAge = intent.getIntExtra("getAge", 0);
        getPhone = intent.getStringExtra("getPhone");


        tv_username.setText(getUsername);
        et_pass.setText(getPassword);
        et_dept.setText(getDepartment);
        et_name.setText(getName);
        et_age.setText(getAge + "");
        et_phone.setText(getPhone);
    }

    public void update(View view) {

        getPassword = et_pass.getText().toString().trim();
        getDepartment = et_dept.getText().toString().trim();
        getName = et_name.getText().toString().trim();
        try {
            getAge = Integer.parseInt(et_age.getText().toString().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        getPhone = et_phone.getText().toString().trim();
        int authorization = -1;
        if (getDepartment.equals("学生管理员"))
            authorization = 1;
        else if (getDepartment.equals("图书管理员"))
            authorization = 2;

        Admin admin = new Admin(getUsername, getPassword, authorization, getName, getAge, getPhone, getDepartment);
        User user = new User(getUsername, getPassword, authorization, getName, getAge, getPhone);

        AdminControl adminControl = new AdminControl(this);
        adminControl.update(admin);

        UserControl userControl = new UserControl(this);
        userControl.updateUser(user);

        finish();
    }
}
