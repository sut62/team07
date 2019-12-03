package com.sut62.team07.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "lecturer_seq")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecturer_seq")
    private Long id;

    @NotNull
    @NotBlank
    private String lecturerCode;

    private String avatar;

    @NotNull
    @NotBlank
    @NotEmpty
    private String password;

    @NotNull
    @NotBlank
    private String name;
    /*
    @ManyToOne
    @JoinColumn(name = "MAJOR_ID")
    private Major major;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "LECTURER_COURSE",
        joinColumns = { @JoinColumn(name = "LECTURER_ID") },
        inverseJoinColumns = { @JoinColumn(name = "COURSE_ID") }
    )
    private Collection<Course> lectureCourses;

    @ManyToOne
    @JoinColumn(name = "GENDER_ID")
    private Gender gender;
    */
}
