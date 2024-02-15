package com.findzach.godsgoodbook.tools.scanner.impl;

import com.findzach.godsgoodbook.model.bible.Bible;
import com.findzach.godsgoodbook.model.bible.book.Book;
import com.findzach.godsgoodbook.model.bible.chapter.Chapter;
import com.findzach.godsgoodbook.model.bible.verse.Verse;
import com.findzach.godsgoodbook.tools.scanner.BibleScanner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 11:20 PM
 * Will read nasb bible and save data to our Database
 */
@Service
public class NASBBibleScanner extends BibleScanner {

    private final String bibleRawURL = "https://raw.githubusercontent.com/FindZach/GodsGoodBook-Backend/develop/src/main/resources/static/bible-data/nasb-raw.txt";


    protected void startScan() {
        toggleJob(true); // Turn to false if no scanning is needed

        if (isJobRunning()) {
            setJobState("Starting to scan");
            List<Book> booksToSave = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(bibleRawURL).openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Skip empty lines
                    if (line.trim().isEmpty() || line.trim().equals(".")) {
                        continue;
                    }

                    String[] parsedString = line.split(" -- ");
                    String verseText = parsedString[0];

                    String newValue = "";

                    if (parsedString[1].contains("1 samuel")) {
                        newValue = parsedString[1].replace("1 samuel", "1-samuel");

                    } else if (parsedString[1].contains("2 samuel")) {
                        newValue = parsedString[1].replace("2 samuel", "2-samuel");

                    } else if (parsedString[1].contains("1 kings")) {
                        newValue = parsedString[1].replace("1 kings", "1-kings");

                    } else if (parsedString[1].contains("2 kings ")) {
                        newValue = parsedString[1].replace("2 kings", "2-kings");

                    } else if (parsedString[1].contains("1 chronicles")) {
                        newValue = parsedString[1].replace("1 chronicles", "1-chronicles");

                    } else if (parsedString[1].contains("2 chronicles")) {
                        newValue = parsedString[1].replace("2 chronicles", "2-chronicles");

                    } else if (parsedString[1].contains("song of solomon")) {
                        newValue = parsedString[1].replace("song of solomon", "song-of-solomon");

                    } else if (parsedString[1].contains("1 corinthians")) {
                        newValue = parsedString[1].replace("1 corinthians", "1-corinthians");

                    } else if (parsedString[1].contains("2 corinthians")) {
                        newValue = parsedString[1].replace("2 corinthians", "2-corinthians");

                    } else if (parsedString[1].contains("1 thessalonians")) {
                        newValue = parsedString[1].replace("1 thessalonians", "1-thessalonians");
                    } else if (parsedString[1].contains("2 thessalonians")) {
                        newValue = parsedString[1].replace("2 thessalonians", "2-thessalonians");

                    } else if (parsedString[1].contains("1 timothy")) {
                        newValue = parsedString[1].replace("1 timothy", "1-timothy");
                    } else if (parsedString[1].contains("2 timothy")) {
                        newValue = parsedString[1].replace("2 timothy", "2-timothy");

                    } else if (parsedString[1].contains("1 peter")) {
                        newValue = parsedString[1].replace("1 peter", "1-peter");
                    } else if (parsedString[1].contains("2 peter")) {
                        newValue = parsedString[1].replace("2 peter", "2-peter");

                        //John books
                    } else if (parsedString[1].contains("1 john")) {
                        newValue = parsedString[1].replace("1 john", "1-john");

                    } else if (parsedString[1].contains("2 john")) {
                        newValue = parsedString[1].replace("2 john", "2-john");

                    } else if (parsedString[1].contains("3 john")) {
                        newValue = parsedString[1].replace("3 john", "3-john");

                    }

                    if (!newValue.isBlank()) {
                        parsedString[1] = newValue;
                    }

                    String[] parsedBook = parsedString[1].split(" ");
                    String bookText = parsedBook[0];
                    String[] parsedChapterVerse = parsedBook[1].split(":");
                   // System.out.println("Line: " + line);
                    try {
                        int chapterNumber = Integer.parseInt(parsedChapterVerse[0]);
                        int verseNumber = Integer.parseInt(parsedChapterVerse.length > 1 ? parsedChapterVerse[1] : parsedChapterVerse[0]);

                        Bible bible = bibleRepository.findBibleByAbbreviatedName("nasb");
                        if (bible == null) {
                            bible = new Bible();
                            bible.setTranslationName("New American Standard Bible");
                            bible.setAbbreviatedName("nasb");
                            bible = bibleRepository.save(bible);

                            this.bibleToScan = bible;
                        }

                        // Create or retrieve the book entity
                        Book book = bookRepository.findByBookName(bookText);
                        if (book == null) {
                            book = new Book();
                            book.setBookName(bookText);
                            book.setBible(bible);
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
                        verse = verseRepository.save(verse);

                    } catch (Exception e) {
                        System.out.println("Cannot load " + parsedString[1]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(); // Log the exception for debugging purposes
            }

            // Save all books and their related entities in bulk within a transaction
            bookRepository.saveAll(booksToSave);
        }
    }

}

