package com.jslink.schedule.serviceimpl;

import com.jslink.schedule.bean.TimeSlot;
import com.jslink.schedule.repository.TimeSlotRepository;
import com.jslink.schedule.responsebody.ResponseBody;
import com.jslink.schedule.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Override
    public ResponseBody<List<TimeSlot>> getTimeSlots() {
        List<TimeSlot> tss = timeSlotRepository.findAll();
        return new ResponseBody(true, null, tss);
    }
}
