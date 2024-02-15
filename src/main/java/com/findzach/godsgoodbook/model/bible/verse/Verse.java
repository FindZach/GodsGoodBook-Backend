package com.findzach.godsgoodbook.model.bible.verse;

import com.findzach.godsgoodbook.model.BaseEntity;
import com.findzach.godsgoodbook.model.bible.chapter.Chapter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 11:05 PM
 */
@Entity
public class Verse extends BaseEntity {

    @Column(name = "verse")
    private int verseNumber;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

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

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
