package com.AndroidUI.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.control.AdminControl;
import com.example.administrator.book.R;
import com.model.Admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show);

    }

    @Override
    public void onStart(){
        super.onStart();
        ListView lv = (ListView) findViewById(R.id.lv);

        //获取到集合数据
        final List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        AdminControl adminControl=new AdminControl (AdminShow.this);
        Admin admins[]=adminControl.getAllAdmin();

        if(admins!=null)
            for (int i = 0; i < admins.length; i++) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("getUsername", admins[i].getUsername());
                item.put("getPassword",admins[i].getPassword());
                item.put("getAuthorization", admins[i].getAuthorization());
                item.put("getName", admins[i].getName());
                item.put("getAge", admins[i].getAge());
                item.put("getPhone", admins[i].getPhone());
                item.put("getDepartment", admins[i].getDepartment());
                data.add(item);
            }
        //创建SimpleAdapter适配器将数据绑定到item显示控件上
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.activity_admin_listview,
                new String[]{"getUsername", "getPassword", "getAuthorization", "getName", "getAge","getPhone","getDepartment"},
                new int[]{R.id.tv_No, R.id.tv_Pass, R.id.tv_Auth, R.id.tv_Name, R.id.tv_Age, R.id.tv_Tel, R.id.tv_Department});
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
                String getDepartment = data.get(position).get("getDepartment") + "";
                //把信息带到下一个界面
                Intent intent = new Intent(AdminShow.this, AdminUpdate.class);

                intent.putExtra("getUsername", getUsername);//把no传递到下一个界面
                intent.putExtra("getPassword", getPassword);
                intent.putExtra("getName", getName);
                intent.putExtra("getAge", getAge);
                intent.putExtra("getPhone", getPhone);
                intent.putExtra("getDepartment", getDepartment);

                startActivity(intent);
            }
        });
    }
}
