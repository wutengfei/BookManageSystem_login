package com.AndroidUI.admin;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.control.AdminControl;
import com.control.UserControl;
import com.example.administrator.book.R;
import com.model.Admin;
import com.model.User;


public class AdminInsert extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private EditText et_name;
    private EditText et_age;
    private EditText et_phone;
    private RadioGroup et_radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_insert);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        et_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId(); //获取变更后的选中项的ID
                radioButton = (RadioButton) findViewById(radioButtonId); //根据ID获取RadioButton的实例

            }
        });
    }

    public void add(View view) {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String name = et_name.getText().toString().trim();

        int age = 0;
        try {
            age = Integer.parseInt(et_age.getText().toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String phone = et_phone.getText().toString().trim();
        String department = radioButton.getText().toString();
        int authorization = -1;  //权限
        if (department.equals("学生管理员")) {
            authorization = 1;
        } else if (department.equals("图书管理员")) {
            authorization = 2;
        }
        Admin admin = new Admin(username, password, authorization, name, age, phone, department);
        User user = new User(username, password, authorization, name, age, phone);
        AdminControl adminControl = new AdminControl(this);
        UserControl userControl=new UserControl(this);

        if (username.equals("") || password.equals("") || name.equals("") || et_age.getText().toString().trim().equals("") ||
                phone.equals("") || department.equals("")) {
            new android.app.AlertDialog.Builder(this).setMessage("请填写完整").show();
        } else {
            if (adminControl.queryByUsername(username) != null) {//检测学生是否已存在
                Toast.makeText(this, "该管理员已存在", Toast.LENGTH_SHORT).show();
            } else {
                if (adminControl.addAdmin(admin)&&userControl.addUser(user)) {
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }

    }
}
