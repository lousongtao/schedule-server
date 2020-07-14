package com.jslink.schedule.responsebody;

import com.jslink.schedule.bean.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RbSchedule {
    private Date date;
    private int timeSlotId;
    private List<Integer> userIds;

    public RbSchedule(Date date, int timeSlotId, List<Integer> userIds) {
        this.date = date;
        this.timeSlotId = timeSlotId;
        this.userIds = userIds;
    }
}
