package com.joyreserve.reserve;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private SparseArray<View> views;
    private int position;
    private View convertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutID, int position) {

        this.position = position;
        this.views = new SparseArray<>();
        convertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
        convertView.setTag(this);

    }
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutID, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutID, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.position = position;
            return holder;
        }
    }

    //通过viewID获取控件
    public <T extends View> T getView(int viewID) {
        View view = views.get(viewID);
        if (view == null) {
            view = convertView.findViewById(viewID);
            views.put(viewID, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return convertView;
    }

    //设置TextView的值
    public ViewHolder setText(int viewID, String text) {
        TextView tv = getView(viewID);
        tv.setText(text);
        return this;
    }

    //设置ImageView的值
    public ViewHolder setImage(int viewID, int icon) {
        ImageView imageView = getView(viewID);
        imageView.setImageResource(icon);
        return this;
    }

    public ViewHolder setBackground(int viewID, int color) {
        PercentRelativeLayout prlItem = getView(viewID);
        prlItem.setBackgroundResource(color);
        return this;
    }
}