package com.findzach.godsgoodbook.web.controller;

import com.findzach.godsgoodbook.model.bible.Bible;
import com.findzach.godsgoodbook.model.bible.book.Book;
import com.findzach.godsgoodbook.model.bible.chapter.Chapter;
import com.findzach.godsgoodbook.model.bible.dto.BibleDTO;
import com.findzach.godsgoodbook.model.bible.dto.BookDTO;
import com.findzach.godsgoodbook.model.bible.dto.ChapterDTO;
import com.findzach.godsgoodbook.model.bible.dto.VerseDTO;
import com.findzach.godsgoodbook.model.bible.verse.Verse;
import com.findzach.godsgoodbook.web.service.BibleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Zach Smith
 * @date: 2/15/2024
 * @time: 8:30 AM
 */
@RestController
@RequestMapping("/api/bible")
public class BibleController {

    private static int currentCounter = 1;
    @Autowired
    private BibleService bibleService;

    @GetMapping("/all")
    public List<BibleDTO> getAllBiblesWithBooksChaptersAndVerses(HttpServletRequest request) {
        List<Bible> bibles = bibleService.getAllBibles();
        String clientIp = getClientIp(request);
        System.out.println("Request from IP: " + clientIp);
        System.out.println("Current User Request Count: " + currentCounter);
        currentCounter++;
        // Return the DTOs as before
        return bibles.stream()
                .map(this::convertToBibleDto)
                .collect(Collectors.toList());
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    private BibleDTO convertToBibleDto(Bible bible) {
        BibleDTO bibleDto = new BibleDTO();
        bibleDto.setTranslationName(bible.getTranslationName());
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : bible.getBooks()) {
            BookDTO bookDto = new BookDTO();
            bookDto.setBookName(book.getBookName());
            List<ChapterDTO> chapterDTOs = new ArrayList<>();
            for (Chapter chapter : book.getChapters()) {
                ChapterDTO chapterDto = new ChapterDTO();
                chapterDto.setChapterNumber(chapter.getChapterNumber());
                List<VerseDTO> verseDTOs = new ArrayList<>();
                for (Verse verse : chapter.getVerses()) {
                    VerseDTO verseDto = new VerseDTO();
                    verseDto.setVerseNumber(verse.getVerseNumber());
                    verseDto.setText(verse.getText());
                    verseDTOs.add(verseDto);
                }
                chapterDto.setVerses(verseDTOs);
                chapterDTOs.add(chapterDto);
            }
            bookDto.setChapters(chapterDTOs);
            bookDTOs.add(bookDto);
        }
        bibleDto.setBooks(bookDTOs);
        return bibleDto;
    }
}
