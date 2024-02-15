package com.findzach.godsgoodbook.model.bible.chapter;

import com.findzach.godsgoodbook.model.BaseEntity;
import com.findzach.godsgoodbook.model.bible.book.Book;
import com.findzach.godsgoodbook.model.bible.verse.Verse;
import jakarta.persistence.*;

import java.util.List;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 11:04 PM
 */
@Entity
public class Chapter extends BaseEntity {

    @Column(name = "chapter")
    private int chapterNumber;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Verse> verses;

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Verse> getVerses() {
        return verses;
    }

    public void setVerses(List<Verse> verses) {
        this.verses = verses;
    }
}
