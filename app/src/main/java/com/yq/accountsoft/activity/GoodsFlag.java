package com.yq.accountsoft.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yq.accountsoft.dao.FlagDAO;
import com.yq.accountsoft.model.Tb_flag;

import activity.accountsoft.yq.com.accountms.R;

public class GoodsFlag extends Activity {

    private EditText id,name,time,flag;
    Button confirm,cancel;

    @Override
    protected void onRestart() {
        time.setText(Tb_flag.getTime());
        super.onRestart();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_flag);
        id=(EditText)findViewById(R.id.txtFlagId);
        name=(EditText)findViewById(R.id.txtFlagName);
        time=(EditText)findViewById(R.id.txtFlagTime);
        flag=(EditText)findViewById(R.id.txtFlagMark);
        confirm=(Button)findViewById(R.id.btnFlagSave);
        cancel=(Button)findViewById(R.id.btnFlagCancel);
        time.setText(Tb_flag.getTime());
        confirm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                FlagDAO flagDAO=new FlagDAO(GoodsFlag.this);
                if (!id.getText().toString().isEmpty()) {
                    Tb_flag tb_flag = new Tb_flag(id.getText().toString(),
                            name.getText().toString(),
                            Tb_flag.getTime(),
                            flag.getText().toString()
                    );
                    flagDAO.add(tb_flag);
                    Toast.makeText(GoodsFlag.this,"报告主人，添加完毕 么么哒",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(GoodsFlag.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(GoodsFlag.this,"主人请检查标签编号以及其余信息...",Toast.LENGTH_SHORT).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GoodsFlag.this,"主人为何放弃我^_^",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(GoodsFlag.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
