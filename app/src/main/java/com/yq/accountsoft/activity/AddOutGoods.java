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
import android.widget.Toast;

import com.yq.accountsoft.dao.OutgoodsDAO;
import com.yq.accountsoft.model.Tb_outgoods;

import java.util.Calendar;

import activity.accountsoft.yq.com.accountms.R;

public class AddOutGoods extends Activity {

    protected static final int DATE_DIALOG_ID = 0;// 创建日期对话框常量
    EditText txtInId,txtInName,txtInMoney,txtInMany, txtInTime, txtInHandler, txtInMark;// 创建4个EditText对象
    Spinner spInType;// 创建Spinner对象
    Button btnInSaveButton;// 创建Button对象“保存”
    Button btnInCancelButton;// 创建Button对象“取消”
    private int mYear;// 年
    private int mMonth;// 月
    private int mDay;// 日
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_outgoods);
        /********************在使用布局文件定义id的时候注意不要重复，请引用本布局文件的id 还有id值的类型不要混乱**************************/
        txtInId=(EditText)findViewById(R.id.txtId);
        txtInName=(EditText)findViewById(R.id.txtName);
        txtInMoney = (EditText) findViewById(R.id.txtMoney);// 获取金额文本框
        txtInMany=(EditText)findViewById(R.id.txtMany);
        txtInTime = (EditText) findViewById(R.id.txtTime);// 获取时间文本框
        txtInHandler = (EditText) findViewById(R.id.txtHandler);// 获取付款方文本框
        txtInMark = (EditText) findViewById(R.id.txtMark);// 获取备注文本框
        spInType = (Spinner) findViewById(R.id.spType);// 获取类别下拉列表
        btnInSaveButton = (Button) findViewById(R.id.btnSave);// 获取保存按钮
        btnInCancelButton = (Button) findViewById(R.id.btnCancel);// 获取取消按钮
        txtInTime.setOnClickListener(new View.OnClickListener() {// 为时间文本框设置单击监听事件
            @Override
            public void onClick(View arg0) {
                showDialog(DATE_DIALOG_ID);// 显示日期选择对话框
            }
        });
        btnInSaveButton.setOnClickListener(new View.OnClickListener() {// 为保存按钮设置监听事件
            @Override
            public void onClick(View arg0) {

                String strInId = txtInId.getText().toString();// 获取金额文本框的值
                if (!strInId.isEmpty()) {// 判断金额不为空
                    // 创建InaccountDAO对象
                    OutgoodsDAO outgoodsDAO = new OutgoodsDAO(
                            AddOutGoods.this);
                    // 创建Tb_inaccount对象
                    Toast.makeText(AddOutGoods.this, "开始创建数据操做类",
                            Toast.LENGTH_SHORT).show();
                    Tb_outgoods tb_outgoods = new Tb_outgoods(
                            strInId, txtInName.getText().toString(),
                            txtInMoney.getText().toString(),txtInMany.getText().toString(),
                            txtInTime.getText().toString(), spInType.getSelectedItem().toString(),
                            txtInHandler.getText().toString(), txtInMark.getText().toString()
                    );
                    Toast.makeText(AddOutGoods.this, "创建完成，准备插入数据裤",
                            Toast.LENGTH_SHORT).show();
                    outgoodsDAO.add(tb_outgoods);
                    Toast.makeText(AddOutGoods.this, "〖新增进货信息〗添加成功！",
                            Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AddOutGoods.this,MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(AddOutGoods.this, "请编号！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnInCancelButton.setOnClickListener(new View.OnClickListener() {// 为取消按钮设置监听事件
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                txtInId.setText("");
                txtInName.setText("");
                txtInMoney.setText("");// 设置金额文本框为空
                txtInMany.setText("");// 为金额文本框设置提示
                txtInTime.setText("");// 设置时间文本框为空
                txtInTime.setHint("2015-01-01");// 为时间文本框设置提示
                txtInHandler.setText("");// 设置付款方文本框为空
                txtInMark.setText("");// 设置备注文本框为空
                spInType.setSelection(0);// 设置类别下拉列表默认选择第一项
                Intent intent=new Intent(AddOutGoods.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        final Calendar c = Calendar.getInstance();// 获取当前系统日期
        mYear = c.get(Calendar.YEAR);// 获取年份
        mMonth = c.get(Calendar.MONTH);// 获取月份
        mDay = c.get(Calendar.DAY_OF_MONTH);// 获取天数
        updateDisplay();// 显示当前系统时间
    }
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
        txtInTime.setText(new StringBuilder().append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay));
    }
}