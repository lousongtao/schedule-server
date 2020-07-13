package com.jslink.schedule.repository;

import com.jslink.schedule.bean.UserTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface UserTimeRepository extends JpaRepository<UserTime, Integer> {
    @Query(value = "select ut from UserTime ut where (:userId = 0 or ut.user.id = :userId) and ut.date >= :startDate and ut.date <= :endDate")
    List<UserTime> findByUserAndDateRange(int userId, Date startDate, Date endDate);

    @Query(value = "select ut from UserTime ut where ut.user.id = :userId and ut.date = :date and ut.timeSlot.id = :timeSlotId")
    UserTime findByDateAndUserIdAndTimeSlotId(Date date, int userId, int timeSlotId);
}
