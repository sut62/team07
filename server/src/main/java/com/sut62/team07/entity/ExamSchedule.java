package com.sut62.team07.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="EXAM_SCHEDULE")
public class ExamSchedule  {
    @Id
    @SequenceGenerator(name="EXAM_SCHEDULE_SEQ",sequenceName="EXAM_SCHEDULE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EXAM_SCHEDULE_SEQ")
    @Column(name = "EXAM_SCHEDULE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name = "ACADEMIC_YEAR")
    private @NonNull String academicYear;

    @Column(name = "DATE")
    private @NonNull LocalDate date;

    @Column(name = "START_TIME")
    private @NonNull LocalTime startTime;

    @Column(name = "END_TIME")
    private @NonNull LocalTime endTime;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "ROOM_ID", insertable = true)
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Semester.class)
    @JoinColumn(name = "SEMESTER_ID", insertable = true)
    private Semester semester;

    @OneToMany(fetch = FetchType.EAGER ,mappedBy="examSchedule")
    @JsonManagedReference
    private Collection<Course_Schedule> course_schedule;

}
