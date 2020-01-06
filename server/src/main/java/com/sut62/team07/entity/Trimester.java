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
@Table(name="TRIMESTER")
public class Trimester {
    @Id    
    @SequenceGenerator(name="TRIMESTER_SEQ",sequenceName="TRIMESTER_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRIMESTER_SEQ")  
    @Column(name="TRIMESTER_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;


    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Subject> Subject;
}
