package com.example.administrator.myapplication.UserDataManage;

/**
 * user表实体类
 */
public class UserData {
    private String userName;                  //用户名
    private String userPwd;                   //用户密码
    private int userId;                       //用户ID号（学号）
    private String className;                 //班级
    private int  balance;                     //余额
    private String  card_state;               //卡片状态
    public int pwdresetFlag=0;
    //获取用户名
    public String getUserName() {             //获取用户名
        return userName;
    }
    //设置用户名
    public void setUserName(String userName) {  //输入用户名
        this.userName = userName;
    }
    //获取用户密码
    public String getUserPwd() {                //获取用户密码
        return userPwd;
    }
    //设置用户密码
    public void setUserPwd(String userPwd) {     //输入用户密码
        this.userPwd = userPwd;
    }
    //获取用户id
    public int getUserId() {                   //获取用户ID号
        return userId;
    }
    //设置用户id
    public void setUserId(int userId) {       //设置用户ID号
        this.userId = userId;
    }

    //获取用户className
    public String getClassName() {                   //获取用户ID号
        return className;
    }
    //设置用户className
    public void  setClassName(String  className) {       //设置用户ID号
         this.className = className;
    }
    //获取用户balance
    public int getBalance() {                   //获取用户ID号
        return balance;
    }
    //设置用户balance
    public void  setBalance(int  balance) {       //设置用户ID号
        this.balance = balance;
    }

    //获取用户card_state
    public String getcard_state() {                   //获取用户ID号
        return card_state;
    }
    //设置用户balance
    public void  setCard_state(String  card_state) {       //设置用户ID号
        this.card_state = card_state;
    }


    public UserData( int userId,String userName, String userPwd,String className,int balance,String card_state) {
        super();
        this.userName = userName;
        this.userPwd = userPwd;
        this.userId = userId;
        this.className = className;
        this.balance = balance;
        this.card_state = card_state;
    }

    //无参构造函数
    public UserData(){

    }

}

