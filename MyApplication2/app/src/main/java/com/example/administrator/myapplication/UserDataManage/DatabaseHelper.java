package com.example.administrator.myapplication.UserDataManage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DatabaseHelper：创建user表
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    static String name="user.db";
    static int dbVersion=1;

    private  Context mContext;
    //创建表
    public static final String CREATE_USERTABLE = "create table user " +
            "(userId integer primary key,userName varchar(50),userPwd varchar(50)," +
            "className varchar(20),balance integer,card_state varchar(20) )";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, name, cursorFactory, version);
        mContext = context;
    }
    //只在创建的时候用一次
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERTABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}