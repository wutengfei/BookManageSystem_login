package com.AndroidUI.borrow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.control.BookControl;
import com.control.BorrowControl;
import com.example.administrator.book.R;
import com.model.Book;
import com.model.Borrow;

public class BorrowReturn extends AppCompatActivity {

    private EditText editText2;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_return);
        editText2 = (EditText) findViewById(R.id.editText2);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }

    public void back(View v) {

        String bookno = editText2.getText().toString().trim();

        String KEY_NO = "username";
        BorrowControl borrowControl = new BorrowControl(BorrowReturn.this);
        Borrow borrow[] = borrowControl.getBorrowMessage(KEY_NO, username);

        if (borrow == null) {
            Toast.makeText(BorrowReturn.this, "还书失败，请检查学号或图书编号是否正确或者您还没有借书", Toast.LENGTH_SHORT).show();
        } else {
            //根据图书编号找到此书，用于显示图书名称
            BookControl bookControl = new BookControl(BorrowReturn.this);
            Book book[] = bookControl.QueryOnByNo(bookno);

            if (borrowControl.deleteBorrow(username, bookno)) {//还书成功
                //更新借书量
                int borrownum = Integer.parseInt(book[0].getBorrownum());
                if (borrownum >= 1) {
                    borrownum = borrownum - 1;
                    book[0].setBorrownum(String.valueOf(borrownum));
                    borrowControl.updateBorrow(book[0]);

                    //还可以再借的数量。
                    int i = 5 - borrow.length + 1;//每位同学可以借五本书，由于在归还之前就查询该学生借了几本，所以还书之后应再加1.
                    Toast.makeText(BorrowReturn.this, "您已归还编号" + book[0].getBookno() +
                            "《" + book[0].getBookname() + "》此书" + "您还可以借" + i + "本书", Toast.LENGTH_LONG).show();
                }
            } else
                Toast.makeText(BorrowReturn.this, "还书失败，请检查学号或图书编号是否正确或者您还没有借此书", Toast.LENGTH_SHORT).show();
        }
    }
}
