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
    @Pattern(regexp = "[A]\\d{4}")
    @NotNull(message = "lecturer code must be not null")
    private String lecturerCode;

    @NotNull(message = "password must be not null")
    @Size(min = 8, message = "password at least 8 characters")
    private String password;

    @NotNull(message = "personalId must be not null")
    @Pattern(regexp = "\\d{13}", message = "personalId must be match")
    private String personalId;

    @NotNull(message = "name must be not null")
    private String name;

    @Email(message = "email is invalid")
    @NotNull(message = "email must be not null")
    private String email;

    @NotNull(message = "tel must be not null")
    @Pattern(regexp = "\\d{10}", message = "tel must be match")
    private String tel;

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