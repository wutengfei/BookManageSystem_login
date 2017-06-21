
package com.AndroidUI.book;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.book.R;
import com.control.BookControl;

import java.io.File;
import java.io.IOException;

public class BookInsertSome extends AppCompatActivity {
    private String fileName;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_insert_some);
        editText = (EditText) findViewById(R.id.editText);

    }

    //插入图书
    public void insert_book(View v) throws IOException {
        fileName = editText.getText().toString().trim() + ".txt";
        BookControl bookControl = new BookControl(BookInsertSome.this);
        File file = new File(Environment.getExternalStorageDirectory() + "/Download", fileName);
        if (file.exists()) {
            if (bookControl.insertFile(file)) {//读入文件
                Toast.makeText(BookInsertSome.this, "插入成功", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(BookInsertSome.this, "没有此文件", Toast.LENGTH_LONG).show();
        }
    }

}
