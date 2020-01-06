package com.sut62.team07.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "lecturer_seq")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecturer_seq")
    private Long id;

    @Column(unique = true)
    private @NotNull String lecturerCode;

    @Size(min = 8)
    private @NotNull String password;

    @Pattern(regexp = "\\d{13}")
    private @NotNull String personalId;

    private @NotNull String name;

    private @NotNull @Email String email;

    @Pattern(regexp = "\\d{10}")
    private @NotNull String tel;

    @ManyToOne
    private Prefix prefix;

    @ManyToOne
    private Major major;

    @ManyToOne
    private Gender gender;

    @ManyToOne
    private RegistrationOfficer createdBy;

    @ManyToMany
    @JoinTable(
        name = "lecturer_course",
        joinColumns = @JoinColumn(name = "lecturer_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Collection<Course> courses;
}