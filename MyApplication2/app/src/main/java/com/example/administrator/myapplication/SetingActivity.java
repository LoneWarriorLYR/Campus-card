package com.example.administrator.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.UserDataManage.UserDataOperator;


public class SetingActivity extends AppCompatActivity {

    private Button btn_delete;
    private EditText edt_delUser;
    private UserDataOperator UserDataOperator;
    private SharedPreferences login_sp;  //SharedPreferences保存数据
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seting);
        //初始化
        init();
        delete();
    }

    //初始化
    private void init() {
        btn_delete = (Button)findViewById(R.id.btn_delete);
        edt_delUser = (EditText)findViewById(R.id.edt_delUser);
        //打开数据库
        UserDataOperator = new UserDataOperator(this);
        //获取保存在userInfo中的数据
        login_sp = getSharedPreferences("userInfo", 0);
        userName = login_sp.getString("USER_NAME", "");
        edt_delUser.setText(userName);
        userName = edt_delUser.getText().toString().trim();
        //delete();

        //判断输入的内容是否为空
        if(userName==null||userName.equals("")){
            Toast.makeText(SetingActivity.this,"删除的用户不能为空哦！",Toast.LENGTH_SHORT).show();
        }else {
        }
    }

    //删除用户
    private void delete() {

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   /* AlertDialog dialog = new AlertDialog.Builder(SetingActivity.this)
                            .setIcon(R.drawable.logo)//设置标题的图片
                            .setTitle("系统提示")//设置对话框的标题
                            .setMessage("是否确认删除:"+userName)//设置对话框的内容
                            //设置对话框的按钮
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(SetingActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(SetingActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                                    //删除用户
                                    UserDataOperator.delete(userName);
                                    //修改SharedPreferences保存数据(修改用户名为空，记住密码为false)
                                    SharedPreferences.Editor edt =login_sp.edit();
                                    edt.putString("USER_NAME","");
                                    edt.putBoolean("mRememberCheck", false);
                                    Toast.makeText(SetingActivity.this,"删除用户成功！请回到登录界面登录或进入注册页面注册账号哦",Toast.LENGTH_SHORT).show();
                                    //跳转到登录页面
                                    Intent intent = new Intent(SetingActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    dialog.dismiss();
                                }
                            }).show();*/

                AlertDialog.Builder builder = new AlertDialog.Builder(SetingActivity.this);
                builder.setIcon(R.drawable.logo); //设置标题的图片
                builder.setTitle("系统提示");//设置对话框的标题
                builder.setMessage("是否确认删除:"+userName);//设置对话框的内容
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(SetingActivity.this, "点击了取消的按钮", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(SetingActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                        //删除用户
                        UserDataOperator.delete(userName);
                        //修改SharedPreferences保存数据(修改用户名为空，记住密码为false)
                        SharedPreferences.Editor edt =login_sp.edit();
                        edt.putString("USER_NAME","");
                        edt.putBoolean("mRememberCheck", false);
                        Toast.makeText(SetingActivity.this,"删除用户成功！请回到登录界面登录或进入注册页面注册账号哦",Toast.LENGTH_SHORT).show();
                        //跳转到登录页面
                        Intent intent = new Intent(SetingActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.create().show();


            }

        });
    }
}
