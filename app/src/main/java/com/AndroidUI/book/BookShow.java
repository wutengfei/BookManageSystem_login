package com.AndroidUI.book;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.example.administrator.book.R;
import com.control.BookControl;
import com.model.Book;

import java.util.*;

public class BookShow extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_show);
        ListView lv = (ListView) findViewById(R.id.lv);

        //获取到集合数据
        final List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        BookControl bookcontrol = new BookControl(BookShow.this);
        Book book[] = bookcontrol.getAllBook();

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
                new int[]{R.id.tv_no, R.id.tv_name, R.id.tv_author, R.id.tv_publisher, R.id.tv_totalnum,
                        R.id.tv_borrownum, R.id.tv_pubday});
        //实现列表的显示
        if (lv != null) {
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Set<String> keySet = data.get(position).keySet();
                    Iterator<String> it = keySet.iterator();
                    while (it.hasNext()) {
                        String key = it.next();
                        if (key.equals("bookname")) {
                            Object value = data.get(position).get(key);
                            Toast.makeText(BookShow.this, value + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}
