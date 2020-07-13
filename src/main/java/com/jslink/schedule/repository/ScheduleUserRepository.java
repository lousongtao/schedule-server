package com.jslink.schedule.repository;

import com.jslink.schedule.bean.ScheduleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ScheduleUserRepository extends JpaRepository<ScheduleUser, Integer> {
    ScheduleUser findByScheduleDateAndScheduleTimeSlotIdAndUserId(Date date, int timeSlotId, int userId);
}
