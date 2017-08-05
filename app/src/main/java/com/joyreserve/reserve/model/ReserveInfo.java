package com.joyreserve.reserve.model;

/**
 * Created by Cloud on 2017/5/15.
 */

public class ReserveInfo {
    private String time;
    private Reservation reservation;

    public ReserveInfo(String time, String username, String phone, String department, String is_attend, String conference_title) {
        this.time = time;
        this.reservation = new Reservation(username, phone, department, is_attend, conference_title);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }


    public class Reservation{

        String username;
        String phone;
        String department;
        String is_attend;
        String conference_title;


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }


        public String getIs_attend() {
            return is_attend;
        }

        public void setIs_attend(String is_attend) {
            this.is_attend = is_attend;
        }

        public String getConference_title() {
            return conference_title;
        }

        public void setConference_title(String conference_title) {
            this.conference_title = conference_title;
        }

        public Reservation(String username, String phone, String department, String is_attend, String conference_title) {
            this.username = username;
            this.phone = phone;
            this.department = department;
            this.is_attend = is_attend;
            this.conference_title = conference_title;
        }

        @Override
        public String toString() {
            return "Reservation{" +
                    "conference_title='" + conference_title + '\'' +
                    ", department='" + department + '\'' +
                    ", phone='" + phone + '\'' +
                    ", username='" + username + '\'' +
                    '}';
        }
    }
}
