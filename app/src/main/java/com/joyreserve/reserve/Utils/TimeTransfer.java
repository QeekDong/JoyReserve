package com.joyreserve.reserve.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JD on 2017/8/4.
 * 主要用于处理服务器返回的时间比较
 */

public class TimeTransfer {
    public TimeTransfer(){

    }


    public boolean isInTimeBlock(String timeBlock) {
//	设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String strDate = sdf.format(date);
        System.out.println(strDate);

        String strDateBegin = timeBlock.substring(0, 5)+":00";
        String strDateEnd = timeBlock.substring(6, 11)+":00";
//	System.out.println(strDateBegin);
//	System.out.println(strDateEnd);

        // 截取当前时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
//	System.out.println("当前小时" + String.valueOf(strDateH));

        int strDateM = Integer.parseInt(strDate.substring(14, 16));
//	System.out.println("当前分钟 " + String.valueOf(strDateM));

        int strDateS = Integer.parseInt(strDate.substring(17, 19));
//	System.out.println("当前秒钟 " + String.valueOf(strDateS));

        // 截取开始时间时分秒

        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
//	System.out.println("开始小时" + String.valueOf(strDateBeginH));

        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
//	System.out.println("开始分钟" + String.valueOf(strDateBeginM));


        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
//	System.out.println("开始秒钟" + String.valueOf(strDateBeginS));


        // 截取结束时间时分秒

        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
//	System.out.println("结束秒钟" + String.valueOf(strDateEndH));

        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
//	System.out.println("结束秒钟" + String.valueOf(strDateEndM));

        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
//	System.out.println("结束秒钟" + String.valueOf(strDateEndS));



//	判断是否在这个时间段内

        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {

            // 当前时间小时数在开始时间和结束时间小时数之间

            if (strDateH > strDateBeginH && strDateH < strDateEndH) {

                return true;

                // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间

            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM

                    && strDateM <= strDateEndM) {

                return true;

                // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间

            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM

                    && strDateS >= strDateBeginS && strDateS <= strDateEndS) {

                return true;

            }

            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数

            else if (strDateH >= strDateBeginH && strDateH == strDateEndH

                    && strDateM <= strDateEndM) {

                return true;

                // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数

            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH

                    && strDateM == strDateEndM && strDateS <= strDateEndS) {

                return true;

            } else {

                return false;

            }

        } else {

            return false;

        }

    }


}
