package com.findzach.godsgoodbook.tools.scanner.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.findzach.godsgoodbook.model.bible.Bible;
import com.findzach.godsgoodbook.model.bible.book.Book;
import com.findzach.godsgoodbook.model.bible.chapter.Chapter;
import com.findzach.godsgoodbook.model.bible.verse.Verse;
import com.findzach.godsgoodbook.tools.scanner.BibleScanner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zach Smith
 * @date: 2/15/2024
 * @time: 7:59 AM
 */
@Service
public class BishopsBibleScanner extends BibleScanner {

    private final String bibleRawURL = "https://raw.githubusercontent.com/FindZach/GodsGoodBook-Backend/develop/src/main/resources/static/bible-data/bishops.json";
    @Override
    protected void startScan() {
        toggleJob(true);

        List<Book> booksToSave = new ArrayList<>();
        try {
            // Step 1: Read JSON from URL
            ObjectMapper objectMapper = new ObjectMapper();
            URL url = new URL(bibleRawURL); // Replace with your actual URL
            JsonNode rootNode = objectMapper.readTree(url);

            // Step 2: Process each entry
            JsonNode metadataNode = rootNode.get("metadata");
            if (metadataNode != null) {
                String name = metadataNode.get("name").asText();
                String shortName = metadataNode.get("shortname").asText();
                String module = metadataNode.get("module").asText();
                String year = metadataNode.get("year").asText();
                String publisher = metadataNode.get("publisher").asText();
                String owner = metadataNode.get("owner").asText();
                String description = metadataNode.get("description").asText();
                String lang = metadataNode.get("lang").asText();
                String langShort = metadataNode.get("lang_short").asText();
                int copyright = metadataNode.get("copyright").asInt();
                String copyrightStatement = metadataNode.get("copyright_statement").asText();
                String urlValue = metadataNode.get("url").asText();
                int citationLimit = metadataNode.get("citation_limit").asInt();
                int restrict = metadataNode.get("restrict").asInt();
                int italics = metadataNode.get("italics").asInt();
                int strongs = metadataNode.get("strongs").asInt();
                int redLetter = metadataNode.get("red_letter").asInt();
                int paragraph = metadataNode.get("paragraph").asInt();
                int official = metadataNode.get("official").asInt();
                int research = metadataNode.get("research").asInt();
                String moduleVersion = metadataNode.get("module_version").asText();

                if (bibleRepository.findBibleByAbbreviatedName(shortName) == null) {
                    this.bibleToScan = new Bible();
                    this.bibleToScan.setTranslationName(name);
                    this.bibleToScan.setAbbreviatedName(module);
                    this.bibleToScan.setYearCreated(Integer.valueOf(year));
                    this.bibleToScan.setLang(lang);

                    this.bibleToScan = bibleRepository.save(this.bibleToScan);
                }
            }


            JsonNode versesNode = rootNode.get("verses");
            if (versesNode != null && versesNode.isArray()) {
                for (JsonNode verseNode : versesNode) {
                    String bookName = verseNode.get("book_name").asText();
                    int chapterNum = verseNode.get("chapter").asInt();
                    int verseNum = verseNode.get("verse").asInt();
                    String verseText = verseNode.get("text").asText();

                    // Create or retrieve the book entity
                    Book book = bookRepository.findByBookName(bookName);
                    if (book == null) {
                        book = new Book();
                        book.setBookName(bookName);
                        book.setBible(bibleToScan);
                        // Save or persist the book entity before setting its reference in the verse
                        book = bookRepository.save(book); // Ensure that the saved book is assigned back to the 'book' variable
                    }

                    // Create or retrieve the chapter entity
                    Chapter chapter = chapterRepository.findByBookAndChapterNumber(book, chapterNum);
                    if (chapter == null) {
                        chapter = new Chapter();
                        chapter.setBook(book);
                        chapter.setChapterNumber(chapterNum);
                        // Save or persist the chapter entity before setting its reference in the verse
                        chapter = chapterRepository.save(chapter); // Ensure that the saved chapter is assigned back to the 'chapter' variable
                    }

                    // Create the verse entity
                    Verse verse = new Verse();
                    verse.setChapter(chapter);
                    verse.setVerseNumber(verseNum);
                    verse.setText(verseText);

                    // Save or persist the verse entity
                    verse = verseRepository.save(verse);

                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }

        // Save all books and their related entities in bulk within a transaction
        bookRepository.saveAll(booksToSave);
    }
}
