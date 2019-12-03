package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name="MEMBER")
public class Member {
  @Id
  @SequenceGenerator(name="MEMBER_SEQ",sequenceName="MEMBER_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MEMBER_SEQ" )
  @Column(name = "ID_MEMBER", unique = true, nullable = true)
  private @NonNull Long id;

  @Column(name="USERID")
  private @NonNull String userid;

  @Column(name="PASSWORD")
  private @NonNull String password;

  @Column(name="FNAME")
  private @NonNull String fName;

  @Column(name="LNAME")
  private @NonNull String lName;

  @Column(name="AGE")
  private @NonNull Integer age;

  @Column(name="EMAIL")
  private @NonNull String email;

  @Column(name="TEL")
  private @NonNull String tel;


  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
  @JoinColumn(name = "ID_GENDER", insertable = true)
  private Gender gender;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Interestnews.class)
  @JoinColumn(name = "ID_INTERESTNEWS", insertable = true)
  private Interestnews interestnews;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Career.class)
  @JoinColumn(name = "ID_CAREER", insertable = true)
  private Career career;
}