package com.jslink.schedule.englishword.controller;

import com.jslink.schedule.englishword.bean.Notebook;
import com.jslink.schedule.englishword.bean.Word;
import com.jslink.schedule.englishword.service.EnglishWordService;
import com.jslink.schedule.englishword.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jslink.schedule.responsebody.ResponseBody;

import java.util.List;

@RestController
@RequestMapping("/englishword")
public class EnglishWordController {

    @Autowired
    private EnglishWordService englishWordService;
    @Autowired
    private NotebookService notebookService;

    @GetMapping("/words/notebook/{notebookId}")
    public ResponseBody<List<Word>> getWords(@PathVariable int notebookId){
        return englishWordService.getWords(notebookId);
    }

    @GetMapping("/notebooks")
    public ResponseBody<List<Notebook>> getNotebook(){
        return notebookService.getNotebooks();
    }

    @PostMapping("/notebook")
    public ResponseBody save(@RequestParam String name){
        return notebookService.saveNotebook(name);
    }

    @PostMapping("/add")
    public ResponseBody addWord(@RequestParam String english,
                                @RequestParam(required = false) String chinese,
                                @RequestParam(required = false) String soundmarkUk,
                                @RequestParam(required = false) String soundmarkUs,
                                @RequestParam int notebookId){
        return englishWordService.addWord(english, chinese, soundmarkUk, soundmarkUs, notebookId);
    }

    @PostMapping("/importwords")
    public ResponseBody addWord(@RequestParam int notebookId,
                                @RequestParam String words){
        return englishWordService.importWords(words, notebookId);
    }

    @PostMapping("/test")
    public ResponseBody testWord(@RequestParam int id,
                                @RequestParam boolean isRight){
        return englishWordService.testWord(id, isRight);
    }
}
