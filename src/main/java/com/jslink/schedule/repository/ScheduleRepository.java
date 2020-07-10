package com.jslink.schedule.repository;

import com.jslink.schedule.bean.Schedule;
import com.jslink.schedule.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
