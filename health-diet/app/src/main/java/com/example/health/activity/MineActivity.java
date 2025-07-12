package com.example.health.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.health.R;
import com.example.health.adapter.MineAdapter;
import com.example.health.bean.MenuBean;
import com.example.health.sqlite.MenuBusiness;

import java.util.List;

public class MineActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_UPDATE_MINE = 1;
    private ListView lvMenu;
    private TextView tvAdd;
    private ImageView ivBack;

    private MineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        lvMenu = findViewById(R.id.lv_menu);
        tvAdd = findViewById(R.id.tv_add);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //跳转界面
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, MineAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_UPDATE_MINE);
            }
        });


        //获取 SQLite 数据库中的数据
        List<MenuBean> caidanList = MenuBusiness.getAll();
         adapter = new MineAdapter(this, caidanList);
        // 设置 ArrayAdapter 对象为 ListView 的适配器
        lvMenu.setAdapter(adapter);


        //点击适配器
        lvMenu.setOnItemClickListener((adapterView, view, i, l) -> {
            MenuBean menuBean = (MenuBean) adapterView.getItemAtPosition(i);
            //提示框
            AlertDialog.Builder dialog = new AlertDialog.Builder(MineActivity.this);
            dialog.setTitle("请选择操作");
            //删除
            dialog.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String s = MenuBusiness.deleteOne(menuBean);
                    Toast.makeText(MineActivity.this, s, Toast.LENGTH_SHORT).show();

                    // 刷新 ListView
                    List<MenuBean> caidanList = MenuBusiness.getAll();
                    adapter.setData(caidanList);
                    adapter.notifyDataSetChanged();

                }
            });

            //修改
            dialog.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MineActivity.this, MenuUpdateActivity.class);
                    intent.putExtra("id", menuBean.getId());
                    intent.putExtra("title", menuBean.getTitle());
                    intent.putExtra("content", menuBean.getContent());
                    startActivityForResult(intent, REQUEST_CODE_UPDATE_MINE);
                }
            });
            // 显示对话框
            dialog.show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_UPDATE_MINE && resultCode == RESULT_OK) {
            // 重新查询最新的数据，并将其设置为 ListView 的适配器数据
            adapter.setData(MenuBusiness.getAll());
            adapter.notifyDataSetChanged();
        }
    }
}
