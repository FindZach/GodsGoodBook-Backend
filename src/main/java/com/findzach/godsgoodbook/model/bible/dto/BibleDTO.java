package com.findzach.godsgoodbook.model.bible.dto;

import java.util.List;

/**
 * @author: Zach Smith
 * @date: 2/15/2024
 * @time: 8:36 AM
 * Simple Transfer object
 */
public class BibleDTO {
    private String translationName;
    private String abbreviatedName;
    private int yearCreated;
    private String lang;
    private String description;
    private String publisher;

    // Constructor, getters, and setters

    private List<BookDTO> books;

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    public void setAbbreviatedName(String abbreviatedName) {
        this.abbreviatedName = abbreviatedName;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTranslationName() {
        return translationName;
    }

    public void setTranslationName(String translationName) {
        this.translationName = translationName;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
