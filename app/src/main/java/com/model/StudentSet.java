package com.model;

/**
 * Created by lsy on 2016/7/14.
 */

import android.content.Context;

import com.example.administrator.book.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StudentSet extends ArrayList<Student> {
    private static StudentSet studentList = null;//定义存储唯一学生集合类的引用变量

    private StudentSet() {//封装构造函数
    }
//静态成员方法，单例模式，

    public static StudentSet getStudentList() {//用静态函数生成集合对象
        if (studentList == null) {
            synchronized (StudentSet.class) {
                if (studentList == null)
                    studentList = new StudentSet();
            }
        }
        return studentList;
    }

    //从文件读取学生信息，并将读取的信息转换成学生类的对象，存储到容器arraylist中
    public static boolean readFile(Context io) {
        try {
            studentList.clear();
            InputStream in = io.getResources().openRawResource(R.raw.student1);

            //把字节流转换成字符流并设置编码为国标码
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "gb2312"));
            String temp;
            while ((temp = br.readLine()) != null) {
                //读取txt文件里的内容
                String[] s = temp.split(" ");
                String username = s[0];
                String password = s[0];
                int authorization = 3;
                String name = s[1];
                int age = 20;
                String phone = s[4];
                String major = s[2];
                String grade = "大二";
                String classNo = s[3];
                int graduated = 0;

                Student student = new Student(username, password, authorization, name, age, phone, major, grade, classNo, graduated);
                studentList.add(student);

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("读取成功！");
        return true;
    }


    //从文件读取学生信息，并将读取的信息转换成学术类的对象，存储到容器arraylist中
    public boolean writeFile() {
        Student s;
        String str;
        boolean b = false;
        File file1 = new File(".res/stu1.txt");
        try {
            FileInputStream fis = new FileInputStream(file1);

            //把字节流转换成字符流
            // BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            //  BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file1),"gb2312"));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file1)), true);
            for (int i = 0; i < this.size(); i++) {
                s = (Student) this.get(i);
                str = s.getUsername() + " " + s.getName() + " " + s.getMajor() + " " + s.getClassNo() + " " + s.getPhone();
                pw.println(str);
            }
            pw.close();
            b = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("保存成功！");
        return b;
    }


    public void ListInsert_S(Student e)   //向学生表中插入对象
    {
        this.add(e);
    }

    public void ListDelete_S(String info)  //删除学生表元素
    {
        Student s1;
        boolean b = false;
        for (int i = 0; i < this.size(); i++) {
            s1 = (Student) this.get(i);
            if ((s1.getMajor().equals(info)) || (s1.getPhone().equals(info)) ||
                    (s1.getName().equals(info)) || (s1.getUsername().equals(info))) {
                this.remove(i);
            }

        }
    }

    public int ListLength_S()   //获取容器大小
    {
        return this.size();
    }

    public ArrayList<Student> stu_search_char(String info) //查询学生信息
    {
        Student s1;
        ArrayList<Student> s = new ArrayList<Student>();
        int i = 0;
        for (int j = 0; j < this.size(); j++) {
            s1 = (Student) this.get(j);
            if ((s1.getMajor().equals(info)) || (s1.getPhone().equals(info)) ||
                    (s1.getName().equals(info)) || (s1.getUsername().equals(info))) {
                s.add(s1);
                i++;
            }
        }

        if (i != 0) {
            System.out.println("共找到" + i + "个符合条件的人员");
            return s;
        } else {
            System.out.println("没有找到符合条件的学生!");
            return null;
        }
    }

    public void outputAll() {
        Student s;
        for (int i = 0; i < this.size(); i++) {
            s = (Student) this.get(i);
            System.out.println("姓名：" + s.getName() + "   学号：" + s.getUsername());
        }
    }

}
