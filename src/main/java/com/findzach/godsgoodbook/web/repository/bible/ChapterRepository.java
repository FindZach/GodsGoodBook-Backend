package com.findzach.godsgoodbook.web.repository.bible;

import com.findzach.godsgoodbook.model.bible.book.Book;
import com.findzach.godsgoodbook.model.bible.chapter.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 11:10 PM
 */
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Chapter findByBookAndChapterNumber(Book book, int chapterNumber);
}
