package com.control;

import android.content.Context;

import com.model.Book;
import com.model.Borrow;
import com.model.DBAdapter;

/**
 * Created by Fly Wu on 2016/7/20.
 */
public class BorrowControl {
    private static DBAdapter stuDB;
    Context context;

    public BorrowControl(Context context) {
        this.context = context;
        stuDB = new DBAdapter(context);
        stuDB.open();
    }

    //借书
    public boolean addBorrow(Borrow borrow) {
        stuDB.insertBorrow(borrow);

        return true;
    }

    //还书
    public boolean deleteBorrow(String username, String bookno) {

        String KEY_BOOKNO = "bookno";
        Borrow borrow[] = stuDB.getNoOrBookno(KEY_BOOKNO, bookno);//查询图书是否存在
        if (borrow != null) {
            stuDB.deleteOneDataBorrow(username, bookno);
            return true;
        } else return false;

    }

    //查看所有借阅信息
    public Borrow[] getAllBorrow(String username) {
        return stuDB.getAllBorrow(username);
    }


    //按图书编号查找借阅的学生学号和借阅日期 或 按学号查找图书编号和借阅日期
    public Borrow[] getBorrowMessage(String KeyUsername, String username) {
        return stuDB.getNoOrBookno(KeyUsername, username);
    }

    //同一本书是否被该学生借阅过
    public int isBorrowed(String studentNo, String bookno) {
        Borrow borrow[] = stuDB.isBorrowed(studentNo, bookno);
        if (borrow == null)
            return 0;
        else
            return borrow.length;
    }

    //修改图书剩余量
    public void updateBorrow(Book book) {

        String no = book.getBookno();
        stuDB.updateByBookno(no, book);
    }
}
