package com.joyreserve.reserve.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.joyreserve.reserve.ViewHolder;

import java.util.List;

public abstract class CommonAdpter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;
    protected int layoutID;
    protected LayoutInflater mInflater;

    public CommonAdpter(Context context, List<T> datas, int layoutID) {
        this.mContext = context;
        this.mDatas = datas;
        this.layoutID = layoutID;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, layoutID, position);
        convert(holder,getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);
}