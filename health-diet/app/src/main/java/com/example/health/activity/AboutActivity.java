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

public class AboutActivity extends AppCompatActivity {

    private ImageView ivBack;

    ViewPager vbTips;
    LinearLayout pointLayout;
    List<View> viewList;   //ViewPager的数据源
    int[] picIds = {R.drawable.ic_about1, R.drawable.ic_about2, R.drawable.ic_about3, R.drawable.ic_about4, R.drawable.ic_about5};
    List<ImageView> pointList;   //存放显示器小点点的集合
    private AboutAdapter adapter;

    private LinearLayout llShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ivBack = findViewById(R.id.iv_back);
        vbTips = findViewById(R.id.vp_tips);
        pointLayout = findViewById(R.id.ll_tips_point);
        llShare=findViewById(R.id.ll_share);
        viewList = new ArrayList<>();
        pointList = new ArrayList<>();

        llShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用系统自带的分享功能
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String msg= "保持健康的饮食至关重要。通过了解营养和热量，选择合适食物，提升健康。想了解更多？快来下载饮食指南app吧！";
                intent.putExtra(Intent.EXTRA_TEXT,msg);
                startActivity(Intent.createChooser(intent,"饮食指南分享"));
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
            pointIv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            pointIv.setImageResource(R.drawable.point_round_hint);
            pointList.add(pointIv); //添加到集合当中便于统一管理
            pointLayout.addView(pointIv); //添加到布局当中显示出来
        }
        pointList.get(0).setImageResource(R.drawable.point_round_red);  //设置第一个小圆点为选中状态

        //创建适配器对象
        adapter = new AboutAdapter(viewList);
        //设置适配器
        vbTips.setAdapter(adapter);
        //发送切换页面消息
        handler.sendEmptyMessageDelayed(1, 5000);
        //设置ViewPager页面监听器
        setVPListener();
    }

    private void setVPListener() {
        /* 设置ViewPager的监听器*/
        vbTips.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                //接收到消息之后，需要使ViewPager页面向下滑动一页
                int currentItem = vbTips.getCurrentItem();
                vbTips.setCurrentItem(currentItem + 1);
                //五秒之后再次发送,形成循环
                handler.sendEmptyMessageDelayed(1, 5000);
            }
        }
    };

}
