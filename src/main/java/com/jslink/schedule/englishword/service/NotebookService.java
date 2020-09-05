package com.jslink.schedule.englishword.service;

import com.jslink.schedule.englishword.bean.Notebook;
import com.jslink.schedule.responsebody.ResponseBody;

import java.util.List;

public interface NotebookService {
    ResponseBody<List<Notebook>> getNotebooks();

    ResponseBody saveNotebook(String name);
}
