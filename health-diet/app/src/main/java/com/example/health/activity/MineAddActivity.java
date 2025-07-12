package com.example.health.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.health.R;
import com.example.health.bean.MenuBean;
import com.example.health.sqlite.MenuBusiness;

public class MineAddActivity extends AppCompatActivity {
    private EditText etTitle, etContent;

    private ImageView ivBack;

    private TextView tvAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_add);
        ivBack = findViewById(R.id.iv_back);
        tvAdd = findViewById(R.id.tv_add);
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuBean menuBean = new MenuBean(-1, etTitle.getText().toString(), etContent.getText().toString());
                String s = MenuBusiness.addOne(menuBean);
                Toast.makeText(MineAddActivity.this, s, Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}

