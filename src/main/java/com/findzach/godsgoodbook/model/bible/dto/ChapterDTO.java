package com.findzach.godsgoodbook.model.bible.dto;

import java.util.List;

/**
 * @author: Zach Smith
 * @date: 2/15/2024
 * @time: 8:42 AM
 */
public class ChapterDTO {
    private int chapterNumber;
    private List<VerseDTO> verses;

    // Constructor, getters, and setters

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public List<VerseDTO> getVerses() {
        return verses;
    }

    public void setVerses(List<VerseDTO> verses) {
        this.verses = verses;
    }
}
