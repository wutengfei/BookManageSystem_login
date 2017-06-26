package com.AndroidUI.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.control.StudentControl;
import com.control.StudentControlSet;
import com.control.UserControl;
import com.example.administrator.book.R;
import com.model.Student;
import com.model.User;

public class StudentUpdate extends AppCompatActivity implements View.OnClickListener {
    private TextView username;
    private EditText password;
    private EditText name;
    private EditText age;
    private EditText phone;
    private EditText major;
    private EditText grade;
    private EditText class_no;
    private EditText graduate;

    String getUsername;
    String getPassword;
    String getName;
    int getAge;
    String getPhone;
    String getMajor;
    String getGrade;
    String getClassNo;
    int getGraduated;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update);
        username = (TextView) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        phone = (EditText) findViewById(R.id.phone);
        major = (EditText) findViewById(R.id.major);
        grade = (EditText) findViewById(R.id.grade);
        class_no = (EditText) findViewById(R.id.class_no);
        graduate = (EditText) findViewById(R.id.graduate);

        Intent intent = getIntent(); //从StudentShow获取数据
        getUsername = intent.getStringExtra("getUsername");
        getPassword = intent.getStringExtra("getPassword");
        getName = intent.getStringExtra("getName");
        getAge = intent.getIntExtra("getAge", -1);
        getPhone = intent.getStringExtra("getPhone");
        getMajor = intent.getStringExtra("getMajor");
        getGrade = intent.getStringExtra("getGrade");
        getClassNo = intent.getStringExtra("getClassNo");
        getGraduated = intent.getIntExtra("getGraduated",-1);


        username.setText(getUsername);
        password.setText(getPassword);
        name.setText(getName);
        age.setText(getAge+"");
        phone.setText(getPhone);
        major.setText(getMajor);
        grade.setText(getGrade);
        class_no.setText(getClassNo);
        graduate.setText(getGraduated+"");
    }

    public void onClick(View v) {


        getPassword = password.getText().toString().trim();
        getName = name.getText().toString().trim();
        try {
            getAge = Integer.parseInt(age.getText().toString().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        getPhone = phone.getText().toString().trim();
        getMajor = major.getText().toString().trim();
        getGrade = grade.getText().toString().trim();
        getClassNo = class_no.getText().toString().trim();
        try {
            getGraduated = Integer.parseInt(graduate.getText().toString().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int authorization = 3;

        Student student = new Student(getUsername, getPassword, authorization, getName, getAge, getPhone,
                getMajor,getGrade,getClassNo,getGraduated);
        User user = new User(getUsername, getPassword, authorization, getName, getAge, getPhone);

        StudentControl studentControl = new StudentControl(this);
        UserControl userControl = new UserControl(this);

        studentControl.updateStudent(student);
        userControl.updateUser(user);

//        StudentControlSet studentControlSet=new StudentControlSet(this);
//        studentControlSet.updataByNo(student);

        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
