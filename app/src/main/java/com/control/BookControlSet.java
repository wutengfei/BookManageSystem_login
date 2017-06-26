package com.control;

import android.content.Context;

import com.model.Book;
import com.model.BookSet;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/18.
 */
public class BookControlSet  implements BookControlInterface{
    private static BookSet set;
    Context context;

    public BookControlSet(Context context) {
        this.context = context;
        set = BookSet.getBookList();
    }

    public void addBook(Book s1) {
        set.ListInsert_S(s1);
    }

    public void saveAll() {
        set.readFile(context);
    }

    public void deleteAll() {
        set.clear();
    }

    public boolean deleteBookByNo(String no) {
        ArrayList<Book> s1 = new ArrayList<Book>();
        s1 = set.stu_search_char(no);
        if (s1 != null) {
            set.ListDelete_S(no);
            return true;
        }
        return false;

    }

    public boolean updataByNo(Book e) {
        String no = e.getBookno();
        ArrayList<Book> s1 = new ArrayList<Book>();
        s1 = set.stu_search_char(no);
        if (s1 != null) {
            set.ListDelete_S(e.getBookno());
            set.ListInsert_S(e);
        }
        return  true;
    }

    public Book[] QueryOnByNo(String no) {
        if (set.stu_search_char(no) == null) return null;
        int Size = set.stu_search_char(no).size();
        ArrayList<Book> s1 = new ArrayList<Book>();
        s1 = set.stu_search_char(no);
        Book s[] = new Book[Size];
        for (int i = 0; i < Size; i++) {
            s[i] = new Book();
            s[i] = s1.get(i);
        }
        return s;
    }

    public Book[] getAllBook() {
        int Size = set.ListLength_S();
        Book s[] = new Book[Size];
        for (int i = 0; i < Size; i++) {
            s[i] = new Book();
            s[i] = set.get(i);
        }
        return s;
    }
}
