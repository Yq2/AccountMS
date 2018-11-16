package com.yq.accountsoft.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yq.accountsoft.dao.PwdDAO;
import com.yq.accountsoft.model.Tb_pwd;

import activity.accountsoft.yq.com.accountms.R;

public class SystemSet extends Activity {
    EditText account,password;
    Button confirm,cancel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_set);
        account=(EditText)findViewById(R.id.txtaccount_sys);
        password= (EditText) findViewById(R.id.txtpassword_sys);
        confirm=(Button)findViewById(R.id.confirm_sys);
        cancel= (Button) findViewById(R.id.cancel_sys);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PwdDAO pwdDAO=new PwdDAO(SystemSet.this);
                if ((account.getText().toString().isEmpty())|(password.getText().toString().isEmpty()))
                {
                    Toast.makeText(SystemSet.this, "请检查用户名或者密码", Toast.LENGTH_SHORT).show();
                    account.setText("");
                    password.setText("");
                }

                Tb_pwd tb_pwd = new Tb_pwd(account.getText().toString(), password.getText().toString());
                pwdDAO.add(tb_pwd);
                Toast.makeText(SystemSet.this, "添加成功!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SystemSet.this,MainActivity.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SystemSet.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
