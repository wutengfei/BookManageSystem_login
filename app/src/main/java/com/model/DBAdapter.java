package com.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class DBAdapter {

    private static final String DB_NAME = "book_manage.db";
    private static final int DB_version = 3;
    //人员
    private static final String DB_TABLE_USER = "user";

    private static final String KEY_ID = "_id";
    private static final String KEY_USER_USERNAME = "username";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_AUTHORIZATION = "authorization";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_AGE = "age";
    private static final String KEY_USER_PHONE = "phone";

    //管理员
    private static final String DB_TABLE_ADMIN = "admin";

    private static final String KEY_ADMIN_DEPARTMENT = "department";  //部门：管理员类型

    //学生
    private static final String DB_TABLE_STUDENT = "student";

    private static final String KEY_MAJOR = "major";
    private static final String KEY_GRADE = "grade";
    private static final String KEY_CLASSNO = "classNo";
    private static final String KEY_GRADUATED = "graduated";

    //图书

    private static final String DB_TABLE_BOOK = "book";

    private static final String KEY_BOOKNO = "bookno";
    private static final String KEY_BOOKNAME = "bookname";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_PUBLISHER = "publisher";
    private static final String KEY_TOTALNUM = "totalnum";
    private static final String KEY_BORROWNUM = "borrownum";
    private static final String KEY_PUBDAY = "pubday";

    //借阅表
    private static final String DB_TABLE_BORROW = "borrow";

    private static final String KEY_BORROW_DATE = "borrowDate";


    public SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;

    private static class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //人员表
        private static final String DB_CREATE_USER = "create table " +
                DB_TABLE_USER + "(" +
                KEY_ID + " integer primary key autoincrement," +
                KEY_USER_USERNAME + " varchar(20)," +
                KEY_USER_PASSWORD + " varchar(20)," +
                KEY_USER_AUTHORIZATION + " integer(10)," +
                KEY_USER_NAME + " varchar(20)," +
                KEY_USER_AGE + " integer(10)," +
                KEY_USER_PHONE + " varchar(20)" +
                ")";
        //管理员表    管理员操作
        private static final String DB_CREATE_ADMIN = "create table " +
                DB_TABLE_ADMIN + "(" +
                KEY_ID + " integer primary key autoincrement," +
                KEY_USER_USERNAME + " varchar(20)," +
                KEY_USER_PASSWORD + " varchar(20)," +
                KEY_USER_AUTHORIZATION + " integer(10)," +
                KEY_USER_NAME + " varchar(20)," +
                KEY_USER_AGE + " integer(10)," +
                KEY_USER_PHONE + " varchar(20)," +
                KEY_ADMIN_DEPARTMENT + " VARCHAR(20)" +
                ")";

        //学生表   --学生管理员操作
        private static final String DB_CREATE = "create table " +
                DB_TABLE_STUDENT + "(" +
                KEY_ID + " integer primary key autoincrement," +
                KEY_USER_USERNAME + " varchar(20)," +
                KEY_USER_PASSWORD + " varchar(20)," +
                KEY_USER_AUTHORIZATION + " integer(10)," +
                KEY_USER_NAME + " varchar(20)," +
                KEY_USER_AGE + " integer(10)," +
                KEY_USER_PHONE + " varchar(20)," +
                KEY_MAJOR + " varchar(20)," +
                KEY_GRADE + " varchar(20)," +
                KEY_CLASSNO + " varchar(20)," +
                KEY_GRADUATED + " integer(10)" +
                ")";
        //图书表  --图书管理员操作
        private static final String DB_CREATE_BOOK = "create table " +
                DB_TABLE_BOOK + "(" +
                KEY_ID + " integer primary key autoincrement," +
                KEY_BOOKNO + " varchar(20)," +
                KEY_BOOKNAME + " varchar(20)," +
                KEY_AUTHOR + " varchar(20)," +
                KEY_PUBLISHER + " varchar(20)," +
                KEY_TOTALNUM + " varchar(20)," +
                KEY_BORROWNUM + " varchar(20)," +
                KEY_PUBDAY + " varchar(20)" +
                ")";
        //借阅表  --学生操作
        private static final String DB_CREATE_BORROW = "create table " +
                DB_TABLE_BORROW + "(" +
                KEY_ID + " integer primary key autoincrement, " +
                KEY_USER_USERNAME + " varchar(20)," +
                KEY_BOOKNO + " varchar(20)," +
                KEY_BOOKNAME + " varchar(20)," +
                KEY_BORROW_DATE + " varchar(20)," +
                KEY_USER_PHONE + " varchar(20)" +
                ")";

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DB_CREATE_USER);
            _db.execSQL(DB_CREATE_ADMIN);
            _db.execSQL(DB_CREATE);
            _db.execSQL(DB_CREATE_BOOK);
            _db.execSQL(DB_CREATE_BORROW);

        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_USER);
            _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_ADMIN);
            _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_STUDENT);
            _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_BOOK);
            _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_BORROW);
            onCreate(_db);
        }

    }

    public DBAdapter(Context _context) {
        context = _context;
    }

    public void open() throws SQLiteException {
        dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_version);
        try {
            db = dbOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbOpenHelper.getReadableDatabase();
        }
    }

    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    ////////////////////////人员
    //增加用户
    public long insert(User user) {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_USER_USERNAME, user.getUsername());
        newValues.put(KEY_USER_PASSWORD, user.getPassword());
        newValues.put(KEY_USER_AUTHORIZATION, user.getAuthorization());
        newValues.put(KEY_USER_NAME, user.getName());
        newValues.put(KEY_USER_AGE, user.getAge());
        newValues.put(KEY_USER_PHONE, user.getPhone());
        return db.insert(DB_TABLE_USER, null, newValues);
    }

    //删除所有用户
    public long deleteAllUser() {
        return db.delete(DB_TABLE_USER, null, null);
    }

    //删出一个用户
    public long deleteOneUser(String username) {
        return db.delete(DB_TABLE_USER, KEY_USER_USERNAME + " like ? ", new String[]{username});
    }

    //更新用户信息
    public long updateUser(String username, User user) {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_USER_USERNAME, user.getUsername());
        newValues.put(KEY_USER_PASSWORD, user.getPassword());
        newValues.put(KEY_USER_AUTHORIZATION, user.getAuthorization());
        newValues.put(KEY_USER_NAME, user.getName());
        newValues.put(KEY_USER_AGE, user.getAge());
        newValues.put(KEY_USER_PHONE, user.getPhone());
        return db.update(DB_TABLE_USER, newValues, KEY_USER_USERNAME + " like ? ", new String[]{username});

    }


    //查询一个
    public User[] queryUser(String username) {
        Cursor cursor = db.query(DB_TABLE_USER, new String[]{KEY_USER_USERNAME, KEY_USER_PASSWORD,
                        KEY_USER_AUTHORIZATION, KEY_USER_NAME, KEY_USER_AGE, KEY_USER_PHONE},
                KEY_USER_USERNAME + " like ? ", new String[]{username}, null, null, null, null);
        return ConvertToUser(cursor);
    }


    private User[] ConvertToUser(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        User[] peoples = new User[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            peoples[i] = new User();
            peoples[i].setUsername(cursor.getString(cursor.getColumnIndex(KEY_USER_USERNAME)));
            peoples[i].setPassword(cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD)));
            peoples[i].setAuthorization(cursor.getInt(cursor.getColumnIndex(KEY_USER_AUTHORIZATION)));
            peoples[i].setName(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
            peoples[i].setAge(cursor.getInt(cursor.getColumnIndex(KEY_USER_AGE)));
            peoples[i].setPhone(cursor.getString(cursor.getColumnIndex(KEY_USER_PHONE)));
            cursor.moveToNext();
        }
        return peoples;
    }

    ///////////////////管理员
    //插入
    public long insert(Admin admin) {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_USER_USERNAME, admin.getUsername());
        newValues.put(KEY_USER_PASSWORD, admin.getPassword());
        newValues.put(KEY_USER_NAME, admin.getName());
        newValues.put(KEY_USER_AGE, admin.getAge());
        newValues.put(KEY_USER_PHONE, admin.getPhone());
        newValues.put(KEY_USER_AUTHORIZATION, admin.getAuthorization());
        newValues.put(KEY_ADMIN_DEPARTMENT, admin.getDepartment());

        return db.insert(DB_TABLE_ADMIN, null, newValues);
    }

    //查询
    public Admin[] queryAdmin(String username) {
        Cursor cursor = db.query(DB_TABLE_ADMIN, new String[]{KEY_USER_USERNAME, KEY_USER_PASSWORD,
                        KEY_USER_AUTHORIZATION, KEY_USER_NAME, KEY_USER_AGE, KEY_USER_PHONE, KEY_ADMIN_DEPARTMENT},
                KEY_USER_USERNAME + " like ? ", new String[]{username}, null, null, null, null);
        return ConvertToAdmin(cursor);
    }

    private Admin[] ConvertToAdmin(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        Admin[] peoples = new Admin[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            peoples[i] = new Admin();
            peoples[i].setUsername(cursor.getString(cursor.getColumnIndex(KEY_USER_USERNAME)));
            peoples[i].setPassword(cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD)));
            peoples[i].setAuthorization(cursor.getInt(cursor.getColumnIndex(KEY_USER_AUTHORIZATION)));
            peoples[i].setName(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
            peoples[i].setAge(cursor.getInt(cursor.getColumnIndex(KEY_USER_AGE)));
            peoples[i].setPhone(cursor.getString(cursor.getColumnIndex(KEY_USER_PHONE)));
            peoples[i].setDepartment(cursor.getString(cursor.getColumnIndex(KEY_ADMIN_DEPARTMENT)));
            cursor.moveToNext();
        }
        return peoples;
    }

    //查询所有管理员
    public Admin[] getAllAdmin() {
        Cursor cursor = db.query(DB_TABLE_ADMIN, new String[]{KEY_USER_USERNAME, KEY_USER_PASSWORD, KEY_USER_AUTHORIZATION,
                        KEY_USER_NAME, KEY_USER_AGE, KEY_USER_PHONE, KEY_ADMIN_DEPARTMENT},
                null, null, null, null, KEY_USER_USERNAME + " asc");
        return ConvertToAdmin(cursor);
    }

    //删除管理员
    public long deleteByUsername(String username) {
        return db.delete(DB_TABLE_ADMIN, KEY_USER_USERNAME + " like ? ", new String[]{username});
    }

    //修改

    public long updateByUsername(String username, Admin admin) {
        ContentValues updateValues = new ContentValues();
        updateValues.put(KEY_USER_PASSWORD, admin.getPassword());
        updateValues.put(KEY_USER_AUTHORIZATION, admin.getAuthorization());
        updateValues.put(KEY_USER_NAME, admin.getName());
        updateValues.put(KEY_USER_AGE, admin.getAge());
        updateValues.put(KEY_USER_PHONE, admin.getPhone());
        updateValues.put(KEY_ADMIN_DEPARTMENT, admin.getDepartment());

        return db.update(DB_TABLE_ADMIN, updateValues, KEY_USER_USERNAME + " like ? ", new String[]{username});

    }

    /////////////////////////////////////学生
    public long insert(Student student) {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_USER_USERNAME, student.getUsername());
        newValues.put(KEY_USER_PASSWORD, student.getPassword());
        newValues.put(KEY_USER_NAME, student.getName());
        newValues.put(KEY_USER_AGE, student.getAge());
        newValues.put(KEY_USER_PHONE, student.getPhone());
        newValues.put(KEY_USER_AUTHORIZATION, student.getAuthorization());
        newValues.put(KEY_MAJOR, student.getMajor());
        newValues.put(KEY_GRADE, student.getGrade());
        newValues.put(KEY_CLASSNO, student.getClassNo());
        newValues.put(KEY_GRADUATED, student.getGraduated());
        return db.insert(DB_TABLE_STUDENT, null, newValues);
    }

    public long deleteAllData() {
        return db.delete(DB_TABLE_STUDENT, null, null);
    }

    public long deleteOneDataByNo(String no) {
        return db.delete(DB_TABLE_STUDENT, KEY_USER_USERNAME + " like ? ", new String[]{no});
    }

    public long updateOneDataByNo(String username, Student student) {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_USER_USERNAME, student.getUsername());
        newValues.put(KEY_USER_PASSWORD, student.getPassword());
        newValues.put(KEY_USER_NAME, student.getName());
        newValues.put(KEY_USER_AGE, student.getAge());
        newValues.put(KEY_USER_PHONE, student.getPhone());
        newValues.put(KEY_USER_AUTHORIZATION, student.getAuthorization());
        newValues.put(KEY_MAJOR, student.getMajor());
        newValues.put(KEY_GRADE, student.getGrade());
        newValues.put(KEY_CLASSNO, student.getClassNo());
        newValues.put(KEY_GRADUATED, student.getGraduated());
        return db.update(DB_TABLE_STUDENT, newValues, KEY_USER_USERNAME + " like ? ", new String[]{username});
    }

    public Student[] getOneByNo(String username) {
        Cursor cursor = db.query(DB_TABLE_STUDENT, new String[]{KEY_USER_USERNAME, KEY_USER_PASSWORD, KEY_USER_NAME,
                        KEY_USER_AGE, KEY_USER_PHONE, KEY_USER_AUTHORIZATION, KEY_MAJOR, KEY_GRADE, KEY_CLASSNO, KEY_GRADUATED},
                KEY_USER_USERNAME + " like ? ", new String[]{username}, null, null, null, null);
        return ConvertToStudent(cursor);

    }

    public Student[] getAllStu() {
        Cursor cursor = db.query(DB_TABLE_STUDENT, new String[]{KEY_USER_USERNAME, KEY_USER_PASSWORD, KEY_USER_NAME,
                        KEY_USER_AGE, KEY_USER_PHONE, KEY_USER_AUTHORIZATION, KEY_MAJOR, KEY_GRADE, KEY_CLASSNO, KEY_GRADUATED},
                null, null, null, null, KEY_USER_USERNAME + " asc");
        return ConvertToStudent(cursor);
    }

    private Student[] ConvertToStudent(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        Student[] peoples = new Student[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            peoples[i] = new Student();
            peoples[i].setUsername(cursor.getString(cursor.getColumnIndex(KEY_USER_USERNAME)));
            peoples[i].setPassword(cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD)));
            peoples[i].setName(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
            peoples[i].setAge(cursor.getInt(cursor.getColumnIndex(KEY_USER_AGE)));
            peoples[i].setPhone(cursor.getString(cursor.getColumnIndex(KEY_USER_PHONE)));
            peoples[i].setAuthorization(cursor.getInt(cursor.getColumnIndex(KEY_USER_AUTHORIZATION)));
            peoples[i].setMajor(cursor.getString(cursor.getColumnIndex(KEY_MAJOR)));
            peoples[i].setGrade(cursor.getString(cursor.getColumnIndex(KEY_GRADE)));
            peoples[i].setClassNo(cursor.getString(cursor.getColumnIndex(KEY_CLASSNO)));
            peoples[i].setGraduated(cursor.getInt(cursor.getColumnIndex(KEY_GRADUATED)));
            cursor.moveToNext();
        }
        return peoples;
    }


    //////////////////////////////////////图书
    public void insertBook(Book book) {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_BOOKNO, book.getBookno());
        newValues.put(KEY_BOOKNAME, book.getBookname());
        newValues.put(KEY_AUTHOR, book.getAuthor());
        newValues.put(KEY_PUBLISHER, book.getPublisher());
        newValues.put(KEY_TOTALNUM, book.getTotalnum());
        newValues.put(KEY_BORROWNUM, book.getBorrownum());
        newValues.put(KEY_PUBDAY, book.getPubday());
        db.insert(DB_TABLE_BOOK, null, newValues);
    }

    public long deleteAllDataBook() {
        return (db.delete(DB_TABLE_BOOK, null, null));
    }

    public long deleteBookDatabyNo(String no) {
        return (db.delete(DB_TABLE_BOOK, KEY_BOOKNO + " like ? ", new String[]{no}));
    }

    //更新图书信息
    public long updateByBookno(String no, Book book) {
        ContentValues updateValues = new ContentValues();
        updateValues.put(KEY_BOOKNO, book.getBookno());
        updateValues.put(KEY_BOOKNAME, book.getBookname());
        updateValues.put(KEY_AUTHOR, book.getAuthor());
        updateValues.put(KEY_PUBLISHER, book.getPublisher());
        updateValues.put(KEY_TOTALNUM, book.getTotalnum());
        updateValues.put(KEY_BORROWNUM, book.getBorrownum());
        updateValues.put(KEY_PUBDAY, book.getPubday());

        return db.update(DB_TABLE_BOOK, updateValues, KEY_BOOKNO + " like ? ", new String[]{no});

    }

    //查找书号
    public Book[] getOneByNoBook(String no) {
        Cursor cursor = db.query(DB_TABLE_BOOK, new String[]{KEY_BOOKNO, KEY_BOOKNAME,
                        KEY_AUTHOR, KEY_PUBLISHER, KEY_TOTALNUM, KEY_BORROWNUM, KEY_PUBDAY},
                KEY_BOOKNO + " like ? ", new String[]{no}, null, null, null, null);
        return ConvertToBook(cursor);
    }

    //查找书名
    public Book[] getOneByBookName(String no) {
        Cursor cursor = db.query(DB_TABLE_BOOK, new String[]{KEY_BOOKNO, KEY_BOOKNAME,
                        KEY_AUTHOR, KEY_PUBLISHER, KEY_TOTALNUM, KEY_BORROWNUM, KEY_PUBDAY},
                KEY_BOOKNAME + " like ? ", new String[]{"%" + no + "%"}, null, null, null, null);
        return ConvertToBook(cursor);
    }

    //查询书名，作者，日期等
    public Book[] getAttrBook(String attr, String book_attr) {//参数分别是bookinfo表中的字段名，用户输入的book的属性
        Cursor cursor = db.query(DB_TABLE_BOOK, new String[]{KEY_BOOKNO, KEY_BOOKNAME,
                        KEY_AUTHOR, KEY_PUBLISHER, KEY_TOTALNUM, KEY_BORROWNUM, KEY_PUBDAY},
                attr + " like ? ", new String[]{"%" + book_attr + "%"}, null, null, null, null);
        return ConvertToBook(cursor);
    }

    public Book[] getAllBook() {
        Cursor cursor = db.query(DB_TABLE_BOOK, new String[]{KEY_BOOKNO, KEY_BOOKNAME,
                KEY_AUTHOR, KEY_PUBLISHER, KEY_TOTALNUM, KEY_BORROWNUM, KEY_PUBDAY}, null, null, null, null, KEY_BOOKNO + " asc");
        return ConvertToBook(cursor);
    }

    private Book[] ConvertToBook(Cursor cursor) {
        int resultCouunts = cursor.getCount();
        if (resultCouunts == 0 || !cursor.moveToFirst()) return null;
        Book[] books = new Book[resultCouunts];
        for (int i = 0; i < resultCouunts; i++) {
            books[i] = new Book();
            books[i].setBookno(cursor.getString(cursor.getColumnIndex(KEY_BOOKNO)));
            books[i].setBookname(cursor.getString(cursor.getColumnIndex(KEY_BOOKNAME)));
            books[i].setAuthor(cursor.getString(cursor.getColumnIndex(KEY_AUTHOR)));
            books[i].setPublisher(cursor.getString(cursor.getColumnIndex(KEY_PUBLISHER)));
            books[i].setTotalnum(cursor.getString(cursor.getColumnIndex(KEY_TOTALNUM)));
            books[i].setBorrownum(cursor.getString(cursor.getColumnIndex(KEY_BORROWNUM)));
            books[i].setPubday(cursor.getString(cursor.getColumnIndex(KEY_PUBDAY)));
            cursor.moveToNext();
        }
        return books;
    }

    ///////////////////////////////////借阅

    //借书
    public long insertBorrow(Borrow borrow) {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_USER_USERNAME, borrow.getUsername());
        newValues.put(KEY_BOOKNO, borrow.getBookno());
        newValues.put(KEY_BOOKNAME, borrow.getBookName());
        newValues.put(KEY_BORROW_DATE, borrow.getBorrowDate());
        newValues.put(KEY_USER_PHONE, borrow.getPhone());

        return db.insert(DB_TABLE_BORROW, null, newValues);
    }

    //还书
    public long deleteOneDataBorrow(String username, String bookno) {
        return (db.delete(DB_TABLE_BORROW, KEY_USER_USERNAME + " like ? " + " and " + KEY_BOOKNO
                + " like ? ", new String[]{username, bookno}));
    }

    //查找学号或图书编号
    public Borrow[] getNoOrBookno(String KEY_NO_OR_BOOKNO, String no) {
        Cursor cursor = db.query(DB_TABLE_BORROW, new String[]{KEY_USER_USERNAME, KEY_BOOKNO, KEY_BOOKNAME,
                        KEY_BORROW_DATE, KEY_USER_PHONE},
                KEY_NO_OR_BOOKNO + " like ? ", new String[]{no}, null, null, null, null);
        return ConvertToBorrow(cursor);
    }

    //查找学号或图书编号
    public Borrow[] isBorrowed(String no, String bookno) {
        Cursor cursor = db.query(DB_TABLE_BORROW, new String[]{KEY_USER_USERNAME, KEY_BOOKNO, KEY_BOOKNAME,
                KEY_BORROW_DATE, KEY_USER_PHONE}, KEY_USER_USERNAME + " like ? " + " and " +
                KEY_BOOKNO + " like ? ", new String[]{no, bookno}, null, null, null, null);
        return ConvertToBorrow(cursor);
    }

    //把cursor中的内容封装成Borrow的数组
    private Borrow[] ConvertToBorrow(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) return null;
        Borrow[] borrows = new Borrow[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            borrows[i] = new Borrow();
            borrows[i].setUsername(cursor.getString(cursor.getColumnIndex(KEY_USER_USERNAME)));
            borrows[i].setBookno(cursor.getString(cursor.getColumnIndex(KEY_BOOKNO)));
            borrows[i].setBookName(cursor.getString(cursor.getColumnIndex(KEY_BOOKNAME)));
            borrows[i].setBorrowDate(cursor.getString(cursor.getColumnIndex(KEY_BORROW_DATE)));
            borrows[i].setPhone(cursor.getString(cursor.getColumnIndex(KEY_USER_PHONE)));
            cursor.moveToNext();
        }
        return borrows;
    }

    // 查询所有借阅信息
    public Borrow[] getAllBorrow(String username) {
        Cursor cursor = db.query(DB_TABLE_BORROW, new String[]{KEY_USER_USERNAME, KEY_BOOKNO, KEY_BOOKNAME,
                        KEY_BORROW_DATE, KEY_USER_PHONE},
                KEY_USER_USERNAME + " like ? ", new String[]{username}, null, null, null, null);
        return ConvertToBorrow(cursor);
    }

}