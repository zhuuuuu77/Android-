package com.example.health.utils;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteUtils extends SQLiteOpenHelper {

    public SqliteUtils() {
        super(AppUtils.getApplication(), "health_diet.db", null, 1);
    }


    /**
     * 创建并获取单例，注意线程安全
     */
    public static SqliteUtils getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists user(_id integer primary key autoincrement ,username,password)");
        //创建菜单表：id，title，content
        db.execSQL("create table app_menu (_id integer primary key autoincrement,title text,content text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //删除表
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS app_menu");
        //重新创建表
        onCreate(db);
    }

    private static final class InstanceHolder {
        /**
         * 单例
         */
        static final SqliteUtils instance = new SqliteUtils();
    }

}
