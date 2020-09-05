package com.jslink.schedule.englishword.service;

import com.jslink.schedule.englishword.bean.Notebook;
import com.jslink.schedule.englishword.repository.NotebookRepository;
import com.jslink.schedule.englishword.repository.WordRepository;
import com.jslink.schedule.englishword.bean.Word;
import com.jslink.schedule.responsebody.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Transactional
public class EnglishWordServiceImpl implements EnglishWordService {
    @Autowired
    private WordRepository wordRepository;
    @Autowired
    private NotebookRepository notebookRepository;

    @Override
    public ResponseBody addNotebook(String name) {
        Notebook notebook = new Notebook();
        notebook.setName(name);
        notebookRepository.save(notebook);
        return new ResponseBody(true);
    }

    @Override
    public ResponseBody<List<Word>> getWords(int notebookId) {
        List<Word> words = wordRepository.findByNotebookId(notebookId);
        return new ResponseBody<>(true, null, words);
    }

    @Override
    public ResponseBody addWord(String english, String chinese, String soundmarkUk, String soundmarkUs, int notebookId) {
        Notebook notebook = notebookRepository.getOne(notebookId);
        Word word = new Word(english, chinese, soundmarkUk, soundmarkUs);
        word.setNotebook(notebook);
        wordRepository.save(word);
        return new ResponseBody(true);
    }

    @Override
    public ResponseBody testWord(int id, boolean isRight) {
        Optional<Word> optword = wordRepository.findById(id);
        if (optword.isPresent()){
            Word word = optword.get();
            if (isRight)
                word.setRightTimes(word.getRightTimes() + 1);
            else
                word.setWrongTimes(word.getWrongTimes() + 1);
            wordRepository.save(word);
        }
        return new ResponseBody(true);
    }

    @Override
    public ResponseBody importWords(String words, int notebookId) {
        Notebook notebook = notebookRepository.findById(notebookId).get();
        String[] wordlist = words.split("\n");
        Arrays.stream(wordlist).forEach(w ->{
            Word word = new Word();
            word.setEnglish(w);
            word.setNotebook(notebook);
            wordRepository.save(word);
        });
        new Thread(() -> downloadWordsInfo(wordlist)).start();
        return new ResponseBody(true);
    }

    private void downloadWordsInfo(String[] wordlist){
        try {
            Thread.sleep(2000);//wait 2s for db persistent
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(String word: wordlist){
            executor.submit(() ->{
                try {
                    netQueryWordByJSoup(word);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private final static String CAMBRIDGE = "https://dictionary.cambridge.org";
    public void netQueryWordByJSoup(String word) throws IOException {
        List<Word> dbwords = wordRepository.findByEnglish(word);
        if (dbwords == null || dbwords.isEmpty()) return;
        Word dbword = dbwords.get(0);
        Document doc = Jsoup.connect(CAMBRIDGE + "/dictionary/english/"+word).get();
        Elements esBody = doc.getElementsByClass("pos-header dpos-h");
        if (esBody == null || esBody.isEmpty()){
            System.out.println("word " + word + " cannot find on dictionary.");
            return;
        }
        Element element = esBody.first();
        Elements esUK = element.getElementsByClass("uk");
        if (esUK != null && !esUK.isEmpty()){
//            Elements esMp3 = esUK.first().getElementsByTag("source");
//            if (esMp3 != null && !esMp3.isEmpty()){
//                for (int i = 0; i < esMp3.size(); i++) {
//                    Element eSrc = esMp3.get(i);
//                    if ("audio/mpeg".equals(eSrc.attr("type"))){
//                        OperateFile.fileDown(word, CAMBRIDGE + eSrc.attr("src"), word + "-uk.mp3");
//                        break;
//                    }
//                }
//            }
            Elements esPron = esUK.first().getElementsByClass("pron dpron");
            if (esPron != null && !esPron.isEmpty()){
                dbword.setSoundmarkUk(esPron.first().text());
            }
        }
        Elements esUS = element.getElementsByClass("us");
        if (esUS != null && !esUS.isEmpty()){
//            Elements esMp3 = esUS.first().getElementsByTag("source");
//            if (esMp3 != null && !esMp3.isEmpty()){
//                for (int i = 0; i < esMp3.size(); i++) {
//                    Element eSrc = esMp3.get(i);
//                    if ("audio/mpeg".equals(eSrc.attr("type"))){
//                        OperateFile.fileDown(word, CAMBRIDGE + eSrc.attr("src"), word + "-us.mp3");
//                        break;
//                    }
//                }
//            }
            Elements esPron = esUS.first().getElementsByClass("pron dpron");
            if (esPron != null && !esPron.isEmpty()){
                dbword.setSoundmarkUs(esPron.first().text());
            }
        }
        wordRepository.save(dbword);
    }
}
