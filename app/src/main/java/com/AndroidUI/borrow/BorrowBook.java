package com.AndroidUI.borrow;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.control.BookControl;
import com.control.BorrowControl;
import com.control.StudentControl;
import com.example.administrator.book.R;
import com.model.Book;
import com.model.Borrow;
import com.model.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BorrowBook extends AppCompatActivity {

    private EditText editText2;
    private String username;
    private ListView listView;
    BookControl bookControl;
    BorrowControl borrowControl;
    StudentControl studentControl;
    Book book[];
    Borrow borrows[];
    Student student[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_book);
        editText2 = (EditText) findViewById(R.id.editText2);
        listView = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        bookControl = new BookControl(BorrowBook.this);
        borrowControl = new BorrowControl(BorrowBook.this);
        studentControl = new StudentControl(BorrowBook.this);
    }


    public void search(View view) {
        String bookName = editText2.getText().toString().trim();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        final String borrowDate = formatter.format(curDate);

        borrows = borrowControl.getBorrowMessage("username", username);
        book = bookControl.QueryByBookName(bookName);
        student = studentControl.QueryOnByNo(username);

        if (book == null) {//检测图书是否存在
            Toast.makeText(BorrowBook.this, "没有此图书", Toast.LENGTH_SHORT).show();
        } else {
            //获取到集合数据
            final List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

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
            final SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.book_listview,
                    new String[]{"bookno", "bookname", "author", "publisher", "totalnum", "borrownum", "pubday"},
                    new int[]{R.id.tv_no, R.id.tv_name, R.id.tv_author, R.id.tv_publisher, R.id.tv_totalnum,
                            R.id.tv_borrownum, R.id.tv_pubday});
            //实现列表的显示
            listView.setAdapter(adapter);
            //listView长按事件
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                    //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(BorrowBook.this);
                    builder.setTitle("是否借阅此图书?");
                    builder.setMessage(book[position].getBookname());//显示书名
                    //添加AlertDialog.Builder对象的setPositiveButton()方法
                    builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            int surplus;//剩余量
                            int totalnum = Integer.parseInt(book[position].getTotalnum());
                            int borrownum = Integer.parseInt(book[position].getBorrownum());
                            surplus = totalnum - borrownum;//库存量=总库存-借出量

                            if (surplus == 0) {//存库量为零
                                Toast.makeText(BorrowBook.this, "该图书已被借完", Toast.LENGTH_LONG).show();
                            } else {
                                String phone = student[0].getPhone();
                                Borrow borrow = new Borrow(username, book[position].getBookno(),
                                        book[position].getBookname(), borrowDate, phone);

                                if (borrowControl.isBorrowed(username, book[position].getBookno()) != 0)//是否借过此书，!=0代表借过
                                    Toast.makeText(BorrowBook.this, "您已借过此图书", Toast.LENGTH_LONG).show();

                                else if (borrows != null && borrows.length >= 5) {//借书超过五本
                                    Toast.makeText(BorrowBook.this, "您已借阅了5本书，不能再借阅 ", Toast.LENGTH_LONG).show();
                                } else if (borrowControl.addBorrow(borrow)) {//借书成功

                                    borrownum = borrownum + 1;//更新借出量
                                    book[position].setBorrownum(String.valueOf(borrownum));

                                    borrowControl.updateBorrow(book[position]);
                                    search(view);  //借完一本书后更新视图(借出数量会发生变化)
                                    Toast.makeText(BorrowBook.this, "您已成功借阅编号为" + book[position].getBookno() +
                                            "《" + book[position].getBookname() + "》" + "请于一个月内归还", Toast.LENGTH_LONG).show();

                                }
                            }


                        }
                    });

                    //添加AlertDialog.Builder对象的setNegativeButton()方法
                    builder.setNegativeButton("否", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.create().show();
                    return true;
                }
            });
        }
    }


}



