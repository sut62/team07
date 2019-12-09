package com.sut62.team07.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "institute_seq")
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "institute_seq")
    private Long id;
    
    private @NotNull String name;
}