package com.jslink.schedule.repository;

import com.jslink.schedule.bean.UserTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface UserTimeRepository extends JpaRepository<UserTime, Integer> {
    @Query(value = "select ut from UserTime ut where ut.user.name = :userName and ut.date = :date order by ut.timeSlot.id")
    List<UserTime> findByUserAndDate(String userName, Date date);
}
