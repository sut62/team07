package com.sut62.team07.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="DAYS")
public class Days {
    @Id
    @SequenceGenerator(name="DAYS_SEQ",sequenceName="DAYS_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DAYS_SEQ")
    @Column(name="DAYS_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;

//    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//    @JoinColumn(name = "TEACHTABLE_ID")
//    @JsonIgnore
//    private Teachtable teachtable;

}
