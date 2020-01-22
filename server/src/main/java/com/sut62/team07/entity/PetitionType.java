package com.sut62.team07.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.*;

@Data
@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="PETITION_TYPE")
public class PetitionType {
  @Id
  @SequenceGenerator(name="PETITION_TYPE_SEQ",sequenceName="PETITION_TYPE_SEQ")
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PETITION_TYPE_SEQ")
  @Column(name="PETITION_TYPE_ID",unique = true, nullable = true)
  private Long id;

  @NotNull(message = "Name cannot be null")
  private String name;

}
