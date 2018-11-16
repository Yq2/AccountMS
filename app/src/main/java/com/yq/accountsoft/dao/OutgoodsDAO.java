package com.yq.accountsoft.dao;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.yq.accountsoft.model.Tb_outgoods;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2015/12/11.
 */
public class OutgoodsDAO {
    private DBOpenHelper helper;// 创建DBOpenHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象
    public OutgoodsDAO(Context context){
        helper=new DBOpenHelper(context);
    }
    public void add(Tb_outgoods tb_outgoods){
        db=helper.getWritableDatabase();
        db.execSQL("insert into tb_outgoods(id,name,money,many,time,type,handler,mark) " +
                "values (?,?,?,?,?,?,?,?)",
                new Object[]{tb_outgoods.getId(),tb_outgoods.getName()
                ,tb_outgoods.getMoney(),tb_outgoods.getMany()
                ,tb_outgoods.getTime(),tb_outgoods.getType()
                ,tb_outgoods.getHandler(),tb_outgoods.getMark()
                });
    }
    public void update(Tb_outgoods tb_outgoods){
        db=helper.getWritableDatabase();
        db.execSQL("update tb_outgoods set name=?,money=?,many=?,time=?,type=?,handler=?,mark=? where id=?",new Object[]{
                tb_outgoods.getName(), tb_outgoods.getMoney()
                ,tb_outgoods.getMany(),tb_outgoods.getTime()
                ,tb_outgoods.getType(),tb_outgoods.getHandler()
                ,tb_outgoods.getMark()
                ,tb_outgoods.getId()
        });
    }
    public Tb_outgoods find(int id) {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db
                .rawQuery(
                        "select id,name,money,many,time,type,handler,mark from tb_outgoods where id = ?",
                        new String[] { String.valueOf(id) });// 根据编号查找支出信息，并存储到Cursor类中
        if (cursor.moveToNext())// 遍历查找到的支出信息
        {
            // 将遍历到的支出信息存储到Tb_outaccount类中
            return new Tb_outgoods(
                    cursor.getString(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("money")),
                    cursor.getString(cursor.getColumnIndex("many")),
                    cursor.getString(cursor.getColumnIndex("time")),
                    cursor.getString(cursor.getColumnIndex("type")),
                    cursor.getString(cursor.getColumnIndex("handler")),
                    cursor.getString(cursor.getColumnIndex("mark"))
            );
        }
        return null;// 如果没有信息，则返回null
    }
    public List<Tb_outgoods> getScrollData(int start, int count) {
        List<Tb_outgoods> tb_outgoods = new ArrayList<Tb_outgoods>();// 创建集合对象
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 获取所有支出信息
        Cursor cursor = db.rawQuery("select * from tb_outgoods limit ?,?",
                new String[] { String.valueOf(start), String.valueOf(count) });
        while (cursor.moveToNext())// 遍历所有的支出信息
        {
            // 将遍历到的支出信息添加到集合中
            tb_outgoods.add(new Tb_outgoods(
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
        return tb_outgoods;// 返回集合
    }

    public long getCount() {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(id) from tb_outgoods",
                null);// 获取支出信息的记录数
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }

    public int getMaxId() {
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select max(id) from tb_outgoods", null);// 获取支出信息表中的最大编号
        while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
            return cursor.getInt(0);// 获取访问到的数据，即最大编号
        }
        return 0;// 如果没有数据，则返回0
    }
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
            // 执行删除支出信息操作
            db.execSQL("delete from tb_outgoods where id in (" + sb + ")",
                    (Object[]) ids);
        }
    }

}