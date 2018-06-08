package com.example.administrator.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.administrator.myapplication.UserDataManage.UserDataOperator;

public class UnLockActivity extends AppCompatActivity {
    private Switch lock;
    private ImageView img;
    private UserDataOperator UserDataOperator;
    private SharedPreferences login_sp;  //SharedPreferences保存数据
    private String name;//用户名
    private String card_state;//校园卡状态
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_lock);
        init();
    }

    //初始化控件
    private  void  init(){
        lock = (Switch)findViewById(R.id.switch_unlock);
        img = (ImageView)findViewById(R.id.img_unlock);
        //打开数据库
        UserDataOperator = new UserDataOperator(this);
        //获取保存在userInfo中的数据
        login_sp = getSharedPreferences("userInfo", 0);
        name=login_sp.getString("USER_NAME", "");
        card_state = login_sp.getString("card_state","");
        //判断校园卡状态是否是挂失状态
        if(card_state.equals("正常")){
            lock.setEnabled(false);
            Toast.makeText(UnLockActivity.this,"校园卡状态已经是正常状态,不可以重复解冻哦！！！",Toast.LENGTH_SHORT).show();
        }else{
            //解冻
            change();
        }

    }

    //switch事件
    private void change(){

        lock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    //选中
                    img.setImageResource(R.drawable.unlock);
                    //改变校园卡状态：正常
                    UserDataOperator.unlock(name);
                    //改变获取保存在userInfo中的校园卡状态
                    SharedPreferences.Editor editor =login_sp.edit();
                    editor.putString("card_state", "正常");
                    editor.commit();
                    Toast.makeText(UnLockActivity.this,"解冻成功！",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
