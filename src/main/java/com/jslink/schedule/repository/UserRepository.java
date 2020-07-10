package com.jslink.schedule.repository;

import com.jslink.schedule.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String userName);
}
