package com.jslink.schedule.service;

import com.jslink.schedule.bean.TimeSlot;
import com.jslink.schedule.responsebody.ResponseBody;

import java.util.List;

public interface CommonService {
    ResponseBody<List<TimeSlot>> getTimeSlots();
}
