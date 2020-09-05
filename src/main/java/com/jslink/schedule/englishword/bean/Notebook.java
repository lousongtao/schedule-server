package com.jslink.schedule.englishword.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "englishword_notebook")
@JsonIgnoreProperties(value = {"words"})
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany
    @JoinColumn(name = "notebook_id")
    private List<Word> words;
}
