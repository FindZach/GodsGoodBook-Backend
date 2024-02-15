package com.findzach.godsgoodbook.tools.scanner.impl;

import com.findzach.godsgoodbook.model.bible.book.Book;
import com.findzach.godsgoodbook.model.bible.chapter.Chapter;
import com.findzach.godsgoodbook.model.bible.verse.Verse;
import com.findzach.godsgoodbook.tools.scanner.BibleScanner;
import com.findzach.godsgoodbook.web.repository.bible.BookRepository;
import com.findzach.godsgoodbook.web.repository.bible.ChapterRepository;
import com.findzach.godsgoodbook.web.repository.bible.VerseRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 11:20 PM
 * Will read nasb bible and save data to our Database
 */
@Service
public class NASBBibleScanner extends BibleScanner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Call the startScan method directly within the run method
        System.out.println("Scanning..");
        startScan();
    }

    protected void startScan() {
        List<Book> booksToSave = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/static/nasb-raw.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty() || line.trim().equals(".")) {
                    continue;
                }

                String[] parsedString = line.split(" -- ");
                String verseText = parsedString[0];
                String[] parsedBook = parsedString[1].split(" ");
                String bookText = parsedBook[0];
                String[] parsedChapterVerse = parsedBook[1].split(":");
                System.out.println("Line: " + line);
                try {
                    int chapterNumber = Integer.valueOf(parsedChapterVerse[0]);
                    int verseNumber = Integer.valueOf(parsedChapterVerse.length > 1 ? parsedChapterVerse[1] : parsedChapterVerse[0]);


                // Create or retrieve the book entity
                Book book = bookRepository.findByBookName(bookText);
                if (book == null) {
                    book = new Book();
                    book.setBookName(bookText);
                    // Save or persist the book entity before setting its reference in the verse
                    book = bookRepository.save(book); // Ensure that the saved book is assigned back to the 'book' variable
                }

// Create or retrieve the chapter entity
                Chapter chapter = chapterRepository.findByBookAndChapterNumber(book, chapterNumber);
                if (chapter == null) {
                    chapter = new Chapter();
                    chapter.setBook(book);

                    chapter.setChapterNumber(chapterNumber);
                    // Save or persist the chapter entity before setting its reference in the verse
                    chapter = chapterRepository.save(chapter); // Ensure that the saved chapter is assigned back to the 'chapter' variable
                }

// Create the verse entity
                Verse verse = new Verse();
                verse.setChapter(chapter);
                verse.setVerseNumber(verseNumber);
                verse.setText(verseText);

// Save or persist the verse entity
                verseRepository.save(verse);
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save all books and their related entities in bulk within a transaction
        bookRepository.saveAll(booksToSave);
    }
}

