package com.jslink.schedule.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "schedule_user")
public class ScheduleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
