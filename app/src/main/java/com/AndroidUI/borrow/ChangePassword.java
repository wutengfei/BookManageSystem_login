package com.AndroidUI.borrow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.control.AdminControl;
import com.control.StudentControl;
import com.control.UserControl;
import com.example.administrator.book.R;
import com.model.Admin;
import com.model.Students;
import com.model.User;

public class ChangePassword extends AppCompatActivity {
    private EditText pass;
    private EditText password;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_change_password);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        pass = (EditText) findViewById(R.id.pass);
        password = (EditText) findViewById(R.id.password);


    }

    public void sure(View view) {
        String password1 = pass.getText().toString().trim();
        String password2 = password.getText().toString().trim();


        if (password1.equals(password2)) {
            //更新user
            UserControl userControl = new UserControl(this);
            User[] user = userControl.QueryUserByUsername(username);
            user[0].setPassword(password1);
            userControl.updateUser(user[0]);
            //更新admin中的信息
            StudentControl studentControl = new StudentControl(this);
            Students[] student = studentControl.QueryOnByNo(username);
            student[0].setPassword(password1);
            studentControl.updateStudent(student[0]);
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
        }
    }
}
