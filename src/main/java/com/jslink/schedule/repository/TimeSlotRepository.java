package com.jslink.schedule.repository;

import com.jslink.schedule.bean.TimeSlot;
import com.jslink.schedule.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
}
