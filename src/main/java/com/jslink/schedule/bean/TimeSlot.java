package com.jslink.schedule.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "time_slot")
@Entity
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String displayText;
}
