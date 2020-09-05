package com.jslink.schedule.englishword.service;

import com.jslink.schedule.englishword.bean.Notebook;
import com.jslink.schedule.englishword.repository.NotebookRepository;
import com.jslink.schedule.responsebody.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotebookServiceImpl implements NotebookService {
    @Autowired
    private NotebookRepository notebookRepository;
    @Override
    public ResponseBody<List<Notebook>> getNotebooks() {
        List<Notebook> notebooks = notebookRepository.findAll();
        return new ResponseBody<>(true, null, notebooks);
    }

    @Override
    public ResponseBody saveNotebook(String name) {
        Notebook notebook = notebookRepository.findByName(name);
        if (notebook != null)
            return new ResponseBody(false, "name duplicate");
        notebook = new Notebook();
        notebook.setName(name);
        notebook = notebookRepository.save(notebook);
        return new ResponseBody(true, null, notebook);
    }
}
