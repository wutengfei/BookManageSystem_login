package com.control;

import android.content.Context;
import com.model.DBAdapter;
import com.model.Book;
import com.model.BookSet;

import java.io.File;
import java.io.IOException;

/**
 * Created by Fly Wu on 2016/7/19.
 */
public class BookControl {
    private static DBAdapter stuDB;
    private static BookSet set;
    Context context;

    public BookControl(Context context) {
        this.context = context;
        stuDB = new DBAdapter(context);
        stuDB.open();

    }

    //添加单本书
    public boolean addBook(Book s1) {
        stuDB.insertBook(s1);
        stuDB.close();
        return true;
    }

    //保存所有初始图书信息
    public void saveAll() {
        set = BookSet.getBookList();
        set.clear();
        set.readFile(context);
        //使用事务可以极大地加快插入速度，不再是一条一条插入，而是暂存在缓存区最后一起插入数据库
        stuDB.db.beginTransaction();//开启事务
        for (int i = 0; i < set.size(); i++) {
            stuDB.insertBook(set.get(i));
        }
        stuDB.db.setTransactionSuccessful();//设置事务标志为成功，当结束事务时就会提交事务
        stuDB.db.endTransaction();//结束事务
        stuDB.close();
    }

    //读文件，批量插入
    public boolean insertFile(File file) throws IOException {

        set = BookSet.getBookList();
        set.insertFile(file);
      stuDB.db.beginTransaction();//开启事务
        for (int i = 0; i < set.size(); i++) {
            stuDB.insertBook(set.get(i));
        }
        stuDB.db.setTransactionSuccessful();//设置事务标志为成功，当结束事务时就会提交事务
        stuDB.db.endTransaction();//结束事务
        stuDB.close();
        return true;

    }

    //删除所有图书
    public void deleteAll() {
        stuDB.deleteAllDataBook();
    }

    //删除单本书
    public boolean deleteBookByNo(String no) {
        Book s[] = stuDB.getOneByNoBook(no);
        if (s != null) {
            stuDB.deleteBookDatabyNo(no);
            stuDB.close();
            return true;
        }
        return false;
    }

    //修改图书信息
    public boolean updataByNo(Book e) {
        String no = e.getBookno();
        Book s[] = stuDB.getOneByNoBook(no);
        if (s != null) {
            stuDB.updateByBookno(no, e);
            stuDB.close();
        }

        return true;
    }

    //查询书号
    public Book[] QueryOnByNo(String no) {
        Book[] books = stuDB.getOneByNoBook(no);
        return books;

    }

    //查询书名
    public Book[] QueryByBookName(String no) {
        Book[] books = stuDB.getOneByBookName(no);
        return books;

    }

    //查询（书名，作者，出版社，库存等）
    public Book[] getAttrBook(String attr, String book_attr) {//参数分别是bookinfo表中的字段名，用户输入的book的属性
        return stuDB.getAttrBook(attr, book_attr);
    }

    //查询所有图书
    public Book[] getAllBook() {
        return stuDB.getAllBook();
    }
}
