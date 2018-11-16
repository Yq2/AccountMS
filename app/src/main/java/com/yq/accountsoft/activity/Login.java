package com.yq.accountsoft.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yq.accountsoft.dao.PwdDAO;

import activity.accountsoft.yq.com.accountms.R;

public class Login extends AppCompatActivity {

    public static final String[] data=new String[]{"杨强","三清道长","王尼玛"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final AutoCompleteTextView autoCompleteTextView= (AutoCompleteTextView) findViewById(R.id.account_login);
        final EditText editText= (EditText) findViewById(R.id.password_login);
        Button confirm= (Button) findViewById(R.id.confirm_login);
        Button cancel= (Button) findViewById(R.id.cancel_login);
        ArrayAdapter<String> arrayAdapter=
                new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,data);
        autoCompleteTextView.setAdapter(arrayAdapter);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,MainActivity.class);
                PwdDAO pwdDAO=new PwdDAO(Login.this);
                if (pwdDAO.getCount()==0) {
                    Toast.makeText(Login.this, "初始使用默认无密码，请及时进入【设置】修改密码", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    if (pwdDAO.find().getAccount().equals(autoCompleteTextView.getText().toString())&&pwdDAO.find().getPassword().equals(editText.getText().toString()))
                        startActivity(intent);
                    else {
                        Toast.makeText(Login.this, "用户名和密码不一致", Toast.LENGTH_SHORT).show();
                        editText.setText("");
                        autoCompleteTextView.setText("");
                    }
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
