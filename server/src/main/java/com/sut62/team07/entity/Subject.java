package com.sut62.team07.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
@Table(name="SUBJECT")
public class Subject {

    @Id
    @SequenceGenerator(name="SUBJECT_seq",sequenceName="SUBJECT_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SUBJECT_seq")  
    @Column(name = "Subject_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @NonNull String code;
    @NonNull String subj;
    @NonNull String year;
  
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Trimester.class)
    @JoinColumn(name = "TRIMESTER_ID", insertable = true)
    private Trimester trimester;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    @JoinColumn(name = "TYPE_ID", insertable = true)
    private Type type;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Grade.class)
    @JoinColumn(name = "Grade_ID", insertable = true)
    private Grade grade;
    
   
}
