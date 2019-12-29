package com.sut62.team07.entity;
import lombok.*;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="STUDENT")
public class Student {

    @Id
    @SequenceGenerator(name="student_seq",sequenceName="student_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="student_seq")
    @Column(name = "STUDENT_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String num;

  
    



}