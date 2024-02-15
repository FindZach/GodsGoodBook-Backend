package com.findzach.godsgoodbook.web.service;

import com.findzach.godsgoodbook.model.bible.Bible;
import com.findzach.godsgoodbook.web.repository.bible.BibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Zach Smith
 * @date: 2/15/2024
 * @time: 8:32 AM
 */
@Service
public class BibleService {

    @Autowired
    private BibleRepository bibleRepository;

    public List<Bible> getAllBibles() {
        return bibleRepository.findAll();
    }

}
