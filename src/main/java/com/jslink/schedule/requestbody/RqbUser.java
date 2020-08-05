package com.jslink.schedule.requestbody;

import lombok.Data;

@Data
public class RqbUser {
    private int id;
    private String name;
    private String password;
    private int shiftTimes;
    private boolean available;
}
