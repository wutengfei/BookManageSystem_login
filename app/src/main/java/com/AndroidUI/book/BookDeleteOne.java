package com.AndroidUI.book;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.administrator.book.R;
import com.control.BookControl;

public class BookDeleteOne extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_delete_one);
        editText = (EditText) findViewById(R.id.editText7);
    }

    public void delete(View v) {
        String bookno = editText.getText().toString().trim();
        BookControl bookControl=new BookControl(BookDeleteOne.this);
        if (bookno.equals("")) {
            new AlertDialog.Builder(BookDeleteOne.this).setMessage("学号不能为空").show();
        } else {
            if (bookControl.deleteBookByNo(bookno)) {//删除学生
                editText.setText("");
                buildDialog();
            } else {
                new AlertDialog.Builder(BookDeleteOne.this).setMessage("没有此学生").show();
            }
        }
    }
    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BookDeleteOne.this);
        builder.setTitle("删除成功,是否继续删除");
        builder.setNegativeButton("返回上一页",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        finish();
                    }

                });
        builder.setPositiveButton("继续删除", null);
        builder.show();
    }
}
