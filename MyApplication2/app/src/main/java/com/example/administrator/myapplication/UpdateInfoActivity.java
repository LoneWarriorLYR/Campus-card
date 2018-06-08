package com.example.administrator.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.UserDataManage.UserDataOperator;

public class UpdateInfoActivity extends AppCompatActivity {

    private Button btn_id,btn_name,btn_class;
    private EditText edt_name,edt_id,edt_class;
    private SharedPreferences login_sp;  //SharedPreferences保存数据
    private String userNameValue,userId,className;
    private UserDataOperator userOperator;        //用户数据管理类
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        init();
    }

    //初始化控件
    public void  init(){
        btn_id = (Button)findViewById(R.id.btn_id);
        btn_name = (Button)findViewById(R.id.btn_name);
        btn_class = (Button)findViewById(R.id.btn_class);
        edt_name = (EditText)findViewById(R.id.edt_names);
        edt_id = (EditText)findViewById(R.id.edt_class_ID);
        edt_class = (EditText)findViewById(R.id.edt_className);
        //获取保存在userInfo中的数据
        login_sp = getSharedPreferences("userInfo", 0);
        userNameValue=login_sp.getString("USER_NAME", "");
        userId =login_sp.getString("id", "");
        className =login_sp.getString("className", "");

        //显示信息
        edt_id.setText("学号："+userId);
        edt_name.setText("用户名："+userNameValue);
        edt_class.setText("班级："+className);
        userOperator = new UserDataOperator(this);

        //修改
        change();
    }

    private void change() {

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor =login_sp.edit();
               if(view.getId()==btn_id.getId()){
                   //获取输入内容
                   String id = edt_id.getText().toString().trim();
                   //输入id为空
                   if(id.equals("")||id.equals(null)){
                       Toast.makeText(UpdateInfoActivity.this,"新学号不能为空！"+id,Toast.LENGTH_SHORT).show();
                   }else {
                       userOperator.updateId(id, userNameValue);
                       //更新学号
                       editor.putString("id", id);
                       //提交保存
                       editor.commit();
                       Toast.makeText(UpdateInfoActivity.this, "嘻嘻！修改学号成功！", Toast.LENGTH_SHORT).show();//修改成功提示
                   }

               }else if(view.getId()==btn_name.getId()){
                   //获取输入内容
                   String name = edt_name.getText().toString().trim();
                   //输入id为空
                   if(name.equals("")||name.equals(null)){
                       Toast.makeText(UpdateInfoActivity.this,"新用户名不能为空！"+name,Toast.LENGTH_SHORT).show();
                   }else {
                       userOperator.updateName(name, userNameValue);
                       //更新用户名
                       editor.putString("USER_NAME", name);
                       //提交保存
                       editor.commit();
                       Toast.makeText(UpdateInfoActivity.this, "嘻嘻！修改用户名成功！", Toast.LENGTH_SHORT).show();//修改成功提示
                   }
               }else if(view.getId()==btn_class.getId()){

                   //获取输入内容
                   String className = edt_class.getText().toString().trim();
                   //输入id为空
                   if(className.equals("")||className.equals(null)){
                       Toast.makeText(UpdateInfoActivity.this,"新班级不能为空！"+className,Toast.LENGTH_SHORT).show();
                   }else {
                       userOperator.updateClassName(className, userNameValue);
                       //更新班级
                       editor.putString("className", className);
                       //提交保存
                       editor.commit();
                       Toast.makeText(UpdateInfoActivity.this, "嘻嘻！修改班级成功！", Toast.LENGTH_SHORT).show();//修改成功提示
                   }
               }

            }
        };

        //设置点击事件
        btn_id.setOnClickListener(click);
        btn_name.setOnClickListener(click);
        btn_class.setOnClickListener(click);




    }
}
