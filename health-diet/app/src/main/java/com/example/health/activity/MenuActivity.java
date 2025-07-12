package com.example.health.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.health.R;
import com.example.health.bean.FoodBean;
import com.example.health.bean.MenuData;
import com.example.health.adapter.MenuAdapter;

import java.util.List;

public class MenuActivity extends AppCompatActivity {
    GridView gv;
    List<FoodBean>mDatas;
    private MenuAdapter adapter;

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        gv = findViewById(R.id.gv_menu);
        ivBack =findViewById(R.id.iv_back);
//        数据源
        mDatas = MenuData.getAllFoodList();
//        创建适配器对象
        adapter = new MenuAdapter(this, mDatas);
//        设置适配器
        gv.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodBean foodBean = mDatas.get(position);
                Intent intent = new Intent(MenuActivity.this, MenuDescActivity.class);
                intent.putExtra("food",foodBean);
                startActivity(intent);
            }
        });
    }
}
