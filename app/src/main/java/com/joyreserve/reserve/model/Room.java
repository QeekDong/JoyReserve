package com.joyreserve.reserve.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cloud on 2017/5/15.
 */

public class Room implements Parcelable {
    private String room_name;
    private String seats;
    private String devices;
    private String sign_qcode;

    private Room(Parcel in) {
        room_name = in.readString();
        seats = in.readString();
        devices = in.readString();
        sign_qcode = in.readString();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public String getRoomName() {
        return room_name;
    }

    public void setRoomName(String room_name) {
        this.room_name = room_name;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getDevices() {
        return devices;
    }

    public void setDevices(String devices) {
        this.devices = devices;
    }

    public String getSign_qcode() {
        return sign_qcode;
    }

    public void setSign_qcode(String sign_qcode) {
        this.sign_qcode = sign_qcode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(room_name);
        dest.writeString(seats);
        dest.writeString(devices);
        dest.writeString(sign_qcode);
    }
}
