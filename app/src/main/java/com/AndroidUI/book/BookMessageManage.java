package com.AndroidUI.book;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.example.administrator.book.R;

import com.control.BookControl;



public class BookMessageManage extends AppCompatActivity {
    BookControl bookControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_message_manage);
         bookControl=new BookControl(BookMessageManage.this);


    }
//新书来了
    public void newBook(View v) {
        Intent intent = new Intent(this, BookInsert.class);
        startActivity(intent);
    }
    //删除单本图书
    public void deleteOne(View v) {
        Intent intent = new Intent(this, BookDeleteOne.class);
        startActivity(intent);
    }
//图书信息查询
    public void select_book(View v) {
        Intent intent = new Intent(this, BookSelect.class);
        startActivity(intent);
    }
//查看所有图书信息
    public void book_showAll(View v) {
        Intent intent = new Intent(this, BookShow.class);
        startActivity(intent);
    }
    //修改图书信息
    public void update(View v) {
        Intent intent = new Intent(this, BookUpdate.class);
        startActivity(intent);
    }
//加载初始图书信息
    public void saveAll(View v) {
        bookControl.saveAll();
        Toast.makeText(BookMessageManage.this,"保存成功",Toast.LENGTH_SHORT).show();
    }
//删除所有数据
    public void deleteAll(View v) {

        // 创建退出对话框
        AlertDialog isExit = new AlertDialog.Builder(this).create();
        // 设置对话框标题
        isExit.setTitle("将会清空所有图书数据");
        // 设置对话框消息
        isExit.setMessage("确定要删除吗");
        // 添加选择按钮并注册监听
        isExit.setButton("确定", listener);
        isExit.setButton2("取消", listener);
        // 显示对话框
        isExit.show();
    }

    /**
     * 监听对话框里面的button点击事件
     */
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    bookControl.deleteAll();
                    Toast.makeText(BookMessageManage.this, "删除成功", Toast.LENGTH_SHORT).show();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };

}
