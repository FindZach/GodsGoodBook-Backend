package com.findzach.godsgoodbook.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.findzach.godsgoodbook.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 10:12 PM
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users") // Specify a different table name
public class User extends BaseEntity {
    private String username;

    private String email;

    // Getters and setters for username and email
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
