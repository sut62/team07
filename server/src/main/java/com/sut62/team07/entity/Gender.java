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
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "gender_seq")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_seq")
    private Long id;
    
    @NotNull(message = "gender name must be not null")
    private String name;
}