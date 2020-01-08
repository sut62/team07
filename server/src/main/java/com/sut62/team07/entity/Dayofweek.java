package com.sut62.team07.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Data
@Entity
@NoArgsConstructor
@Table(name="DAYOFWEEK")
public class Dayofweek {
    @Id
    @SequenceGenerator(name="DAYOFWEEK_seq",sequenceName="DAYOFWEEK_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DAYOFWEEK_seq")
    @Column(name="DAYOFWEEK_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;

//    @OneToMany(fetch = FetchType.EAGER)
//    private Collection<Days> Days;

}