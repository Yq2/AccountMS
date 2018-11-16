package com.yq.accountsoft.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yq.accountsoft.model.Tb_ingoods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/9.
 */

public class IngoodsDAO {
    private DBOpenHelper helper;// 创建DBOpenHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象

    public IngoodsDAO(Context context)// 定义构造函数
    {
        helper = new DBOpenHelper(context);// 初始化DBOpenHelper对象
    }

    public void add(Tb_ingoods tb_ingoods) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行添加收入信息操作
        db.execSQL(
                "insert into tb_ingoods (id,name,money,many,time,type,handler,mark) values (?,?,?,?,?,?,?,?)",
                new Object[] {
                        tb_ingoods.getId(),
                        tb_ingoods.getName(),
                        tb_ingoods.getMoney(),
                        tb_ingoods.getMany(),
                        tb_ingoods.getTime(),
                        tb_ingoods.getType(),
                        tb_ingoods.getHandler(),
                        tb_ingoods.getMark(),
                        });
    }
    public void update(Tb_ingoods tb_ingoods) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行修改收入信息操作
        db.execSQL(
                "update tb_ingoods set name=?,money = ?,many=?,time = ?,type = ?,handler = ?,mark = ? where id= ?",
                new Object[] {
                        tb_ingoods.getName(),tb_ingoods.getMoney(),
                        tb_ingoods.getMany(), tb_ingoods.getTime(),
                        tb_ingoods.getType(), tb_ingoods.getHandler(),
                        tb_ingoods.getMark(),
                        tb_ingoods.getId()
                });
    }

    public Tb_ingoods find(int id) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db
                .rawQuery(
                        "select id,name,money,many,time,type,handler,mark from tb_ingoods where id= ?",
                        new String[] { String.valueOf(id) });// 根据编号查找收入信息，并存储到Cursor类中
        if (cursor.moveToNext())// 遍历查找到的收入信息
        {
            // 将遍历到的收入信息存储到Tb_inaccount类中
            return new Tb_ingoods(
                    cursor.getString(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("many")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),
                    cursor.getString(cursor.getColumnIndex("mark"))
                    //cursor.getDouble(cursor.getColumnIndex("amount"))
            );
        }
        return null;// 如果没有信息，则返回null
    }

    /**
     * 刪除收入信息
     *
     * @param ids
     */
    public void detele(Integer... ids) {
        if (ids.length > 0)// 判断是否存在要删除的id
        {
            StringBuffer sb = new StringBuffer();// 创建StringBuffer对象
            for (int i = 0; i < ids.length; i++)// 遍历要删除的id集合
            {
                sb.append('?').append(',');// 将删除条件添加到StringBuffer对象中
            }
            sb.deleteCharAt(sb.length() - 1);// 去掉最后一个“,“字符
            db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
            // 执行删除收入信息操作
            db.execSQL("delete from tb_ingoods where id in (" + sb + ")",
                    (Object[]) ids);
        }
    }

    /**
     * 获取收入信息
     *
     * @param start
     *            起始位置
     * @param count
     *            每页显示数量
     * @return
     */
    public List<Tb_ingoods> getScrollData(int start, int count) {
        List<Tb_ingoods> tb_ingoods = new ArrayList<Tb_ingoods>();// 创建集合对象
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 获取所有收入信息
        Cursor cursor = db.rawQuery("select * from tb_ingoods limit ?,?",
                new String[] { String.valueOf(start), String.valueOf(count) });
        while (cursor.moveToNext())// 遍历所有的收入信息
        {
            // 将遍历到的收入信息添加到集合中
            tb_ingoods.add(new Tb_ingoods(
                    cursor.getString(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("many")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),
                    cursor.getString(cursor.getColumnIndex("mark"))
            ));
        }
        return tb_ingoods;// 返回集合
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount() {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db
                .rawQuery("select count(id) from tb_ingoods", null);// 获取收入信息的记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }

    /**
     * 获取收入最大编号
     *
     * @return
     */
    public  int getMaxId() {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select max(id) from tb_ingoods", null);// 获取收入信息表中的最大编号
        while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
            return cursor.getInt(0);// 获取访问到的数据，即最大编号
        }
        return 0;// 如果没有数据，则返回0
    }
}