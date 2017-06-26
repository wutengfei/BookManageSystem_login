package com.AndroidUI.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.control.StudentControlSet;
import com.example.administrator.book.R;
import com.control.StudentControl;
import com.model.StudentSet;
import com.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentShow extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_show);
        lv = (ListView) findViewById(R.id.lv);

    }
     @Override
    public void onStart(){
          super.onStart();

         //获取到集合数据
         final List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

         StudentControl studentControl=new StudentControl (this);
         Student s[]=studentControl.getAllStudent();

//         StudentControlSet studentControlSet=new StudentControlSet(this);
//         Student s[]= studentControlSet.getAllStudent() ;

         if(s!=null)
             for (int i = 0; i < s.length; i++) {
                 HashMap<String, Object> item = new HashMap<String, Object>();
                 item.put("getUsername", s[i].getUsername());
                 item.put("getPassword",s[i].getPassword());
                 item.put("getName", s[i].getName());
                 item.put("getAge", s[i].getAge());
                 item.put("getPhone", s[i].getPhone());
                 item.put("getMajor", s[i].getMajor());
                 item.put("getGrade", s[i].getGrade());
                 item.put("getClassNo", s[i].getClassNo());
                 item.put("getGraduated", s[i].getGraduated());
                 data.add(item);
             }
         //创建SimpleAdapter适配器将数据绑定到item显示控件上
         SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.activity_student_listview,
                 new String[]{"getUsername", "getPassword", "getName", "getAge", "getPhone",
                         "getMajor", "getGrade", "getClassNo", "getGraduated"},
                 new int[]{R.id.username, R.id.password, R.id.name, R.id.age, R.id.phone,
                         R.id.major, R.id.grade, R.id.classNo, R.id.graduate});
         //实现列表的显示
         lv.setAdapter(adapter);
         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 //获取点击条目的内容
                 String getUsername = data.get(position).get("getUsername") + "";
                 String getPassword = data.get(position).get("getPassword") + "";
                 String getName = data.get(position).get("getName") + "";
                 int getAge = (int) data.get(position).get("getAge");
                 String getPhone = data.get(position).get("getPhone") + "";
                 String getMajor = data.get(position).get("getMajor") + "";
                 String getGrade = data.get(position).get("getGrade") + "";
                 String getClassNo = data.get(position).get("getClassNo") + "";
                 int getGraduated = (int) data.get(position).get("getGraduated");
                 //把信息带到下一个界面
                 Intent intent = new Intent(StudentShow.this, StudentUpdate.class);

                 intent.putExtra("getUsername", getUsername);//把no传递到下一个界面
                 intent.putExtra("getPassword", getPassword);
                 intent.putExtra("getName", getName);
                 intent.putExtra("getAge", getAge);
                 intent.putExtra("getPhone", getPhone);
                 intent.putExtra("getMajor", getMajor);
                 intent.putExtra("getGrade", getGrade);
                 intent.putExtra("getClassNo", getClassNo);
                 intent.putExtra("getGraduated", getGraduated);

                 startActivity(intent);
             }
         });
    }
}
