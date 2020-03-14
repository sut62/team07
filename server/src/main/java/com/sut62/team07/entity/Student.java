package com.sut62.team07.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import com.sut62.team07.entity.Major;
import com.sut62.team07.entity.Year;
import com.sut62.team07.entity.Prefix;
@Data
@Entity
@NoArgsConstructor
@Table(name="STUDENT")
public class Student {

    @Id
    @SequenceGenerator(name="id_student_seq",sequenceName="id_student_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_student_seq")
    @Column(name = "ID_STUDENT_ID", unique = true, nullable = true)
    private  Long id;
    

    @Column(name="STUDENT_ID")
    @Size(min=2,max=8)
    private @NotNull String student_id;

    @Column(name="STUDENT_PHONE")
    @Pattern(regexp = "\\d{10}")
    private @NotNull String student_phone;

    @Column(name="STUDENT_NAME")
    private @NotNull String student_name;
    
    @Column(name="STUDENT_EMAIL")
    @Email(message = "email is invalid")
    private @NotNull String student_email;
    
    @Column(name="Password")
    @Size(min=8,max=15)
    private @NotNull String password;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Major.class)
    @JoinColumn(name = "majors", insertable = true)
    private Major major;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Year.class)
    @JoinColumn(name = "YEAR_ID", insertable = true)
    private Year year;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Prefix.class)
    @JoinColumn(name = "PREFIX_ID", insertable = true)
    private Prefix prefix;

        

    
}
