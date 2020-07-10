package com.jslink.schedule.requestbody;

import lombok.Data;

import java.util.Date;

@Data
public class RqbUserTime {
    private int id;
    private String userName;
    private int timeSlotId;
    private Date date;
    private boolean isAvailable;
}
