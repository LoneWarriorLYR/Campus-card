package com.example.administrator.myapplication.UserDataManage;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库操作类：用于数据库表的增删改查操作
 */

public class UserDataOperator {

    private  DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    //创建数据库
    public UserDataOperator(Context context){
        dbHelper = new DatabaseHelper(context,"userInfo",null,1);
        db = dbHelper.getWritableDatabase();
    }

    //注册账号
    public boolean  add(UserData user){
        db.execSQL("insert into user (userId,userName,userPwd,className,balance,card_state) values(?,?,?,?,?,?)",
                new Object[] { user.getUserId(),user.getUserName(),user.getUserPwd(),
                        user.getClassName(),user.getBalance(),user.getcard_state()}
                );
        return true;
    }

    //修改密码
    public void update(String newPsw,String name) {
        db.execSQL("update user set userPwd=? where userName=?",
                new Object[] {newPsw,name });
    }

    //修改学号
    public void updateId(String id,String name) {
        db.execSQL("update user set userId=? where userName=?",
                new Object[] {id,name });
    }

    //修改用户名
    public void updateName(String newUserName,String name) {
        db.execSQL("update user set userName=? where userName=?",
                new Object[] {newUserName,name });
    }

    //修改班级
    public void updateClassName(String className,String name) {
        db.execSQL("update user set className=? where userName=?",
                new Object[] {className,name });
    }

    //登录：根据用户名和密码找用户
    public boolean  findUserByNameAndPwd(String userName,String pwd){
        Cursor c = db.rawQuery("select * from user where userName= ? and userPwd=?", new String[] { userName,pwd });
        if (c.getCount() > 0) {
            c.close();
            return true;
        }
        c.close();
        return false;
    }


    // 删除用户
    public void delete(String name) {
        db.execSQL("delete from user where userName=?", new String[] { name });
    }



    // 查询用户信息
    public UserData accountInquiry(String userName) {
        Cursor c = db.rawQuery("select * from user where userName=? ", new String[] { userName });
        UserData user = new UserData();
        while (c.moveToNext()) {
            user.setUserId(Integer.parseInt(c.getString(0)));
            user.setUserName(c.getString(1));
            user.setUserPwd(c.getString(2));
            user.setClassName(c.getString(3));
            user.setBalance(Integer.parseInt(c.getString(4)));
            user.setCard_state(c.getString(5));
        }
        c.close();
        return user;
    }

    // 检验用户名是否已存在
    public boolean findUserByName(String userName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String Query = "Select * from user where userName =?";
        Cursor cursor = db.rawQuery(Query, new String[] { userName });
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    //卡片充值
    //先查询到用户的余额
    public boolean  recharge(int number,String name) {
        boolean isOk = false;
        //更新用户的余额
        db.execSQL("update user set balance= ? where userName=?",
                new Object[] {number,name });
        isOk = true;
        return isOk;
    }


    //校园卡挂失
    public void lock (String name ) {
        String card_state = "挂失中";
        //更新校园卡状态
        db.execSQL("update user set card_state= ? where userName=?",
                new Object[] {card_state,name });
    }

    //校园卡解挂失
    public void unlock (String name ) {
        String card_state = "正常";
        //更新校园卡状态
        db.execSQL("update user set card_state= ? where userName=?",
                new Object[] {card_state,name });
    }


}
