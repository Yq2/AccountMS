package com.yq.accountsoft.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yq.accountsoft.dao.FlagDAO;
import com.yq.accountsoft.dao.IngoodsDAO;
import com.yq.accountsoft.dao.OutgoodsDAO;
import com.yq.accountsoft.model.Tb_flag;
import com.yq.accountsoft.model.Tb_ingoods;
import com.yq.accountsoft.model.Tb_outgoods;

import java.util.List;

import activity.accountsoft.yq.com.accountms.R;

public class ShowInfo extends Activity {

    public static final String FLAG = "id";// 定义一个常量，用来作为请求码
    Button btnoutinfo, btnininfo, btnflaginfo;// 创建3个Button对象
    ListView lvinfo;// 创建ListView对象
    String strType = "";// 创建字符串，记录管理类型
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);// 设置布局文件
        lvinfo = (ListView) findViewById(R.id.lvinfo);// 获取布局文件中的ListView组件
        btnoutinfo = (Button) findViewById(R.id.btnoutinfo);// 获取布局文件中的支出信息按钮
        btnininfo = (Button) findViewById(R.id.btnininfo);// 获取布局文件中的收入信息按钮
        btnflaginfo = (Button) findViewById(R.id.btnflaginfo);// 获取布局文件中的便签信息按钮

        ShowInfo(R.id.btnoutinfo);// 默认显示支出信息

        btnoutinfo.setOnClickListener(new View.OnClickListener() {// 为支出信息按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ShowInfo(R.id.btnoutinfo);// 显示支出信息
            }
        });

        btnininfo.setOnClickListener(new View.OnClickListener() {// 为收入信息按钮设置监听事件

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ShowInfo(R.id.btnininfo);// 显示收入信息
            }
        });
        btnflaginfo.setOnClickListener(new View.OnClickListener() {// 为便签信息按钮设置监听事件

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ShowInfo(R.id.btnflaginfo);// 显示便签信息
            }
        });

        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener()// 为ListView添加项单击事件
        {
            // 覆写onItemClick方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = null;// 创建Intent对象
                if (strType == "btnoutinfo" | strType == "btnininfo") {
                    String strInfo = String.valueOf(((TextView) view).getText());// 记录单击的项信息
                    String strid = strInfo.substring(0, strInfo.indexOf('|'));// 从项信息中截取编号
                    intent = new Intent(ShowInfo.this, InfoManage.class);
                    intent.putExtra(FLAG, new String[]{strid, strType});
                    startActivity(intent);
                }
            }
        });
        lvinfo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                //final String items="删除";    items必须为数组
                if(strType.equals("btnflaginfo")) {
                    String strInfo = String.valueOf(((TextView) view).getText());// 记录收入信息
                    String strid = strInfo.substring(0, strInfo.indexOf('|'));// 从收入信息中截取收入编号
                    final int strId =Integer.valueOf(strid);
                    final String[] items = new String[]{"删除"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShowInfo.this);
                    builder.setTitle("是否删除");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            FlagDAO flagDAO = new FlagDAO(ShowInfo.this);
                            flagDAO.detele(Integer.valueOf(strId));
                            Toast.makeText(ShowInfo.this, "删除成功", Toast.LENGTH_SHORT).show();
                            ShowInfo(R.id.btnflaginfo);
                        }
                    });
                    builder.create().show();//此方法必不可少
                }
                else if (strType.equals("btnininfo"))
                {
                    String strInfo=String.valueOf(((TextView)view).getText());
                    final int  strId=Integer.parseInt(strInfo.substring(0, strInfo.indexOf("|")));
                    final String[] items = new String[]{"删除"};
                    AlertDialog.Builder builder=new AlertDialog.Builder(ShowInfo.this);
                    builder.setTitle("是否删除");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            IngoodsDAO ingoodsDAO = new IngoodsDAO(ShowInfo.this);
                            ingoodsDAO.detele(strId);
                            Toast.makeText(ShowInfo.this, "删除成功", Toast.LENGTH_SHORT).show();
                            ShowInfo(R.id.btnininfo);
                        }
                    }); builder.create().show();//此方法必不可少

                }
                else
                {
                    String strInfo=String.valueOf(((TextView)view).getText());
                    final int  strId=Integer.parseInt(strInfo.substring(0, strInfo.indexOf("|")));
                    final String[] items = new String[]{"删除"};
                    AlertDialog.Builder builder=new AlertDialog.Builder(ShowInfo.this);
                    builder.setTitle("是否删除");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            OutgoodsDAO outgoodsDAO=new OutgoodsDAO(ShowInfo.this);
                            outgoodsDAO.detele(strId);
                            Toast.makeText(ShowInfo.this, "删除成功", Toast.LENGTH_SHORT).show();
                            ShowInfo(R.id.btnoutinfo);
                        }
                    }); builder.create().show();//此方法必不可少

                }
                return true;
            }
        });




    }
//下面可以理解为构造方法
    private void ShowInfo(int intType) {// 用来根据传入的管理类型，显示相应的信息
        String[] strInfos = null;// 定义字符串数组，用来存储收入信息
        ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
        switch (intType) {// 以intType为条件进行判断
            case R.id.btnoutinfo:// 如果是btnoutinfo按钮
                strType = "btnoutinfo";// 为strType变量赋值
                OutgoodsDAO outaccountinfo = new OutgoodsDAO(ShowInfo.this);// 创建OutaccountDAO对象
                // 获取所有支出信息，并存储到List泛型集合中
                List<Tb_outgoods> listoutinfos = outaccountinfo.getScrollData(0,
                        (int) outaccountinfo.getCount());
                strInfos = new String[listoutinfos.size()];// 设置字符串数组的长度
                int i = 0;// 定义一个开始标识
                for (Tb_outgoods tb_outaccount : listoutinfos) {// 遍历List泛型集合
                    // 将支出相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[i] = tb_outaccount.getId() + "| "
                            + tb_outaccount.getType() + " "
                            + String.valueOf(tb_outaccount.getMoney()) + "元 "
                            + tb_outaccount.getTime();
                    i++;// 标识加1
                }
                /****************集合的遍历不能像数组那样******************/
                /*
                for (i=0;i<listoutinfos.size();i++)
                {
                    listoutinfos[i].;
                }
                */
                break;
            case R.id.btnininfo:// 如果是btnininfo按钮
                strType = "btnininfo";// 为strType变量赋值
                IngoodsDAO inaccountinfo = new IngoodsDAO(ShowInfo.this);// 创建InaccountDAO对象
                // 获取所有收入信息，并存储到List泛型集合中
                List<Tb_ingoods> listinfos = inaccountinfo.getScrollData(0,
                        (int) inaccountinfo.getCount());
                strInfos = new String[listinfos.size()];// 设置字符串数组的长度
                int m = 0;// 定义一个开始标识
                for (Tb_ingoods tb_inaccount : listinfos) {// 遍历List泛型集合
                    // 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[m] = tb_inaccount.getId() + "| "
                            + tb_inaccount.getType() + " "
                            + String.valueOf(tb_inaccount.getMoney()) + "元 "
                            + tb_inaccount.getTime();
                    m++;// 标识加1
                }
                break;
            case R.id.btnflaginfo:// 如果是btnflaginfo按钮
                strType = "btnflaginfo";// 为strType变量赋值
                FlagDAO flaginfo = new FlagDAO(ShowInfo.this);// 创建FlagDAO对象
                // 获取所有便签信息，并存储到List泛型集合中
                List<Tb_flag> listFlags = flaginfo.getScrollData(0,
                        (int) flaginfo.getCount());
                strInfos = new String[listFlags.size()];// 设置字符串数组的长度
                int n = 0;// 定义一个开始标识
                for (Tb_flag tb_flag : listFlags) {// 遍历List泛型集合
                    // 将便签相关信息组合成一个字符串，存储到字符串数组的相应位置
                    strInfos[n] = tb_flag.getId() + "| " +tb_flag.getName()+" "+ tb_flag.getFlag()+" 时间 "+Tb_flag.getTime();
                    if (strInfos[n].length() > 35)// 判断便签信息的长度是否大于15
                        strInfos[n] = strInfos[n].substring(0, 35) + "……";// 将位置大于15之后的字符串用……代替
                    n++;// 标识加1
                }
                break;
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
        ShowInfo(R.id.btnoutinfo);// 显示支出信息
    }
}