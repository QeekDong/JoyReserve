package com.joyreserve.reserve.model;

/**
 * Created by Cloud on 2017/5/15.
 */

public class RoomResult {
    int errcode;
    String msg;
    Room data;

    public RoomResult(int errcode, String msg, Room data) {
        this.errcode = errcode;
        this.msg = msg;
        this.data = data;
    }

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

    public Room getRoom() {
        return data;
    }

    public void setRoom(Room room) {
        this.data = room;
    }
}
