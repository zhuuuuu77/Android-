package com.example.health.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.health.R;
import com.example.health.bean.FoodBean;

public class MenuDescActivity extends AppCompatActivity {

    TextView titleTv1,titleTv2,descTv,notTv;
    ImageView backIv,bigPicIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_desc);
        initView();
//        接受上一级页面传来的数据
        Intent intent = getIntent();
        FoodBean foodBean1 = (FoodBean) intent.getSerializableExtra("food");
//        设置显示控件
        titleTv1.setText(foodBean1.getTitle());
        titleTv2.setText(foodBean1.getTitle());
        descTv.setText(foodBean1.getDesc());
        notTv.setText(foodBean1.getNotmatch());
        bigPicIv.setImageResource(foodBean1.getPicId());
    }

    private void initView() {
        titleTv1 = findViewById(R.id.tv_title);
        titleTv2 = findViewById(R.id.tv_food_name);
        descTv = findViewById(R.id.tv_food_desc);
        notTv = findViewById(R.id.tv_food_not);
        backIv = findViewById(R.id.iv_back);
        bigPicIv = findViewById(R.id.iv_big_pic);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();   //销毁当前的activity
            }
        });
    }
}