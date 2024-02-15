package com.findzach.godsgoodbook.model;


import jakarta.persistence.*;

import java.util.Date;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 10:09 PM
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date entityCreationDate;
    @PrePersist
    protected void onCreate() {
        entityCreationDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getEntityCreationDate() {
        return entityCreationDate;
    }

    public void setEntityCreationDate(Date entityCreationDate) {
        this.entityCreationDate = entityCreationDate;
    }
}