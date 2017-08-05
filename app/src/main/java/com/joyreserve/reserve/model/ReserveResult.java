package com.joyreserve.reserve.model;

import java.util.List;

/**
 * Created by Cloud on 2017/5/15.
 */

public class ReserveResult {
    private int errcode;
    private String msg;
    private List<ReserveInfo> states;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ReserveInfo> getReserveInfoList() {
        return states;
    }

    public void setReserveInfoList(List<ReserveInfo> states) {
        this.states = states;
    }
}
