package com.sut62.team07.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    private Long id;

    @NotNull(message = "Trimester cannot be null")
    private String name;


    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Course> Course;


	public static Object builder() {
		return null;
	}
}
