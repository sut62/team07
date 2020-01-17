package com.sut62.team07.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@SequenceGenerator(name = "registration_officer_seq")
public class RegistrationOfficer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registration_officer_seq")
    private Long id;

    @Column(unique = true)
    @NotNull(message = "officer code must be not null")
    private String officerCode;

    @NotNull(message = "password must be not null")
    @Size(min = 8, message = "password at least 8 characters")
    private String password;

    @NotNull(message = "name must be not null")
    private String name;

    @ManyToOne
    @NotNull(message = "prefix must be not null")
    private Prefix prefix;

    @ManyToOne
    @NotNull(message = "gender must be not null")
    private Gender gender;
}