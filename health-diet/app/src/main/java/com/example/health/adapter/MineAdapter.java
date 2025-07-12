package com.example.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.health.R;
import com.example.health.bean.MenuBean;

import java.util.List;

public class MineAdapter extends BaseAdapter {
    private Context mContext;
    private List<MenuBean> mCaidanList;

    public MineAdapter(Context context, List<MenuBean> caidanList) {
        mContext = context;
        mCaidanList = caidanList;
    }

    @Override
    public int getCount() {
        return mCaidanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCaidanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = convertView;
        ViewHolder holder= null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mine, parent, false);
            holder = new ViewHolder();

            holder.titleTextView = convertView.findViewById(R.id.tv_mine);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String title = mCaidanList.get(position).getTitle(); // 获取title数据
        holder.titleTextView.setText(title);
        return convertView;
    }


//    优化
    private static class ViewHolder {
        TextView titleTextView;
    }
//    更新
    public void setData(List<MenuBean> dataList) {
        mCaidanList = dataList;
        notifyDataSetChanged();
    }
}
