package com.example.health.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.health.R;
import com.example.health.adapter.AboutAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeMenuActivity extends AppCompatActivity {
    ViewPager tvTips;
    LinearLayout pointLayout;
    List<View> viewList;   //ViewPager的数据源
    int[] picIds = {R.drawable.ic_home_menu_tips1, R.drawable.ic_home_menu_tips2, R.drawable.ic_home_menu_tips3, R.drawable.ic_home_menu_tips4, R.drawable.ic_home_menu_tips5};
    List<ImageView> pointList;   //存放显示器小点点的集合
    private AboutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        tvTips = findViewById(R.id.vp_tips);

        pointLayout = findViewById(R.id.ll_tips_point);

        findViewById(R.id.ll_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMenuActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.ll_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMenuActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.ll_mine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMenuActivity.this, MineActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.ll_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMenuActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });


        viewList = new ArrayList<>();
        pointList = new ArrayList<>();


        //初始化ViewPager的页面信息
        for (int picId : picIds) {
            //创建view添加到集合中
            View view = LayoutInflater.from(this).inflate(R.layout.item_aboutvp, null);
            ImageView iv = view.findViewById(R.id.item_aboutvp_iv);
            iv.setImageResource(picId);
            viewList.add(view);
            //创建指示器内容
            ImageView pointIv = new ImageView(this);
            //在代码中设置控件的宽高和外边距等属性
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 20, 0);
            //将布局参数设置给ImageView
            pointIv.setLayoutParams(lp);
            pointIv.setImageResource(R.drawable.point_round_hint);
            pointList.add(pointIv); //添加到集合当中便于统一管理
            pointLayout.addView(pointIv); //添加到布局当中显示出来
        }
        //设置第一个小圆点为选中状态
        pointList.get(0).setImageResource(R.drawable.point_round_red);


        //创建适配器对象
        adapter = new AboutAdapter(viewList);
        //设置适配器
        tvTips.setAdapter(adapter);
        //发送切换页面消息
        handler.sendEmptyMessageDelayed(1, 5000);
        //设置ViewPager页面监听器
        setVPListener();
    }

    private void setVPListener() {
        /* 设置ViewPager的监听器*/
        tvTips.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //改变颜色为白色
                for (int i = 0; i < pointList.size(); i++) {
                    pointList.get(i).setImageResource(R.drawable.point_round_hint);
                }
                //指示的变为红色
                pointList.get(position % pointList.size()).setImageResource(R.drawable.point_round_red);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                //接收到消息之后，需要使ViewPager页面向下滑动一页
                int currentItem = tvTips.getCurrentItem();
                tvTips.setCurrentItem(currentItem + 1);
                //五秒之后再次发送,形成循环
                handler.sendEmptyMessageDelayed(1, 5000);
            }
        }
    };

}
