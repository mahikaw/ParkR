package com.example.mananwason.parkr.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by mananwason on 7/25/17.
 */

@IgnoreExtraProperties
public class Slots implements Serializable {
    String uuid;
    String start;
    String end;
    String apartmentNum;
    int floor;

    public Slots() {
    }

    public Slots(String uuid, String start, String end, String apartmentNum, int floor) {
        this.uuid = uuid;
        this.start = start;
        this.end = end;
        this.apartmentNum = apartmentNum;
        this.floor = floor;
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

    public String getFloor() {
        switch (floor) {
            case 0:
                return "GROUND FLOOR";
            case 1:
                return "FIRST FLOOR";
            case 2:
                return "SECOND FLOOR";
            case 3:
                return "THIRD FLOOR";
        }
        return "GROUND FLOOR";
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
