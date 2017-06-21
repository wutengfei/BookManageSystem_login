package com.AndroidUI.borrow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.control.BorrowControl;
import com.example.administrator.book.R;
import com.model.Borrow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BorrowOverDateSelect extends AppCompatActivity {
    //////////////////////超期查询无法测试，超过30天才能查询出来，这里用1天代替
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_over_date_select);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        ListView lv = (ListView) findViewById(R.id.lv);

        //获取到集合数据
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        BorrowControl borrowControl = new BorrowControl(BorrowOverDateSelect.this);
        Borrow borrow[] = borrowControl.getAllBorrow(username);

        ArrayList borrowList = new ArrayList();

        Date curDate = new Date(System.currentTimeMillis()); //获取当前时间
        Calendar aCalendar = Calendar.getInstance();//新建日历对象
        aCalendar.setTime(curDate);//设置当前时间为日历中的时间
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);//获取当前时间在一年中的第几天

        for (int i = 0, j = 0; borrow != null && i < borrow.length; i++) {
            String dateStr = borrow[i].getBorrowDate();
            Date date = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = formatter.parse(dateStr);//把字符串日期转换为Date类型
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar bCalendar = Calendar.getInstance();
            bCalendar.setTime(date);
            int day2 = bCalendar.get(Calendar.DAY_OF_YEAR);
            if ((day1 - day2) > 1) //实际应该为30天
                borrowList.add(j++, borrow[i]);
        }
//把链表中的数据存入Borrow数组
        Borrow borrow1[] = new Borrow[borrowList.size()];
        for (int i = 0; i < borrowList.size(); i++)
            borrow1[i] = (Borrow) borrowList.get(i);

        if (borrowList != null)
            for (int i = 0; i < borrowList.size(); i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("studentNo", borrow1[i].getUsername());
                item.put("bookno", borrow1[i].getBookno());
                item.put("bookName", borrow1[i].getBookName());
                item.put("borrowDate", borrow1[i].getBorrowDate());
                item.put("studentMobile", borrow1[i].getPhone());

                data.add(item);
            }
        //创建SimpleAdapter适配器将数据绑定到item显示控件上
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.borrow_listview4,
                new String[]{"studentNo", "bookno", "bookName", "borrowDate", "studentMobile"},
                new int[]{R.id.tv_no, R.id.tv_bookno, R.id.tv_bookName, R.id.tv_borrowDate, R.id.tv_mobile});
        //实现列表的显示
        lv.setAdapter(adapter);

    }

}
