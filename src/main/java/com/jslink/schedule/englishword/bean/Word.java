package com.jslink.schedule.englishword.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="englishword")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String english;
    private String meaning;
    private String soundmarkUk;
    private String soundmarkUs;
    private int wrongTimes;
    private int rightTimes;
    @ManyToOne
    @JoinColumn(name = "notebook_id")
    private Notebook notebook;
    public Word() {
    }

    public Word(String english, String meaning, String soundmarkUk, String soundmarkUs) {
        this.english = english;
        this.meaning = meaning;
        this.soundmarkUk = soundmarkUk;
        this.soundmarkUs = soundmarkUs;
    }
}
