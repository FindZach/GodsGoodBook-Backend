package com.findzach.godsgoodbook.model.bible.dto;

import java.util.List;

/**
 * @author: Zach Smith
 * @date: 2/15/2024
 * @time: 8:42 AM
 */
public class BookDTO {
    private String bookName;
    private List<ChapterDTO> chapters;

    // Constructor, getters, and setters

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<ChapterDTO> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterDTO> chapters) {
        this.chapters = chapters;
    }
}