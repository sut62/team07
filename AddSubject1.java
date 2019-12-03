package com.cpe.backend.entity;

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
@Table(name="ADDSUBJECT")
public class AddSubject {

    @Id
    @SequenceGenerator(name="ADDSUBJECT_seq",sequenceName="ADDSUBJECT_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ADDSUBJECT_seq")  
    @Column(name = "AddSubject_ID", unique = true, nullable = true)
    private @NonNull Long id;

    
   
}
