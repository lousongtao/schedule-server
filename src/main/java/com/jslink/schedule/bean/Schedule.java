package com.jslink.schedule.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "schedule")
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Date date;
}
