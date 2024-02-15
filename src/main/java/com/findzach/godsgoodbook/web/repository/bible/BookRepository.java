package com.findzach.godsgoodbook.web.repository.bible;

import com.findzach.godsgoodbook.model.bible.Bible;
import com.findzach.godsgoodbook.model.bible.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 11:09 PM
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookName(String bookName);

    List<Book> findBooksByBible(Bible bible);
}
