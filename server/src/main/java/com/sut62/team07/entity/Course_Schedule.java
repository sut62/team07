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
@Table(name="COURSE_SCHEDULE")
public class Course_Schedule {
    @Id
    @SequenceGenerator(name="COURSE_SCHEDULE_SEQ",sequenceName="COURSE_SCHEDULE_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COURSE_SCHEDULE_SEQ")
    @Column(name="COURSE_SCHEDULE_ID",unique = true, nullable = true)
    private @NonNull Long id;
   // private @NonNull String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "EXAM_SCHEDULE_ID")
    @JsonIgnore
    private ExamSchedule examSchedule;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "COURSE_ID")
    @JsonIgnore
    private Course course;
}
