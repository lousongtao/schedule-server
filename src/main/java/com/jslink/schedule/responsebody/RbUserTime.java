package com.jslink.schedule.responsebody;

import lombok.Data;

@Data
public class RbUserTime {
    private String date;
    private int timeSlotId;
    private boolean available;
    private int userId;
    private String userName;

    public RbUserTime(String date, int timeSlotId, int userId, String userName, boolean available) {
        this.date = date;
        this.timeSlotId = timeSlotId;
        this.available = available;
        this.userId = userId;
        this.userName = userName;
    }
}
