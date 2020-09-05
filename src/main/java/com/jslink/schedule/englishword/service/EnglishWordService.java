package com.jslink.schedule.englishword.service;

import com.jslink.schedule.englishword.bean.Word;
import com.jslink.schedule.responsebody.ResponseBody;

import java.util.List;

public interface EnglishWordService {
    ResponseBody addNotebook(String name);

    ResponseBody<List<Word>> getWords(int notebookId);

    ResponseBody addWord(String english, String chinese, String soundmarkUk, String soundmarkUs, int notebookId);

    ResponseBody testWord(int id, boolean isRight);

    ResponseBody importWords(String words, int notebookId);
}
