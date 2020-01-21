package com.sut62.team07.entity;
import lombok.*;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="SEMESTER")
public class Semester {

    @Id
    @SequenceGenerator(name="semester_seq",sequenceName="semester_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="semester_seq")
    @Column(name = "SEMESTER_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String sem;
    public void setName(String name) {
        this.sem = name;
	}



}
