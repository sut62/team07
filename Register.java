package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Data
@Entity
@NoArgsConstructor
@Table(name="REGISTER")
public class Register {

    @Id
    @SequenceGenerator(name="register_seq",sequenceName="register_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="register_seq")
    @Column(name = "REGISTER_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="REGISTER_DATE")
    private @NonNull Date Date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Semester.class)
    @JoinColumn(name = "SEMESTER_ID", insertable = true)
    private Semester Semester;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Section.class)
    @JoinColumn(name = "SECTION_ID", insertable = true)
    private Section Section;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Student.class)
    @JoinColumn(name = "STUDENT_ID", insertable = true)
    private Student Student;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Subject.class)
    @JoinColumn(name = "SUBJECT_ID", insertable = true)
    private Subject Subject;
}