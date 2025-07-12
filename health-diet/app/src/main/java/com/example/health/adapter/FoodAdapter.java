package com.example.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.health.R;
import com.example.health.bean.FoodBean;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    Context context;
    List<FoodBean> mDatas;

    public FoodAdapter(Context context, List<FoodBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    //  决定了ListView列表展示的行数
    @Override
    public int getCount() {
        return mDatas.size();
    }

    //返回指定位置对应的数据
    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    // 返回指定位置所对应的id
    @Override
    public long getItemId(int position) {
        return position;
    }

    //  当下滑后不显示的资源在内存中释放空间
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food, null); //将布局转换成view对象的方法
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        加载控件显示的内容
//        获取集合指定位置的数据
        FoodBean foodBean = mDatas.get(position);
        holder.tvTitle.setText(foodBean.getTitle());
        holder.tvNot.setText("不可匹配:" + foodBean.getNotmatch());
        holder.tvDetail.setText(foodBean.getDesc());
        holder.iv.setImageResource(foodBean.getPicId());
        return convertView;

    }

    class ViewHolder {
        ImageView iv;
        TextView tvTitle, tvNot, tvDetail;

        public ViewHolder(View view) {
            iv = view.findViewById(R.id.iv_info);
            tvTitle = view.findViewById(R.id.tv_title);
            tvNot = view.findViewById(R.id.tv_notmatch);
            tvDetail = view.findViewById(R.id.tv_detail);
        }
    }
}
