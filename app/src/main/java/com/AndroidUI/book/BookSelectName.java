
package com.AndroidUI.book;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import com.example.administrator.book.R;
import com.control.BookControl;
import com.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BookSelectName extends AppCompatActivity {
    private EditText bookname;
    public static final String attr="bookname";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_select_name);
        bookname = (EditText) findViewById(R.id.editText);
    }

    public void select(View v) {

        ListView lv = (ListView) findViewById(R.id.lv);
        BookControl bookcontrol = new BookControl(BookSelectName.this);
        String book_attr = bookname.getText().toString().trim();//输入的字段
        Book book[] = bookcontrol.getAttrBook(attr,book_attr);
        //获取到集合数据
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        if (book != null)
            for (int i = 0; i < book.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("bookno", book[i].getBookno());
                item.put("bookname", book[i].getBookname());
                item.put("author", book[i].getAuthor());
                item.put("publisher", book[i].getPublisher());
                item.put("totalnum", book[i].getTotalnum());
                item.put("borrownum", book[i].getBorrownum());
                item.put("pubday", book[i].getPubday());
                data.add(item);
            }
        //创建SimpleAdapter适配器将数据绑定到item显示控件上
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.book_listview,
                new String[]{"bookno", "bookname", "author", "publisher", "totalnum", "borrownum", "pubday"},
                new int[]{R.id.tv_no, R.id.tv_name, R.id.tv_author, R.id.tv_publisher, R.id.tv_totalnum, R.id.tv_borrownum, R.id.tv_pubday});
        //实现列表的显示
        lv.setAdapter(adapter);

    }
}

