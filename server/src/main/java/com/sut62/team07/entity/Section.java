package com.sut62.team07.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
@Entity
@NoArgsConstructor
@Table(name="SECTION") 
public class Section {

    @Id
    @SequenceGenerator(name="section_seq",sequenceName="section_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="section_seq")
    @Column(name = "SECTION_ID", unique = true, nullable = true)
    private  Long id;

    private @NotNull String sec;

    private @NotNull String time;

    // *-1 with subject
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Course.class)
    private Course subInSec;

	
	
	

}