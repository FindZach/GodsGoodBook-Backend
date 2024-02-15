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
 * @time: 12:01 PM
 */
@Service
public class KJVBibleScanner extends SuperSearchScanner {
    @Override
    protected String getBibleURL() {
        return "https://raw.githubusercontent.com/FindZach/GodsGoodBook-Backend/develop/src/main/resources/static/bible-data/kjv.json";
    }

}
