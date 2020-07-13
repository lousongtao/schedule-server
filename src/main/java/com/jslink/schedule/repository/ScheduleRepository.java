package com.jslink.schedule.repository;

import com.jslink.schedule.bean.Schedule;
import com.jslink.schedule.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query(value = "select s from Schedule s where s.date >= :startDate and s.date <= :endDate")
    List<Schedule> findByDayRange(Date startDate, Date endDate);

    Schedule findByDateAndTimeSlotId(Date date, int timeSlotId);
}
