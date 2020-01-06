package com.sut62.team07.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="Grade")
public class Grade {
    @Id    
    @SequenceGenerator(name="Grade_SEQ",sequenceName="Grade_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Grade_SEQ")  
    @Column(name="Grade_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Subject> Subject;
}
