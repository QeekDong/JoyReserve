package com.joyreserve.reserve.adapter;

import android.content.Context;

import com.joyreserve.reserve.R;
import com.joyreserve.reserve.Utils.TimeTransfer;
import com.joyreserve.reserve.ViewHolder;
import com.joyreserve.reserve.activity.ReserveActivity;
import com.joyreserve.reserve.model.ReserveInfo;

import java.util.List;

/**
 * Created by Cloud on 2017/4/5.
 */

public class MyAdapter extends CommonAdpter<ReserveInfo> {
    private boolean isInTimeBlock;

    public MyAdapter(Context context, List<ReserveInfo> datas, int layoutID) {
        super(context, datas, layoutID);
    }

    @Override
    public void convert(ViewHolder holder, ReserveInfo bean) {
        TimeTransfer timeTransfer = new TimeTransfer();
        isInTimeBlock = timeTransfer.isInTimeBlock(bean.getTime());

        holder.setText(R.id.tv_time, bean.getTime());
        if (bean.getReservation() == null) {
            holder.setText(R.id.tv_name, "")
                    .setText(R.id.tv_department, "")
                    .setText(R.id.tv_phone, "")
                    .setText(R.id.tv_theme, "")
                    .setBackground(R.id.background, R.drawable.bg_list_item);
        } else {
            holder.setText(R.id.tv_name, bean.getReservation().getUsername())
                    .setText(R.id.tv_department, bean.getReservation().getDepartment())
                    .setText(R.id.tv_phone, bean.getReservation().getPhone())
                    .setText(R.id.tv_theme, bean.getReservation().getConference_title());

            if (isInTimeBlock & ReserveActivity.isToday){
                holder.setBackground(R.id.background, R.drawable.bg_list_item2);
            }else {
                holder.setBackground(R.id.background, R.drawable.bg_list_item1);
            }

        }
    }
}
