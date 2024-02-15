package com.findzach.godsgoodbook.model.bible;

import com.findzach.godsgoodbook.model.BaseEntity;
import com.findzach.godsgoodbook.model.bible.book.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

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

    @OneToMany(mappedBy = "bible", cascade = CascadeType.ALL)
    private List<Book> books;

    public String getTranslationName() {
        return translationName;
    }

    public void setTranslationName(String translationName) {
        this.translationName = translationName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
