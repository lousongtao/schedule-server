package com.jslink.schedule.repository;

import com.jslink.schedule.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select u from User u where u.name = :userName and u.available = true")
    User findByName(String userName);
}
