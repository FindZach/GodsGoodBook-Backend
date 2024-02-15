package com.findzach.godsgoodbook.web.repository;

import com.findzach.godsgoodbook.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 10:12 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
