package com.example.health.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.health.bean.MenuBean;
import com.example.health.utils.SqliteUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuBusiness {

    /**
     * 插入
     */
    public static String addOne(MenuBean menu) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", menu.getTitle());
        contentValues.put("content", menu.getContent());

        SQLiteDatabase sqLiteDatabase = SqliteUtils.getInstance().getWritableDatabase();
        long insert = sqLiteDatabase.insert("app_menu", null, contentValues);
        if (insert == -1) {
            return "添加失败";
        }
        return "添加成功";
    }


    /**
     * 删除
     */
    public static String deleteOne(MenuBean MenuBean) {
        SQLiteDatabase sqLiteDatabase = SqliteUtils.getInstance().getWritableDatabase();
        int delete = sqLiteDatabase.delete("app_menu", "_id=?", new String[]{String.valueOf(MenuBean.getId())});
        if (delete == -1) {
            return "删除失败";
        }
        return "删除成功";
    }


    /**
     * 修改
     */
    public static String updateOne(MenuBean MenuBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", MenuBean.getTitle());
        contentValues.put("content", MenuBean.getContent());

        SQLiteDatabase sqLiteDatabase = SqliteUtils.getInstance().getWritableDatabase();
        int update = sqLiteDatabase.update("app_menu", contentValues, "_id=?", new String[]{String.valueOf(MenuBean.getId())});
        if (update == 0) {
            return "修改失败";
        }
        return "修改成功";
    }


    /**
     * 查询
     */
    public static List<MenuBean> getAll() {
        MenuBean MenuBean;
        List<MenuBean> list = new ArrayList<>();

        String sql = "select * from app_menu";
        SQLiteDatabase sqLiteDatabase = SqliteUtils.getInstance().getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        int idIndex = cursor.getColumnIndex("_id");
        int titleIndex = cursor.getColumnIndex("title");
        int authorIndex = cursor.getColumnIndex("content");
        //把查询到的数据循环输出
        while (cursor.moveToNext()) {
            MenuBean = new MenuBean(cursor.getInt(idIndex), cursor.getString(titleIndex), cursor.getString(authorIndex));
            list.add(MenuBean);
        }
        cursor.close();
        return list;
    }

}
