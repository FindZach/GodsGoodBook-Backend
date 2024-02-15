package com.findzach.godsgoodbook.model.bootstrap;

import com.findzach.godsgoodbook.model.user.User;
import com.findzach.godsgoodbook.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 10:11 PM
 */
@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private final UserRepository userRepository;
    public Bootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User testAccount = new User();

        testAccount.setEmail("Email Test");
        testAccount.setUsername("Username Test");
        userRepository.save(testAccount);
    }
}
