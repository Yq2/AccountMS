package com.yq.accountsoft.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yq.accountsoft.dao.OutgoodsDAO;
import com.yq.accountsoft.model.Tb_outgoods;

import java.util.List;

import activity.accountsoft.yq.com.accountms.R;

public class OutGoodsInfo extends Activity {

    protected static final String FLAG="id";
    ListView listView;
    String strType="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_goods_info);
        listView= (ListView) findViewById(R.id.lvoutgoodsinfo);
        ShowInfo(R.id.btnoutinfo);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()// 为ListView添加项单击事件
        {
            // 覆写onItemClick方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());// 记录收入信息
                String strid = strInfo.substring(0, strInfo.indexOf('|'));// 从收入信息中截取收入编号
                Intent intent=new Intent();
                intent.setClass(OutGoodsInfo.this,InfoManage.class);

                intent.putExtra(FLAG, new String[]{strid, strType});// 设置传递数据
                startActivity(intent);// 执行Intent操作
                //finish();
            }
        });
    }
    protected void ShowInfo(int intType){
        String strInfos[];
        strType="btnoutinfo";
        ArrayAdapter<String> arrayAdapter=null;
        OutgoodsDAO outgoodsDAO=new OutgoodsDAO(OutGoodsInfo.this);
        List<Tb_outgoods> tb_outgoods=outgoodsDAO.getScrollData(0, (int) outgoodsDAO.getCount());
        strInfos=new String[tb_outgoods.size()];
        int i=0;
        for (Tb_outgoods listoutinfo:tb_outgoods)
        {
            //注意getId()后面的   "|"
            strInfos[i]=listoutinfo.getId()+"|"+listoutinfo.getName()+listoutinfo.getMoney()
                    +"元"+listoutinfo.getMany()+listoutinfo.getTime()
                    +listoutinfo.getType()+listoutinfo.getHandler()
                    +listoutinfo.getMark();
            i++;
        }
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,strInfos);
        listView.setAdapter(arrayAdapter);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        ShowInfo(R.id.btnoutinfo);
    }
}