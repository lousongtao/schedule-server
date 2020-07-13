package com.jslink.schedule.service;

import com.jslink.schedule.responsebody.RbSchedule;
import com.jslink.schedule.responsebody.ResponseBody;

import java.util.Date;
import java.util.List;

public interface ScheduleService {
    List<RbSchedule> queryScheduleByMonth(int month);

    ResponseBody<List<RbSchedule>> queryScheduleByDayRange(Date startDate, Date endDate);

    ResponseBody arrangeSchedule(Date date, int timeSlotId, int userId);

    ResponseBody deleteSchedule(Date date, int timeSlotId, int userId);
}
