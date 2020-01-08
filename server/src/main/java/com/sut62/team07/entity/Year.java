package com.sut62.team07.entity;
import lombok.*;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@Entity
@NoArgsConstructor
@Table(name="YEAR")
public class Year {
    @Id
    @SequenceGenerator(name="year_seq",sequenceName="year_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="year_seq")
    @Column(name="YEAR_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String year_name;
   // @OneToMany(fetch = FetchType.EAGER)
   // private Collection<Student> student;
    
    public void setName(String name2) {
		this.year_name = name2;
	}
}