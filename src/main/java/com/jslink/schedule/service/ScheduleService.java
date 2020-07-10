package com.jslink.schedule.service;

import com.jslink.schedule.responsebody.RbSchedule;

import java.util.Date;
import java.util.List;

public interface ScheduleService {
    List<RbSchedule> queryScheduleByMonth(int month);

    List<RbSchedule> queryScheduleByDayRange(String startTime, String endTime);

    List<RbSchedule> arrangeSchedule(Date date, int timeSlotId, String user);

    List<RbSchedule> deleteSchedule(Date date, int timeSlotId, String user);
}
