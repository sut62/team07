package com.sut62.team07.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@SequenceGenerator(name = "major_seq")
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "major_seq")
    private Long id;

    @NotNull(message = "name must be not null")
    private String name;

    @ManyToOne
    @NotNull(message = "institute must be not null")
    private Institute institute;
}