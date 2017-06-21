package com.AndroidUI.student;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.control.StudentControl;
import com.control.UserControl;
import com.example.administrator.book.R;
import com.model.Students;

public class StudentManage extends AppCompatActivity implements View.OnClickListener {
    private EditText edit;
    StudentControl studentcontrol;
    UserControl userControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentcontrol = new StudentControl(StudentManage.this);
        userControl = new UserControl(this);
        setContentView(R.layout.activity_student_manage);
    }

    @Override
    public void onClick(View v) {

        //插入
        if (v.getId() == R.id.button6) {
            Intent intent = new Intent();
            intent.setClass(StudentManage.this, StudentInsert.class);
            StudentManage.this.startActivity(intent);
        }
        //删除
        if (v.getId() == R.id.button5) {
            edit = new EditText(StudentManage.this);
            new AlertDialog.Builder(StudentManage.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                    .setTitle("请输入")
                    .setMessage("学号")
                    .setView(edit)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String username = edit.getText().toString();
                            if (studentcontrol.deleteStudentByNo(username) && userControl.deleteByUsername(username))
                                Toast.makeText(StudentManage.this, "删除成功", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(StudentManage.this, "无此人", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
        //查询
        if (v.getId() == R.id.button8) {
            edit = new EditText(StudentManage.this);
            new AlertDialog.Builder(StudentManage.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                    .setTitle("请输入")
                    .setMessage("学号")
                    .setView(edit)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String username = edit.getText().toString();
                            Students student[] = studentcontrol.QueryOnByNo(username);
                            if (student != null)
                                new AlertDialog.Builder(StudentManage.this)
                                        .setTitle("结果为")
                                        .setMessage(
                                                "学号 " + student[0].getUsername() +
                                                        "\n密码 " + student[0].getPassword() +
                                                        "\n权限 " + student[0].getAuthorization() +
                                                        "\n姓名 " + student[0].getName()+
                                                        "\n年龄 " + student[0].getAge()+
                                                        "\n电话 " + student[0].getPhone()+
                                                        "\n专业 " + student[0].getMajor()+
                                                        "\n年级 " + student[0].getGrade()+
                                                        "\n班级 " + student[0].getClassNo()+
                                                        "\n是否毕业 " + student[0].getGraduated()
                                        )
                                        .show();
                            else
                                Toast.makeText(StudentManage.this, "没有符合的记录", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }

        //全部删除
        if (v.getId() == R.id.buttonDeleteAll) {
            studentcontrol.deleteAll();
            userControl.deleteAllUser();
            Toast.makeText(StudentManage.this, "删除成功", Toast.LENGTH_SHORT).show();
        }
        //输出全部信息
        if (v.getId() == R.id.button7) {
            Intent intent = new Intent();
            intent.setClass(StudentManage.this, StudentShow.class);
            StudentManage.this.startActivity(intent);
        }
    }
}
