package com.AndroidUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.AndroidUI.admin.AdminManage;
import com.AndroidUI.book.BookMessageManage;
import com.AndroidUI.borrow.BorrowManage;
import com.AndroidUI.student.StudentManage;
import com.control.UserControl;
import com.example.administrator.book.R;
import com.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private boolean mIsExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

    }


    /**
     * 登录
     *
     * @param v
     */
    public void login(View v) {
        String name = et_username.getText().toString().trim();
        String pass = et_password.getText().toString().trim();
        int authorization;
        UserControl userController = new UserControl(LoginActivity.this);
        User[] user = userController.QueryUserByUsername(name);

        if (name.equals("") || pass.equals("")) {
            new android.app.AlertDialog.Builder(LoginActivity.this).setMessage("请填写完整").show();
        } else {
            if (user == null) {
                Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();

            } else if (user[0].getUsername().equals(name) && user[0].getPassword().equals(pass)) {


                authorization = user[0].getAuthorization();
                Intent intent = new Intent();
                switch (authorization) {
                    case 1:
                        intent.setClass(this, StudentManage.class);
                        break;
                    case 2:
                        intent.setClass(this, BookMessageManage.class);
                        break;
                    case 3:
                        intent.setClass(this, BorrowManage.class);
                        intent.putExtra("username", name);
                        break;
                    case 0:
                       intent.setClass(this, AdminManage.class);     break;
                }

                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();


                startActivity(intent);
            } else if (!user[0].getPassword().equals(pass)) {
                Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * 跳转到注册
     *
     * @param v
     */
    public void register(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 双击返回键退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
