package com.jslink.schedule.englishword.repository;

import com.jslink.schedule.englishword.bean.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Integer> {
    List<Word> findByNotebookId(int notebookId);

    List<Word> findByEnglish(String word);
}
