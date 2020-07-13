package com.jslink.schedule.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "user_time")
@Entity
public class UserTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column
    private boolean available;
}
