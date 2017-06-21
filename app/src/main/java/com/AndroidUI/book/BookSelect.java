package com.AndroidUI.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.administrator.book.R;


public class BookSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_select);
    }

    //跳转到按书号查询
    public void bookno(View v) {
        Intent intent = new Intent(this, BookSelectNo.class);
        startActivity(intent);
    } //跳转到按出版社查询

    public void publisher(View v) {
        Intent intent = new Intent(this, BookSelectPublisher.class);
        startActivity(intent);
    }

    //跳转到按书名查询
    public void bookname(View v) {
        Intent intent = new Intent(this, BookSelectName.class);
        startActivity(intent);
    }

    //跳转到按出版日期查询

    public void pubday(View v) {
        Intent intent = new Intent(this, BookSelectPubday.class);
        startActivity(intent);
    }

    //跳转到按作者查询
    public void author(View v) {
        Intent intent = new Intent(this, BookSelectAuthor.class);
        startActivity(intent);
    }
}
