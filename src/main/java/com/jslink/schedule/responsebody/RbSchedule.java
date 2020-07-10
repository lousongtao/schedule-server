package com.jslink.schedule.responsebody;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RbSchedule {
    private Date date;
    private List<RbTimeSlot> timeSlots;
}
