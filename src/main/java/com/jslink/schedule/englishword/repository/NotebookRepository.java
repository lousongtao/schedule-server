package com.jslink.schedule.englishword.repository;

import com.jslink.schedule.englishword.bean.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook, Integer> {
    Notebook findByName(String name);
}
