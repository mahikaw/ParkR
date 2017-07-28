package com.example.mananwason.parkr.Models;

/**
 * Created by mananwason on 7/25/17.
 */

public class GuestBooking {
    private String uuid;
    private String start;
    private String end;
    private String apartmentNum;
    private String guestName;
    private String carNo;
    private String guestPhoneNumber;

    public GuestBooking() {
    }

    public GuestBooking(String uuid, String start, String end, String apartmentNum, String guestName, String carNo, String guestPhoneNumber) {
        this.uuid = uuid;
        this.start = start;
        this.end = end;
        this.apartmentNum = apartmentNum;
        this.guestName = guestName;
        this.carNo = carNo;
        this.guestPhoneNumber = guestPhoneNumber;
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

    public String getGuestPhoneNumber() {
        return guestPhoneNumber;
    }

    public void setGuestPhoneNumber(String guestPhoneNumber) {
        this.guestPhoneNumber = guestPhoneNumber;
    }
}