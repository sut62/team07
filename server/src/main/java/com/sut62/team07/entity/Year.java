package com.sut62.team07.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="YEAR")
public class Year {
    @Id
    @SequenceGenerator(name="year_seq",sequenceName="year_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="year_seq")
    @Column(name="YEAR_ID",unique = true, nullable = true)
    private Long id;

    @NotNull(message = "year name must be not null")
    private String year_name;
    
    public void setName(String name2) {
		this.year_name = name2;
	}
}