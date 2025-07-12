package com.example.health.activity;

import android.content.Intent;
import android.graphics.Color;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.health.R;
import com.example.health.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity{
    ViewPager vpGuide;
    TextView tv1,tv2,tv3;
    Button btnGuide;
    List<View>viewList;  //ViewPager的数据源
    List<TextView>numList;  //表示页码的集合
    int resId[] = {R.drawable.ic_home_menu_tips1,R.drawable.ic_home_menu_tips2,R.drawable.ic_home_menu_tips3};  //所显示的图片资源数组
    private GuideAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        bindView();
        initView();
    }

    private void bindView() {
        vpGuide = findViewById(R.id.vp_guide);
        tv1 = findViewById(R.id.tv_guide1);
        tv2 = findViewById(R.id.tv_guide2);
        tv3 = findViewById(R.id.tv_guide3);
        btnGuide = findViewById(R.id.btn_guide);
    }

    private void initView() {
        //设置按钮的监听器
        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        viewList = new ArrayList<>();
        numList = new ArrayList<>();
        numList.add(tv1);
        numList.add(tv2);
        numList.add(tv3);
        //初始化ViewPager的页面资源
        for (int j : resId) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_guide1, null);
            ImageView ivImage = view.findViewById(R.id.iv_image);
            ivImage.setImageResource(j);
            viewList.add(view);
        }
        //创建适配器对象
        adapter = new GuideAdapter(viewList);
        //设置适配器
        vpGuide.setAdapter(adapter);
        tv1.setTextColor(Color.RED);
        //设置ViewPager的监听
        setVPListener();
    }

    private void setVPListener() {
        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < numList.size(); i++) {
                    numList.get(i).setTextColor(Color.WHITE);
                }
                numList.get(position).setTextColor(Color.RED);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
