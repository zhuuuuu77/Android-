package com.example.health.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.health.R;
import com.example.health.utils.SqliteUtils;

public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText checkpassword;
    Button register;
    ImageView ivBack;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db= SqliteUtils.getInstance().getWritableDatabase();
        initView();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getText().toString();
                String pwd=password.getText().toString();
                String pwd2=checkpassword.getText().toString();
                if(!(name.isEmpty()||pwd.isEmpty()||pwd.length()<6)){
                    if (pwd.equals(pwd2)){
                        Cursor cursor=db.rawQuery("select count(*) from user where username = '" + name + "'",null);
                        cursor.moveToNext();
                        int count=cursor.getInt(0);
                        if (count==0){
                            ContentValues values=new ContentValues();
                            values.put("username",name);
                            values.put("password",pwd);
                            db.insert("user",null,values);
                            Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
//                            注册完后将注册线清空
                            username.setText("");
                            password.setText("");
                            checkpassword.setText("");
                            Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "您注册的用户名已存在！",
                                    Toast.LENGTH_SHORT).show();
                        } cursor.close();
                    }else {
                        Toast.makeText(RegisterActivity.this, "您两次输入的密码不一样！",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else if(name.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "用户名不能为空",
                            Toast.LENGTH_SHORT).show();
                }
                else if (pwd.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "密码不能为空",
                            Toast.LENGTH_SHORT).show();
                }else if (pwd.length() <6 ){
                    Toast.makeText(RegisterActivity.this, "密码不能低于6位！", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void initView() {
        username=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        checkpassword=findViewById(R.id.et_password_again);
        register=findViewById(R.id.btn_confirm);
        ivBack =findViewById(R.id.iv_back);
    }
}