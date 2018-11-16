package com.yq.accountsoft.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yq.accountsoft.dao.IngoodsDAO;
import com.yq.accountsoft.dao.OutgoodsDAO;
import com.yq.accountsoft.model.Tb_ingoods;
import com.yq.accountsoft.model.Tb_outgoods;

import java.util.Calendar;

import activity.accountsoft.yq.com.accountms.R;

public class InfoManage extends Activity {
    protected static final int DATE_DIALOG_ID = 0;// 创建日期对话框常量
    TextView tvtitle;//
    EditText txtMany,txtTime,txtMark,txtHandler;// 创建4个EditText对象
    Spinner spType;// 创建Spinner对象
    Button btnEdit, btnDel;// 创建两个Button对象
    String[] strInfos;// 定义字符串数组
    String strid, strType;// 定义两个字符串变量，分别用来记录信息编号和管理类型

    private int mYear;// 年
    private int mMonth;// 月
    private int mDay;// 日

    OutgoodsDAO outgoodsDAO = new OutgoodsDAO(InfoManage.this);// 创建OutaccountDAO对象
    IngoodsDAO ingoodsDAO = new IngoodsDAO(InfoManage.this);// 创建InaccountDAO对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_manage);// 设置布局文件
        tvtitle = (TextView) findViewById(R.id.inouttitle);// 获取标题标签对象
        txtMany = (EditText) findViewById(R.id.txtInOutMany);// 获取地点/付款方标签对象
        txtTime = (EditText) findViewById(R.id.txtInOutTime);// 获取时间文本框
        spType = (Spinner) findViewById(R.id.spInOutType);// 获取类别下拉列表
        txtMark = (EditText) findViewById(R.id.txtInOutMark);// 获取备注文本框
        txtHandler=(EditText)findViewById(R.id.txtInOutHandler);
        btnEdit = (Button) findViewById(R.id.btnInOutEdit);// 获取修改按钮
        btnDel = (Button) findViewById(R.id.btnInOutDelete);// 获取删除按钮
        Intent intent = getIntent();// 创建Intent对象
        Bundle bundle = intent.getExtras();// 获取传入的数据，并使用Bundle记录
        strInfos = bundle.getStringArray(ShowInfo.FLAG);// flag是一个数组或字段
        strid = strInfos[0];// 记录id
        strType = strInfos[1];// 记录类型
        if (strType.equals("btnoutinfo"))// 如果类型是btnoutinfo
        {
            tvtitle.setText("出货管理");// 设置标题为“支出管理”
            // 根据编号查找支出信息，并存储到Tb_outaccount对象中
            Tb_outgoods tb_outgoods = outgoodsDAO.find(Integer
                    .parseInt(strid));
            txtMany.setText(String.valueOf(tb_outgoods.getMany()));// 显示金额
            txtTime.setText(tb_outgoods.getTime());// 显示时间
            spType.setPrompt(tb_outgoods.getType());// 显示类别
            txtMark.setText(tb_outgoods.getMark());// 显示备注
            txtHandler.setText(tb_outgoods.getHandler());
        } else if (strType.equals("btnininfo"))// 如果类型是btnininfo
        {
            tvtitle.setText("进货管理");// 设置标题为“收入管理”
            // 根据编号查找收入信息，并存储到Tb_outaccount对象中
            Tb_ingoods tb_ingoods = ingoodsDAO.find(Integer
                    .parseInt(strid));
            txtMany.setText(String.valueOf(tb_ingoods.getMoney()));// 显示金额
            txtTime.setText(tb_ingoods.getTime());// 显示时间
            spType.setPrompt(tb_ingoods.getType());// 显示类别
            txtMark.setText(tb_ingoods.getMark());// 显示备注
            txtHandler.setText(tb_ingoods.getHandler());
        }

        txtTime.setOnClickListener(new View.OnClickListener() {// 为时间文本框设置单击监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID);// 显示日期选择对话框
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {// 为修改按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (strType.equals("btnoutinfo"))// 判断类型如果是btnoutinfo
                {
                    Tb_outgoods tb_outgoods = new Tb_outgoods();// 创建Tb_outaccount对象
                    tb_outgoods.setId(strid);// 设置编号
                    tb_outgoods.setMany(txtMany.getText().toString());// 设置金额
                    tb_outgoods.setTime(txtTime.getText().toString());// 设置时间
                    tb_outgoods.setType(spType.getSelectedItem().toString());// 设置类别
                    tb_outgoods.setMark(txtMark.getText().toString());// 设置备注
                    tb_outgoods.setHandler(txtHandler.getText().toString());
                    outgoodsDAO.update(tb_outgoods);// 更新支出信息
                } else if (strType.equals("btnininfo"))// 判断类型如果是btnininfo
                {
                    Tb_ingoods tb_inaccount = new Tb_ingoods();// 创建Tb_inaccount对象
                    tb_inaccount.setId(strid);// 设置编号
                    tb_inaccount.setMany(txtMany.getText().toString());// 设置金额
                    tb_inaccount.setTime(txtTime.getText().toString());// 设置时间
                    tb_inaccount.setType(spType.getSelectedItem().toString());// 设置类别
                    tb_inaccount.setMark(txtMark.getText().toString());// 设置备注
                    tb_inaccount.setHandler(txtHandler.getText().toString());
                    ingoodsDAO.update(tb_inaccount);// 更新收入信息
                }
                // 弹出信息提示
                Toast.makeText(InfoManage.this, "〖数据〗修改成功！", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {// 为删除按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (strType.equals("btnoutinfo"))// 判断类型如果是btnoutinfo
                {
                    outgoodsDAO.detele(Integer.parseInt(strid));// 根据编号删除支出信息
                } else if (strType.equals("btnininfo"))// 判断类型如果是btnininfo
                {
                    ingoodsDAO.detele(Integer.parseInt(strid));// 根据编号删除收入信息
                }
                Toast.makeText(InfoManage.this, "〖数据〗删除成功！", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });

        final Calendar c = Calendar.getInstance();// 获取当前系统日期
        mYear = c.get(Calendar.YEAR);// 获取年份
        mMonth = c.get(Calendar.MONTH);// 获取月份
        mDay = c.get(Calendar.DAY_OF_MONTH);// 获取天数
        updateDisplay();// 显示当前系统时间
    }

    @Override
    protected Dialog onCreateDialog(int id)// 重写onCreateDialog方法
    {
        switch (id) {
            case DATE_DIALOG_ID:// 弹出日期选择对话框
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;// 为年份赋值
            mMonth = monthOfYear;// 为月份赋值
            mDay = dayOfMonth;// 为天赋值
            updateDisplay();// 显示设置的日期
        }
    };

    private void updateDisplay() {
        // 显示设置的时间
        txtTime.setText(new StringBuilder().append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay));
    }
}
