package com.sut62.team07.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Pattern(regexp = "[0]\\d{9}", message = "tel must be match")
    private String tel;

    @ManyToOne
    @NotNull(message = "prefix must be not null")
    private Prefix prefix;

    @ManyToOne
    @NotNull(message = "major must be not null")
    private Major major;

    @ManyToOne
    @NotNull(message = "gender must be not null")
    private Gender gender;

    @ManyToOne
    @NotNull(message = "registration officer must be not null")
    private RegistrationOfficer createdBy;

}