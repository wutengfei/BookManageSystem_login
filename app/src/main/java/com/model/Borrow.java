package com.model;

/**
 * Created by Fly Wu on 2016/7/20.
 */
public class Borrow  {
    private String username;
    private String bookno;
    private String borrowDate;
    private String phone;
    private String bookName;


    public Borrow() {
    }

    public Borrow(String username, String bookno, String borrowDate, String bookName) {
        this.username = username;
        this.bookno = bookno;
        this.borrowDate = borrowDate;
        this.bookName = bookName;
    }

    public Borrow(String username, String bookno, String bookName,String borrowDate, String phone) {
        this.username = username;
        this.bookno = bookno;
        this.bookName = bookName;
        this.borrowDate = borrowDate;
        this.phone = phone;

    }

    public Borrow(String username, String borrowDate, String bookno) {
        this.username = username;
        this.borrowDate = borrowDate;
        this.bookno = bookno;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookno() {
        return bookno;
    }

    public void setBookno(String bookno) {
        this.bookno = bookno;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
