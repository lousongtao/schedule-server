package com.jslink.schedule.responsebody;

import com.jslink.schedule.bean.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RbSchedule {
    private Date date;
    private int timeSlotId;
    private List<String> userNames;

    public RbSchedule(Date date, int timeSlotId, List<String> userNames) {
        this.date = date;
        this.timeSlotId = timeSlotId;
        this.userNames = userNames;
    }
}
