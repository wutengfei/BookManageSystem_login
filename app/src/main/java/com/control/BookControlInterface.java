package com.control;

import com.model.Book;
import com.model.Student;

/**
 * Created by Fly Wu on 2016/7/19.
 */
public interface BookControlInterface {
    public void addBook(Book book);
    public void saveAll();
    public void deleteAll();
    public boolean deleteBookByNo(String no);
    public boolean updataByNo(Book e);
    public Book [] QueryOnByNo(String no);
    public Book[] getAllBook();
}
