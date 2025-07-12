package com.example.health.activity;

import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.health.R;
import com.example.health.utils.SqliteUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

//        登录按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getText().toString();
                String pwd=password.getText().toString();

                Cursor cursor= SqliteUtils.getInstance().getReadableDatabase().query("user",null,"username=? and password=?",new String[]{name,pwd},null,null,null);
                ArrayList<Map<String,String>> resultList=new ArrayList<Map<String,String>>();
                while (cursor.moveToNext()){
                    Map<String,String> map=new HashMap<String,String>();
                    map.put("name",cursor.getString(1));
                    resultList.add(map);
                }
                if (resultList.isEmpty()){
                    Toast.makeText(LoginActivity.this,"账户或密码错误!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this,"登录成功!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this, HomeMenuActivity.class);
                    intent.putExtra("username",name);
                    startActivity(intent);
                    finish();
                }
            }
        });
//        注册按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }



    private void initView() {
        username=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        login=findViewById(R.id.btn_login);
        register=findViewById(R.id.btn_register);
    }

}