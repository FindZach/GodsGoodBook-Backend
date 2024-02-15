package com.findzach.godsgoodbook.web.repository.bible;

import com.findzach.godsgoodbook.model.bible.verse.Verse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 11:10 PM
 */
@Repository
public interface VerseRepository extends JpaRepository<Verse, Long> {
}
