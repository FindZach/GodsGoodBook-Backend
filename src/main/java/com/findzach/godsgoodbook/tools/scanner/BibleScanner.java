package com.findzach.godsgoodbook.tools.scanner;

import com.findzach.godsgoodbook.model.bible.Bible;
import com.findzach.godsgoodbook.tools.BibleScanJob;
import com.findzach.godsgoodbook.web.repository.bible.BibleRepository;
import com.findzach.godsgoodbook.web.repository.bible.BookRepository;
import com.findzach.godsgoodbook.web.repository.bible.ChapterRepository;
import com.findzach.godsgoodbook.web.repository.bible.VerseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 11:15 PM
 */
public abstract class BibleScanner implements BibleScanJob, CommandLineRunner {

    @Autowired
    protected BibleRepository bibleRepository;

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected ChapterRepository chapterRepository;

    @Autowired
    protected VerseRepository verseRepository;

    protected Bible bibleToScan;
    private int totalRecordsProcessed = 0;
    private String bibleState = "Loading...";
    private boolean executing = false;

    @Override
    public int getTotalProcessed() {
        return this.totalRecordsProcessed;
    }

    @Override
    public String getJobState() {
        return this.bibleState;
    }

    @Override
    public void toggleJob(boolean newState) {
        this.executing = newState;
    }

    @Override
    public boolean isJobRunning() {
        return executing;
    }

    public void setTotalRecordsProcessed(int totalRecordsProcessed) {
        this.totalRecordsProcessed = totalRecordsProcessed;
    }

    @Override
    public void setJobState(String bibleState) {
        this.bibleState = bibleState;
    }

    protected abstract void startScan();

    @Override
    public void run(String... args) {
        startScan();
        System.out.println("Completed Data Load... Total Verses Saved: " + verseRepository.count());
        System.out.println("Completed Data Load for " + bibleToScan.getTranslationName() + " Total Books = " + bookRepository.findBooksByBible(bibleToScan).size());
    }

}
