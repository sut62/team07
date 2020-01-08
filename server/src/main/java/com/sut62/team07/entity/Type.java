package com.sut62.team07.entity;


import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="TYPE")
public class Type {
    @Id    
    @SequenceGenerator(name="TYPE_SEQ",sequenceName="TYPE_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TYPE_SEQ")  
    @Column(name="TYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Course> Course;
}

