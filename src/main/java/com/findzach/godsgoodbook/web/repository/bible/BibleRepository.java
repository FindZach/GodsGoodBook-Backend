package com.findzach.godsgoodbook.web.repository.bible;

import com.findzach.godsgoodbook.model.bible.Bible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 11:09 PM
 */
@Repository
public interface BibleRepository extends JpaRepository<Bible, Long> {
    Bible findBibleByAbbreviatedName(String abbreviatedName);
}
