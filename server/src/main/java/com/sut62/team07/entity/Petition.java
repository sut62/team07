package com.sut62.team07.entity;

import lombok.*;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name="PETITION")
public class Petition  {
  @Id
  @SequenceGenerator(name="PETITION_SEQ",sequenceName="PETITION_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PETITION_SEQ")
  @Column(name = "PETITION_ID", unique = true, nullable = true)
  private Long id;

  @Column(name = "TITLE")
  @NotNull(message = "Title cannot be null")
  @Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters")
  private  String title;

  @Column(name = "DETAIL")
  @NotNull(message = "Detail cannot be null")
  @Size(min = 5, max = 200, message = "About Me must be between 5 and 200 characters")
  private String detail;

  @Column(name = "PETITION_DATE")
  @NotNull(message = "Petition date cannot be null")
  private LocalDate petitionDate;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Student.class)
  @NotNull(message = "Student id cannot be null")
  @JoinColumn(name = "STUDENT_ID", insertable = true)
  private Student student;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = PetitionType.class)
  @NotNull(message = "Petition type cannot be null")
  @JoinColumn(name = "PETITION_TYPE_ID", insertable = true)
  private PetitionType petitionType;

}
