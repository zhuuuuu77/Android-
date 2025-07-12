package com.example.health.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.health.R;
import com.example.health.adapter.MineAdapter;
import com.example.health.bean.MenuBean;
import com.example.health.sqlite.MenuBusiness;

import java.util.List;

public class MenuUpdateActivity extends AppCompatActivity {
    private EditText etTitle, etContent;

    private ImageView ivBack;

    private TextView tvUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_update);
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        tvUpdate = findViewById(R.id.tv_update);
        ivBack = findViewById(R.id.iv_back);

        Intent intent=getIntent();
        int id=intent.getIntExtra("id",0);
        String title=intent.getStringExtra("title");
        String content=intent.getStringExtra("content");
        etTitle.setText(title);
        etContent.setText(content);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuBean menuBean =new MenuBean(id, etTitle.getText().toString(), etContent.getText().toString());
                MenuBusiness.updateOne(menuBean);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}