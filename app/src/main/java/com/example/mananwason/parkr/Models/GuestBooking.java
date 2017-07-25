package com.example.mananwason.parkr.Models;

/**
 * Created by mananwason on 7/25/17.
 */

public class GuestBooking {
    String uuid;
    String start;
    String end;
    String apartmentNum;
    String guestName;
    String carNo;

    public GuestBooking() {
    }

    public GuestBooking(String uuid, String start, String end, String apartmentNum, String guestName, String carNo) {
        this.uuid = uuid;
        this.start = start;
        this.end = end;
        this.apartmentNum = apartmentNum;
        this.guestName = guestName;
        this.carNo = carNo;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getApartmentNum() {
        return apartmentNum;
    }

    public void setApartmentNum(String apartmentNum) {
        this.apartmentNum = apartmentNum;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
}