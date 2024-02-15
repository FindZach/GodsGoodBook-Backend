package com.findzach.godsgoodbook.model.bible.dto;

/**
 * @author: Zach Smith
 * @date: 2/15/2024
 * @time: 8:43 AM
 */
public class VerseDTO {
    private int verseNumber;
    private String text;

    // Constructor, getters, and setters

    public int getVerseNumber() {
        return verseNumber;
    }

    public void setVerseNumber(int verseNumber) {
        this.verseNumber = verseNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}