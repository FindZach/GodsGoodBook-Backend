package com.findzach.godsgoodbook.model.bible.book;

import com.findzach.godsgoodbook.model.BaseEntity;
import com.findzach.godsgoodbook.model.bible.Bible;
import com.findzach.godsgoodbook.model.bible.chapter.Chapter;
import jakarta.persistence.*;

import java.util.List;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 11:02 PM
 */
@Entity
public class Book extends BaseEntity {

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "book_name")
    private String bookName;

    @ManyToOne
    @JoinColumn(name = "bible_id")
    private Bible bible;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Chapter> chapters;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}
