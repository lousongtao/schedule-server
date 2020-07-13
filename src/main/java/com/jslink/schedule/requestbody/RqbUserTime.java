package com.jslink.schedule.requestbody;

import lombok.Data;

import java.util.Date;

@Data
public class RqbUserTime {
    private int userId;
    private int timeSlotId;
    private String date;
    private boolean available;
}
