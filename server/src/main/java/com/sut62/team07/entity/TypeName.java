package com.sut62.team07.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="TYPENAME")
public class TypeName {
	@Id
	@SequenceGenerator(name="typename_seq",sequenceName="typename_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="typename_seq")
	@Column(name="TYPENAME_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String type_name;
	
	@OneToMany(fetch = FetchType.EAGER)
    private Collection<Student> student;
    
	public void setName(String name2) {
		this.type_name = name2; 
	}

	
}

