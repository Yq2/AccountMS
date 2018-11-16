package com.yq.accountsoft.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;// 定义数据库版本号
    private static final String DBNAME = "goods.db";// 定义数据库名

    public DBOpenHelper(Context context){// 定义构造函数

        super(context, DBNAME, null, VERSION);// 重写基类的构造函数
        //参数 上下文 数据库名称 cosor工厂 版本号
    }
    @Override
    public void onCreate(SQLiteDatabase db){// 创建数据库

        db.execSQL("create table tb_outgoods (id varchar(10) primary key,name varchar(200),money varchar(10),many varchar(10),time varchar(10),"
                + "type varchar(10),handler varchar(10),mark varchar(200))");// 创建支出信息表
        db.execSQL("create table tb_ingoods (id varchar(10) primary key,name varchar(200),money varchar(10),many varchar(10),time varchar(10),"
                +"type varchar(10),handler varchar(10),mark varchar(200))");// 创建收入信息表
        db.execSQL("create table tb_pwd (account varchar(20) primary key,password varchar(20))");// 创建密码表
        db.execSQL("create table tb_flag (id varchar(10) primary key,name varchar(20),time varchar(10),flag varchar(200))");// 创建便签信息表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)// 覆写基类的onUpgrade方法，以便数据库版本更新
    {
        //本方法主要用于更新数据库 通过对当前版本的判断 实现数据库的更新
    }
}

