package com.AndroidUI.student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.control.StudentControl;
import com.control.UserControl;
import com.example.administrator.book.R;
import com.model.Students;
import com.model.User;

public class StudentInsert extends AppCompatActivity implements View.OnClickListener {
    private EditText username;
    private EditText password;
    private EditText name;
    private EditText age;
    private EditText phone;
    private EditText major;
    private EditText grade;
    private EditText classNo;
    private EditText graduate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_insert);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        phone = (EditText) findViewById(R.id.phone);
        major = (EditText) findViewById(R.id.major);
        grade = (EditText) findViewById(R.id.grade);
        classNo = (EditText) findViewById(R.id.classNo);
        graduate = (EditText) findViewById(R.id.graduate);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.insert) {

            String usernameS = username.getText().toString().trim();
            String passwordS = password.getText().toString().trim();
            int authorization = 3;
            String nameS = name.getText().toString().trim();
            int ageS = 0;

            try {
                ageS = Integer.parseInt(age.getText().toString().trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }


            String phoneS = phone.getText().toString().trim();
            String majorS = major.getText().toString().trim();
            String gradeS = grade.getText().toString().trim();
            String classNoS = classNo.getText().toString().trim();

            int graduateS = 0;
            try {
                graduateS = Integer.parseInt(graduate.getText().toString().trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            Students students = new Students(usernameS, passwordS, authorization, nameS, ageS, phoneS, majorS, gradeS, classNoS, graduateS);
            StudentControl studentControl = new StudentControl(StudentInsert.this);

            User user = new User(usernameS, passwordS, authorization, nameS, ageS, phoneS);
            UserControl userControl = new UserControl(StudentInsert.this);

            if (usernameS.equals("") || passwordS.equals("") || nameS.equals("") || age.getText().toString().trim().equals("") ||
                    phoneS.equals("") || majorS.equals("") || gradeS.equals("") || classNoS.equals("") ||
                    graduate.getText().toString().trim().equals("")) {
                Toast.makeText(StudentInsert.this, "请填写完整", Toast.LENGTH_SHORT).show();
            } else {
                if (studentControl.QueryOnByNo(usernameS) != null) {
                    Toast.makeText(StudentInsert.this, "该学生已经存在", Toast.LENGTH_SHORT).show();
                } else {
                    if (studentControl.addStudent(students) && userControl.addUser(user))
                        Toast.makeText(StudentInsert.this, "插入成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }


}
