package com.okta.entity.member;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@Table(name = "Title")
public class Title {
    @Id
    @SequenceGenerator(name = "Title_SEQ", sequenceName = "Title_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Title_SEQ")
    @Column(name = "Title_id", unique = true, nullable = true)

    private Long id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}