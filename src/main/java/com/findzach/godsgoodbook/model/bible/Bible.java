package com.findzach.godsgoodbook.model.bible;

import com.findzach.godsgoodbook.model.BaseEntity;
import com.findzach.godsgoodbook.model.bible.book.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 10:44 PM
 */
@Entity
public class Bible extends BaseEntity {

    @Column(name = "title")
    private String translationName;

    private String abbreviatedName;

    @OneToMany(mappedBy = "bible", cascade = CascadeType.ALL)
    private List<Book> books;

    public String getTranslationName() {
        return translationName;
    }

    public void setTranslationName(String translationName) {
        this.translationName = translationName;
    }

    public List<Book> getBooks() {
        if (books == null) {
            books = new ArrayList<>(); // Initialize the books collection if not already initialized
        }
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    public void setAbbreviatedName(String abbreviatedName) {
        this.abbreviatedName = abbreviatedName;
    }
}
