package com.jslink.schedule.serviceimpl;

import com.jslink.schedule.bean.Schedule;
import com.jslink.schedule.bean.ScheduleUser;
import com.jslink.schedule.bean.User;
import com.jslink.schedule.repository.ScheduleRepository;
import com.jslink.schedule.repository.ScheduleUserRepository;
import com.jslink.schedule.repository.UserRepository;
import com.jslink.schedule.responsebody.RbSchedule;
import com.jslink.schedule.responsebody.RbTimeSlot;
import com.jslink.schedule.responsebody.ResponseBody;
import com.jslink.schedule.service.ScheduleService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScheduleUserRepository scheduleUserRepository;
    @Override
    public List<RbSchedule> queryScheduleByMonth(int month) {
        return null;
    }

    @Override
    public ResponseBody<List<RbSchedule>> queryScheduleByDayRange(Date startDate, Date endDate) {
        List<Schedule> schedules = scheduleRepository.findByDayRange(startDate, endDate);
        List<RbSchedule> rbSchedules = schedules.stream().map(s -> {
            List<ScheduleUser> scheduleUsers = s.getScheduleUsers();
            List<String> userNames = scheduleUsers.stream().map(su -> su.getUser().getName()).collect(Collectors.toList());
            RbSchedule rs = new RbSchedule(s.getDate(), s.getTimeSlot().getId(), userNames);
            return rs;
        }).collect(Collectors.toList());
        return new ResponseBody<>(true, null, rbSchedules);
    }

    @Override
    public ResponseBody arrangeSchedule(Date date, int timeSlotId, int userId) {
        Schedule schedule = scheduleRepository.findByDateAndTimeSlotId(date, timeSlotId);
        User user = userRepository.findById(userId).get();
        ScheduleUser su = new ScheduleUser();
        su.setSchedule(schedule);
        su.setUser(user);
        scheduleUserRepository.save(su);
        return new ResponseBody(true);
    }

    @Override
    public ResponseBody deleteSchedule(Date date, int timeSlotId, int userId) {
        ScheduleUser su = scheduleUserRepository.findByScheduleDateAndScheduleTimeSlotIdAndUserId(date, timeSlotId, userId);
        if (su == null)
            throw new RuntimeException("cannot find this user's schedule "+ date + ", timeSlotId = "+timeSlotId +", userId = " + userId);
        scheduleUserRepository.delete(su);
        return new ResponseBody(true);
    }
}
