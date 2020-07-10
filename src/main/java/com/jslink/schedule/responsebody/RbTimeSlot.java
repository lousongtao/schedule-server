package com.jslink.schedule.responsebody;

import com.jslink.schedule.bean.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RbTimeSlot {
    private int timeSlotId;
    private String timeSlot;
    private List<User> scheduledUsers = new ArrayList<>();//已经排版的人员
}
