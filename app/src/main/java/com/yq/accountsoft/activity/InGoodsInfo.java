package com.yq.accountsoft.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yq.accountsoft.dao.IngoodsDAO;
import com.yq.accountsoft.model.Tb_ingoods;

import java.util.List;

import activity.accountsoft.yq.com.accountms.R;

public class InGoodsInfo extends Activity {

    public static final String FLAG = "id";// 定义一个常量，用来作为请求码
    ListView lvinfo;// 创建ListView对象
    String strType = "";// 创建字符串，记录管理类型
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_goods_info);// 设置布局文件
        lvinfo = (ListView) findViewById(R.id.lvingoodsinfo);// 获取布局文件中的ListView组件
        ShowInfo(R.id.btnininfo);// 调用自定义方法显示收入信息
        //ShowInfo(1);
        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener()// 为ListView添加项单击事件
        {
            // 覆写onItemClick方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());// 记录收入信息
                String strid = strInfo.substring(0, strInfo.indexOf('|'));// 从收入信息中截取收入编号
                Intent intent=new Intent();
                intent.setClass(InGoodsInfo.this,InfoManage.class);
                //Intent intent = new Intent(InGoodsInfo.this, InfoManage.class);// 创建Intent对象
                intent.putExtra(FLAG, new String[]{strid, strType});// 设置传递数据
                startActivity(intent);// 执行Intent操作
            }
        });
    }

    private void ShowInfo(int intType) {// 用来根据传入的管理类型，显示相应的信息
        String[] strInfos = null;// 定义字符串数组，用来存储收入信息
        ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
        strType = "btnininfo";// 为strType变量赋值
        IngoodsDAO ingoodsinfo = new IngoodsDAO(InGoodsInfo.this);// 创建InaccountDAO对象
        // 获取所有收入信息，并存储到List泛型集合中
        List<Tb_ingoods> listinfos = ingoodsinfo.getScrollData(0,
                (int) ingoodsinfo.getCount());
        strInfos = new String[listinfos.size()];// 设置字符串数组的长度
        int m = 0;// 定义一个开始标识
        for (Tb_ingoods tb_ingoods : listinfos) {// 遍历List泛型集合
            // 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
            strInfos[m] = tb_ingoods.getId() + "|" +tb_ingoods.getName()+" "
                    +tb_ingoods.getMoney()+"元"+tb_ingoods.getMany()+"单位"+tb_ingoods.getType()
                    + " "
                    + tb_ingoods.getTime();
            m++;// 标识加1
        }
        // 使用字符串数组初始化ArrayAdapter对象
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
    }
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();// 实现基类中的方法
        ShowInfo(R.id.btnininfo);// 显示收入信息
        //ShowInfo(1);
    }
}