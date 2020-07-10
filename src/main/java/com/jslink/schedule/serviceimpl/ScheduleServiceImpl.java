package com.jslink.schedule.serviceimpl;

import com.jslink.schedule.responsebody.RbSchedule;
import com.jslink.schedule.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Override
    public List<RbSchedule> queryScheduleByMonth(int month) {
        return null;
    }

    @Override
    public List<RbSchedule> queryScheduleByDayRange(String startTime, String endTime) {
        return null;
    }

    @Override
    public List<RbSchedule> arrangeSchedule(Date date, int timeSlotId, String user) {
        return null;
    }

    @Override
    public List<RbSchedule> deleteSchedule(Date date, int timeSlotId, String user) {
        return null;
    }
}
