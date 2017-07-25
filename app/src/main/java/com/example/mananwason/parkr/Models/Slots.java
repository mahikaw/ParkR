package com.example.mananwason.parkr.Models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by mananwason on 7/25/17.
 */

@IgnoreExtraProperties
public class Slots {
    String uuid;
    String start;
    String end;
    String apartmentNum;

    public Slots() {
    }

    public Slots(String uuid, String start, String end, String apartmentNum) {
        this.uuid = uuid;
        this.start = start;
        this.end = end;
        this.apartmentNum = apartmentNum;
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
}
