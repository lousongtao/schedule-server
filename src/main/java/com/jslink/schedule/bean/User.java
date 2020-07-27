package com.jslink.schedule.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private boolean available;
    @Column
    private int shiftTimes; //每周可以排班几次
}
