package com.AndroidUI.borrow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.book.R;

public class BorrowManage extends AppCompatActivity {
    private String username;
    private static final int item1 = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_manage);
        Intent intent_username = getIntent();
        username = intent_username.getStringExtra("username");
    }

    //借书
    public void borrow(View v) {
        Intent intent = new Intent(this, BorrowBook.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    //还书
    public void back(View v) {
        Intent intent = new Intent(this, BorrowReturn.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    //借阅信息查询
    public void borrowMessageSelect(View v) {
        Intent intent = new Intent(this, BorrowShow.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    //超期借书查询
    public void overtimeQuery(View v) {
        Intent intent = new Intent(this, BorrowOverDateSelect.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    //菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, item1, 0, "修改密码");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case item1:
                Intent intent = new Intent(this, ChangePassword.class);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
        }
        return true;
    }

}
