package com.sut62.team07.entity;

import lombok.*;

import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="ProgramInfo")
public class ProgramInfo {
    @Id    
    @SequenceGenerator(name="ProgramInfo_SEQ",sequenceName="ProgramInfo_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ProgramInfo_SEQ")  
    @Column(name="ProgramInfo_ID",unique = true, nullable = true)
    private Long id;

    @NotNull(message = "ProgramInfo cannot be null")
    private String name;


	public static Object builder() {
		return null;
	}

}
