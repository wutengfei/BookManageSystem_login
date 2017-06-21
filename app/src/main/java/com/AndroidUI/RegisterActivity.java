package com.AndroidUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.control.UserControl;
import com.example.administrator.book.R;
import com.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private EditText et_name;
    private EditText et_age;
    private EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //  getSupportActionBar().hide();
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_name = (EditText) findViewById(R.id.editText9);
        et_age = (EditText) findViewById(R.id.editText10);
        et_phone = (EditText) findViewById(R.id.editText11);
    }

    /**
     * 注册
     *
     * @param v
     */
    public void register(View v) {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        int authorization = 0;
        int age = 0;
        try {

            age = Integer.parseInt(et_age.getText().toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = et_name.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        User user = new User(username, password, authorization, name, age, phone);

        UserControl userController = new UserControl(RegisterActivity.this);
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(et_age.getText().toString().trim()) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请填写完整", Toast.LENGTH_SHORT).show();
        } else {
            if (userController.QueryUserByUsername(username) != null) {
                Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
            } else if (userController.addUser(user)) {
                Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }
}
