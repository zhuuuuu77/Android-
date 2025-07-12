package com.example.health.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.health.R;
import com.example.health.bean.FoodBean;
import com.example.health.bean.FoodData;
import com.example.health.bean.MenuData;
import com.example.health.adapter.FoodAdapter;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText searchEt;
    private ImageView searchIv,flushIv;
    private ListView showLv;
    //生成ListView内部数据源
    private List<FoodBean>mDatas;
    //全部数据源
    private List<FoodBean>allFoodList;
    private FoodAdapter adapter;

    private ImageView ivBack;


//    匹配查询界面的list集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
//        查找控件
        initView();
//        2.找到ListView对应的数据源
        mDatas = new ArrayList<>();
        allFoodList = FoodData.getAllFoodList();
        mDatas.addAll(allFoodList);
//        3.创建适配器  BaseAdapter的子类
        adapter = new FoodAdapter(this, mDatas);
        showLv.setAdapter(adapter); //4.设置适配器
//        设置单向点击监听功能
        setListener();
    }


    private void setListener() {
        showLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodBean foodBean = mDatas.get(position);
                Intent intent = new Intent(FoodActivity.this, FoodDescActivity.class);
                intent.putExtra("food",foodBean);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        searchEt = findViewById(R.id.et_search);
        searchIv = findViewById(R.id.iv_search);
        flushIv = findViewById(R.id.iv_flush);
        showLv = findViewById(R.id.lv_info);
        ivBack=findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        searchIv.setOnClickListener(this); //添加点击事件的监听器
        flushIv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_flush) {  //刷新点击
            searchEt.setText("");
            mDatas.clear();
            mDatas.addAll(allFoodList);
            adapter.notifyDataSetChanged();
        } else if (id == R.id.iv_search) {  //搜索点击
//                1.获取输入内容，判断不为空
            String msg = searchEt.getText().toString().trim();  //获取输入信息
//                判断输入是否为空
            if (TextUtils.isEmpty(msg)) {
                Toast.makeText(this, "输入内容不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }
//                判断所有食物列表的标题是否包含输入内容，如果包含，就添加到小集合中
            List<FoodBean> list = new ArrayList<>();
            for (int i = 0; i < allFoodList.size(); i++) {
                String title = allFoodList.get(i).getTitle();
//                    title包含输入的内容，则添加集合
                if (title.contains(msg)) {
                    list.add(allFoodList.get(i));
                }
            }
            mDatas.clear();   // 清空ListView的适配器数据源内容
            mDatas.addAll(list);  // 添加新的数据到数据源中
            adapter.notifyDataSetChanged(); // 提示适配器更新
        }else if (id == R.id.iv_back){
            finish();
        }
    }
}
