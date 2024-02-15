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
public class BishopsBibleScanner extends SuperSearchScanner {
    private final String bibleRawURL = "https://raw.githubusercontent.com/FindZach/GodsGoodBook-Backend/develop/src/main/resources/static/bible-data/bishops.json";

    @Override
    protected String getBibleURL() {
        return bibleRawURL;
    }

}